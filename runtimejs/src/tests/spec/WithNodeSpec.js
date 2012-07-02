if(typeof require !== 'undefined') {
  var Plaid = require('Plaid.js').Plaid
}

describe("WithNode", function(){
  var target = new Plaid.tree.WithNode();

	it("should have prototype chain", function(){
		expect(target instanceof Plaid.tree.AbstractNode).toBeTruthy();
		expect(target instanceof Plaid.tree.WithNode).toBeTruthy();
	});
});
