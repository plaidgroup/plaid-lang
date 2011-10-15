var plaidNewState_Board = new PlaidState();
plaidNewState_Board['init'] = function init () {
var this$plaid=this;
var plaidNewVar1 = this$plaid.createArray();
this$plaid.cells = plaidNewVar1;
var vAr80$plaid = this$plaid.cells;
var plaidNewVar2 = this$plaid.resetBoard();
return plaidNewVar2;
}
plaidNewState_Board['resetBoard'] = function resetBoard () {
var this$plaid=this;
var plaidNewVar3 = "#" + this$plaid.id;
var plaidNewVar4 = jQuery(plaidNewVar3);
var elm = plaidNewVar4;
var plaidNewVar5 = elm.html("");
var vAr91$plaid = plaidNewVar5;
var plaidNewVar7 = jQuery("<div />");
var plaidNewVar6 = plaidNewVar7.addClass("cell");
var deadCell = plaidNewVar6;
var plaidNewVar8 = jQuery("<br />");
var lineBreak = plaidNewVar8;
var sepalater = "_";
var plaidNewVar9 = this$plaid.cells.clear();
var vAr90$plaid = plaidNewVar9;
var i = 0;
var plaidNewVar10 = function(vAr89$plaid) {
var j = 0;
var plaidNewVar11 = function(vAr86$plaid) {
var plaidNewVar15 = "cell" + this$plaid.sepalater;
var plaidNewVar14 = plaidNewVar15 + i;
var plaidNewVar13 = plaidNewVar14 + this$plaid.sepalater;
var plaidNewVar12 = plaidNewVar13 + j;
var cellId = plaidNewVar12;
var plaidNewVar17 = deadCell.clone();
var plaidNewVar16 = plaidNewVar17.attr("id",cellId);
var plaidNewVar18 = elm.append(plaidNewVar16);
var vAr85$plaid = plaidNewVar18;
var id = cellId;
var plaidNewVar19 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Cell).with(plaidNewState_Population)).instantiate();
var cell = plaidNewVar19;
cell.stateChange(plaidNewState_DeadCell);
var vAr84$plaid = cell;
var plaidNewVar20 = this$plaid.cells.push(cell);
var vAr83$plaid = plaidNewVar20;
var plaidNewVar21 = j + 1;
j = plaidNewVar21;
return j;
};
var plaidNewVar22 = function(vAr82$plaid) {
var plaidNewVar23 = j < this$plaid.width;
return plaidNewVar23;
};
var plaidNewVar24 = while$plaid(plaidNewVar22);
var plaidNewVar25 = plaidNewVar24(plaidNewVar11);
var vAr88$plaid = plaidNewVar25;
var plaidNewVar26 = lineBreak.clone();
var plaidNewVar27 = elm.append(plaidNewVar26);
var vAr87$plaid = plaidNewVar27;
var plaidNewVar28 = i + 1;
i = plaidNewVar28;
return i;
};
var plaidNewVar29 = function(vAr81$plaid) {
var plaidNewVar30 = i < this$plaid.height;
return plaidNewVar30;
};
var plaidNewVar31 = while$plaid(plaidNewVar29);
var plaidNewVar32 = plaidNewVar31(plaidNewVar10);
return plaidNewVar32;
}
plaidNewState_Board['update'] = function update () {
var this$plaid=this;
var plaidNewVar33 = function(cell,i) {
var plaidNewVar34 = this$plaid.getAround(this$plaid.cells,i);
var plaidNewVar35 = cell.updatePopulation(plaidNewVar34);
return plaidNewVar35;
};
var plaidNewVar36 = this$plaid.each(plaidNewVar33);
var vAr92$plaid = plaidNewVar36;
var plaidNewVar37 = function(cell) {
var plaidNewVar38 = cell.updateCell();
return plaidNewVar38;
};
var plaidNewVar39 = this$plaid.each(plaidNewVar37);
return plaidNewVar39;
}
plaidNewState_Board['getAround'] = function getAround (previousCells,idx) {
var this$plaid=this;
var plaidNewVar40 = this$plaid.createArray();
var around = plaidNewVar40;
var plaidNewVar41 = this$plaid.getRow(idx);
var r = plaidNewVar41;
var plaidNewVar42 = this$plaid.getCol(idx);
var c = plaidNewVar42;
var i = -1;
var plaidNewVar43 = function(vAr99$plaid) {
var j = -1;
var plaidNewVar44 = function(vAr97$plaid) {
var plaidNewVar45 = function(vAr95$plaid) {
var plaidNewVar46 = r + i;
var plaidNewVar47 = c + j;
var plaidNewVar48 = this$plaid.getCell(previousCells,plaidNewVar46,plaidNewVar47);
var plaidNewVar49 = around.push(plaidNewVar48);
return plaidNewVar49;
};
var plaidNewVar50 = j != 0;
var plaidNewVar52 = i != 0;
var plaidNewVar51 = plaidNewVar52 || plaidNewVar50;
var plaidNewVar53 = if$plaid(plaidNewVar51);
var plaidNewVar54 = plaidNewVar53(plaidNewVar45);
var vAr96$plaid = plaidNewVar54;
var plaidNewVar55 = j + 1;
j = plaidNewVar55;
return j;
};
var plaidNewVar56 = function(vAr94$plaid) {
var plaidNewVar57 = j <= 1;
return plaidNewVar57;
};
var plaidNewVar58 = while$plaid(plaidNewVar56);
var plaidNewVar59 = plaidNewVar58(plaidNewVar44);
var vAr98$plaid = plaidNewVar59;
var plaidNewVar60 = i + 1;
i = plaidNewVar60;
return i;
};
var plaidNewVar61 = function(vAr93$plaid) {
var plaidNewVar62 = i <= 1;
return plaidNewVar62;
};
var plaidNewVar63 = while$plaid(plaidNewVar61);
var plaidNewVar64 = plaidNewVar63(plaidNewVar43);
var vAr100$plaid = plaidNewVar64;
return around;
}
plaidNewState_Board['getCell'] = function getCell (cells,row,col) {
var this$plaid=this;
var plaidNewVar65 = function(vAr102$plaid) {
var plaidNewVar66 = plaidNewState_NilCell.instantiate();
return plaidNewVar66;
};
var plaidNewVar67 = function(vAr101$plaid) {
var plaidNewVar69 = row * this$plaid.width;
var plaidNewVar68 = plaidNewVar69 + col;
var plaidNewVar70 = cells.get(plaidNewVar68);
return plaidNewVar70;
};
var plaidNewVar71 = col < this$plaid.height;
var plaidNewVar73 = 0 <= col;
var plaidNewVar75 = row < this$plaid.width;
var plaidNewVar77 = 0 <= row;
var plaidNewVar76 = plaidNewVar77 && plaidNewVar75;
var plaidNewVar74 = plaidNewVar76 && plaidNewVar73;
var plaidNewVar72 = plaidNewVar74 && plaidNewVar71;
var plaidNewVar78 = ifElse(plaidNewVar72);
var plaidNewVar79 = plaidNewVar78(plaidNewVar67);
var plaidNewVar80 = plaidNewVar79(plaidNewVar65);
return plaidNewVar80;
}
plaidNewState_Board['getRow'] = function getRow (idx) {
var this$plaid=this;
var plaidNewVar81 = idx / this$plaid.width;
var plaidNewVar82 = Math.floor(plaidNewVar81);
return plaidNewVar82;
}
plaidNewState_Board['getCol'] = function getCol (idx) {
var this$plaid=this;
var plaidNewVar84 = idx / this$plaid.width;
var plaidNewVar85 = Math.floor(plaidNewVar84);
var plaidNewVar83 = plaidNewVar85 * this$plaid.width;
var plaidNewVar86 = idx - plaidNewVar83;
return plaidNewVar86;
}
plaidNewState_Board['createArray'] = function createArray () {
var this$plaid=this;
var plaidNewVar87 = jQuery.makeArray("");
var array = plaidNewVar87;
var length = array.length;
var plaidNewVar88 = function(vAr105$plaid) {
var plaidNewVar89 = array.pop();
var vAr104$plaid = plaidNewVar89;
var plaidNewVar90 = length - 1;
length = plaidNewVar90;
return length;
};
var plaidNewVar91 = function(vAr103$plaid) {
var plaidNewVar92 = length > 0;
return plaidNewVar92;
};
var plaidNewVar93 = while$plaid(plaidNewVar91);
var plaidNewVar94 = plaidNewVar93(plaidNewVar88);
var vAr106$plaid = plaidNewVar94;
return array;
}
plaidNewState_Board['each'] = function each (f) {
var this$plaid=this;
var i = 0;
var plaidNewVar95 = function(vAr109$plaid) {
var plaidNewVar96 = this$plaid.cells.get(i);
var plaidNewVar97 = f(plaidNewVar96,i);
var vAr108$plaid = plaidNewVar97;
var plaidNewVar98 = i + 1;
i = plaidNewVar98;
return i;
};
var plaidNewVar99 = function(vAr107$plaid) {
var plaidNewVar100 = i < this$plaid.cells.length;
return plaidNewVar100;
};
var plaidNewVar101 = while$plaid(plaidNewVar99);
var plaidNewVar102 = plaidNewVar101(plaidNewVar95);
return plaidNewVar102;
}
plaidNewState_Board.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with']]];
var plaidNewState_TenCellRow = new PlaidState();
plaidNewState_TenCellRow['init'] = plaidNewState_Board['init'];
plaidNewState_TenCellRow['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_TenCellRow['update'] = plaidNewState_Board['update'];
plaidNewState_TenCellRow['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_TenCellRow['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_TenCellRow['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_TenCellRow['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_TenCellRow['createArray'] = plaidNewState_Board['createArray'];
plaidNewState_TenCellRow['each'] = plaidNewState_Board['each'];
plaidNewState_TenCellRow['onTenCellRow'] = function onTenCellRow () {
var this$plaid=this;
var plaidNewVar103 = this$plaid.height / 2;
var plaidNewVar104 = Math.floor(plaidNewVar103);
var row = plaidNewVar104;
var plaidNewVar105 = this$plaid.width / 2;
var plaidNewVar106 = Math.floor(plaidNewVar105);
var right = plaidNewVar106;
var plaidNewVar107 = right - 1;
var left = plaidNewVar107;
var i = 0;
var plaidNewVar108 = function(vAr113$plaid) {
var plaidNewVar109 = left - i;
var plaidNewVar110 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar109);
plaidNewVar110.stateChange(plaidNewState_AliveCell);
var vAr112$plaid = plaidNewVar110;
var plaidNewVar111 = right + i;
var plaidNewVar112 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar111);
plaidNewVar112.stateChange(plaidNewState_AliveCell);
var vAr111$plaid = plaidNewVar112;
var plaidNewVar113 = i + 1;
i = plaidNewVar113;
return i;
};
var plaidNewVar114 = function(vAr110$plaid) {
var plaidNewVar115 = i < 5;
return plaidNewVar115;
};
var plaidNewVar116 = while$plaid(plaidNewVar114);
var plaidNewVar117 = plaidNewVar116(plaidNewVar108);
return plaidNewVar117;
}
plaidNewState_TenCellRow['offTenCellRow'] = function offTenCellRow () {
var this$plaid=this;
var plaidNewVar118 = this$plaid.resetBoard();
return plaidNewVar118;
}
plaidNewState_TenCellRow.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['TenCellRow', ['onTenCellRow', 'offTenCellRow'], '']]]];
var plaidNewState_Glider = new PlaidState();
plaidNewState_Glider['init'] = plaidNewState_Board['init'];
plaidNewState_Glider['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Glider['update'] = plaidNewState_Board['update'];
plaidNewState_Glider['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Glider['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Glider['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Glider['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Glider['createArray'] = plaidNewState_Board['createArray'];
plaidNewState_Glider['each'] = plaidNewState_Board['each'];
plaidNewState_Glider['onGlider'] = function onGlider () {
var this$plaid=this;
var plaidNewVar119 = this$plaid.height / 2;
var plaidNewVar120 = Math.floor(plaidNewVar119);
var row = plaidNewVar120;
var plaidNewVar121 = this$plaid.width / 2;
var plaidNewVar122 = Math.floor(plaidNewVar121);
var col = plaidNewVar122;
var plaidNewVar123 = row - 1;
var plaidNewVar124 = this$plaid.getCell(this$plaid.cells,plaidNewVar123,col);
plaidNewVar124.stateChange(plaidNewState_AliveCell);
var vAr117$plaid = plaidNewVar124;
var plaidNewVar125 = col + 1;
var plaidNewVar126 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar125);
plaidNewVar126.stateChange(plaidNewState_AliveCell);
var vAr116$plaid = plaidNewVar126;
var plaidNewVar127 = row + 1;
var plaidNewVar128 = col - 1;
var plaidNewVar129 = this$plaid.getCell(this$plaid.cells,plaidNewVar127,plaidNewVar128);
plaidNewVar129.stateChange(plaidNewState_AliveCell);
var vAr115$plaid = plaidNewVar129;
var plaidNewVar130 = row + 1;
var plaidNewVar131 = this$plaid.getCell(this$plaid.cells,plaidNewVar130,col);
plaidNewVar131.stateChange(plaidNewState_AliveCell);
var vAr114$plaid = plaidNewVar131;
var plaidNewVar132 = row + 1;
var plaidNewVar133 = col + 1;
var plaidNewVar134 = this$plaid.getCell(this$plaid.cells,plaidNewVar132,plaidNewVar133);
plaidNewVar134.stateChange(plaidNewState_AliveCell);
return plaidNewVar134;
}
plaidNewState_Glider['offGlider'] = function offGlider () {
var this$plaid=this;
var plaidNewVar135 = this$plaid.resetBoard();
return plaidNewVar135;
}
plaidNewState_Glider.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['Glider', ['onGlider', 'offGlider'], '']]]];
var plaidNewState_SmallExploder = new PlaidState();
plaidNewState_SmallExploder['init'] = plaidNewState_Board['init'];
plaidNewState_SmallExploder['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_SmallExploder['update'] = plaidNewState_Board['update'];
plaidNewState_SmallExploder['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_SmallExploder['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_SmallExploder['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_SmallExploder['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_SmallExploder['createArray'] = plaidNewState_Board['createArray'];
plaidNewState_SmallExploder['each'] = plaidNewState_Board['each'];
plaidNewState_SmallExploder['onSmallExploder'] = function onSmallExploder () {
var this$plaid=this;
var plaidNewVar136 = this$plaid.height / 2;
var plaidNewVar137 = Math.floor(plaidNewVar136);
var row = plaidNewVar137;
var plaidNewVar138 = this$plaid.width / 2;
var plaidNewVar139 = Math.floor(plaidNewVar138);
var col = plaidNewVar139;
var plaidNewVar140 = row - 1;
var plaidNewVar141 = this$plaid.getCell(this$plaid.cells,plaidNewVar140,col);
plaidNewVar141.stateChange(plaidNewState_AliveCell);
var vAr123$plaid = plaidNewVar141;
var plaidNewVar142 = col - 1;
var plaidNewVar143 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar142);
plaidNewVar143.stateChange(plaidNewState_AliveCell);
var vAr122$plaid = plaidNewVar143;
var plaidNewVar144 = this$plaid.getCell(this$plaid.cells,row,col);
plaidNewVar144.stateChange(plaidNewState_AliveCell);
var vAr121$plaid = plaidNewVar144;
var plaidNewVar145 = col + 1;
var plaidNewVar146 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar145);
plaidNewVar146.stateChange(plaidNewState_AliveCell);
var vAr120$plaid = plaidNewVar146;
var plaidNewVar147 = row + 1;
var plaidNewVar148 = col - 1;
var plaidNewVar149 = this$plaid.getCell(this$plaid.cells,plaidNewVar147,plaidNewVar148);
plaidNewVar149.stateChange(plaidNewState_AliveCell);
var vAr119$plaid = plaidNewVar149;
var plaidNewVar150 = row + 1;
var plaidNewVar151 = col + 1;
var plaidNewVar152 = this$plaid.getCell(this$plaid.cells,plaidNewVar150,plaidNewVar151);
plaidNewVar152.stateChange(plaidNewState_AliveCell);
var vAr118$plaid = plaidNewVar152;
var plaidNewVar153 = row + 2;
var plaidNewVar154 = this$plaid.getCell(this$plaid.cells,plaidNewVar153,col);
plaidNewVar154.stateChange(plaidNewState_AliveCell);
return plaidNewVar154;
}
plaidNewState_SmallExploder['offSmallExploder'] = function offSmallExploder () {
var this$plaid=this;
var plaidNewVar155 = this$plaid.resetBoard();
return plaidNewVar155;
}
plaidNewState_SmallExploder.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['SmallExploder', ['onSmallExploder', 'offSmallExploder'], '']]]];
var plaidNewState_Exploder = new PlaidState();
plaidNewState_Exploder['init'] = plaidNewState_Board['init'];
plaidNewState_Exploder['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Exploder['update'] = plaidNewState_Board['update'];
plaidNewState_Exploder['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Exploder['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Exploder['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Exploder['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Exploder['createArray'] = plaidNewState_Board['createArray'];
plaidNewState_Exploder['each'] = plaidNewState_Board['each'];
plaidNewState_Exploder['onExploder'] = function onExploder () {
var this$plaid=this;
var plaidNewVar156 = this$plaid.height / 2;
var plaidNewVar157 = Math.floor(plaidNewVar156);
var row = plaidNewVar157;
var plaidNewVar158 = this$plaid.width / 2;
var plaidNewVar159 = Math.floor(plaidNewVar158);
var col = plaidNewVar159;
var plaidNewVar160 = row - 2;
var plaidNewVar161 = this$plaid.getCell(this$plaid.cells,plaidNewVar160,col);
plaidNewVar161.stateChange(plaidNewState_AliveCell);
var vAr134$plaid = plaidNewVar161;
var plaidNewVar162 = row + 2;
var plaidNewVar163 = this$plaid.getCell(this$plaid.cells,plaidNewVar162,col);
plaidNewVar163.stateChange(plaidNewState_AliveCell);
var vAr133$plaid = plaidNewVar163;
var plaidNewVar164 = row - 2;
var plaidNewVar165 = col - 2;
var plaidNewVar166 = this$plaid.getCell(this$plaid.cells,plaidNewVar164,plaidNewVar165);
plaidNewVar166.stateChange(plaidNewState_AliveCell);
var vAr132$plaid = plaidNewVar166;
var plaidNewVar167 = row - 1;
var plaidNewVar168 = col - 2;
var plaidNewVar169 = this$plaid.getCell(this$plaid.cells,plaidNewVar167,plaidNewVar168);
plaidNewVar169.stateChange(plaidNewState_AliveCell);
var vAr131$plaid = plaidNewVar169;
var plaidNewVar170 = col - 2;
var plaidNewVar171 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar170);
plaidNewVar171.stateChange(plaidNewState_AliveCell);
var vAr130$plaid = plaidNewVar171;
var plaidNewVar172 = row + 1;
var plaidNewVar173 = col - 2;
var plaidNewVar174 = this$plaid.getCell(this$plaid.cells,plaidNewVar172,plaidNewVar173);
plaidNewVar174.stateChange(plaidNewState_AliveCell);
var vAr129$plaid = plaidNewVar174;
var plaidNewVar175 = row + 2;
var plaidNewVar176 = col - 2;
var plaidNewVar177 = this$plaid.getCell(this$plaid.cells,plaidNewVar175,plaidNewVar176);
plaidNewVar177.stateChange(plaidNewState_AliveCell);
var vAr128$plaid = plaidNewVar177;
var plaidNewVar178 = row - 2;
var plaidNewVar179 = col + 2;
var plaidNewVar180 = this$plaid.getCell(this$plaid.cells,plaidNewVar178,plaidNewVar179);
plaidNewVar180.stateChange(plaidNewState_AliveCell);
var vAr127$plaid = plaidNewVar180;
var plaidNewVar181 = row - 1;
var plaidNewVar182 = col + 2;
var plaidNewVar183 = this$plaid.getCell(this$plaid.cells,plaidNewVar181,plaidNewVar182);
plaidNewVar183.stateChange(plaidNewState_AliveCell);
var vAr126$plaid = plaidNewVar183;
var plaidNewVar184 = col + 2;
var plaidNewVar185 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar184);
plaidNewVar185.stateChange(plaidNewState_AliveCell);
var vAr125$plaid = plaidNewVar185;
var plaidNewVar186 = row + 1;
var plaidNewVar187 = col + 2;
var plaidNewVar188 = this$plaid.getCell(this$plaid.cells,plaidNewVar186,plaidNewVar187);
plaidNewVar188.stateChange(plaidNewState_AliveCell);
var vAr124$plaid = plaidNewVar188;
var plaidNewVar189 = row + 2;
var plaidNewVar190 = col + 2;
var plaidNewVar191 = this$plaid.getCell(this$plaid.cells,plaidNewVar189,plaidNewVar190);
plaidNewVar191.stateChange(plaidNewState_AliveCell);
return plaidNewVar191;
}
plaidNewState_Exploder['offExploder'] = function offExploder () {
var this$plaid=this;
var plaidNewVar192 = this$plaid.resetBoard();
return plaidNewVar192;
}
plaidNewState_Exploder.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['Exploder', ['onExploder', 'offExploder'], '']]]];
var plaidNewState_Spaceship = new PlaidState();
plaidNewState_Spaceship['init'] = plaidNewState_Board['init'];
plaidNewState_Spaceship['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Spaceship['update'] = plaidNewState_Board['update'];
plaidNewState_Spaceship['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Spaceship['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Spaceship['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Spaceship['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Spaceship['createArray'] = plaidNewState_Board['createArray'];
plaidNewState_Spaceship['each'] = plaidNewState_Board['each'];
plaidNewState_Spaceship['onSpaceship'] = function onSpaceship () {
var this$plaid=this;
var plaidNewVar193 = this$plaid.height / 2;
var plaidNewVar194 = Math.floor(plaidNewVar193);
var row = plaidNewVar194;
var plaidNewVar195 = this$plaid.width / 2;
var plaidNewVar196 = Math.floor(plaidNewVar195);
var col = plaidNewVar196;
var plaidNewVar197 = row - 2;
var plaidNewVar198 = col - 1;
var plaidNewVar199 = this$plaid.getCell(this$plaid.cells,plaidNewVar197,plaidNewVar198);
plaidNewVar199.stateChange(plaidNewState_AliveCell);
var vAr142$plaid = plaidNewVar199;
var plaidNewVar200 = row - 2;
var plaidNewVar201 = this$plaid.getCell(this$plaid.cells,plaidNewVar200,col);
plaidNewVar201.stateChange(plaidNewState_AliveCell);
var vAr141$plaid = plaidNewVar201;
var plaidNewVar202 = row - 2;
var plaidNewVar203 = col + 1;
var plaidNewVar204 = this$plaid.getCell(this$plaid.cells,plaidNewVar202,plaidNewVar203);
plaidNewVar204.stateChange(plaidNewState_AliveCell);
var vAr140$plaid = plaidNewVar204;
var plaidNewVar205 = row - 2;
var plaidNewVar206 = col + 2;
var plaidNewVar207 = this$plaid.getCell(this$plaid.cells,plaidNewVar205,plaidNewVar206);
plaidNewVar207.stateChange(plaidNewState_AliveCell);
var vAr139$plaid = plaidNewVar207;
var plaidNewVar208 = row - 1;
var plaidNewVar209 = col - 2;
var plaidNewVar210 = this$plaid.getCell(this$plaid.cells,plaidNewVar208,plaidNewVar209);
plaidNewVar210.stateChange(plaidNewState_AliveCell);
var vAr138$plaid = plaidNewVar210;
var plaidNewVar211 = row - 1;
var plaidNewVar212 = col + 2;
var plaidNewVar213 = this$plaid.getCell(this$plaid.cells,plaidNewVar211,plaidNewVar212);
plaidNewVar213.stateChange(plaidNewState_AliveCell);
var vAr137$plaid = plaidNewVar213;
var plaidNewVar214 = col + 2;
var plaidNewVar215 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar214);
plaidNewVar215.stateChange(plaidNewState_AliveCell);
var vAr136$plaid = plaidNewVar215;
var plaidNewVar216 = row + 1;
var plaidNewVar217 = col - 2;
var plaidNewVar218 = this$plaid.getCell(this$plaid.cells,plaidNewVar216,plaidNewVar217);
plaidNewVar218.stateChange(plaidNewState_AliveCell);
var vAr135$plaid = plaidNewVar218;
var plaidNewVar219 = row + 1;
var plaidNewVar220 = col + 1;
var plaidNewVar221 = this$plaid.getCell(this$plaid.cells,plaidNewVar219,plaidNewVar220);
plaidNewVar221.stateChange(plaidNewState_AliveCell);
return plaidNewVar221;
}
plaidNewState_Spaceship['offSpaceship'] = function offSpaceship () {
var this$plaid=this;
var plaidNewVar222 = this$plaid.resetBoard();
return plaidNewVar222;
}
plaidNewState_Spaceship.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['Spaceship', ['onSpaceship', 'offSpaceship'], '']]]];
