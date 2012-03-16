var plaidNewState_Board = new PlaidState();
plaidNewState_Board['init'] = function init () {
var this$plaid=this;
var plaidNewVar1 = returnArray();
this$plaid.cells = plaidNewVar1;
var vAr76$plaid = this$plaid.cells;
var plaidNewVar2 = this$plaid.resetBoard();
return plaidNewVar2;
}
plaidNewState_Board['resetBoard'] = function resetBoard () {
var this$plaid=this;
var plaidNewVar3 = "#" + this$plaid.id;
var plaidNewVar4 = jQuery(plaidNewVar3);
var elm = plaidNewVar4;
var plaidNewVar5 = elm.html("");
var vAr87$plaid = plaidNewVar5;
var plaidNewVar7 = jQuery("<div />");
var plaidNewVar6 = plaidNewVar7.addClass("cell");
var deadCell = plaidNewVar6;
var plaidNewVar8 = jQuery("<br />");
var lineBreak = plaidNewVar8;
var sepalater = "_";
var plaidNewVar9 = this$plaid.cells.clear();
var vAr86$plaid = plaidNewVar9;
var i = 0;
var plaidNewVar10 = function(vAr85$plaid) {
var j = 0;
var plaidNewVar11 = function(vAr82$plaid) {
var plaidNewVar15 = "cell" + this$plaid.sepalater;
var plaidNewVar14 = plaidNewVar15 + i;
var plaidNewVar13 = plaidNewVar14 + this$plaid.sepalater;
var plaidNewVar12 = plaidNewVar13 + j;
var cellId = plaidNewVar12;
var plaidNewVar17 = deadCell.clone();
var plaidNewVar16 = plaidNewVar17.attr("id",cellId);
var plaidNewVar18 = elm.append(plaidNewVar16);
var vAr81$plaid = plaidNewVar18;
var id = cellId;
var plaidNewVar19 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Cell).with(plaidNewState_Population)).instantiate();
var cell = plaidNewVar19;
cell.stateChange(plaidNewState_DeadCell);
var vAr80$plaid = cell;
var plaidNewVar20 = this$plaid.cells.push(cell);
var vAr79$plaid = plaidNewVar20;
var plaidNewVar21 = j + 1;
j = plaidNewVar21;
return j;
};
var plaidNewVar22 = function(vAr78$plaid) {
var plaidNewVar23 = j < this$plaid.width;
return plaidNewVar23;
};
var plaidNewVar24 = while$plaid(plaidNewVar22);
var plaidNewVar25 = plaidNewVar24(plaidNewVar11);
var vAr84$plaid = plaidNewVar25;
var plaidNewVar26 = lineBreak.clone();
var plaidNewVar27 = elm.append(plaidNewVar26);
var vAr83$plaid = plaidNewVar27;
var plaidNewVar28 = i + 1;
i = plaidNewVar28;
return i;
};
var plaidNewVar29 = function(vAr77$plaid) {
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
var vAr88$plaid = plaidNewVar36;
var plaidNewVar37 = function(cell) {
var plaidNewVar38 = cell.updateCell();
return plaidNewVar38;
};
var plaidNewVar39 = this$plaid.each(plaidNewVar37);
return plaidNewVar39;
}
plaidNewState_Board['getAround'] = function getAround (previousCells,idx) {
var this$plaid=this;
var plaidNewVar40 = returnArray();
var around = plaidNewVar40;
var plaidNewVar41 = this$plaid.getRow(idx);
var r = plaidNewVar41;
var plaidNewVar42 = this$plaid.getCol(idx);
var c = plaidNewVar42;
var i = -1;
var plaidNewVar43 = function(vAr95$plaid) {
var j = -1;
var plaidNewVar44 = function(vAr93$plaid) {
var plaidNewVar45 = function(vAr91$plaid) {
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
var vAr92$plaid = plaidNewVar54;
var plaidNewVar55 = j + 1;
j = plaidNewVar55;
return j;
};
var plaidNewVar56 = function(vAr90$plaid) {
var plaidNewVar57 = j <= 1;
return plaidNewVar57;
};
var plaidNewVar58 = while$plaid(plaidNewVar56);
var plaidNewVar59 = plaidNewVar58(plaidNewVar44);
var vAr94$plaid = plaidNewVar59;
var plaidNewVar60 = i + 1;
i = plaidNewVar60;
return i;
};
var plaidNewVar61 = function(vAr89$plaid) {
var plaidNewVar62 = i <= 1;
return plaidNewVar62;
};
var plaidNewVar63 = while$plaid(plaidNewVar61);
var plaidNewVar64 = plaidNewVar63(plaidNewVar43);
var vAr96$plaid = plaidNewVar64;
return around;
}
plaidNewState_Board['getCell'] = function getCell (cells,row,col) {
var this$plaid=this;
var plaidNewVar65 = function(vAr98$plaid) {
var plaidNewVar66 = plaidNewState_NilCell.instantiate();
return plaidNewVar66;
};
var plaidNewVar67 = function(vAr97$plaid) {
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
plaidNewState_Board['each'] = function each (f) {
var this$plaid=this;
var i = 0;
var plaidNewVar87 = function(vAr101$plaid) {
var plaidNewVar88 = this$plaid.cells.get(i);
var plaidNewVar89 = f(plaidNewVar88,i);
var vAr100$plaid = plaidNewVar89;
var plaidNewVar90 = i + 1;
i = plaidNewVar90;
return i;
};
var plaidNewVar91 = function(vAr99$plaid) {
var plaidNewVar92 = i < this$plaid.cells.length;
return plaidNewVar92;
};
var plaidNewVar93 = while$plaid(plaidNewVar91);
var plaidNewVar94 = plaidNewVar93(plaidNewVar87);
return plaidNewVar94;
}
plaidNewState_Board.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with']]];
var plaidNewState_TenCellRow = new PlaidState();
plaidNewState_TenCellRow['init'] = plaidNewState_Board['init'];
plaidNewState_TenCellRow['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_TenCellRow['update'] = plaidNewState_Board['update'];
plaidNewState_TenCellRow['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_TenCellRow['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_TenCellRow['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_TenCellRow['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_TenCellRow['each'] = plaidNewState_Board['each'];
plaidNewState_TenCellRow['onTenCellRow'] = function onTenCellRow () {
var this$plaid=this;
var plaidNewVar95 = this$plaid.height / 2;
var plaidNewVar96 = Math.floor(plaidNewVar95);
var row = plaidNewVar96;
var plaidNewVar97 = this$plaid.width / 2;
var plaidNewVar98 = Math.floor(plaidNewVar97);
var right = plaidNewVar98;
var plaidNewVar99 = right - 1;
var left = plaidNewVar99;
var i = 0;
var plaidNewVar100 = function(vAr105$plaid) {
var plaidNewVar101 = left - i;
var plaidNewVar102 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar101);
plaidNewVar102.stateChange(plaidNewState_AliveCell);
var vAr104$plaid = plaidNewVar102;
var plaidNewVar103 = right + i;
var plaidNewVar104 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar103);
plaidNewVar104.stateChange(plaidNewState_AliveCell);
var vAr103$plaid = plaidNewVar104;
var plaidNewVar105 = i + 1;
i = plaidNewVar105;
return i;
};
var plaidNewVar106 = function(vAr102$plaid) {
var plaidNewVar107 = i < 5;
return plaidNewVar107;
};
var plaidNewVar108 = while$plaid(plaidNewVar106);
var plaidNewVar109 = plaidNewVar108(plaidNewVar100);
return plaidNewVar109;
}
plaidNewState_TenCellRow['offTenCellRow'] = function offTenCellRow () {
var this$plaid=this;
var plaidNewVar110 = this$plaid.resetBoard();
return plaidNewVar110;
}
plaidNewState_TenCellRow.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with'], [['TenCellRow', ['onTenCellRow', 'offTenCellRow'], '']]]];
var plaidNewState_Glider = new PlaidState();
plaidNewState_Glider['init'] = plaidNewState_Board['init'];
plaidNewState_Glider['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Glider['update'] = plaidNewState_Board['update'];
plaidNewState_Glider['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Glider['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Glider['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Glider['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Glider['each'] = plaidNewState_Board['each'];
plaidNewState_Glider['onGlider'] = function onGlider () {
var this$plaid=this;
var plaidNewVar111 = this$plaid.height / 2;
var plaidNewVar112 = Math.floor(plaidNewVar111);
var row = plaidNewVar112;
var plaidNewVar113 = this$plaid.width / 2;
var plaidNewVar114 = Math.floor(plaidNewVar113);
var col = plaidNewVar114;
var plaidNewVar115 = row - 1;
var plaidNewVar116 = this$plaid.getCell(this$plaid.cells,plaidNewVar115,col);
plaidNewVar116.stateChange(plaidNewState_AliveCell);
var vAr109$plaid = plaidNewVar116;
var plaidNewVar117 = col + 1;
var plaidNewVar118 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar117);
plaidNewVar118.stateChange(plaidNewState_AliveCell);
var vAr108$plaid = plaidNewVar118;
var plaidNewVar119 = row + 1;
var plaidNewVar120 = col - 1;
var plaidNewVar121 = this$plaid.getCell(this$plaid.cells,plaidNewVar119,plaidNewVar120);
plaidNewVar121.stateChange(plaidNewState_AliveCell);
var vAr107$plaid = plaidNewVar121;
var plaidNewVar122 = row + 1;
var plaidNewVar123 = this$plaid.getCell(this$plaid.cells,plaidNewVar122,col);
plaidNewVar123.stateChange(plaidNewState_AliveCell);
var vAr106$plaid = plaidNewVar123;
var plaidNewVar124 = row + 1;
var plaidNewVar125 = col + 1;
var plaidNewVar126 = this$plaid.getCell(this$plaid.cells,plaidNewVar124,plaidNewVar125);
plaidNewVar126.stateChange(plaidNewState_AliveCell);
return plaidNewVar126;
}
plaidNewState_Glider['offGlider'] = function offGlider () {
var this$plaid=this;
var plaidNewVar127 = this$plaid.resetBoard();
return plaidNewVar127;
}
plaidNewState_Glider.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with'], [['Glider', ['onGlider', 'offGlider'], '']]]];
var plaidNewState_SmallExploder = new PlaidState();
plaidNewState_SmallExploder['init'] = plaidNewState_Board['init'];
plaidNewState_SmallExploder['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_SmallExploder['update'] = plaidNewState_Board['update'];
plaidNewState_SmallExploder['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_SmallExploder['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_SmallExploder['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_SmallExploder['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_SmallExploder['each'] = plaidNewState_Board['each'];
plaidNewState_SmallExploder['onSmallExploder'] = function onSmallExploder () {
var this$plaid=this;
var plaidNewVar128 = this$plaid.height / 2;
var plaidNewVar129 = Math.floor(plaidNewVar128);
var row = plaidNewVar129;
var plaidNewVar130 = this$plaid.width / 2;
var plaidNewVar131 = Math.floor(plaidNewVar130);
var col = plaidNewVar131;
var plaidNewVar132 = row - 1;
var plaidNewVar133 = this$plaid.getCell(this$plaid.cells,plaidNewVar132,col);
plaidNewVar133.stateChange(plaidNewState_AliveCell);
var vAr115$plaid = plaidNewVar133;
var plaidNewVar134 = col - 1;
var plaidNewVar135 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar134);
plaidNewVar135.stateChange(plaidNewState_AliveCell);
var vAr114$plaid = plaidNewVar135;
var plaidNewVar136 = this$plaid.getCell(this$plaid.cells,row,col);
plaidNewVar136.stateChange(plaidNewState_AliveCell);
var vAr113$plaid = plaidNewVar136;
var plaidNewVar137 = col + 1;
var plaidNewVar138 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar137);
plaidNewVar138.stateChange(plaidNewState_AliveCell);
var vAr112$plaid = plaidNewVar138;
var plaidNewVar139 = row + 1;
var plaidNewVar140 = col - 1;
var plaidNewVar141 = this$plaid.getCell(this$plaid.cells,plaidNewVar139,plaidNewVar140);
plaidNewVar141.stateChange(plaidNewState_AliveCell);
var vAr111$plaid = plaidNewVar141;
var plaidNewVar142 = row + 1;
var plaidNewVar143 = col + 1;
var plaidNewVar144 = this$plaid.getCell(this$plaid.cells,plaidNewVar142,plaidNewVar143);
plaidNewVar144.stateChange(plaidNewState_AliveCell);
var vAr110$plaid = plaidNewVar144;
var plaidNewVar145 = row + 2;
var plaidNewVar146 = this$plaid.getCell(this$plaid.cells,plaidNewVar145,col);
plaidNewVar146.stateChange(plaidNewState_AliveCell);
return plaidNewVar146;
}
plaidNewState_SmallExploder['offSmallExploder'] = function offSmallExploder () {
var this$plaid=this;
var plaidNewVar147 = this$plaid.resetBoard();
return plaidNewVar147;
}
plaidNewState_SmallExploder.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with'], [['SmallExploder', ['onSmallExploder', 'offSmallExploder'], '']]]];
var plaidNewState_Exploder = new PlaidState();
plaidNewState_Exploder['init'] = plaidNewState_Board['init'];
plaidNewState_Exploder['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Exploder['update'] = plaidNewState_Board['update'];
plaidNewState_Exploder['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Exploder['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Exploder['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Exploder['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Exploder['each'] = plaidNewState_Board['each'];
plaidNewState_Exploder['onExploder'] = function onExploder () {
var this$plaid=this;
var plaidNewVar148 = this$plaid.height / 2;
var plaidNewVar149 = Math.floor(plaidNewVar148);
var row = plaidNewVar149;
var plaidNewVar150 = this$plaid.width / 2;
var plaidNewVar151 = Math.floor(plaidNewVar150);
var col = plaidNewVar151;
var plaidNewVar152 = row - 2;
var plaidNewVar153 = this$plaid.getCell(this$plaid.cells,plaidNewVar152,col);
plaidNewVar153.stateChange(plaidNewState_AliveCell);
var vAr126$plaid = plaidNewVar153;
var plaidNewVar154 = row + 2;
var plaidNewVar155 = this$plaid.getCell(this$plaid.cells,plaidNewVar154,col);
plaidNewVar155.stateChange(plaidNewState_AliveCell);
var vAr125$plaid = plaidNewVar155;
var plaidNewVar156 = row - 2;
var plaidNewVar157 = col - 2;
var plaidNewVar158 = this$plaid.getCell(this$plaid.cells,plaidNewVar156,plaidNewVar157);
plaidNewVar158.stateChange(plaidNewState_AliveCell);
var vAr124$plaid = plaidNewVar158;
var plaidNewVar159 = row - 1;
var plaidNewVar160 = col - 2;
var plaidNewVar161 = this$plaid.getCell(this$plaid.cells,plaidNewVar159,plaidNewVar160);
plaidNewVar161.stateChange(plaidNewState_AliveCell);
var vAr123$plaid = plaidNewVar161;
var plaidNewVar162 = col - 2;
var plaidNewVar163 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar162);
plaidNewVar163.stateChange(plaidNewState_AliveCell);
var vAr122$plaid = plaidNewVar163;
var plaidNewVar164 = row + 1;
var plaidNewVar165 = col - 2;
var plaidNewVar166 = this$plaid.getCell(this$plaid.cells,plaidNewVar164,plaidNewVar165);
plaidNewVar166.stateChange(plaidNewState_AliveCell);
var vAr121$plaid = plaidNewVar166;
var plaidNewVar167 = row + 2;
var plaidNewVar168 = col - 2;
var plaidNewVar169 = this$plaid.getCell(this$plaid.cells,plaidNewVar167,plaidNewVar168);
plaidNewVar169.stateChange(plaidNewState_AliveCell);
var vAr120$plaid = plaidNewVar169;
var plaidNewVar170 = row - 2;
var plaidNewVar171 = col + 2;
var plaidNewVar172 = this$plaid.getCell(this$plaid.cells,plaidNewVar170,plaidNewVar171);
plaidNewVar172.stateChange(plaidNewState_AliveCell);
var vAr119$plaid = plaidNewVar172;
var plaidNewVar173 = row - 1;
var plaidNewVar174 = col + 2;
var plaidNewVar175 = this$plaid.getCell(this$plaid.cells,plaidNewVar173,plaidNewVar174);
plaidNewVar175.stateChange(plaidNewState_AliveCell);
var vAr118$plaid = plaidNewVar175;
var plaidNewVar176 = col + 2;
var plaidNewVar177 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar176);
plaidNewVar177.stateChange(plaidNewState_AliveCell);
var vAr117$plaid = plaidNewVar177;
var plaidNewVar178 = row + 1;
var plaidNewVar179 = col + 2;
var plaidNewVar180 = this$plaid.getCell(this$plaid.cells,plaidNewVar178,plaidNewVar179);
plaidNewVar180.stateChange(plaidNewState_AliveCell);
var vAr116$plaid = plaidNewVar180;
var plaidNewVar181 = row + 2;
var plaidNewVar182 = col + 2;
var plaidNewVar183 = this$plaid.getCell(this$plaid.cells,plaidNewVar181,plaidNewVar182);
plaidNewVar183.stateChange(plaidNewState_AliveCell);
return plaidNewVar183;
}
plaidNewState_Exploder['offExploder'] = function offExploder () {
var this$plaid=this;
var plaidNewVar184 = this$plaid.resetBoard();
return plaidNewVar184;
}
plaidNewState_Exploder.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with'], [['Exploder', ['onExploder', 'offExploder'], '']]]];
var plaidNewState_Spaceship = new PlaidState();
plaidNewState_Spaceship['init'] = plaidNewState_Board['init'];
plaidNewState_Spaceship['resetBoard'] = plaidNewState_Board['resetBoard'];
plaidNewState_Spaceship['update'] = plaidNewState_Board['update'];
plaidNewState_Spaceship['getAround'] = plaidNewState_Board['getAround'];
plaidNewState_Spaceship['getCell'] = plaidNewState_Board['getCell'];
plaidNewState_Spaceship['getRow'] = plaidNewState_Board['getRow'];
plaidNewState_Spaceship['getCol'] = plaidNewState_Board['getCol'];
plaidNewState_Spaceship['each'] = plaidNewState_Board['each'];
plaidNewState_Spaceship['onSpaceship'] = function onSpaceship () {
var this$plaid=this;
var plaidNewVar185 = this$plaid.height / 2;
var plaidNewVar186 = Math.floor(plaidNewVar185);
var row = plaidNewVar186;
var plaidNewVar187 = this$plaid.width / 2;
var plaidNewVar188 = Math.floor(plaidNewVar187);
var col = plaidNewVar188;
var plaidNewVar189 = row - 2;
var plaidNewVar190 = col - 1;
var plaidNewVar191 = this$plaid.getCell(this$plaid.cells,plaidNewVar189,plaidNewVar190);
plaidNewVar191.stateChange(plaidNewState_AliveCell);
var vAr134$plaid = plaidNewVar191;
var plaidNewVar192 = row - 2;
var plaidNewVar193 = this$plaid.getCell(this$plaid.cells,plaidNewVar192,col);
plaidNewVar193.stateChange(plaidNewState_AliveCell);
var vAr133$plaid = plaidNewVar193;
var plaidNewVar194 = row - 2;
var plaidNewVar195 = col + 1;
var plaidNewVar196 = this$plaid.getCell(this$plaid.cells,plaidNewVar194,plaidNewVar195);
plaidNewVar196.stateChange(plaidNewState_AliveCell);
var vAr132$plaid = plaidNewVar196;
var plaidNewVar197 = row - 2;
var plaidNewVar198 = col + 2;
var plaidNewVar199 = this$plaid.getCell(this$plaid.cells,plaidNewVar197,plaidNewVar198);
plaidNewVar199.stateChange(plaidNewState_AliveCell);
var vAr131$plaid = plaidNewVar199;
var plaidNewVar200 = row - 1;
var plaidNewVar201 = col - 2;
var plaidNewVar202 = this$plaid.getCell(this$plaid.cells,plaidNewVar200,plaidNewVar201);
plaidNewVar202.stateChange(plaidNewState_AliveCell);
var vAr130$plaid = plaidNewVar202;
var plaidNewVar203 = row - 1;
var plaidNewVar204 = col + 2;
var plaidNewVar205 = this$plaid.getCell(this$plaid.cells,plaidNewVar203,plaidNewVar204);
plaidNewVar205.stateChange(plaidNewState_AliveCell);
var vAr129$plaid = plaidNewVar205;
var plaidNewVar206 = col + 2;
var plaidNewVar207 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar206);
plaidNewVar207.stateChange(plaidNewState_AliveCell);
var vAr128$plaid = plaidNewVar207;
var plaidNewVar208 = row + 1;
var plaidNewVar209 = col - 2;
var plaidNewVar210 = this$plaid.getCell(this$plaid.cells,plaidNewVar208,plaidNewVar209);
plaidNewVar210.stateChange(plaidNewState_AliveCell);
var vAr127$plaid = plaidNewVar210;
var plaidNewVar211 = row + 1;
var plaidNewVar212 = col + 1;
var plaidNewVar213 = this$plaid.getCell(this$plaid.cells,plaidNewVar211,plaidNewVar212);
plaidNewVar213.stateChange(plaidNewState_AliveCell);
return plaidNewVar213;
}
plaidNewState_Spaceship['offSpaceship'] = function offSpaceship () {
var this$plaid=this;
var plaidNewVar214 = this$plaid.resetBoard();
return plaidNewVar214;
}
plaidNewState_Spaceship.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'each'], 'with'], [['Spaceship', ['onSpaceship', 'offSpaceship'], '']]]];
