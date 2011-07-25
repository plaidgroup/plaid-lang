describe("PlaidObject", function() {
  var obj1;
  var obj2;
  var obj3;
  var obj4;
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

  var treeBuilder4=new Tree();
  var car = new WithNode("car",[],treeBuilder4.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var car = new WithNode("",["three"],treeBuilder4.root);
  var md4 = treeBuilder4.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["startBraking"];
  var membersAtStart3=["speed","acceleration","stopDriving","startBraking","straight","turnRight","getDirty"];
  var membersAtStart4=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty","three"];


  beforeEach(function() {
    obj1 = new PlaidObject(md1.clone());
    obj2 = new PlaidObject(md2.clone());
    obj3 = new PlaidObject(md3.clone());
    obj4 = new PlaidObject(md4.clone());
    state1 = new PlaidState();
    state1.setTree(md1.clone());
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
    var length4=membersAtStart4.length;
    for (var i=0;i<length4;i++){
      obj4[membersAtStart4[i]]=10;
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

  it("should have correct tree and members after state change passing in only a member", function() {
    obj1.stateChangeMember("three",3);
    var result=obj1.tree;
    expect(result).toEqual(obj4.tree);
    var obj1Members=[]
    for (var item in obj1){
      obj1Members.push(item);
    }
    var obj4Members=[]
    for (var item in obj4){
      obj4Members.push(item);
    }
    expect(obj1Members).toHaveSameElementsAs(obj4Members);
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

  var treeBuilder7=new Tree();
  var car = new WithNode("car",[],treeBuilder7.root);
  var drivingStatus = new WithNode("drivingStatus",[],car);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("braking",["stopBraking","hi"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md7 = treeBuilder7.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["speed","acceleration","stopDriving","straight","turnRight","getDirty"];
  var membersAtStart3=["speed","acceleration","stopDriving","stopBraking2","straight","turnRight","getDirty"];
  var membersAtStart4=["addWheels"];
  var membersAtStart6=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty","addWheels"];


  beforeEach(function() {
    state1 = new PlaidState();
    state1.setTree(md1.clone());
    state2 = new PlaidState();
    state2.setTree(md2.clone());
    state3 = new PlaidState();
    state3.setTree(md3.clone());
    state4 = new PlaidState();
    state4.setTree(md4.clone());
    state6 = new PlaidState();
    state6.setTree(md6.clone());
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

  it("should produce PlaidState with correct tree and member values after specialize", function() {
    var result = state1.specialize("braking","hi",3);
    var tree=result.tree;
    expect(tree).toEqual(md7);
    expect(result['hi']).toEqual(3);

    var result = state1.specialize("braking","stopBraking",3);
    var tree=result.tree;
    expect(tree).toEqual(state1.tree);
    expect(result['stopBraking']).toEqual(3);
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
  var brakingStatus2 = new WithNode("brakingStatus2",[],treeBuilder2.root);
  var braking = new CaseOfNode("braking",["stopBraking2"],brakingStatus2);
  var md2 = treeBuilder2.toMetadata();

  var treeBuilder3=new Tree();
  var car2 = new WithNode("car2",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus",["speed"],car2);
  var driving = new CaseOfNode("driving",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus",[],driving);
  var braking = new CaseOfNode("notBraking",["startBraking"],brakingStatus);
  var directionStatus = new WithNode("directionStatus",[],driving);
  var turningLeft = new CaseOfNode("turningLeft",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus",[],car2);
  var clean = new CaseOfNode("clean",["getDirty"],cleanStatus);
  var md3 = treeBuilder3.toMetadata();

  var membersAtStart1=["speed","acceleration","stopDriving","stopBraking","straight","turnRight","getDirty"];
  var membersAtStart2=["startBraking2"];
  var membersAtStart3=["speed","acceleration","stopDriving","startBraking","straight","turnRight","getDirty"];


  beforeEach(function() {
    obj1 = new PlaidObject(md1.clone());
    obj2 = new PlaidObject(md2.clone());
    obj3 = new PlaidObject(md3.clone());
    state1 = new PlaidState();
    state1.setTree(md1.clone());
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
    expect(function() {
        obj1.stateChange(obj3);
      }).toThrow("Error: state change violates unique members by attempting to add speed to item that already contains speed");
  });

  it("should throw an error if state change would violate unique members passing in only a member", function() {    
    expect(function() {
        obj1.stateChangeMember("getDirty",3);
      }).toThrow("Error: state change operation violates unique members by attempting to add member getDirty to state that already contains getDirty");
  });

  it("should throw an error if state change would violate unique tags", function() {    
    expect(function() {
        obj1.stateChange(obj2);
      }).toThrow("Error: state change violates unique tags by containing tag braking twice");
  });
  
});

describe("PlaidState Errors", function() {
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
  var car = new WithNode("car2",[],treeBuilder3.root);
  var drivingStatus = new WithNode("drivingStatus2",[],car);
  var driving = new CaseOfNode("driving2",["speed","acceleration","stopDriving"],drivingStatus);
  var brakingStatus = new WithNode("brakingStatus2",[],driving);
  var braking = new CaseOfNode("braking2",["stopBraking2"],brakingStatus);
  var directionStatus = new WithNode("directionStatus2",[],driving);
  var turningLeft = new CaseOfNode("turningLeft2",["straight","turnRight"], directionStatus);
  var cleanStatus = new WithNode("cleanStatus2",[],car);
  var clean = new CaseOfNode("clean2",["getDirty"],cleanStatus);
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
    state1 = new PlaidState();
    state1.setTree(md1.clone());
    state2 = new PlaidState();
    state2.setTree(md2.clone());
    state3 = new PlaidState();
    state3.setTree(md3.clone());
    state4 = new PlaidState();
    state4.setTree(md4.clone());
    state6 = new PlaidState();
    state6.setTree(md6.clone());
    obj1 = new PlaidObject();
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

  it("should throw an error if with operation would violate unique members", function() {    
    expect(function() {
        state1.with(state2);
      }).toThrow("Error: with operation violates unique tags by containing tag car in both component states");
  });

  it("should throw an error if with operation would violate unique tags", function() {    
    expect(function() {
        state1.with(state3);
      }).toThrow("Error: with operation violates unique members by containing member speed in both component states");
  });

  it("should throw an error if rename call attempts to give member a name already used in the state", function() {    
    expect(function() {
        state1.rename("speed","getDirty");
      }).toThrow("Error: attempt to rename a member with name getDirty which already names a member");
  });

  it("should throw an error if rename call attempts to rename a member that does not exist in the state", function() {    
    expect(function() {
        state1.rename("speed2","getDirty2");
      }).toThrow("Error: attempt to rename a member speed2 that does not exist in the state");
  });

  it("should throw an error if remove call attempts to remove a member that does not exist in the state", function() {    
    expect(function() {
        state1.remove("speed2");
      }).toThrow("Error: attempt to remove a member speed2 that does not exist in the state");
  });
  
  it("should throw an error if specialize call attempts to add a member that already exists in the state, but for a different tag", function() {
    expect(function() {
        state1.specialize("braking","getDirty",3);
      }).toThrow("Error: attempt to associate member getDirty with tag braking which already names a member of another tag");
  });

});
