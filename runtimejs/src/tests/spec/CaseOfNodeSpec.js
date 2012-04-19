describe("CaseOfNode", function(){
  var target = new Plaid.tree.CaseOfNode();

	it("should have prototype chain", function(){
		expect(target instanceof Plaid.tree.AbstractNode).toBeTruthy();
		expect(target instanceof Plaid.tree.CaseOfNode).toBeTruthy();
	});
});