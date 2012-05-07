if(typeof require !== 'undefined') {
  var Plaid = require('Plaid.js').Plaid
}

describe("Tree", function(){
  var target = new Plaid.tree.Tree();
	var tree1;
	var tags;
	var members;

	beforeEach(function() {
		tree1 = new Plaid.tree.Tree();
		var car = new Plaid.tree.WithNode("car",new Plaid.Array(),tree1.root);
		var drivingStatus = new Plaid.tree.WithNode("drivingStatus",new Plaid.Array(),car);
		var driving = new Plaid.tree.CaseOfNode("driving",new Plaid.Array("speed","acceleration","stopDriving"),drivingStatus);
		var brakingStatus = new Plaid.tree.WithNode("brakingStatus",new Plaid.Array(),driving);
		var braking = new Plaid.tree.CaseOfNode("braking",new Plaid.Array("stopBraking"),brakingStatus);
		var directionStatus = new Plaid.tree.WithNode("directionStatus",new Plaid.Array(),driving);
		var turningLeft = new Plaid.tree.CaseOfNode("turningLeft",new Plaid.Array("straight","turnRight"), directionStatus);
		var cleanStatus = new Plaid.tree.WithNode("cleanStatus",new Plaid.Array(),car);
		var clean = new Plaid.tree.CaseOfNode("clean",new Plaid.Array("getDirty"),cleanStatus);

		tags = new Plaid.Array("car", "drivingStatus", "driving", "brakingStatus", "braking", "directionStatus", "turningLeft", "cleanStatus", "clean");
		members = new Plaid.Array("speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty");

	});

	it("should have prototype chain", function(){
		expect(target instanceof Plaid.tree.Tree).toBeTruthy();
	});

	it("shold have root node when created", function(){
		expect(target.root).toBeDefined();
		expect(target.root instanceof Plaid.tree.WithNode);
	});

	describe("toMetadata", function(){
		var metadata;
		beforeEach(function(){
			target = tree1;
			metadata = target.toMetadata();
		});

		it("should return correct metadata", function(){
			expect(metadata.length).toEqual(2);
			var mdCar = metadata[1];
			expect(mdCar.length).toEqual(3);

			var car = mdCar[0];
			expect(car.tag).toEqual("car");
			expect(car.memberList).toHaveSameElements(new Plaid.Array());

			var mdDrivingStatus = mdCar[1];
			expect(mdDrivingStatus.length).toEqual(2);

			var drivingStatus = mdDrivingStatus[0];
			expect(drivingStatus.tag).toEqual("drivingStatus");
			expect(drivingStatus.memberList).toHaveSameElements(new Plaid.Array());

			var mdDriving = mdDrivingStatus[1];
			expect(mdDriving.length).toEqual(3);

			var driving = mdDriving[0];
			expect(driving.tag).toEqual("driving");
			expect(driving.memberList).toHaveSameElements(new Plaid.Array("speed","acceleration","stopDriving"));

			var mdBrakingStatus = mdDriving[1];
			expect(mdBrakingStatus.length).toEqual(2);

			var brakingStatus = mdBrakingStatus[0];
			expect(brakingStatus.tag).toEqual("brakingStatus");
			expect(brakingStatus.memberList).toHaveSameElements(new Plaid.Array());

			var mdBraking = mdBrakingStatus[1];
			expect(mdBraking.length).toEqual(1);

			var braking = mdBraking[0];
			expect(braking.tag).toEqual("braking");
			expect(braking.memberList).toHaveSameElements(new Plaid.Array("stopBraking"));
		
			var mdDirectionStatus = mdDriving[2];
			expect(mdDirectionStatus.length).toEqual(2);

			var directionStatus = mdDirectionStatus[0];
			expect(directionStatus.tag).toEqual("directionStatus");
			expect(directionStatus.memberList).toHaveSameElements(new Plaid.Array());

			var mdTurningLeft = mdDirectionStatus[1];
			expect(mdTurningLeft.length).toEqual(1);

			var turningLeft = mdTurningLeft[0];
			expect(turningLeft.tag).toEqual("turningLeft");
			expect(turningLeft.memberList).toHaveSameElements(new Plaid.Array("straight", "turnRight"));

			var mdCleanStatus = mdCar[2];
			expect(mdCleanStatus.length).toEqual(2);

			var cleanStatus = mdCleanStatus[0];
			expect(cleanStatus.tag).toEqual("cleanStatus");
			expect(cleanStatus.memberList).toHaveSameElements(new Plaid.Array());

			var mdClean = mdCleanStatus[1];
			expect(mdClean.length).toEqual(1);
			
			var clean = mdClean[0];
			expect(clean.tag).toEqual("clean");
			expect(clean.memberList).toHaveSameElements(new Plaid.Array("getDirty"));
		});
		
		it("should have correct tags", function(){
			expect(metadata.tags()).toHaveSameElements(tags);			
		});

		it("should have correct members", function(){
			expect(metadata.members()).toHaveSameElements(members);
		});
	});
});
