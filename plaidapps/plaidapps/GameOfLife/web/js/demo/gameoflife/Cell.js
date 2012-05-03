var plaidNewState_Cell = new PlaidState();
plaidNewState_Cell.tree = [['', [], 'with'], [['Cell', [], 'with']]];
var plaidNewState_NilCell = new PlaidState();
plaidNewState_NilCell.tree = [['', [], 'with'], [['Cell', [], 'with'], [['NilCell', [], '']]]];
var plaidNewState_AliveCell = new PlaidState();
plaidNewState_AliveCell['updatePopulation'] = function updatePopulation (surroundCells) {
var this$plaid=this;
var numActive = 0;
var numDead = 0;
var plaidNewVar1 = function(cell) {
var matchVar = cell;
if (matchVar.match("AliveCell")) {
var plaidNewVar2 = numActive + 1;
numActive = plaidNewVar2;
var returnVal = numActive;
}
else if (matchVar.match("DeadCell")) {
var plaidNewVar3 = numDead + 1;
numDead = plaidNewVar3;
var returnVal = numDead;
}
return returnVal;
};
var plaidNewVar4 = surroundCells.each(plaidNewVar1);
var vAr13$plaid = plaidNewVar4;
var plaidNewVar5 = function(vAr12$plaid) {
var plaidNewVar6 = function(vAr11$plaid) {
this$plaid.stateChange(plaidNewState_Survive);
return this$plaid;
};
var plaidNewVar7 = function(vAr10$plaid) {
this$plaid.stateChange(plaidNewState_OverPopulated);
return this$plaid;
};
var plaidNewVar8 = numActive >= 4;
var plaidNewVar9 = ifElse(plaidNewVar8);
var plaidNewVar10 = plaidNewVar9(plaidNewVar7);
var plaidNewVar11 = plaidNewVar10(plaidNewVar6);
return plaidNewVar11;
};
var plaidNewVar12 = function(vAr9$plaid) {
this$plaid.stateChange(plaidNewState_Lonely);
return this$plaid;
};
var plaidNewVar13 = numActive <= 1;
var plaidNewVar14 = ifElse(plaidNewVar13);
var plaidNewVar15 = plaidNewVar14(plaidNewVar12);
var plaidNewVar16 = plaidNewVar15(plaidNewVar5);
return plaidNewVar16;
}
plaidNewState_AliveCell['updateCell'] = function updateCell () {
var this$plaid=this;
var matchVar = this$plaid;
if (matchVar.match("Survive")) {
this$plaid.stateChange(plaidNewState_AliveCell);
var returnVal = this$plaid;
}
else if (matchVar.match("Lonely")) {
this$plaid.stateChange(plaidNewState_DeadCell);
var returnVal = this$plaid;
}
else if (matchVar.match("OverPopulated")) {
this$plaid.stateChange(plaidNewState_DeadCell);
var returnVal = this$plaid;
}
return returnVal;
}
plaidNewState_AliveCell['onAliveCell'] = function onAliveCell () {
var this$plaid=this;
var plaidNewVar18 = "#" + this$plaid.id;
var plaidNewVar19 = jQuery(plaidNewVar18);
var plaidNewVar17 = plaidNewVar19.addClass("alive");
return plaidNewVar17;
}
plaidNewState_AliveCell['offAliveCell'] = function offAliveCell () {
var this$plaid=this;
var plaidNewVar21 = "#" + this$plaid.id;
var plaidNewVar22 = jQuery(plaidNewVar21);
var plaidNewVar20 = plaidNewVar22.removeClass("alive");
return plaidNewVar20;
}
plaidNewState_AliveCell.tree = [['', [], 'with'], [['Cell', [], 'with'], [['AliveCell', ['updatePopulation', 'updateCell', 'onAliveCell', 'offAliveCell'], '']]]];
var plaidNewState_DeadCell = new PlaidState();
plaidNewState_DeadCell['updatePopulation'] = function updatePopulation (surroundCells) {
var this$plaid=this;
var numActive = 0;
var plaidNewVar23 = function(cell) {
var matchVar = cell;
if (matchVar.match("AliveCell")) {
var plaidNewVar24 = numActive + 1;
numActive = plaidNewVar24;
var returnVal = numActive;
}
return returnVal;
};
var plaidNewVar25 = surroundCells.each(plaidNewVar23);
var vAr16$plaid = plaidNewVar25;
var plaidNewVar26 = function(vAr15$plaid) {
this$plaid.stateChange(plaidNewState_Unpopulated);
return this$plaid;
};
var plaidNewVar27 = function(vAr14$plaid) {
this$plaid.stateChange(plaidNewState_Populated);
return this$plaid;
};
var plaidNewVar28 = numActive == 3;
var plaidNewVar29 = ifElse(plaidNewVar28);
var plaidNewVar30 = plaidNewVar29(plaidNewVar27);
var plaidNewVar31 = plaidNewVar30(plaidNewVar26);
return plaidNewVar31;
}
plaidNewState_DeadCell['updateCell'] = function updateCell () {
var this$plaid=this;
var matchVar = this$plaid;
if (matchVar.match("Populated")) {
this$plaid.stateChange(plaidNewState_AliveCell);
var returnVal = this$plaid;
}
else if (matchVar.match("Unpopulated")) {
this$plaid.stateChange(plaidNewState_DeadCell);
var returnVal = this$plaid;
}
return returnVal;
}
plaidNewState_DeadCell['onDeadCell'] = function onDeadCell () {
var this$plaid=this;
var plaidNewVar33 = "#" + this$plaid.id;
var plaidNewVar34 = jQuery(plaidNewVar33);
var plaidNewVar32 = plaidNewVar34.addClass("dead");
return plaidNewVar32;
}
plaidNewState_DeadCell['offDeadCell'] = function offDeadCell () {
var this$plaid=this;
var plaidNewVar36 = "#" + this$plaid.id;
var plaidNewVar37 = jQuery(plaidNewVar36);
var plaidNewVar35 = plaidNewVar37.removeClass("dead");
return plaidNewVar35;
}
plaidNewState_DeadCell.tree = [['', [], 'with'], [['Cell', [], 'with'], [['DeadCell', ['updatePopulation', 'updateCell', 'onDeadCell', 'offDeadCell'], '']]]];
