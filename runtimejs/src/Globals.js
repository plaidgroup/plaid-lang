//True and False states
var plaidNewState_True = new Object();
var plaidNewState_False = new Object();
function returnTrue() {return true;}
function returnFalse() {return false;}
plaidNewState_True.instantiate = returnTrue;
plaidNewState_False.instantiate = returnFalse;

//None state
var plaidNewState_None = new Object();
function returnNull() {return null;}
plaidNewState_None.instantiate = returnNull;

//Array state
var plaidNewState_Array = new Object();
function returnArray() {return [];}
plaidNewState_Array.instantiate = returnArray;
//Anything declared in Plaid as an Array should be able to use all the methods available to JavaScript arrays
Array.prototype.get = function(i){
	return this[i];
}
Array.prototype.set = function(i, value){
	this[i]=value;
}

//if function
var if$plaid = function(bool) {
	if (bool===true) {
		var plaidNewVar4 = function(func) {
			var plaidNewVar5 = func();
			return plaidNewVar5;
		};
		var returnVal=plaidNewVar4;
	}
	else if (bool===false) {
		var plaidNewVar6 = function(func) {
			return undefined;
		};
		var returnVal=plaidNewVar6;
	}
	return returnVal;
};

//ifElse function
var ifElse = function(bool) {
	if (bool===true) {
		var plaidNewVar8 = function(trueClause) {
			var plaidNewVar9 = function(falseClause) {
				var plaidNewVar10 = trueClause();
				return plaidNewVar10;
			};
			return plaidNewVar9;
		};
		var returnVal = plaidNewVar8;
	}
	else if (bool===false) {
		var plaidNewVar11 = function(trueClause) {
			var plaidNewVar12 = function(falseClause) {
				var plaidNewVar13 = falseClause();
				return plaidNewVar13;
			};
			return plaidNewVar12;
		};
		var returnVal = plaidNewVar11;
	}
	return returnVal;
};

//while function

var plaidNewState_While = new PlaidState();
	plaidNewState_While['whileLoop'] = function whileLoop (result) {
		var plaidNewVar1 = this.condition();
		var test = plaidNewVar1;
		var matchVar = test;
		if (matchVar==true) {
			var plaidNewVar2 = this.whileBody();
			var newResult = plaidNewVar2;
			var plaidNewVar3 = this.whileLoop(newResult);
			var returnVal = plaidNewVar3;
		}
		else if (matchVar==false) {
			var returnVal = result;
		}
		return returnVal;
	}
	plaidNewState_While.tree = [['', [], 'with'], [['While', ['whileLoop'], 'with']]];

var while$plaid = function(cond) {
	var plaidNewVar15 = function(body) {
		var condition = cond;
		var whileBody = body;
		var plaidNewVar17 = plaidNewState_While.specialize('While', 'condition', condition).specialize('While', 'whileBody', whileBody).instantiate();
		var plaidNewVar16 = plaidNewVar17.whileLoop();
		return plaidNewVar16;
	};
	return plaidNewVar15;
};
