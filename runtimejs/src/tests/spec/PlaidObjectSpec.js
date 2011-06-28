describe("PlaidObject", function() {
  var obj1;
  var obj2;
  var obj3;
  var state1;

  var treeBuilder=new Tree();
  var car = new WithNode("car",[],treeBuilder.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md1 = treeBuilder.toMetadata();

  var treeBuilder2=new Tree();
  var brakingStatus = new WithNode("brakingStatus",[],treeBuilder2.root);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var md2 = treeBuilder2.toMetadata();

  var treeBuilder3=new Tree();
  var car = new WithNode("car",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md3 = treeBuilder3.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["startBraking"];
  var membersAtStart3=["speed","acceleration","stopDriving","startBraking","straight","turnRight","getDirty"];


  beforeEach(function() {
    obj1 = new PlaidObject(md1.clone());
    obj2 = new PlaidObject(md2.clone());
    obj3 = new PlaidObject(md3.clone());
    state1 = new PlaidState(md1.clone());
    var length1=membersAtStart1.length;
    for (var i=0;i<length1;i++){
      obj1[membersAtStart1[i]]=10;
      state1[membersAtStart1[i]]=10;
    }   
    var length2=membersAtStart2.length;
    for (var i=0;i<length2;i++){
      obj2[membersAtStart2[i]]=10;
    }
    var length3=membersAtStart3.length;
    for (var i=0;i<length3;i++){
      obj3[membersAtStart3[i]]=10;
    }
    
  });

  it("should be able to match a tag in it", function() {
    var result=obj1.match("turningLeft");
    expect(result).toBe(true);
  });

  it("should fail to match a tag not in it", function() {
    var result=obj1.match("lksjlkdj");
    expect(result).toBe(false);
  });

  it("should have the expected tag list", function() {
    var result=obj1.tags();
    var desiredTags=[ 'car', 'drivingStatus', 'driving', 'brakingStatus', 'braking', 'directionStatus', 'turningLeft', 'cleanStatus', 'clean' ];
    expect(result).toHaveSameElementsAs(desiredTags);
  });

  it("should have the expected member list", function() {
    var result=obj1.members();
    expect(result).toHaveSameElementsAs(membersAtStart1);
  });

  it("should have correct tree and members after replace", function() {
    obj1.replace(obj2); 
    var result=obj1.tree;
    expect(result).toEqual(obj2.tree);   
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    var obj2Members=[]
    for (var item in obj2){
      obj2Members.push(item);
    }
    expect(obj1Members).toEqual(obj2Members);
  });

  it("should have correct tree and members after state change", function() {
    obj1.stateChange(obj2);
    var result=obj1.tree;
    expect(result).toEqual(obj3.tree);
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    var obj3Members=[]
    for (var item in obj3){
      obj3Members.push(item);
    }
    expect(obj1Members).toHaveSameElementsAs(obj3Members);
  });

  it("should produce PlaidState with correct tree and members after freeze", function() {
    var state2 = obj1.freeze();
    var result=state2.tree;
    expect(result).toEqual(state1.tree);
    var state2Members=[]
    for (var item in state2){
      state2Members.push(item);
    }
    var state1Members=[]
    for (var item in state1){
      state1Members.push(item);
    }
    expect(state2Members).toHaveSameElementsAs(state1Members);
  });
  
});



describe("PlaidState", function() {
  var state1;
  var state2;
  var state3;
  var state4;
  var state6;
  var obj1;

  var treeBuilder=new Tree();
  var car = new WithNode("car",[],treeBuilder.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md1 = treeBuilder.toMetadata();

  var treeBuilder2=new Tree();
  var car = new WithNode("car",[],treeBuilder2.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",[],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md2 = treeBuilder2.toMetadata();

  var treeBuilder3=new Tree();
  var car = new WithNode("car",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking2"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md3 = treeBuilder3.toMetadata();

  var treeBuilder4=new Tree();
  var trailer = new WithNode("trailer",[],treeBuilder4.root);
  var twoWheeled = new CaseOfNode("twoWheeled",["addWheels"],trailer);
  var md4 = treeBuilder4.toMetadata();

  var treeBuilder6=new Tree();
  var car = new WithNode("car",[],treeBuilder6.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var trailer = new WithNode("trailer",[],treeBuilder6.root);
  var twoWheeled = new CaseOfNode("twoWheeled",["addWheels"],trailer);
  var md6 = treeBuilder6.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["speed","acceleration","stopDriving","straight","turnRight","getDirty"];
  var membersAtStart3=["speed","acceleration","stopDriving","stopBraking2","straight","turnRight","getDirty"];
  var membersAtStart4=["addWheels"];
  var membersAtStart6=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty","addWheels"];


  beforeEach(function() {
    state1 = new PlaidState(md1.clone());
    state2 = new PlaidState(md2.clone());
    state3 = new PlaidState(md3.clone());
    state4 = new PlaidState(md4.clone());
    state6 = new PlaidState(md6.clone());
    obj1 = new PlaidObject(md1.clone());
    var length1=membersAtStart1.length;
    for (var i=0;i<length1;i++){
      state1[membersAtStart1[i]]=10;
      obj1[membersAtStart1[i]]=10;
    }   
    var length2=membersAtStart2.length;
    for (var i=0;i<length2;i++){
      state2[membersAtStart2[i]]=10;
    }
    var length3=membersAtStart3.length;
    for (var i=0;i<length3;i++){
      state3[membersAtStart3[i]]=10;
    }    
    var length4=membersAtStart4.length;
    for (var i=0;i<length4;i++){
      state4[membersAtStart4[i]]=10;
    }
    var length6=membersAtStart6.length;
    for (var i=0;i<length6;i++){
      state6[membersAtStart6[i]]=10;
    }
    
  });

  it("should have the expected member list", function() {
    var result=state1.members();
    expect(result).toHaveSameElementsAs(membersAtStart1);
  });

  it("should produce PlaidObject with correct tree and members after instantiate", function() {
    var obj2 = state1.instantiate();
    var result=obj2.tree;
    expect(result).toEqual(obj1.tree);
    var obj2Members=[]
    for (var item in obj2){
      obj2Members.push(item);
    }
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    expect(obj2Members).toHaveSameElementsAs(obj1Members);
  });

  it("should produce PlaidState with correct tree and members after remove", function() {
    var state5 = state1.remove("stopBraking");
    var result=state5.tree;
    expect(result).toEqual(state2.tree);
    var state5Members=[]
    for (var item in state5){
      state5Members.push(item);
    }
    var state2Members=[]
    for (var item in state2){
      state2Members.push(item);
    }
    expect(state5Members).toHaveSameElementsAs(state2Members);
  });

  it("should produce PlaidState with correct tree and members after replace", function() {
    var state5 = state1.rename("stopBraking","stopBraking2");
    var result=state5.tree;
    expect(result).toEqual(state3.tree);
    var state5Members=[]
    for (var item in state5){
      state5Members.push(item);
    }
    var state3Members=[]
    for (var item in state3){
      state3Members.push(item);
    }
    expect(state5Members).toHaveSameElementsAs(state3Members);
  });

  it("should produce PlaidState with correct tree and members after with operation", function() {
    var state5 = state1.with(state4);
    var result=state5.tree;
    expect(result).toEqual(state6.tree);
    var state5Members=[]
    for (var item in state5){
      state5Members.push(item);
    }
    var state6Members=[]
    for (var item in state6){
      state6Members.push(item);
    }
    expect(state5Members).toHaveSameElementsAs(state6Members);
  });

});

describe("PlaidObject Errors", function() {
  var obj1;
  var obj2;
  var obj3;
  var state1;

  var treeBuilder=new Tree();
  var car = new WithNode("car",[],treeBuilder.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md1 = treeBuilder.toMetadata();

  var treeBuilder2=new Tree();
  var brakingStatus = new WithNode("brakingStatus",[],treeBuilder2.root);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var md2 = treeBuilder2.toMetadata();

  var treeBuilder3=new Tree();
  var car2 = new WithNode("car2",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md3 = treeBuilder3.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["startBraking"];
  var membersAtStart3=["speed","acceleration","stopDriving","startBraking","straight","turnRight","getDirty"];


  beforeEach(function() {
    obj1 = new PlaidObject(md1.clone());
    obj2 = new PlaidObject(md2.clone());
    obj3 = new PlaidObject(md3.clone());
    state1 = new PlaidState(md1.clone());
    var length1=membersAtStart1.length;
    for (var i=0;i<length1;i++){
      obj1[membersAtStart1[i]]=10;
      state1[membersAtStart1[i]]=10;
    }   
    var length2=membersAtStart2.length;
    for (var i=0;i<length2;i++){
      obj2[membersAtStart2[i]]=10;
    }
    var length3=membersAtStart3.length;
    for (var i=0;i<length3;i++){
      obj3[membersAtStart3[i]]=10;
    }
    
  });

  it("should throw an error if state change would violate unique members", function() {
    var result=obj1.match("turningLeft");
    expect(result).toBe(true);
  });
  
});
