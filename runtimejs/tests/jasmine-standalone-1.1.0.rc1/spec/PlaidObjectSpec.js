describe("PlaidObject", function() {
  var metadata1=[["",[]],[["car",[],"with"],[["drivingStatus",[],"with"],[["driving",["speed","acceleration","stopDriving"],""],[["brakingStatus",[],"with"],[["braking",["stopBraking"],""]]],[["directionStatus",[],"with"],[["turningLeft",["straight","turnRight"],""]]]]],[["cleanStatus",[],"with"],[["clean",["getDirty"],""]]]]];

  var metadata2=[["",[]],[["brakingStatus",[],"with"],[["notBraking",["startBraking"],""],[["speedIncreasing",[],""]]]]];
  var obj1;
  var obj2;

  beforeEach(function() {
    obj1 = new PlaidObject(metadata1);
    obj2 = new PlaidObject(metadata2);
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

  
});
