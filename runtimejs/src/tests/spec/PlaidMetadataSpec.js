describe("Metadata", function(){
	var target;
	var tags;
	var members;
	var metadata1;
	var metadata2;
	var metadata3;
	var metadata4;
	var metadata5;
	var metadata6;

	beforeEach(function(){
		var root = new Plaid.metadata.WithEntry("", new Plaid.Array());
		metadata1 = new Plaid.metadata.Metadata(root);

		var car = new Plaid.metadata.WithEntry("car", new Plaid.Array());
		var mdCar = new Plaid.metadata.Metadata(car);

		var drivingStatus = new Plaid.metadata.WithEntry("drivingStatus", new Plaid.Array());
		var mdDrivingStatus = new Plaid.metadata.Metadata(drivingStatus);

		var driving = new Plaid.metadata.CaseOfEntry("driving", new Plaid.Array("speed","acceleration","stopDriving"));
		var mdDriving = new Plaid.metadata.Metadata(driving);
		
		var brakingStatus = new Plaid.metadata.WithEntry("brakingStatus", new Plaid.Array());
		var mdBrakingStatus = new Plaid.metadata.Metadata(brakingStatus);

		var braking = new Plaid.metadata.CaseOfEntry("braking", new Plaid.Array("stopBraking"));
		var mdBraking = new Plaid.metadata.Metadata(braking);
		
		var directionStatus = new Plaid.metadata.WithEntry("directionStatus", new Plaid.Array());
		var mdDirectionStatus = new Plaid.metadata.Metadata(directionStatus);
		
		var turningLeft = new Plaid.metadata.CaseOfEntry("turningLeft", new Plaid.Array("straight","turnRight"));
		var mdTurningLeft = new Plaid.metadata.Metadata(turningLeft);
		
		var cleanStatus = new Plaid.metadata.WithEntry("cleanStatus", new Plaid.Array());
		var mdCleanStatus = new Plaid.metadata.Metadata(cleanStatus);

		var clean = new Plaid.metadata.CaseOfEntry("clean", new Plaid.Array("getDirty"));
		var mdClean = new Plaid.metadata.Metadata(clean);

		metadata1.addChild(mdCar);
		mdCar.addChild(mdDrivingStatus);
		mdCar.addChild(mdCleanStatus);
		mdDrivingStatus.addChild(mdDriving);
		mdDriving.addChild(mdBrakingStatus);
		mdDriving.addChild(mdDirectionStatus);
		mdBrakingStatus.addChild(mdBraking);
		mdDirectionStatus.addChild(mdTurningLeft);
		mdCleanStatus.addChild(mdClean);


		tags = new Plaid.Array("car", "drivingStatus", "driving", "brakingStatus", "braking", "directionStatus", "turningLeft", "cleanStatus", "clean");

		members = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty");

		target = metadata1;


		metadata2 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("", new Plaid.Array()))

		var mdA2 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("a",new Plaid.Array()))
		metadata2.addChild(mdA2);

		var mdB2 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("b",new Plaid.Array("x")))
		mdA2.addChild(mdB2);

		var mdC2 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("c",new Plaid.Array("x")))
		mdB2.addChild(mdC2);



		metadata3 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("", new Plaid.Array()))

		var mdA3 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("a",new Plaid.Array("x")))
		metadata3.addChild(mdA3);

		var mdB3 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("b",new Plaid.Array()))
		mdA3.addChild(mdB3);

		var mdC3 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("c",new Plaid.Array("x")))
		mdB3.addChild(mdC3);



		metadata4 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("", new Plaid.Array()))

		var mdA4 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("a",new Plaid.Array()))
		metadata4.addChild(mdA4);

		var mdB4 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("b",new Plaid.Array("x")))
		mdA4.addChild(mdB4);

		var mdC4 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("c",new Plaid.Array("x")))
		mdA4.addChild(mdC4);



		metadata5 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("", new Plaid.Array()))

		var mdA5 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("a",new Plaid.Array("x")))
		metadata5.addChild(mdA5);

		var mdB5 = new Plaid.metadata.Metadata(new Plaid.metadata.CaseOfEntry("b",new Plaid.Array()))
		mdA5.addChild(mdB5);

		var mdC5 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("c",new Plaid.Array("x")))
		mdA5.addChild(mdC5);


		metadata6 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("", new Plaid.Array()))

		var mdA6 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("a",new Plaid.Array()))
		metadata6.addChild(mdA6);

		var mdB6 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("b",new Plaid.Array("x")))
		mdA6.addChild(mdB6);

		var mdC6 = new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry("c",new Plaid.Array("x")))
		mdA6.addChild(mdC6);
	});

	describe("addWithEntry", function(){
		beforeEach(function(){
			target.addWithEntry("tag", new Plaid.Array("member1", "member2"));
			members.push("member1");
			members.push("member2");
			tags.push("tag");
		});

		it("should add member", function(){
			expect(target.members()).toHaveSameElements(members);
		});

		it("should add tags", function(){
			expect(target.tags()).toHaveSameElements(tags);
		});
	});

	describe("tags", function(){
		it("should have correct tags", function(){
			expect(target.tags()).toHaveSameElements(tags);
		});
	});

  describe("members", function(){
		it("should have correct members", function(){
			expect(target.members()).toHaveSameElements(members);
		});
	});

	// describe("membersByTag", function(){
	// 	it("should have correct members", function(){
	// 		expect(target.membersByTag("driving")).toHaveSameElements(new Plaid.Array("speed", "acceleration", "stopDriving"));
	// 		expect(target.membersByTag("directionStatus")).toHaveSameElements(new Plaid.Array());
	// 	});

	// 	it("should return empty Array when the tag does not exist", function(){
	// 		expect(target.membersByTag("car2")).toHaveSameElements(new Plaid.Array());
	// 	});
	// });

	describe("findEntryByTag", function(){
		it("should return entry specified by tag name", function(){
			target = target.findEntryByTag("driving");
			expect(target instanceof Plaid.metadata.CaseOfEntry).toBeTruthy();
			expect(target.tag).toEqual("driving");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed", "acceleration", "stopDriving"));
		});

		it("should return nothing when the tag does not exist", function(){
			target = target.findEntryByTag("car2");
			expect(target).not.toBeDefined();
		});
	});

	describe("remove", function(){
		it("should have correct members after removing the first member", function(){
			target.remove("speed");
			expect(target.members()).toHaveSameElements(new Plaid.Array("acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"));
		});
		it("should have correct members after removing member", function(){
			target.remove("stopBraking");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed","acceleration","stopDriving","straight","turnRight","getDirty"));
		});

		it("should have correct members after removing the last member", function(){
			target.remove("getDirty");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed", "acceleration","stopDriving","stopBraking","straight","turnRight"));
		});
	});

	describe("rename", function(){
		it("should have correct members after renaming the first member", function(){
			target.rename("speed", "speed2");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed2", "acceleration","stopDriving","stopBraking","straight","turnRight", "getDirty"));
		});

		it("should have correct members after renaming member", function(){
			target.rename("stopBraking", "stopBraking2");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed", "acceleration","stopDriving","stopBraking2","straight","turnRight", "getDirty"));
		});

		it("should have correct members after renaming the last member", function(){
			target.rename("getDirty", "getDirty2");
			expect(target.members()).toHaveSameElements(new Plaid.Array("speed", "acceleration","stopDriving","stopBraking","straight","turnRight", "getDirty2"));
		});
	});

	describe("match", function(){
		it("should match when tag is included", function(){
			expect(target.match("car")).toBeTruthy();
		});
		it("should match when tag is included", function(){
			expect(target.match("driving")).toBeTruthy();
		});
		it("should match when tag is included", function(){
			expect(target.match("clean")).toBeTruthy();
		});

		it("should not match when tag is not included", function(){
			expect(target.match("car2")).toBeFalsy()
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

	describe("checkUniqueMembers", function(){
		it("should end normally when same members are included in different WithEntries or their descendants", function(){
			expect(metadata2.checkUniqueMembers(new Plaid.Array(), new Plaid.Array())['unique']).toBeTruthy();
		});

		it("should end normally when same members are included in different WithEntries or their descendants", function(){
			expect(metadata3.checkUniqueMembers(new Plaid.Array(), new Plaid.Array())['unique']).toBeTruthy();
		});

		it("should return error if it violates unique members", function(){
			expect(metadata4.checkUniqueMembers(new Plaid.Array(), new Plaid.Array())['unique']).toBeFalsy();
		});

		it("should return error if it violates unique members", function(){
			expect(metadata5.checkUniqueMembers(new Plaid.Array(), new Plaid.Array())['unique']).toBeFalsy();
		});

		it("should return error if it violates unique members", function(){
			expect(metadata6.checkUniqueMembers(new Plaid.Array(), new Plaid.Array())['unique']).toBeFalsy();
		});
	});

});
