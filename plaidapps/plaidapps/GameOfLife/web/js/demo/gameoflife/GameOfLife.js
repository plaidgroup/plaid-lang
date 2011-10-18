var plaidNewState_GameOfLife = new PlaidState();
plaidNewState_GameOfLife['init'] = function init () {
var this$plaid=this;
var plaidNewVar1 = function() {
this$plaid.stateChange(plaidNewState_RunningPeriodicalTimer);
var vAr165$plaid = this$plaid;
buttonStop.stateChange(plaidNewState_Visible);
var vAr164$plaid = buttonStop;
buttonStart.stateChange(plaidNewState_Invisible);
var vAr163$plaid = buttonStart;
buttonNext.stateChange(plaidNewState_Disabled);
return buttonNext;
};
var startTimer = plaidNewVar1;
var plaidNewVar2 = function() {
this$plaid.stateChange(plaidNewState_StoppingPeriodicalTimer);
var vAr168$plaid = this$plaid;
buttonStop.stateChange(plaidNewState_Invisible);
var vAr167$plaid = buttonStop;
buttonStart.stateChange(plaidNewState_Visible);
var vAr166$plaid = buttonStart;
buttonNext.stateChange(plaidNewState_Enabled);
return buttonNext;
};
var stopTimer = plaidNewVar2;
var id = "gameBoard";
var width = 21;
var height = 21;
var plaidNewVar3 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Board.specialize('Board', 'width', width).specialize('Board', 'height', height))).instantiate();
this$plaid.board = plaidNewVar3;
var vAr187$plaid = this$plaid.board;
var plaidNewVar4 = this$plaid.board.init();
var vAr186$plaid = plaidNewVar4;
this$plaid.board.stateChange(plaidNewState_Glider);
var vAr185$plaid = this$plaid.board;
var id = "buttonStart";
var plaidNewVar6 = function() {
var plaidNewVar7 = startTimer();
return plaidNewVar7;
};
var click = plaidNewVar6;
var plaidNewVar5 = (plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).with(plaidNewState_Visibility)).instantiate();
var buttonStart = plaidNewVar5;
var plaidNewVar8 = buttonStart.init();
var vAr184$plaid = plaidNewVar8;
buttonStart.stateChange(plaidNewState_Visible);
var vAr183$plaid = buttonStart;
var id = "buttonStop";
var plaidNewVar10 = function() {
var plaidNewVar11 = stopTimer();
return plaidNewVar11;
};
var click = plaidNewVar10;
var plaidNewVar9 = (plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).with(plaidNewState_Visibility)).instantiate();
var buttonStop = plaidNewVar9;
var plaidNewVar12 = buttonStop.init();
var vAr182$plaid = plaidNewVar12;
buttonStop.stateChange(plaidNewState_Invisible);
var vAr181$plaid = buttonStop;
var id = "buttonNext";
var plaidNewVar14 = function() {
var plaidNewVar15 = this$plaid.forward();
return plaidNewVar15;
};
var click = plaidNewVar14;
var plaidNewVar13 = (plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).with(plaidNewState_Availability)).instantiate();
var buttonNext = plaidNewVar13;
var plaidNewVar16 = buttonNext.init();
var vAr180$plaid = plaidNewVar16;
buttonNext.stateChange(plaidNewState_Enabled);
var vAr179$plaid = buttonNext;
var id = "selectBoard";
var plaidNewVar18 = function(evt) {
var plaidNewVar19 = stopTimer();
return plaidNewVar19;
};
var beforeChange = plaidNewVar18;
var plaidNewVar17 = plaidNewState_DropDownList.specialize('DropDownList', 'id', id).specialize('DropDownList', 'beforeChange', beforeChange).instantiate();
var selectBoard = plaidNewVar17;
var plaidNewVar20 = selectBoard.init();
var vAr178$plaid = plaidNewVar20;
var name = "Glider";
var value = "Glider";
var plaidNewVar22 = function() {
this$plaid.board.stateChange(plaidNewState_Glider);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar22;
var plaidNewVar21 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionGlider = plaidNewVar21;
var plaidNewVar23 = optionGlider.init(selectBoard);
var vAr177$plaid = plaidNewVar23;
var name = "TenCellRow";
var value = "TenCellRow";
var plaidNewVar25 = function() {
this$plaid.board.stateChange(plaidNewState_TenCellRow);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar25;
var plaidNewVar24 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionTenCellRow = plaidNewVar24;
var plaidNewVar26 = optionTenCellRow.init(selectBoard);
var vAr176$plaid = plaidNewVar26;
var name = "SmallExploder";
var value = "SmallExploder";
var plaidNewVar28 = function() {
this$plaid.board.stateChange(plaidNewState_SmallExploder);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar28;
var plaidNewVar27 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionSmallExploder = plaidNewVar27;
var plaidNewVar29 = optionSmallExploder.init(selectBoard);
var vAr175$plaid = plaidNewVar29;
var name = "Exploder";
var value = "Exploder";
var plaidNewVar31 = function() {
this$plaid.board.stateChange(plaidNewState_Exploder);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar31;
var plaidNewVar30 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionExploder = plaidNewVar30;
var plaidNewVar32 = optionExploder.init(selectBoard);
var vAr174$plaid = plaidNewVar32;
var name = "Spaceship";
var value = "Spaceship";
var plaidNewVar34 = function() {
this$plaid.board.stateChange(plaidNewState_Spaceship);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar34;
var plaidNewVar33 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionSpaceship = plaidNewVar33;
var plaidNewVar35 = optionSpaceship.init(selectBoard);
var vAr173$plaid = plaidNewVar35;
var id = "selectSpeed";
var plaidNewVar37 = function(evt) {
var matchVar = this$plaid;
if (matchVar.match("RunningPeriodicalTimer")) {
var plaidNewVar38 = stopTimer();
var vAr169$plaid = plaidNewVar38;
var plaidNewVar39 = startTimer();
var returnVal = plaidNewVar39;
}
return returnVal;
};
var afterChange = plaidNewVar37;
var plaidNewVar36 = plaidNewState_DropDownList.specialize('DropDownList', 'id', id).specialize('DropDownList', 'afterChange', afterChange).instantiate();
var selectSpeed = plaidNewVar36;
var plaidNewVar40 = selectSpeed.init();
var vAr172$plaid = plaidNewVar40;
var name = "slow";
var value = "slow";
var plaidNewVar42 = function() {
this$plaid.stateChange(plaidNewState_SlowGameOfLife);
return this$plaid;
};
var onSelectedOption = plaidNewVar42;
var plaidNewVar41 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionSlow = plaidNewVar41;
var plaidNewVar43 = optionSlow.init(selectSpeed);
var vAr171$plaid = plaidNewVar43;
var name = "fast";
var value = "fast";
var plaidNewVar45 = function() {
this$plaid.stateChange(plaidNewState_FastGameOfLife);
return this$plaid;
};
var onSelectedOption = plaidNewVar45;
var plaidNewVar44 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionFast = plaidNewVar44;
var plaidNewVar46 = optionFast.init(selectSpeed);
var vAr170$plaid = plaidNewVar46;
var name = "hyper";
var value = "hyper";
var plaidNewVar48 = function() {
this$plaid.stateChange(plaidNewState_HyperGameOfLife);
return this$plaid;
};
var onSelectedOption = plaidNewVar48;
var plaidNewVar47 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionHyper = plaidNewVar47;
var plaidNewVar49 = optionHyper.init(selectSpeed);
return plaidNewVar49;
}
plaidNewState_GameOfLife['forward'] = function forward () {
var this$plaid=this;
var plaidNewVar50 = this$plaid.board.update();
return plaidNewVar50;
}
plaidNewState_GameOfLife.tree = [['', [], 'with'], [['GameOfLife', ['init', 'forward'], 'with']]];
var plaidNewState_SlowGameOfLife = new PlaidState();
plaidNewState_SlowGameOfLife['init'] = plaidNewState_GameOfLife['init'];
plaidNewState_SlowGameOfLife['forward'] = plaidNewState_GameOfLife['forward'];
plaidNewState_SlowGameOfLife['onSlowGameOfLife'] = function onSlowGameOfLife () {
var this$plaid=this;
this$plaid.interval = 500;
return this$plaid.interval;
}
plaidNewState_SlowGameOfLife['offSlowGameOfLife'] = function offSlowGameOfLife () {
var this$plaid=this;
return undefined;
}
plaidNewState_SlowGameOfLife.tree = [['', [], 'with'], [['GameOfLife', ['init', 'forward'], 'with'], [['SlowGameOfLife', ['onSlowGameOfLife', 'offSlowGameOfLife'], '']]]];
var plaidNewState_FastGameOfLife = new PlaidState();
plaidNewState_FastGameOfLife['init'] = plaidNewState_GameOfLife['init'];
plaidNewState_FastGameOfLife['forward'] = plaidNewState_GameOfLife['forward'];
plaidNewState_FastGameOfLife['onFastGameOfLife'] = function onFastGameOfLife () {
var this$plaid=this;
this$plaid.interval = 300;
return this$plaid.interval;
}
plaidNewState_FastGameOfLife['offFastGameOfLife'] = function offFastGameOfLife () {
var this$plaid=this;
return undefined;
}
plaidNewState_FastGameOfLife.tree = [['', [], 'with'], [['GameOfLife', ['init', 'forward'], 'with'], [['FastGameOfLife', ['onFastGameOfLife', 'offFastGameOfLife'], '']]]];
var plaidNewState_HyperGameOfLife = new PlaidState();
plaidNewState_HyperGameOfLife['init'] = plaidNewState_GameOfLife['init'];
plaidNewState_HyperGameOfLife['forward'] = plaidNewState_GameOfLife['forward'];
plaidNewState_HyperGameOfLife['onHyperGameOfLife'] = function onHyperGameOfLife () {
var this$plaid=this;
this$plaid.interval = 100;
return this$plaid.interval;
}
plaidNewState_HyperGameOfLife['offHyperGameOfLife'] = function offHyperGameOfLife () {
var this$plaid=this;
return undefined;
}
plaidNewState_HyperGameOfLife.tree = [['', [], 'with'], [['GameOfLife', ['init', 'forward'], 'with'], [['HyperGameOfLife', ['onHyperGameOfLife', 'offHyperGameOfLife'], '']]]];
var plaidNewState_DebugWriter = new PlaidState();
plaidNewState_DebugWriter['write'] = function write (str) {
var this$plaid=this;
return undefined;
}
plaidNewState_DebugWriter.tree = [['', [], 'with'], [['DebugWriter', ['write'], 'with']]];
function main () {
var this$plaid=this;
var plaidNewVar51 = function(vAr188$plaid) {
var plaidNewVar52 = plaidNewState_DebugWriter.instantiate();
window.DebugWriter = plaidNewVar52;
return window.DebugWriter;
};
var plaidNewVar53 = window.DebugWriter == undefined;
var plaidNewVar54 = if$plaid(plaidNewVar53);
var plaidNewVar55 = plaidNewVar54(plaidNewVar51);
var vAr190$plaid = plaidNewVar55;
var plaidNewVar57 = function() {
var plaidNewVar58 = game.forward();
return plaidNewVar58;
};
var callback = plaidNewVar57;
var plaidNewVar56 = (plaidNewState_GameOfLife.with(plaidNewState_PeriodicalTimer.specialize('PeriodicalTimer', 'callback', callback))).instantiate();
var game = plaidNewVar56;
var plaidNewVar59 = game.init();
var vAr189$plaid = plaidNewVar59;
game.stateChange(plaidNewState_SlowGameOfLife);
return game;
}
$(main);
