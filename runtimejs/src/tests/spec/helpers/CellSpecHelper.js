function makeArray(alive, dead) {
	var array = new Plaid.Array();
	for(var i = 0; i < alive; i++){
		array.push(Plaid.clone(plaidNewState_AliveCell.instantiate()));
	}
	for(var i = 0; i < dead; i++){
		array.push(Plaid.clone(plaidNewState_DeadCell.instantiate()));
	}
	return array;
}

beforeEach(function() {
	this.addMatchers({
		onState: function(testArr) {
			if(this.actual.match(testArr)){
				return true;
			}
			return false;
		}
	});
});
