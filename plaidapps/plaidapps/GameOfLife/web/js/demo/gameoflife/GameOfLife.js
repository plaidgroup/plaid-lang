var plaidNewState_GameOfLife = new PlaidState();
plaidNewState_GameOfLife['init'] = function init () {
var this$plaid=this;
var id = "gameBoard";
var width = 21;
var height = 21;
var plaidNewVar1 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Board.specialize('Board', 'width', width).specialize('Board', 'height', height))).instantiate();
this$plaid.board = plaidNewVar1;
var vAr158$plaid = this$plaid.board;
var plaidNewVar2 = this$plaid.board.init();
var vAr157$plaid = plaidNewVar2;
this$plaid.board.stateChange(plaidNewState_Glider);
var vAr156$plaid = this$plaid.board;
var id = "buttonStart";
var plaidNewVar4 = function() {
this$plaid.stateChange(plaidNewState_RunningPeriodicalTimer);
return this$plaid;
};
var click = plaidNewVar4;
var plaidNewVar3 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).instantiate();
var buttonStart = plaidNewVar3;
var plaidNewVar5 = buttonStart.init();
var vAr155$plaid = plaidNewVar5;
var id = "buttonStop";
var plaidNewVar7 = function() {
this$plaid.stateChange(plaidNewState_StoppingPeriodicalTimer);
return this$plaid;
};
var click = plaidNewVar7;
var plaidNewVar6 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).instantiate();
var buttonStop = plaidNewVar6;
var plaidNewVar8 = buttonStop.init();
var vAr154$plaid = plaidNewVar8;
var id = "buttonNext";
var plaidNewVar10 = function() {
var plaidNewVar11 = this$plaid.forward();
return plaidNewVar11;
};
var click = plaidNewVar10;
var plaidNewVar9 = plaidNewState_Button.specialize('Button', 'id', id).specialize('Button', 'click', click).instantiate();
var buttonNext = plaidNewVar9;
var plaidNewVar12 = buttonNext.init();
var vAr153$plaid = plaidNewVar12;
var id = "selectBoard";
var plaidNewVar14 = function(evt) {
this$plaid.stateChange(plaidNewState_StoppingPeriodicalTimer);
return this$plaid;
};
var change = plaidNewVar14;
var plaidNewVar13 = plaidNewState_DropDownList.specialize('DropDownList', 'id', id).specialize('DropDownList', 'change', change).instantiate();
var selectBoard = plaidNewVar13;
var plaidNewVar15 = selectBoard.init();
var vAr152$plaid = plaidNewVar15;
var name = "Glider";
var value = "Glider";
var plaidNewVar17 = function() {
this$plaid.board.stateChange(plaidNewState_Glider);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar17;
var plaidNewVar16 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionGlider = plaidNewVar16;
var plaidNewVar18 = optionGlider.init(selectBoard);
var vAr151$plaid = plaidNewVar18;
var name = "TenCellRow";
var value = "TenCellRow";
var plaidNewVar20 = function() {
this$plaid.board.stateChange(plaidNewState_TenCellRow);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar20;
var plaidNewVar19 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionTenCellRow = plaidNewVar19;
var plaidNewVar21 = optionTenCellRow.init(selectBoard);
var vAr150$plaid = plaidNewVar21;
var name = "SmallExploder";
var value = "SmallExploder";
var plaidNewVar23 = function() {
this$plaid.board.stateChange(plaidNewState_SmallExploder);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar23;
var plaidNewVar22 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionSmallExploder = plaidNewVar22;
var plaidNewVar24 = optionSmallExploder.init(selectBoard);
var vAr149$plaid = plaidNewVar24;
var name = "Exploder";
var value = "Exploder";
var plaidNewVar26 = function() {
this$plaid.board.stateChange(plaidNewState_Exploder);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar26;
var plaidNewVar25 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionExploder = plaidNewVar25;
var plaidNewVar27 = optionExploder.init(selectBoard);
var vAr148$plaid = plaidNewVar27;
var name = "Spaceship";
var value = "Spaceship";
var plaidNewVar29 = function() {
this$plaid.board.stateChange(plaidNewState_Spaceship);
return this$plaid.board;
};
var onSelectedOption = plaidNewVar29;
var plaidNewVar28 = plaidNewState_Option.specialize('Option', 'name', name).specialize('Option', 'value', value).specialize('Option', 'onSelectedOption', onSelectedOption).instantiate();
var optionSpaceship = plaidNewVar28;
var plaidNewVar30 = optionSpaceship.init(selectBoard);
return plaidNewVar30;
}
plaidNewState_GameOfLife['forward'] = function forward () {
var this$plaid=this;
var plaidNewVar31 = this$plaid.board.update();
return plaidNewVar31;
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
var plaidNewVar32 = function(vAr159$plaid) {
var plaidNewVar33 = plaidNewState_DebugWriter.instantiate();
window.DebugWriter = plaidNewVar33;
return window.DebugWriter;
};
var plaidNewVar34 = window.DebugWriter == undefined;
var plaidNewVar35 = if$plaid(plaidNewVar34);
var plaidNewVar36 = plaidNewVar35(plaidNewVar32);
var vAr160$plaid = plaidNewVar36;
var interval = 500;
var plaidNewVar38 = function() {
var plaidNewVar39 = game.forward();
return plaidNewVar39;
};
var callback = plaidNewVar38;
var plaidNewVar37 = (plaidNewState_GameOfLife.with(plaidNewState_PeriodicalTimer.specialize('PeriodicalTimer', 'interval', interval).specialize('PeriodicalTimer', 'callback', callback))).instantiate();
var game = plaidNewVar37;
var plaidNewVar40 = game.init();
return plaidNewVar40;
}
$(main);
