var plaidNewState_GameOfLife = new PlaidState();
plaidNewState_GameOfLife['init'] = function init () {
var this$plaid=this;
var id = "gameBoard";
var width = 21;
var height = 21;
var plaidNewVar1 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Board.specialize('Board', 'width', width).specialize('Board', 'height', height))).instantiate();
this$plaid.board = plaidNewVar1;
var vAr174$plaid = this$plaid.board;
var plaidNewVar2 = this$plaid.board.init();
var vAr173$plaid = plaidNewVar2;
this$plaid.board.stateChange(plaidNewState_Glider);
var vAr172$plaid = this$plaid.board;
var id = "buttonStart";
var plaidNewVar4 = function() {
this$plaid.stateChange(plaidNewState_RunningPeriodicalTimer);
return this$plaid;
};
var onclick = plaidNewVar4;
var plaidNewVar3 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'onclick', onclick).instantiate();
var buttonStart = plaidNewVar3;
var plaidNewVar5 = buttonStart.init();
var vAr171$plaid = plaidNewVar5;
var id = "buttonStop";
var plaidNewVar7 = function() {
this$plaid.stateChange(plaidNewState_StoppingPeriodicalTimer);
return this$plaid;
};
var onclick = plaidNewVar7;
var plaidNewVar6 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'onclick', onclick).instantiate();
var buttonStop = plaidNewVar6;
var plaidNewVar8 = buttonStop.init();
var vAr170$plaid = plaidNewVar8;
var id = "buttonNext";
var plaidNewVar10 = function() {
var plaidNewVar11 = this$plaid.forward();
return plaidNewVar11;
};
var onclick = plaidNewVar10;
var plaidNewVar9 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'onclick', onclick).instantiate();
var buttonNext = plaidNewVar9;
var plaidNewVar12 = buttonNext.init();
var vAr169$plaid = plaidNewVar12;
var id = "selectBoard";
var plaidNewVar14 = function(evt) {
this$plaid.stateChange(plaidNewState_StoppingPeriodicalTimer);
var vAr168$plaid = this$plaid;
var v = evt.target.value;
var plaidNewVar15 = function(vAr159$plaid) {
this$plaid.board.stateChange(plaidNewState_Glider);
return this$plaid.board;
};
var plaidNewVar16 = v == "Glider";
var plaidNewVar17 = if$plaid(plaidNewVar16);
var plaidNewVar18 = plaidNewVar17(plaidNewVar15);
var vAr167$plaid = plaidNewVar18;
var plaidNewVar19 = function(vAr160$plaid) {
this$plaid.board.stateChange(plaidNewState_TenCellRow);
return this$plaid.board;
};
var plaidNewVar20 = v == "TenCellRow";
var plaidNewVar21 = if$plaid(plaidNewVar20);
var plaidNewVar22 = plaidNewVar21(plaidNewVar19);
var vAr166$plaid = plaidNewVar22;
var plaidNewVar23 = function(vAr161$plaid) {
this$plaid.board.stateChange(plaidNewState_SmallExploder);
return this$plaid.board;
};
var plaidNewVar24 = v == "SmallExploder";
var plaidNewVar25 = if$plaid(plaidNewVar24);
var plaidNewVar26 = plaidNewVar25(plaidNewVar23);
var vAr165$plaid = plaidNewVar26;
var plaidNewVar27 = function(vAr162$plaid) {
this$plaid.board.stateChange(plaidNewState_Exploder);
return this$plaid.board;
};
var plaidNewVar28 = v == "Exploder";
var plaidNewVar29 = if$plaid(plaidNewVar28);
var plaidNewVar30 = plaidNewVar29(plaidNewVar27);
var vAr164$plaid = plaidNewVar30;
var plaidNewVar31 = function(vAr163$plaid) {
this$plaid.board.stateChange(plaidNewState_Spaceship);
return this$plaid.board;
};
var plaidNewVar32 = v == "Spaceship";
var plaidNewVar33 = if$plaid(plaidNewVar32);
var plaidNewVar34 = plaidNewVar33(plaidNewVar31);
return plaidNewVar34;
};
var onchange = plaidNewVar14;
var plaidNewVar13 = plaidNewState_DropDownList.specialize('DropDownList', 'id', id).specialize('DropDownList', 'onchange', onchange).instantiate();
var selectBoard = plaidNewVar13;
var plaidNewVar35 = selectBoard.init();
return plaidNewVar35;
}
plaidNewState_GameOfLife['forward'] = function forward () {
var this$plaid=this;
var plaidNewVar36 = this$plaid.board.update();
return plaidNewVar36;
}
plaidNewState_GameOfLife.tree = [['', [], 'with'], [['GameOfLife', ['init', 'forward'], 'with']]];
var plaidNewState_DebugWriter = new PlaidState();
plaidNewState_DebugWriter['write'] = function write (str) {
var this$plaid=this;
return undefined;
}
plaidNewState_DebugWriter.tree = [['', [], 'with'], [['DebugWriter', ['write'], 'with']]];
function main () {
var this$plaid=this;
var plaidNewVar37 = function(vAr175$plaid) {
var plaidNewVar38 = plaidNewState_DebugWriter.instantiate();
window.DebugWriter = plaidNewVar38;
return window.DebugWriter;
};
var plaidNewVar39 = window.DebugWriter == undefined;
var plaidNewVar40 = if$plaid(plaidNewVar39);
var plaidNewVar41 = plaidNewVar40(plaidNewVar37);
var vAr176$plaid = plaidNewVar41;
var interval = 500;
var plaidNewVar43 = function() {
var plaidNewVar44 = game.forward();
return plaidNewVar44;
};
var callback = plaidNewVar43;
var plaidNewVar42 = (plaidNewState_GameOfLife.with(plaidNewState_PeriodicalTimer.specialize('PeriodicalTimer', 'interval', interval).specialize('PeriodicalTimer', 'callback', callback))).instantiate();
var game = plaidNewVar42;
var plaidNewVar45 = game.init();
return plaidNewVar45;
}
$(main);
