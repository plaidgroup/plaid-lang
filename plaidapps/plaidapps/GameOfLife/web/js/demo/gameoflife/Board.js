var plaidNewState_Board = new PlaidState();
plaidNewState_Board['init'] = function init () {
var this$plaid=this;
var plaidNewVar1 = this$plaid.createArray();
this$plaid.cells = plaidNewVar1;
var vAr79$plaid = this$plaid.cells;
var plaidNewVar2 = this$plaid.resetBoard();
return plaidNewVar2;
}
plaidNewState_Board['resetBoard'] = function resetBoard () {
var this$plaid=this;
var plaidNewVar3 = "#" + this$plaid.id;
var plaidNewVar4 = jQuery(plaidNewVar3);
var elm = plaidNewVar4;
var plaidNewVar5 = elm.html("");
var vAr90$plaid = plaidNewVar5;
var plaidNewVar7 = jQuery("<div />");
var plaidNewVar6 = plaidNewVar7.addClass("cell");
var deadCell = plaidNewVar6;
var plaidNewVar8 = jQuery("<br />");
var lineBreak = plaidNewVar8;
var sepalater = "_";
var plaidNewVar9 = this$plaid.cells.clear();
var vAr89$plaid = plaidNewVar9;
var i = 0;
var plaidNewVar10 = function(vAr88$plaid) {
var j = 0;
var plaidNewVar11 = function(vAr85$plaid) {
var plaidNewVar15 = "cell" + this$plaid.sepalater;
var plaidNewVar14 = plaidNewVar15 + i;
var plaidNewVar13 = plaidNewVar14 + this$plaid.sepalater;
var plaidNewVar12 = plaidNewVar13 + j;
var cellId = plaidNewVar12;
var plaidNewVar17 = deadCell.clone();
var plaidNewVar16 = plaidNewVar17.attr("id",cellId);
var plaidNewVar18 = elm.append(plaidNewVar16);
var vAr84$plaid = plaidNewVar18;
var id = cellId;
var plaidNewVar19 = (plaidNewState_Box.specialize('Box', 'id', id).with(plaidNewState_Cell).with(plaidNewState_Population)).instantiate();
var cell = plaidNewVar19;
cell.stateChange(plaidNewState_Unpopulated);
var vAr83$plaid = cell;
var plaidNewVar20 = this$plaid.cells.push(cell);
var vAr82$plaid = plaidNewVar20;
var plaidNewVar21 = j + 1;
j = plaidNewVar21;
return j;
};
var plaidNewVar22 = function(vAr81$plaid) {
var plaidNewVar23 = j < this$plaid.width;
return plaidNewVar23;
};
var plaidNewVar24 = while$plaid(plaidNewVar22);
var plaidNewVar25 = plaidNewVar24(plaidNewVar11);
var vAr87$plaid = plaidNewVar25;
var plaidNewVar26 = lineBreak.clone();
var plaidNewVar27 = elm.append(plaidNewVar26);
var vAr86$plaid = plaidNewVar27;
var plaidNewVar28 = i + 1;
i = plaidNewVar28;
return i;
};
var plaidNewVar29 = function(vAr80$plaid) {
var plaidNewVar30 = i < this$plaid.height;
return plaidNewVar30;
};
var plaidNewVar31 = while$plaid(plaidNewVar29);
var plaidNewVar32 = plaidNewVar31(plaidNewVar10);
return plaidNewVar32;
}
plaidNewState_Board['update'] = function update () {
var this$plaid=this;
var plaidNewVar33 = this$plaid.cells.clone();
var previousCells = plaidNewVar33;
var plaidNewVar34 = function(cell,i) {
var plaidNewVar35 = this$plaid.getAround(previousCells,i);
var plaidNewVar36 = cell.update(plaidNewVar35);
return plaidNewVar36;
};
var plaidNewVar37 = this$plaid.each(plaidNewVar34);
return plaidNewVar37;
}
plaidNewState_Board['getAround'] = function getAround (previousCells,idx) {
var this$plaid=this;
var plaidNewVar38 = this$plaid.createArray();
var around = plaidNewVar38;
var plaidNewVar39 = this$plaid.getRow(idx);
var r = plaidNewVar39;
var plaidNewVar40 = this$plaid.getCol(idx);
var c = plaidNewVar40;
var i = -1;
var plaidNewVar41 = function(vAr97$plaid) {
var j = -1;
var plaidNewVar42 = function(vAr95$plaid) {
var plaidNewVar43 = function(vAr93$plaid) {
var plaidNewVar44 = r + i;
var plaidNewVar45 = c + j;
var plaidNewVar46 = this$plaid.getCell(previousCells,plaidNewVar44,plaidNewVar45);
var plaidNewVar47 = around.push(plaidNewVar46);
return plaidNewVar47;
};
var plaidNewVar48 = j != 0;
var plaidNewVar50 = i != 0;
var plaidNewVar49 = plaidNewVar50 || plaidNewVar48;
var plaidNewVar51 = if$plaid(plaidNewVar49);
var plaidNewVar52 = plaidNewVar51(plaidNewVar43);
var vAr94$plaid = plaidNewVar52;
var plaidNewVar53 = j + 1;
j = plaidNewVar53;
return j;
};
var plaidNewVar54 = function(vAr92$plaid) {
var plaidNewVar55 = j <= 1;
return plaidNewVar55;
};
var plaidNewVar56 = while$plaid(plaidNewVar54);
var plaidNewVar57 = plaidNewVar56(plaidNewVar42);
var vAr96$plaid = plaidNewVar57;
var plaidNewVar58 = i + 1;
i = plaidNewVar58;
return i;
};
var plaidNewVar59 = function(vAr91$plaid) {
var plaidNewVar60 = i <= 1;
return plaidNewVar60;
};
var plaidNewVar61 = while$plaid(plaidNewVar59);
var plaidNewVar62 = plaidNewVar61(plaidNewVar41);
var vAr98$plaid = plaidNewVar62;
return around;
}
plaidNewState_Board['getCell'] = function getCell (cells,row,col) {
var this$plaid=this;
var plaidNewVar63 = function(vAr100$plaid) {
var plaidNewVar64 = plaidNewState_NilCell.instantiate();
return plaidNewVar64;
};
var plaidNewVar65 = function(vAr99$plaid) {
var plaidNewVar67 = row * this$plaid.width;
var plaidNewVar66 = plaidNewVar67 + col;
var plaidNewVar68 = cells.get(plaidNewVar66);
return plaidNewVar68;
};
var plaidNewVar69 = col < this$plaid.height;
var plaidNewVar71 = 0 <= col;
var plaidNewVar73 = row < this$plaid.width;
var plaidNewVar75 = 0 <= row;
var plaidNewVar74 = plaidNewVar75 && plaidNewVar73;
var plaidNewVar72 = plaidNewVar74 && plaidNewVar71;
var plaidNewVar70 = plaidNewVar72 && plaidNewVar69;
var plaidNewVar76 = ifElse(plaidNewVar70);
var plaidNewVar77 = plaidNewVar76(plaidNewVar65);
var plaidNewVar78 = plaidNewVar77(plaidNewVar63);
return plaidNewVar78;
}
plaidNewState_Board['getRow'] = function getRow (idx) {
var this$plaid=this;
var plaidNewVar79 = idx / this$plaid.width;
var plaidNewVar80 = Math.floor(plaidNewVar79);
return plaidNewVar80;
}
plaidNewState_Board['getCol'] = function getCol (idx) {
var this$plaid=this;
var plaidNewVar82 = idx / this$plaid.width;
var plaidNewVar83 = Math.floor(plaidNewVar82);
var plaidNewVar81 = plaidNewVar83 * this$plaid.width;
var plaidNewVar84 = idx - plaidNewVar81;
return plaidNewVar84;
}
plaidNewState_Board['createArray'] = function createArray () {
var this$plaid=this;
var plaidNewVar85 = jQuery.makeArray("");
var array = plaidNewVar85;
var length = array.length;
var plaidNewVar86 = function(vAr103$plaid) {
var plaidNewVar87 = array.pop();
var vAr102$plaid = plaidNewVar87;
var plaidNewVar88 = length - 1;
length = plaidNewVar88;
return length;
};
var plaidNewVar89 = function(vAr101$plaid) {
var plaidNewVar90 = length > 0;
return plaidNewVar90;
};
var plaidNewVar91 = while$plaid(plaidNewVar89);
var plaidNewVar92 = plaidNewVar91(plaidNewVar86);
var vAr104$plaid = plaidNewVar92;
return array;
}
plaidNewState_Board['each'] = function each (f) {
var this$plaid=this;
var i = 0;
var plaidNewVar93 = function(vAr107$plaid) {
var plaidNewVar94 = this$plaid.cells.get(i);
var plaidNewVar95 = f(plaidNewVar94,i);
var vAr106$plaid = plaidNewVar95;
var plaidNewVar96 = i + 1;
i = plaidNewVar96;
return i;
};
var plaidNewVar97 = function(vAr105$plaid) {
var plaidNewVar98 = i < this$plaid.cells.length;
return plaidNewVar98;
};
var plaidNewVar99 = while$plaid(plaidNewVar97);
var plaidNewVar100 = plaidNewVar99(plaidNewVar93);
return plaidNewVar100;
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
var plaidNewVar101 = this$plaid.height / 2;
var plaidNewVar102 = Math.floor(plaidNewVar101);
var row = plaidNewVar102;
var plaidNewVar103 = this$plaid.width / 2;
var plaidNewVar104 = Math.floor(plaidNewVar103);
var right = plaidNewVar104;
var plaidNewVar105 = right - 1;
var left = plaidNewVar105;
var i = 0;
var plaidNewVar106 = function(vAr111$plaid) {
var plaidNewVar107 = left - i;
var plaidNewVar108 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar107);
plaidNewVar108.stateChange(plaidNewState_Populated);
var vAr110$plaid = plaidNewVar108;
var plaidNewVar109 = right + i;
var plaidNewVar110 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar109);
plaidNewVar110.stateChange(plaidNewState_Populated);
var vAr109$plaid = plaidNewVar110;
var plaidNewVar111 = i + 1;
i = plaidNewVar111;
return i;
};
var plaidNewVar112 = function(vAr108$plaid) {
var plaidNewVar113 = i < 5;
return plaidNewVar113;
};
var plaidNewVar114 = while$plaid(plaidNewVar112);
var plaidNewVar115 = plaidNewVar114(plaidNewVar106);
return plaidNewVar115;
}
plaidNewState_TenCellRow['offTenCellRow'] = function offTenCellRow () {
var this$plaid=this;
var plaidNewVar116 = this$plaid.resetBoard();
return plaidNewVar116;
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
var plaidNewVar117 = this$plaid.height / 2;
var plaidNewVar118 = Math.floor(plaidNewVar117);
var row = plaidNewVar118;
var plaidNewVar119 = this$plaid.width / 2;
var plaidNewVar120 = Math.floor(plaidNewVar119);
var col = plaidNewVar120;
var plaidNewVar121 = row - 1;
var plaidNewVar122 = this$plaid.getCell(this$plaid.cells,plaidNewVar121,col);
plaidNewVar122.stateChange(plaidNewState_Populated);
var vAr115$plaid = plaidNewVar122;
var plaidNewVar123 = col + 1;
var plaidNewVar124 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar123);
plaidNewVar124.stateChange(plaidNewState_Populated);
var vAr114$plaid = plaidNewVar124;
var plaidNewVar125 = row + 1;
var plaidNewVar126 = col - 1;
var plaidNewVar127 = this$plaid.getCell(this$plaid.cells,plaidNewVar125,plaidNewVar126);
plaidNewVar127.stateChange(plaidNewState_Populated);
var vAr113$plaid = plaidNewVar127;
var plaidNewVar128 = row + 1;
var plaidNewVar129 = this$plaid.getCell(this$plaid.cells,plaidNewVar128,col);
plaidNewVar129.stateChange(plaidNewState_Populated);
var vAr112$plaid = plaidNewVar129;
var plaidNewVar130 = row + 1;
var plaidNewVar131 = col + 1;
var plaidNewVar132 = this$plaid.getCell(this$plaid.cells,plaidNewVar130,plaidNewVar131);
plaidNewVar132.stateChange(plaidNewState_Populated);
return plaidNewVar132;
}
plaidNewState_Glider['offGlider'] = function offGlider () {
var this$plaid=this;
var plaidNewVar133 = this$plaid.resetBoard();
return plaidNewVar133;
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
var plaidNewVar134 = this$plaid.height / 2;
var plaidNewVar135 = Math.floor(plaidNewVar134);
var row = plaidNewVar135;
var plaidNewVar136 = this$plaid.width / 2;
var plaidNewVar137 = Math.floor(plaidNewVar136);
var col = plaidNewVar137;
var plaidNewVar138 = row - 1;
var plaidNewVar139 = this$plaid.getCell(this$plaid.cells,plaidNewVar138,col);
plaidNewVar139.stateChange(plaidNewState_Populated);
var vAr121$plaid = plaidNewVar139;
var plaidNewVar140 = col - 1;
var plaidNewVar141 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar140);
plaidNewVar141.stateChange(plaidNewState_Populated);
var vAr120$plaid = plaidNewVar141;
var plaidNewVar142 = this$plaid.getCell(this$plaid.cells,row,col);
plaidNewVar142.stateChange(plaidNewState_Populated);
var vAr119$plaid = plaidNewVar142;
var plaidNewVar143 = col + 1;
var plaidNewVar144 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar143);
plaidNewVar144.stateChange(plaidNewState_Populated);
var vAr118$plaid = plaidNewVar144;
var plaidNewVar145 = row + 1;
var plaidNewVar146 = col - 1;
var plaidNewVar147 = this$plaid.getCell(this$plaid.cells,plaidNewVar145,plaidNewVar146);
plaidNewVar147.stateChange(plaidNewState_Populated);
var vAr117$plaid = plaidNewVar147;
var plaidNewVar148 = row + 1;
var plaidNewVar149 = col + 1;
var plaidNewVar150 = this$plaid.getCell(this$plaid.cells,plaidNewVar148,plaidNewVar149);
plaidNewVar150.stateChange(plaidNewState_Populated);
var vAr116$plaid = plaidNewVar150;
var plaidNewVar151 = row + 2;
var plaidNewVar152 = this$plaid.getCell(this$plaid.cells,plaidNewVar151,col);
plaidNewVar152.stateChange(plaidNewState_Populated);
return plaidNewVar152;
}
plaidNewState_SmallExploder['offSmallExploder'] = function offSmallExploder () {
var this$plaid=this;
var plaidNewVar153 = this$plaid.resetBoard();
return plaidNewVar153;
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
var plaidNewVar154 = this$plaid.height / 2;
var plaidNewVar155 = Math.floor(plaidNewVar154);
var row = plaidNewVar155;
var plaidNewVar156 = this$plaid.width / 2;
var plaidNewVar157 = Math.floor(plaidNewVar156);
var col = plaidNewVar157;
var plaidNewVar158 = row - 2;
var plaidNewVar159 = this$plaid.getCell(this$plaid.cells,plaidNewVar158,col);
plaidNewVar159.stateChange(plaidNewState_Populated);
var vAr132$plaid = plaidNewVar159;
var plaidNewVar160 = row + 2;
var plaidNewVar161 = this$plaid.getCell(this$plaid.cells,plaidNewVar160,col);
plaidNewVar161.stateChange(plaidNewState_Populated);
var vAr131$plaid = plaidNewVar161;
var plaidNewVar162 = row - 2;
var plaidNewVar163 = col - 2;
var plaidNewVar164 = this$plaid.getCell(this$plaid.cells,plaidNewVar162,plaidNewVar163);
plaidNewVar164.stateChange(plaidNewState_Populated);
var vAr130$plaid = plaidNewVar164;
var plaidNewVar165 = row - 1;
var plaidNewVar166 = col - 2;
var plaidNewVar167 = this$plaid.getCell(this$plaid.cells,plaidNewVar165,plaidNewVar166);
plaidNewVar167.stateChange(plaidNewState_Populated);
var vAr129$plaid = plaidNewVar167;
var plaidNewVar168 = col - 2;
var plaidNewVar169 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar168);
plaidNewVar169.stateChange(plaidNewState_Populated);
var vAr128$plaid = plaidNewVar169;
var plaidNewVar170 = row + 1;
var plaidNewVar171 = col - 2;
var plaidNewVar172 = this$plaid.getCell(this$plaid.cells,plaidNewVar170,plaidNewVar171);
plaidNewVar172.stateChange(plaidNewState_Populated);
var vAr127$plaid = plaidNewVar172;
var plaidNewVar173 = row + 2;
var plaidNewVar174 = col - 2;
var plaidNewVar175 = this$plaid.getCell(this$plaid.cells,plaidNewVar173,plaidNewVar174);
plaidNewVar175.stateChange(plaidNewState_Populated);
var vAr126$plaid = plaidNewVar175;
var plaidNewVar176 = row - 2;
var plaidNewVar177 = col + 2;
var plaidNewVar178 = this$plaid.getCell(this$plaid.cells,plaidNewVar176,plaidNewVar177);
plaidNewVar178.stateChange(plaidNewState_Populated);
var vAr125$plaid = plaidNewVar178;
var plaidNewVar179 = row - 1;
var plaidNewVar180 = col + 2;
var plaidNewVar181 = this$plaid.getCell(this$plaid.cells,plaidNewVar179,plaidNewVar180);
plaidNewVar181.stateChange(plaidNewState_Populated);
var vAr124$plaid = plaidNewVar181;
var plaidNewVar182 = col + 2;
var plaidNewVar183 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar182);
plaidNewVar183.stateChange(plaidNewState_Populated);
var vAr123$plaid = plaidNewVar183;
var plaidNewVar184 = row + 1;
var plaidNewVar185 = col + 2;
var plaidNewVar186 = this$plaid.getCell(this$plaid.cells,plaidNewVar184,plaidNewVar185);
plaidNewVar186.stateChange(plaidNewState_Populated);
var vAr122$plaid = plaidNewVar186;
var plaidNewVar187 = row + 2;
var plaidNewVar188 = col + 2;
var plaidNewVar189 = this$plaid.getCell(this$plaid.cells,plaidNewVar187,plaidNewVar188);
plaidNewVar189.stateChange(plaidNewState_Populated);
return plaidNewVar189;
}
plaidNewState_Exploder['offExploder'] = function offExploder () {
var this$plaid=this;
var plaidNewVar190 = this$plaid.resetBoard();
return plaidNewVar190;
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
var plaidNewVar191 = this$plaid.height / 2;
var plaidNewVar192 = Math.floor(plaidNewVar191);
var row = plaidNewVar192;
var plaidNewVar193 = this$plaid.width / 2;
var plaidNewVar194 = Math.floor(plaidNewVar193);
var col = plaidNewVar194;
var plaidNewVar195 = row - 2;
var plaidNewVar196 = col - 1;
var plaidNewVar197 = this$plaid.getCell(this$plaid.cells,plaidNewVar195,plaidNewVar196);
plaidNewVar197.stateChange(plaidNewState_Populated);
var vAr140$plaid = plaidNewVar197;
var plaidNewVar198 = row - 2;
var plaidNewVar199 = this$plaid.getCell(this$plaid.cells,plaidNewVar198,col);
plaidNewVar199.stateChange(plaidNewState_Populated);
var vAr139$plaid = plaidNewVar199;
var plaidNewVar200 = row - 2;
var plaidNewVar201 = col + 1;
var plaidNewVar202 = this$plaid.getCell(this$plaid.cells,plaidNewVar200,plaidNewVar201);
plaidNewVar202.stateChange(plaidNewState_Populated);
var vAr138$plaid = plaidNewVar202;
var plaidNewVar203 = row - 2;
var plaidNewVar204 = col + 2;
var plaidNewVar205 = this$plaid.getCell(this$plaid.cells,plaidNewVar203,plaidNewVar204);
plaidNewVar205.stateChange(plaidNewState_Populated);
var vAr137$plaid = plaidNewVar205;
var plaidNewVar206 = row - 1;
var plaidNewVar207 = col - 2;
var plaidNewVar208 = this$plaid.getCell(this$plaid.cells,plaidNewVar206,plaidNewVar207);
plaidNewVar208.stateChange(plaidNewState_Populated);
var vAr136$plaid = plaidNewVar208;
var plaidNewVar209 = row - 1;
var plaidNewVar210 = col + 2;
var plaidNewVar211 = this$plaid.getCell(this$plaid.cells,plaidNewVar209,plaidNewVar210);
plaidNewVar211.stateChange(plaidNewState_Populated);
var vAr135$plaid = plaidNewVar211;
var plaidNewVar212 = col + 2;
var plaidNewVar213 = this$plaid.getCell(this$plaid.cells,row,plaidNewVar212);
plaidNewVar213.stateChange(plaidNewState_Populated);
var vAr134$plaid = plaidNewVar213;
var plaidNewVar214 = row + 1;
var plaidNewVar215 = col - 2;
var plaidNewVar216 = this$plaid.getCell(this$plaid.cells,plaidNewVar214,plaidNewVar215);
plaidNewVar216.stateChange(plaidNewState_Populated);
var vAr133$plaid = plaidNewVar216;
var plaidNewVar217 = row + 1;
var plaidNewVar218 = col + 1;
var plaidNewVar219 = this$plaid.getCell(this$plaid.cells,plaidNewVar217,plaidNewVar218);
plaidNewVar219.stateChange(plaidNewState_Populated);
return plaidNewVar219;
}
plaidNewState_Spaceship['offSpaceship'] = function offSpaceship () {
var this$plaid=this;
var plaidNewVar220 = this$plaid.resetBoard();
return plaidNewVar220;
}
plaidNewState_Spaceship.tree = [['', [], 'with'], [['Board', ['init', 'resetBoard', 'update', 'getAround', 'getCell', 'getRow', 'getCol', 'createArray', 'each'], 'with'], [['Spaceship', ['onSpaceship', 'offSpaceship'], '']]]];
