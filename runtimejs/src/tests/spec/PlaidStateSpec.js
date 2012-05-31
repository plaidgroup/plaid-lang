describe("PlaidState", function(){
	var target;
	var stateAfterRemove;
	var stateAfterRename;
	var stateAfterSpecialize;
	var stateAdditional;
	var stateAfterWith;
	var objectCreatedWithTree;
  var members;
	var membersAfterRemove;
	var membersAfterRename;
	var membersAfterSpecialize;
	var membersAfterWithMember;

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
		objectCreatedWithTree = new Plaid.PlaidObject(Plaid.clone(tree1.toMetadata()));


		var tree2 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),tree2.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array(),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);

		stateAfterRemove = new Plaid.PlaidState();
		stateAfterRemove.setMetadata(Plaid.clone(tree2.toMetadata()));

		
		var tree3 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),tree3.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking2"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);

		stateAfterRename = new Plaid.PlaidState();
		stateAfterRename.setMetadata(Plaid.clone(tree3.toMetadata()));


		var tree4 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),tree4.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking", "hi"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);

		stateAfterSpecialize = new Plaid.PlaidState();
		stateAfterSpecialize.setMetadata(Plaid.clone(tree4.toMetadata()));


		var tree5=new Plaid.tree.Tree();
		var trailer = new Plaid.tree.WithNode("trailer", new Plaid.Array(), tree5.root);
		var twoWheeled = new Plaid.tree.CaseOfNode("twoWheeled", new Plaid.Array("addWheels"),trailer);

		stateAdditional = new Plaid.PlaidState();
		stateAdditional.setMetadata(Plaid.clone(tree5.toMetadata()));


		var tree6 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car", new Plaid.Array(), tree6.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus", new Plaid.Array(), car);
		var driving = new Plaid.tree.CaseOfNode("driving", new Plaid.Array("speed","acceleration","stopDriving"), drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus", new Plaid.Array(), driving);
		var braking = new Plaid.tree.CaseOfNode("braking", new Plaid.Array("stopBraking"), brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus", new Plaid.Array(), driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft", new Plaid.Array("straight","turnRight"),  directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus", new Plaid.Array(), car);
		var clean = new Plaid.tree.CaseOfNode("clean", new Plaid.Array("getDirty"), cleanStatus);
		var trailer = new Plaid.tree.WithNode("trailer", new Plaid.Array(), tree6.root);
		var twoWheeled = new Plaid.tree.CaseOfNode("twoWheeled", new Plaid.Array("addWheels"), trailer);

		stateAfterWith = new Plaid.PlaidState();
		stateAfterWith.setMetadata(Plaid.clone(tree6.toMetadata()));		


		members = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty");
		membersAfterRemove = new Plaid.Array("speed","acceleration", "stopDriving","straight","turnRight","getDirty");
		membersAfterRename = new Plaid.Array("speed","acceleration","stopDriving","stopBraking2","straight","turnRight","getDirty");
		membersAfterWithMember = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty", "speed2");		
	});

	it("should have prototype chain", function(){
		expect(target instanceof Plaid.AbstractPlaidBase).toBeTruthy();
		expect(target instanceof Plaid.AbstractPlaidState).toBeTruthy();
		expect(target instanceof Plaid.PlaidState).toBeTruthy();
	});

	describe("instantiate", function(){
		beforeEach(function(){
			target = target.instantiate();
		});

		it("should produce PlaidObject which has correct metadata", function(){
			expect(target.getMetadata()).toHaveSameMetadata(objectCreatedWithTree.getMetadata());
		});

		it("should return PlaidObject which has correct members", function(){
			expect(target.members()).toHaveSameElements(objectCreatedWithTree.members());
		});

		it("should return PlaidObject which has correct tags", function(){
			expect(target.tags()).toHaveSameElements(objectCreatedWithTree.tags());
		});

		it("should throw error when the state has duplicated members", function(){
			var treeWithDuplicatedMembers = new Plaid.tree.Tree();
			new Plaid.tree.WithNode("a", new Plaid.Array("tag"), treeWithDuplicatedMembers.root);
			new Plaid.tree.WithNode("b", new Plaid.Array("tag"), treeWithDuplicatedMembers.root);
			
			target = new Plaid.PlaidState();
			target.setMetadata(treeWithDuplicatedMembers.toMetadata());

			expect(function() {
				target.instantiate();
			}).toThrow("Error: Instantiate violates unique members by attempting to create a state that contains tag twice.");
		});

		it("should throw error when the state has duplicated tags", function(){
			var treeWithDuplicatedTags = new Plaid.tree.Tree();
			new Plaid.tree.WithNode("a", new Plaid.Array(), treeWithDuplicatedTags.root);
			new Plaid.tree.WithNode("a", new Plaid.Array(), treeWithDuplicatedTags.root);

			target = new Plaid.PlaidState();
			target.setMetadata(treeWithDuplicatedTags.toMetadata());
			
			expect(function() {
				target.instantiate();
			}).toThrow("Error: Attempt to instantiate violates unique tags by containing tag a twice.");
		});
	});

	describe("with$plaid", function(){
		beforeEach(function(){
			target = target.with$plaid(stateAdditional);
		});

		it("should produce PlaidState which has correct metadata", function(){
			expect(target.getMetadata()).toHaveSameMetadata(stateAfterWith.getMetadata());
		});

		it("should return PlaidState which has correct members", function(){
			expect(target.members()).toHaveSameElements(stateAfterWith.members());
		});

		it("should return PlaidState which has correct tags", function(){
			expect(target.tags()).toHaveSameElements(stateAfterWith.tags());
		});
	});
	
	describe("remove", function(){
		beforeEach(function(){
			target = target.remove("stopBraking");
		});

		it("should return copied state which has correct metadata", function(){
			expect(target.getMetadata()).toHaveSameMetadata(stateAfterRemove.getMetadata());
		});

		it("should return copied state which does not have specific field", function(){
			expect(target.stopBraking).not.toBeDefined();
		});
		
		it("should return copied state which does not have specific member", function(){
			expect(target.members()).toHaveSameElements(membersAfterRemove);
		});
		
		it("should throw exception when the member is not present in the state", function(){
			expect(function(){
				target.remove("unknown");
			}).toThrow("Error: attempt to remove a member unknown that does not exist in the state");
		});
	});

	describe("rename", function(){
		it("should return copied state which has correct metadata", function(){
			target = target.rename("stopBraking", "stopBraking2");
			expect(target.getMetadata()).toHaveSameMetadata(stateAfterRename.getMetadata());
		});

		it("should return copied state which has correct members", function(){
			target = target.rename("stopBraking", "stopBraking2");
			expect(target.members()).toHaveSameElements(membersAfterRename);
		});

		it("should return copied state whitch has same value as the previous name", function(){
			var value = target.stopBraking;
			target = target.rename("stopBraking", "stopBraking2");
			expect(target.stopBraking).not.toBeDefined();
			expect(target.stopBraking2).toEqual(value);
		});
		
		it("should throw an error if rename call attempts to rename a member that does not exist in the state", function(){
			expect(function(){
				target.rename("speed2", "getDirty2");
			}).toThrow("Error: attempt to rename a member speed2 that does not exist in the state");
		});
	});

	describe("specialize", function(){
		it("should return copied state that has correct metadata", function(){
			target = target.specialize("braking", "hi", 3);
			expect(target.getMetadata()).toHaveSameMetadata(stateAfterSpecialize.getMetadata());
		});

		it("should return copied state that has correct value associated with additional member", function(){
			target = target.specialize("braking", "hi", 3);
			expect(target["hi"]).toEqual(3);
		});

		it("should return copied state that has updated value of existing member", function(){
			target = target.specialize("braking", "stopBraking", 3);
			expect(target["stopBraking"]).toEqual(3);
		});

		it("should not add member if the tag does not exist", function(){
			target = target.specialize("speed2", "getDirty2", 3);
			expect(target["speed2"]).not.toBeDefined();
		});

		describe("callback functions", function(){
			var f1 = function(){};
			var f2 = function(){};

			it("should add callback function when the member name is matched with activated callback name", function(){
				target[Plaid.keyword].prepend.onCallback = new Plaid.Array(f1);
				target = target.specialize("", "onCallback", f2);
				
				expect(target[Plaid.keyword].prepend.onCallback).toHaveSameElements(new Plaid.Array(f2, f1));
			});

			it("should add callback function when the name is matched with  passivated callback name", function(){
				target[Plaid.keyword].prepend.offCallback = new Plaid.Array(f1);
				target = target.specialize("", "offCallback", f2);

				expect(target[Plaid.keyword].prepend.offCallback).toHaveSameElements(new Plaid.Array(f2, f1));
			});
		});
	});

	describe("withMember", function(){
		it("should return copied state that has correct value associated with additional member", function(){
			target = target.withMember("speed2", 3);
			expect(target.members()).toHaveSameElements(membersAfterWithMember);
			expect(target["speed2"]).toEqual(3);
		});

		it("should return copied state that has upudated value of existing member", function(){
			target = target.withMember("stopBraking", 3);
			expect(target["stopBraking"]).toEqual(3);
		});
	});

	describe("withMemberNoValue", function(){
		it("should return copied state that has correct members", function(){
			target = target.withMemberNoValue("speed2");
			expect(target.members()).toHaveSameElements(membersAfterWithMember);
			expect(target["speed2"]).toBeFalsy();
		});
		
		it("should return copied state that has upudated value of existing member", function(){
			target = target.withMemberNoValue("stopBraking");
			expect(target["stopBraking"]).toBeFalsy();
		});

	});
});

