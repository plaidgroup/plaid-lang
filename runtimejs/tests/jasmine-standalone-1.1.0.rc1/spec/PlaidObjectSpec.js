describe("PlaidObject", function() {
  var metadata1=[["",[],"with"],[["car",[],"with"],[["drivingStatus",[],"with"],[["driving",["speed","acceleration","stopDriving"],""],[["brakingStatus",[],"with"],[["braking",["stopBraking"],""]]],[["directionStatus",[],"with"],[["turningLeft",["straight","turnRight"],""]]]]],[["cleanStatus",[],"with"],[["clean",["getDirty"],""]]]]];

  var metadata2=[["",[],"with"],[["brakingStatus",[],"with"],[["notBraking",["startBraking"],""],[["speedIncreasing",[],""]]]]];
  var obj1;
  var obj2;
  var obj3;
  var treeBuilder;
  var md3;

  treeBuilder=new Tree();
  var car = new Node("car",[],"with",treeBuilder.root);
  var drivingStatus = new Node("drivingStatus",[],"with",car);
  var driving = new Node("driving",["speed","acceleration","stopDriving"],"case of",drivingStatus);
  var brakingStatus = new Node("brakingStatus",[],"with",driving);
  var braking = new Node("braking",["stopBraking"],"case of",brakingStatus);
  var directionStatus = new Node("directionStatus",[],"with",driving);
  var turningLeft = new Node("turningLeft",["straight","turnRight"], "case of", directionStatus);
  var cleanStatus = new Node("cleanStatus",[],"with",car);
  var clean = new Node("clean",["getDirty"],"case of",cleanStatus);
  md3 = treeBuilder.toMetadata();


  beforeEach(function() {
    obj1 = new PlaidObject(metadata1);
    obj2 = new PlaidObject(metadata2);
    
  });

  it("should be able to match a tag in it", function() {
    jasmine.log(metadata1);
    jasmine.log(md3);
    var result=obj1.match("turningLeft");
    expect(result).toBe(false);
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

  
});
