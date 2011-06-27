describe("PlaidObject", function() {
  var obj1;
  var obj2;

  var treeBuilder=new Tree();
  var car = new Node("car",[],"with",treeBuilder.root);
  var drivingStatus = new Node("drivingStatus",[],"with",car);
  var driving = new Node("driving",["speed","acceleration","stopDriving"],"case of",drivingStatus);
  var brakingStatus = new Node("brakingStatus",[],"with",driving);
  var braking = new Node("braking",["stopBraking"],"case of",brakingStatus);
  var directionStatus = new Node("directionStatus",[],"with",driving);
  var turningLeft = new Node("turningLeft",["straight","turnRight"], "case of", directionStatus);
  var cleanStatus = new Node("cleanStatus",[],"with",car);
  var clean = new Node("clean",["getDirty"],"case of",cleanStatus);
  var md3 = treeBuilder.toMetadata();


  beforeEach(function() {
    obj1 = new PlaidObject(md3);
    
  });

  it("should be able to match a tag in it", function() {
    var result=obj1.match("turningLeft");
    expect(result).toBe(true);
  });

  it("should fail to match a tag not in it", function() {
    var result=obj1.match("lksjlkdj");
    expect(result).toBe(false);
  });

  it("should have expected tag list", function() {
    var result=obj1.tags();
    var desiredTags=[ 'car', 'drivingStatus', 'driving', 'brakingStatus', 'braking', 'directionStatus', 'turningLeft', 'cleanStatus', 'clean' ];
    expect(result).toHaveSameElementsAs(desiredTags);
  });

  it("should have expected member list", function() {
    var result=obj1.members();
    var desiredMembers=[ "speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
    expect(result).toHaveSameElementsAs(desiredMembers);
  });

  
});
