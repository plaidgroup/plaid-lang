if(typeof require !== 'undefined') {
  var Plaid = require('Plaid.js').Plaid
}

describe("PlaidObject", function(){
	var target;
	var targetState;
	var objectToStateChange;
	var objectToStateChangeWithDuplicatedTag;
	var objectAfterStateChange;
	var objectAfterReplace;
	var objectWithDuplicatedMembers;
	var objectWithDuplicatedTags;
	var objectWithAdditionalMember;
	
	var members;
	var tags;
	
	beforeEach(function() {
		var treeBuilder = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),treeBuilder.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);
		target = new Plaid.PlaidObject(Plaid.clone(treeBuilder.toMetadata()));
		targetState = new Plaid.PlaidState();
		targetState.setMetadata(Plaid.clone(treeBuilder.toMetadata()));

		var treeBuilder2 = new Plaid.tree.Tree();
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),treeBuilder2.root);
		var braking = new Plaid.tree.CaseOfNode("notBraking",new Plaid.Array("startBraking"),brakingStatus);

		objectAfterReplace = new Plaid.PlaidObject(Plaid.clone(treeBuilder2.toMetadata()));

		var treeBuilder3 = new Plaid.tree.Tree();
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),treeBuilder3.root);
		var braking = new Plaid.tree.WithNode("notBraking",new Plaid.Array("startBraking"),brakingStatus);
		var turning = new Plaid.tree.WithNode("notBraking", new Plaid.Array(), brakingStatus);
		objectWithDuplicatedTags = new Plaid.PlaidObject(Plaid.clone(treeBuilder3.toMetadata()));

		var treeBuilder4 = new Plaid.tree.Tree();
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),treeBuilder4.root);
		var braking = new Plaid.tree.WithNode("notBraking",new Plaid.Array("startBraking"),brakingStatus);
		var turning = new Plaid.tree.WithNode("notBraking2", new Plaid.Array("startBraking"), brakingStatus);
		objectWithDuplicatedMembers = new Plaid.PlaidObject(Plaid.clone(treeBuilder4.toMetadata()));

		var treeBuilder5 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),treeBuilder5.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);
		var car = new Plaid.tree.WithNode("", new Plaid.Array("three"), treeBuilder5.root);
		objectWithAdditionalMember = new Plaid.PlaidObject(Plaid.clone(treeBuilder5.toMetadata()));

		var treeBuilder6 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car", new Plaid.Array(), treeBuilder6.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus", new Plaid.Array(), car);
		var driving = new Plaid.tree.CaseOfNode("driving", new Plaid.Array("speed","acceleration","stopDriving"), drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus", new Plaid.Array(), driving);
		var braking = new Plaid.tree.CaseOfNode("notBraking", new Plaid.Array("startBraking"), brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus", new Plaid.Array(), driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft", new Plaid.Array("straight","turnRight"),  directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus", new Plaid.Array(), car);
		var clean = new Plaid.tree.CaseOfNode("clean", new Plaid.Array("getDirty"), cleanStatus);
		objectAfterStateChange = new Plaid.PlaidObject(Plaid.clone(treeBuilder6.toMetadata()));

		var treeBuilder7 = new Plaid.tree.Tree();
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus", new Plaid.Array(), treeBuilder7.root);
		var braking = new Plaid.tree.CaseOfNode("notBraking", new Plaid.Array("startBraking"), brakingStatus);
		
		objectToStateChange = new Plaid.PlaidObject(Plaid.clone(treeBuilder7.toMetadata()));

		var treeBuilder8 = new Plaid.tree.Tree();
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus2", new Plaid.Array(), treeBuilder8.root);
		var braking = new Plaid.tree.CaseOfNode("braking", new Plaid.Array("stopBraking2"), brakingStatus);
		
		objectToStateChangeWithDuplicatedTag = new Plaid.PlaidObject(Plaid.clone(treeBuilder8.toMetadata()));


		members = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty");
		
		tags = new Plaid.Array('car', 'drivingStatus', 'driving', 'brakingStatus', 'braking', 'directionStatus', 'turningLeft', 'cleanStatus', 'clean');

	});

  it("should have appropriate prototype chain", function(){
		expect(target instanceof Plaid.AbstractPlaidBase).toBeTruthy();
		expect(target instanceof Plaid.AbstractPlaidObject).toBeTruthy();
		expect(target instanceof Plaid.PlaidObject).toBeTruthy();
	});

	describe("match", function(){
		it("should match when the given tag is include", function(){
			expect(target.match("turningLeft")).toBeTruthy();
		});

		it("should not match when the given tag is not included", function(){
			expect(target.match("turningLeft2")).toBeFalsy();
		});
	});

	describe("membersUnique", function(){
		it("should return all the members bundled with its tag", function(){
			var array = target.membersUnique();
			expect(array.length).toEqual(7);
			expect(array[0].tag).toEqual("driving");
			expect(array[0].name).toEqual("speed");

			expect(array[3].tag).toEqual("braking");
			expect(array[3].name).toEqual("stopBraking");

			expect(array[6].tag).toEqual("clean");
			expect(array[6].name).toEqual("getDirty");
		});
	});

	describe("replace", function(){
		beforeEach(function(){
			target.replace(objectAfterReplace);
		});

		it("should update object with correct metadata", function(){
			expect(target.getMetadata()).toEqual(objectAfterReplace.getMetadata());
		});

		it("should have appropriate members", function(){
			expect(target.members()).toHaveSameElements(objectAfterReplace.members());
		});

		it("should have appropriate tags", function(){
			expect(target.tags()).toHaveSameElements(objectAfterReplace.tags());
		});

		it("should throw error when the state violates the unique members", function(){
			expect(function(){
				target.replace(objectWithDuplicatedMembers);
			}).toThrow("Error: Replace operation violates unique members by attempting to change to a state that contains startBraking twice.");
		});

		it("should throw error when the state violates the unique tags", function(){
			expect(function() {
				target.replace(objectWithDuplicatedTags);
			}).toThrow("Error: Replace operation violates unique tags by containing tag notBraking twice");
		});
	});

	describe("freeze", function(){
		beforeEach(function(){
			target = target.freeze();
		});

		it("should produce PlaidState with correct metadata", function(){
			expect(target.getMetadata()).toHaveSameMetadata(targetState.getMetadata());
		});

		it("should produce PlaidState with correct members", function(){
			expect(target.members()).toHaveSameElements(targetState.members());
		});

		it("should produce PlaidState with corerct tags", function(){
			expect(target.tags()).toHaveSameElements(targetState.tags());
		});
	});

	describe("stateChange", function(){
		describe("normal case", function(){
			beforeEach(function(){
				target.stateChange(objectToStateChange);
			});

			it("should produce PlaidObject with correct metadata", function(){
				expect(target.getMetadata()).toHaveSameMetadata(objectAfterStateChange.getMetadata());
			});

			it("should produce PlaidObject with correct members", function(){
				expect(target.members()).toHaveSameElements(objectAfterStateChange.members());
			});

			it("should produce PlaidObject with correct tags", function(){
				expect(target.tags()).toHaveSameElements(objectAfterStateChange.tags());
			});
		});

		describe("error case", function(){
			it("should throw error if the state has duplicated members", function(){
				expect(function() {
					target.stateChange(objectWithDuplicatedMembers);
				}).toThrow("Error: State change violates unique members by attempting to change to a state that contains startBraking twice.");
			});

			it("should throw error if the state has duplicated tags", function(){
				expect(function() {
					target.stateChange(objectToStateChangeWithDuplicatedTag);
				}).toThrow("Error: state change violates unique tags by containing tag braking twice");
			});
		});			

		describe("test cases from old version", function() {
			var obj1;
			var state1;
			var state2;
			var state3;
			var state4;
			var state5;
			var state6;
			var basicA;
			var BCaseOfA;
			var DWithOfA;

			var treeBuilder=new Plaid.tree.Tree();
			var car = new Plaid.tree.WithNode("car", new Plaid.Array(), treeBuilder.root);
			var drivingStatus = new Plaid.tree.WithNode("drivingStatus", new Plaid.Array(), car);
			var driving = new Plaid.tree.CaseOfNode("driving", new Plaid.Array("speed","acceleration","stopDriving"), drivingStatus);
			var brakingStatus = new Plaid.tree.WithNode("brakingStatus", new Plaid.Array(), driving);
			var braking = new Plaid.tree.CaseOfNode("braking", new Plaid.Array("stopBraking"), brakingStatus);
			var directionStatus = new Plaid.tree.WithNode("directionStatus", new Plaid.Array(), driving);
			var turningLeft = new Plaid.tree.CaseOfNode("turningLeft", new Plaid.Array("straight","turnRight"),  directionStatus);
			var cleanStatus = new Plaid.tree.WithNode("cleanStatus", new Plaid.Array(), car);
			var clean = new Plaid.tree.CaseOfNode("clean", new Plaid.Array("getDirty"), cleanStatus);
			var md1 = treeBuilder.toMetadata();

			var treeBuilder2 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array(), treeBuilder2.root);
			var b = new Plaid.tree.CaseOfNode("b", new Plaid.Array("x"), a);
			var c = new Plaid.tree.CaseOfNode("c", new Plaid.Array("x"), b);
			var md2 = treeBuilder2.toMetadata();

			var treeBuilder3 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array("x"), treeBuilder3.root);
			var b = new Plaid.tree.CaseOfNode("b", new Plaid.Array(), a);
			var c = new Plaid.tree.CaseOfNode("c", new Plaid.Array("x"), b);
			var md3 = treeBuilder3.toMetadata();

			var treeBuilder4 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array(), treeBuilder4.root);
			var b = new Plaid.tree.CaseOfNode("b", new Plaid.Array("x"), a);
			var c = new Plaid.tree.WithNode("c", new Plaid.Array("x"), a);
			var md4 = treeBuilder4.toMetadata();

			var treeBuilder5 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array("x"), treeBuilder5.root);
			var b = new Plaid.tree.CaseOfNode("b", new Plaid.Array(), a);
			var c = new Plaid.tree.WithNode("c", new Plaid.Array("x"), a);
			var md5 = treeBuilder5.toMetadata();

			var treeBuilder6 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array(), treeBuilder6.root);
			var b = new Plaid.tree.WithNode("b", new Plaid.Array("x"), a);
			var c = new Plaid.tree.WithNode("c", new Plaid.Array("x"), a);
			var md6 = treeBuilder6.toMetadata();

			var treeBuilder7 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array("x"), treeBuilder7.root);
			var md7 = treeBuilder7.toMetadata();

			var treeBuilder8 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array("x"), treeBuilder8.root);
			var b = new Plaid.tree.CaseOfNode("b", new Plaid.Array("x"), a);
			var md8 = treeBuilder8.toMetadata();

			var treeBuilder9 = new Plaid.tree.Tree();
			var a = new Plaid.tree.WithNode("a", new Plaid.Array("x"), treeBuilder9.root);
			var b = new Plaid.tree.WithNode("c", new Plaid.Array("x"), a);
			var md9 = treeBuilder9.toMetadata();

			beforeEach(function() {
				obj1 = new Plaid.PlaidObject(Plaid.clone(md1));
				state2 = new Plaid.PlaidState();
				state2.setMetadata(Plaid.clone(md2));
				state3 = new Plaid.PlaidState();
				state3.setMetadata(Plaid.clone(md3));
				state4 = new Plaid.PlaidState();
				state4.setMetadata(Plaid.clone(md4));
				state5 = new Plaid.PlaidState();
				state5.setMetadata(Plaid.clone(md5));
				state6 = new Plaid.PlaidState();
				state6.setMetadata(Plaid.clone(md6));
				basicA = new Plaid.PlaidObject(Plaid.clone(md7));
				BCaseOfA = new Plaid.PlaidState();
				BCaseOfA.setMetadata(Plaid.clone(md8));
				DWithOfA = new Plaid.PlaidObject(Plaid.clone(md9));
			});

			it("should not throw an error if state change to a state with A{x}<:B{x}", function() { 
				expect(function() {
					obj1.stateChange(state2);
				}).not.toThrow();
			});
			it("should not throw an error if state change from B{x} <- A{x}<:B{x}", function() { 
				expect(function() {
					basicA.stateChange(BCaseOfA);
				}).not.toThrow();
			});
			it("should not throw an error if state change to a state with A{x}<:B{}<:C{x}", function() {  
				expect(function() {
					obj1.stateChange(state3);
				}).not.toThrow();
			});
			it("should throw an error if state change to a state with A{x}<:B{C{x}}", function() {   
				expect(function() {
					obj1.stateChange(state4);
				}).toThrow("Error: State change violates unique members by attempting to change to a state that contains x twice.");
			});
			it("should throw an error if state change from B{C{x}} <- A{x}<:B{C{x}}", function() { 
				expect(function() {
					DWithOfA.stateChange(BCaseOfA);
				}).toThrow("Error: state change violates unique members by attempting to add x to item that already contains x");
			});
			it("should throw an error if state change to a state with A{}<:B{x,C{x}}", function() {    
				expect(function() {
					obj1.stateChange(state5);
				}).toThrow("Error: State change violates unique members by attempting to change to a state that contains x twice.");
			});
			it("should throw an error if state change to a state with A{x} with B{x}", function() {    
				expect(function() {
					obj1.stateChange(state6);
				}).toThrow("Error: State change violates unique members by attempting to change to a state that contains x twice.");
			});
		});		
	});

	describe("stateChangeMember", function(){
		beforeEach(function(){
			target.stateChangeMember("three", 3);
		});

		it("should have correct metadata", function(){
			expect(target.getMetadata()).toHaveSameMetadata(objectWithAdditionalMember.getMetadata());
		});
		
		it("should have correct members", function(){
			expect(target.members()).toHaveSameElements(objectWithAdditionalMember.members());
		});

		it("should have correct value", function(){
			expect(target["three"]).toEqual(3);
		});
	});

	describe("stateChangeMemberNoValue", function(){
		beforeEach(function(){
			target.stateChangeMemberNoValue("three");
		});

		it("should have correct members", function(){
			expect(target.members()).toHaveSameElements(objectWithAdditionalMember.members());
		});
		
		it("should have value 'undefined'", function(){
			expect(target["three"]).toBeNull();
		});
	});

	describe("executeMethodChain", function(){
		var f1 = jasmine.createSpy().andReturn(true);
		var f2 = jasmine.createSpy().andReturn(true);
		var f3 = jasmine.createSpy().andReturn(false);
		var f4 = jasmine.createSpy().andReturn(true);
		var prepend;

		beforeEach(function(){
			prepend = target[Plaid.keyword].prepend;
			prepend["p1"] = new Plaid.Array(f1, f2);
			prepend["p2"] = new Plaid.Array(f1, f2, f3, f4);
		});

		it("should call method chain", function(){
			target.executeMethodChain("p1");
			
			expect(f1).toHaveBeenCalled();
			expect(f2).toHaveBeenCalled();
		});

		it("should stop calling when a method returns false", function(){
			target.executeMethodChain("p2");
			
			expect(f1).toHaveBeenCalled();
			expect(f2).toHaveBeenCalled();
			expect(f3).toHaveBeenCalled();
			expect(f4).not.toHaveBeenCalled();
		});
	});
});
