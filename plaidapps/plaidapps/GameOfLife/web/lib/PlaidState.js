/*This file defines the type of JavaScript object that represents Plaid objects at runtime*/

/*Helper method clones objects and arrays*/
PlaidState.prototype.clone = function() {
//	var obj = (this instanceof Array) ? [] : new PlaidState();
	var obj = new PlaidState();
  var i;
  for (i in this) {
    if (i == 'clone') continue;
    if (this[i] && typeof this[i] == "object") {
      obj[i] = this[i].clone();
    } else obj[i] = this[i]
  } return obj;
}

/*Constructor*/
function PlaidState(){
	this.tree=[["",[],"with"]];
	this.prepend={};
}

/*set the tree equal to the argument passed in*/
PlaidState.prototype.setTree = function(t) {
  this.tree=t;
}

/*Deletes the member that was passed in from the tree of the object on which method was called*/
function s_remove(md1, member){
  var memberList=md1[0][1];
  var memberLength=memberList.length;
  for (var i=0;i<memberLength;i++){
    if (memberList[i]===member){
      memberList=memberList.splice(i,1);
      return true;
    }
  }
  var length=md1.length;
  for (var i=1;i<length;i++){
    var removed=s_remove(md1[i],member);
    if(removed===true){
      return true;
    }
  }
  return false;
}

/*Renames member as newName in the tree of the object on which method was called*/
function s_rename(md1, member, newName){
  var memberList=md1[0][1];
  var memberLength=memberList.length;
  for (var i=0;i<memberLength;i++){
    if (memberList[i]===member){
      memberList[i]=newName;
      return true;
    }
  }
  var length=md1.length;
  for (var i=1;i<length;i++){
    var renamed=s_rename(md1[i],member,newName);
    if(renamed===true){
      return true;
    }
  }
  return false;
}

/*returns an array of all the fields and methods of the state that is reflected in the current metadata*/
function s_members(md1){
  var memberList=md1[0][1];
  var length=md1.length;
  for (var i=1;i<length;i++){
    memberList=memberList.concat(s_members(md1[i]));
  }
  return memberList;
}

/*returns an object with true in field 'unique' if the tree passed in reflects a state that has unique members, false otherwise; if unique members is false, the member that caused the conflict is in field 'member'*/
function s_checkUniqueMembers(md1,hierarchy,memberList){
  if (md1[0][2]==="with"){
    hierarchy=[md1[0][0]];
  }
  else{
    hierarchy.push(md1[0][0]);
  }
  var members = md1[0][1];
  var length=members.length;
  for (var i=0;i<length;i++) {
    //check if this member is already in the memberList
    var memberListLength=memberList.length;
    for (var j=0; j<memberListLength; j++){
      if(memberList[j]['memberName']===members[i]){
        if (has(hierarchy,memberList[j]['tag'])===false) {
          var itemToReturn={
            unique: false,
            member: memberList[j]['memberName']
          }
          return itemToReturn;
        }
      }
    }
    var itemToAdd={
      memberName:members[i],
      tag:md1[0][0]
    }
    memberList.push(itemToAdd);
  }
  var length=md1.length;
  for (var i=1;i<length;i++){
    var unique = m_checkUniqueMembers(md1[i],hierarchy, memberList);
    if (unique['unique']===false) {
      return unique;
    }
  }
  var itemToReturn={
    unique: true,
    member: ""
  }
  return itemToReturn;
}

/*returns an array of all the fields and methods of the object on which the function is called*/
PlaidState.prototype.members=function() {
  return s_members(this.tree);
}

/*returns a PlaidObject created from the PlaidState on which the function is called*/
PlaidState.prototype.instantiate = function() {

  //ensure that the state being instantiated has unique members
  var unique = m_checkUniqueMembers(this.tree,[],[]);
  if (unique['unique']===false) {
    throw "Error: Instantiate violates unique members by attempting to create a state that contains "+unique['member']+" twice.";
  }

  //check unique tags
  var tags=s_tags(this.tree);
  var tagsLength=tags.length;
  for (var j=0;j<tagsLength;j++){
    if(count(tags,tags[j])>1){
      throw "Error: Attempt to instantiate violates unique tags by containing tag "+tags[j]+" twice";
    }
  }

  var obj=new PlaidObject(this.tree.clone());
  copyMembers(obj,this);
  return obj;
}

/*returns a copy of the state on which it was called, copy lacks the member that was passed in*/
PlaidState.prototype.remove = function(member) {

  var obj=new PlaidState();
  obj.setTree(this.tree.clone());
  copyMembers(obj,this);
  if(s_remove(obj.tree,member)===false){
    throw "Error: attempt to remove a member "+member+" that does not exist in the state";
    return;
  }
  delete obj[member];
  return obj;
}

/*returns a copy of the state on which it was called, in copy member has the name newName*/
PlaidState.prototype.rename = function(member, newName) {
  //var currMembers=this.members();
  /*
		if (has(currMembers,newName)){
    throw "Error: attempt to rename a member with name "+newName+" which already names a member";
		}
  */
  var obj=new PlaidState();
  obj.setTree(this.tree.clone());
  copyMembers(obj,this);

  if(s_rename(obj.tree,member,newName)===false){
    throw "Error: attempt to rename a member "+member+" that does not exist in the state";
    return;
  }
  addMember(obj, newName, obj[member]);
  delete obj[member];
  return obj;
}

/*returns an array of all the fields and methods associated with the given tag, if the tag is found; returns empty array if tag not present*/
function membersByTag(md1,tag){
  if (md1[0][0]===tag){
    return md1[0][1];
  }
  else{
    var length=md1.length;
    for (var i=1;i<length;i++){
      var members = membersByTag(md1[i],tag);
      if (members!==[]){
        return members;
      }
    }
  }
  return [];
}

/*returns a copy of the state on which it was called, in copy member has the value passed in; the member is associated with the tag passed in*/
PlaidState.prototype.specialize = function(tag, member, value) {
  //var currMembers=this.members();
	
  var obj=new PlaidState();
  obj.setTree(this.tree.clone());
  copyMembers(obj,this);

	//if the member is callback, add to prepend
	if(Plaid.isCallback(member)){
		if(obj.prepend[member] == undefined){
			obj.prepend[member] = [];
		}
		obj.prepend[member].unshift(value);
		return obj;
	}

  var tagMembers=membersByTag(obj.tree,tag);

  if (!has(tagMembers,member)){
    /*if the member is already in the object and associated with any tag but the target tag, this is an error
      if(has(currMembers,member)){
      throw "Error: attempt to associate member "+member+" with tag "+tag+" which already names a member of another tag";  
      }
    */
    //the member is not yet associated with the given tag and must be added to the tree
    //else{
    tagMembers.push(member);
    //}
  }
  addMember(obj,member,value);
  return obj;
}

/*returns an array of all the tags of the state that is reflected in the current metadata*/
function s_tags(md1){
  var tagList=[];
  if (md1[0][0]!==""){
    tagList=[md1[0][0]];
  }
  var length=md1.length;
  for (var i=1;i<length;i++){
    tagList=tagList.concat(s_tags(md1[i]));
  }
  return tagList;
}

/*returns a new state composed of both the state on which the function was called and the state that was passed in (should be object?)*/
PlaidState.prototype.with = function(state) {

  var md1=this.tree;
  var md2=state.tree;

  /*check unique tags
		var tags1=s_tags(md1);
		var tags2=s_tags(md2);
		var tags1Length=tags1.length;
		for (var j=0;j<tags1Length;j++){
    if(has(tags2,tags1[j])){
    throw "Error: with operation violates unique tags by containing tag "+tags1[j]+" in both component states";
    }
		} 
  */

  /*
		var members1=s_members(md1);
		var members2=s_members(md2);
		var members1Length=members1.length;
		for (var j=0;j<members1Length;j++){
    if(has(members2,members1[j])){
    throw "Error: with operation violates unique members by containing member "+members1[j]+" in both component states";
    }
		} 
  */

  var obj=new PlaidState();
  obj.setTree(this.tree.clone());
  var i;
  copyMembers(obj,this);
  copyMembers(obj,state);
  var md2Length=md2.length;
  for (var i=1;i<md2Length;i++){
    obj.tree.push(md2[i].clone());
  }  

  return obj;
}

/*returns a new state composed of both the state on which the function was called and the member passed in, assigns memberName's value to be memberValue; this method handles the case where a member and not a state is passed in*/
PlaidState.prototype.withMember = function(memberName,memberValue) {
  /*
		var members1=this.members();
		var members1Length=members1.length;
		for (var j=0;j<members1Length;j++){
    if(members1[j]===memberName){
    throw "Error: with operation violates unique members by containing member "+members1[j]+" in both component states";
    }
		} 
  */

  var obj=new PlaidState();
  obj.setTree(this.tree.clone());
  var i;
  copyMembers(obj,this);
  addMember(obj,memberName,memberValue);

  obj.tree.push([["",[memberName],"with"]]);  

  return obj;
}

/*returns a new state composed of both the state on which the function was called and the member passed in; this method handles the case where a member and not a state is passed in, and the member is given no value, and is only declared*/
PlaidState.prototype.withMemberNoValue = function(memberName) {
  var md1=this.tree;

  /*
		var members1=s_members(md1);
		var members1Length=members1.length;
		for (var j=0;j<members1Length;j++){
    if(members1[j]===memberName){
    throw "Error: with operation violates unique members by containing member "+members1[j]+" in both component states";
    }
		} 
  */

  //var obj=new PlaidState();
  //obj.setTree(this.tree.clone());
  var i;
  //copyMembers(obj,this);
  var obj = this.begetObj();

  obj.tree.push([["",[memberName],"with"]]);  

  return obj;
}

/*Returns true if item is contained in array, false if it is not*/
function has(array, item){
  var length=array.length;
  for (var i=0;i<length;i++){
    if (array[i]===item){
      return true;
    }
  }
  return false;
}

/*copies members of obj2 to obj1*/
function copyMembers(obj1,obj2){
  var i;
  for (i in obj2) {
    if (i==="tree" || i==="instantiate" || i==="members" || i==="remove" || i==="rename" || i==="with" || i==="specialize" || i==="withMember" || i==="withMemberNoValue" || i==="setTree" || i==="clone") {
      continue;
    }
		else if (i === "prepend") {
			for(p in obj2.prepend) {
				if(obj1.prepend[p] == undefined) {
					obj1.prepend[p] = [];
				}
				obj1.prepend[p] = obj2.prepend[p].concat(obj1.prepend[p]);
			}
			continue;
		}
    else {
      addMember(obj1,i,obj2[i]);
    } 
  }
  return;
}

/*a helper function that adds member to obj1, giving it the name memberName;  currently just copies all members and adds the copy;  in future, could be made more efficient*/
function addMember(obj1,memberName,member){
  if (member==null) {
    obj1[memberName]=null;
  }
  //else if (typeof member == "object") {
	else if (member instanceof PlaidState) {
    obj1[memberName]=member.clone();
  } 
  else {
    obj1[memberName] = member;
  }
}
