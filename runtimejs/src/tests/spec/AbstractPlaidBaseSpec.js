if(typeof require !== 'undefined') {
  var Plaid = require('Plaid.js').Plaid
}

describe("AbstractPlaidBase", function(){
	var target;
  var members;
	var tags;

	beforeEach(function(){
		var tree1 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),tree1.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);

		target = new Plaid.PlaidState();
		target.setMetadata(Plaid.clone(tree1.toMetadata()));

		members = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty");

		tags = new Plaid.Array('car', 'drivingStatus', 'driving', 'brakingStatus', 'braking', 'directionStatus', 'turningLeft', 'cleanStatus', 'clean');
	});

 	describe("members", function(){
		it("should return all members of the state", function(){
			expect(target.members()).toHaveSameElements(members);			
		});
	});

	describe("tags", function(){
		it("should return all tags of the state", function(){
			expect(target.tags()).toHaveSameElements(tags);
		});
	});

	describe("addMember", function(){
		it("should add member", function(){
			expect(target.a).not.toBeDefined();
			target.addMember("a", "a");
			expect(target.a).toBeDefined();
			expect(target.a).toEqual("a");
		});
	});

	describe("copyMembers", function(){
		var state;
		beforeEach(function(){
			state = new Plaid.PlaidState();
			state.addMember("a", 1);
			state.addMember("b", "1");
			state.addMember("f" ,function(){});
		});

		it("should copy all the members", function(){
			expect(target.a).not.toBeDefined();
			expect(target.b).not.toBeDefined();
			expect(target.f).not.toBeDefined();

			target.copyMembers(state);

			expect(target.a).toBeDefined();
			expect(target.a).toEqual(state.a);
			expect(target.b).toBeDefined();
			expect(target.b).toEqual(state.b);
			expect(target.f).toBeDefined();
			expect(target.f).toEqual(state.f);
		});

		it("should have concatinated arrays of callbacks", function(){
			var f1 = function(){};
			var f2 = function(){};
			var f3 = function(){};
			var f4 = function(){};
			var f5 = function(){};
			var f6 = function(){};

			target[Plaid.keyword].prepend.a = new Plaid.Array(f1);
			target[Plaid.keyword].prepend.b = new Plaid.Array(f2, f3);
			state[Plaid.keyword].prepend.a = new Plaid.Array(f4, f5);
			state[Plaid.keyword].prepend.c = new Plaid.Array(f6);

			target.copyMembers(state);

			expect(target[Plaid.keyword].prepend).toBeDefined();
			expect(target[Plaid.keyword].prepend.a).toHaveSameElements(new Plaid.Array(f4, f5, f1));
			expect(target[Plaid.keyword].prepend.b).toHaveSameElements(new Plaid.Array(f2, f3));
			expect(target[Plaid.keyword].prepend.c).toHaveSameElements(new Plaid.Array(f6));
		});
	});

	describe("checkUniqueMembers", function(){
		it("should not throw error when the object doesn't have duplicated members", function(){
			expect(function() {
				target.checkUniqueMembers("duplicated member : $1");
			}).not.toThrow();
		});

		it("should throw error when the object has duplicated members", function(){
			var treeWithDuplicatedMembers = new Plaid.tree.Tree();
			new Plaid.tree.WithNode("a", new Plaid.Array("tag"), treeWithDuplicatedMembers.root);
			new Plaid.tree.WithNode("b", new Plaid.Array("tag"), treeWithDuplicatedMembers.root);
			
			target = new Plaid.PlaidState();
			target.setMetadata(treeWithDuplicatedMembers.toMetadata());

			expect(function() {
				target.checkUniqueMembers("duplicated member : $1");
			}).toThrow("duplicated member : tag");
		});
	});

	describe("membersHierarchy", function(){
		it("should return all the members bundled with tag hierarchy ", function(){
			var array = target.membersHierarchy(new Plaid.Array());
			expect(array.length).toEqual(7);
			expect(array[0].hierarchy).toHaveSameElements(new Plaid.Array("drivingStatus", "driving"));
			expect(array[0].name).toEqual("speed");

			expect(array[3].hierarchy).toHaveSameElements(new Plaid.Array("brakingStatus", "braking"));
			expect(array[3].name).toEqual("stopBraking");

			expect(array[6].hierarchy).toHaveSameElements(new Plaid.Array("cleanStatus", "clean"));
			expect(array[6].name).toEqual("getDirty");
		});
	});

	describe("checkUniqueTags", function(){
		it("should not throw error when the object doesn't have duplicated tags", function(){
			expect(function() {
				target.checkUniqueTags("duplicated tag : $1");
			}).not.toThrow();
		});

		it("should throw error when the object has duplicated tags", function(){
			var treeWithDuplicatedTags = new Plaid.tree.Tree();
			new Plaid.tree.WithNode("a", new Plaid.Array(), treeWithDuplicatedTags.root);
			new Plaid.tree.WithNode("a", new Plaid.Array(), treeWithDuplicatedTags.root);

			target = new Plaid.PlaidState();
			target.setMetadata(treeWithDuplicatedTags.toMetadata());
			
			expect(function() {
				target.checkUniqueTags("duplicated tag : $1");
			}).toThrow("duplicated tag : a");
		});
	});
});

