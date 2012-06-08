/*This file defines the type of JavaScript object that represents Plaid objects at runtime*/

/*Helper method clones objects and arrays*/
Array.prototype.clone = function() {
	var obj = [];
	var i;
	for (i in this) {
		if (i == 'clone') continue;
		if (this[i] && typeof this[i] == "object") {
			obj[i] = this[i].clone();
		} else obj[i] = this[i]
	} return obj;
}

Array.prototype.clear = function() {
	while(this.length > 0){
		this.pop();
	}
}

Array.prototype.each = function(f) {
	for(var i = 0; i < this.length; i++){
		f(this.get(i), i);
	}
}

PlaidObject.prototype.clone = function() {
	var obj = new PlaidObject(this.tree.clone());
	var i;
	for (i in this) {
		if (i == 'clone') continue;
		//    if (this[i] && typeof this[i] == "object") {
		if (this[i] && this[i] instanceof PlaidObject) {
			obj[i] = this[i].clone();
		} else obj[i] = this[i]
	} return obj;
}

PlaidObject.prototype.begetObj = function() {
	function F() {}
	F.prototype = this;
	return new F();
}

PlaidObject.prototype.executeMethodChain = function(name){
	if(this.prepend[name] == undefined){
		return true;
	}

	for(var i = 0; i < this.prepend[name].length; i++){
		var result = this.prepend[name][i]();
		if(result === false){
			return false;
		}
	}
	return true;
}

/*Constructor*/
function PlaidObject(t){
	this.tree=t;
	this.prepend = {};
}

/*tests whether the state reflected in the given metadata contains the given tag; returns true if yes, false if no; includes and-state tags*/
function m_match(md1,tag){
	if (md1[0][0]===tag){
		return true;
	}
	else{
		var length=md1.length;
		for (var i=1;i<length;i++){
			var matched = m_match(md1[i],tag);
			if (matched===true){
				return true;
			}
		}
	}
	return false;
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

/*returns an array of all the fields and methods of the state that is reflected in the current metadata*/
function m_members(md1){
	var memberList=md1[0][1];
	var length=md1.length;
	for (var i=1;i<length;i++){
		memberList=memberList.concat(m_members(md1[i]));
	}
	return memberList;
}

/*returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag name with which it is associated*/
function m_membersUnique(md1){
	var members = md1[0][1];
	var length=members.length;
	var memberList = [];
	for (var i=0;i<length;i++) {
		var itemToAdd={
			memberName:members[i],
			tag:md1[0][0]
		}
		memberList.push(itemToAdd);
	}
	var length=md1.length;
	for (var i=1;i<length;i++){
		memberList=memberList.concat(m_membersUnique(md1[i]));
	}
	return memberList;
}

/*returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag names in its hierarchy*/
function m_membersHierarchy(md1,hierarchy){
	if (md1[0][2]==="with"){
		hierarchy=[md1[0][0]];
	}
	else{
		hierarchy.push(md1[0][0]);
	}
	var members = md1[0][1];
	var length=members.length;
	var memberList = [];
	for (var i=0;i<length;i++) {
		var itemToAdd={
			memberName:members[i],
			hierarchy:hierarchy.clone()
		}
		memberList.push(itemToAdd);
	}
	var length=md1.length;
	for (var i=1;i<length;i++){
		memberList=memberList.concat(m_membersHierarchy(md1[i],hierarchy));
	}
	return memberList;
}

/*returns an object with true in field 'unique' if the tree passed in reflects a state that has unique members, false otherwise; if unique members is false, the member that caused the conflict is in field 'member'*/
function m_checkUniqueMembers(md1,hierarchy,memberList){
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

/*returns an array of all the tags of the state that is reflected in the current metadata*/
function m_tags(md1){
	var tagList=[];
	if (md1[0][0]!==""){
		tagList=[md1[0][0]];
	}
	var length=md1.length;
	for (var i=1;i<length;i++){
		tagList=tagList.concat(m_tags(md1[i]));
	}
	return tagList;
}

/*Once a matching tag has been found, descends the two trees to find any tags that do not match, make appropriate modifications to state*/
function stateChangeDescend(md1,md2, returnItem, hierarchy){
	//becuase this function has been called, we know that md1[0][0]=md2[0][0]
	if (md1[0][2]==="with"){
		hierarchy=[md1[0][0]];
	}
	else{
		hierarchy.push(md1[0][0]);
	}

	var length1=md1.length;
	var length2=md2.length;
	if(length1===1 && length2===1){
		//this branch already matches
		return returnItem;
	}
	else{
		var foundMatches=[];
		for (var i=1;i<length2;i++){
			foundMatches.push(false);
		}
		/*the nested for loops below continue matching and-states, descending down matching ones; if one of md2's and-states is not found in md1, it is ignored; or-states are also matched if the same or-state is present in both md1 and md2*/
		for (var i=1;i<length2;i++){
			for (var j=1;j<length1;j++){
				if (md2[i][0][0]===md1[j][0][0]){
					foundMatches[i-1]=true;
					returnItem=stateChangeDescend(md1[j],md2[i],returnItem, hierarchy);
					break;
				}
			}
		}
		/*there could also exist differing or-states in md1 and md2; if an or-state needs to be changed, it is identified below*/
		for (var i=1;i<length2;i++){
			for (var j=1;j<length1;j++){
				if (md2[i][0][2]===md1[j][0][2] && md2[i][0][2]==="" && md2[i][0][0]!==md1[j][0][0]){
					returnItem.membersToAdd=returnItem.membersToAdd.concat(m_membersHierarchy(md2[i],hierarchy));
					returnItem.membersToRemove=returnItem.membersToRemove.concat(m_members(md1[j]));
					md1[j]=md2[i].clone();
					/*if an or-state was found that needs to be changed, it is not possible for an or-state to have to be added, since a state can only be in one or state at any given time*/
					return returnItem;
				}
			}
		}
		//it is also possible that an or-state must be added (is not present in md1, but is present in md2); this would not be found above
		for (var i=0;i<length2-1;i++){
			if (foundMatches[i]==false) {
				md1.push(md2[i+1].clone());
				returnItem.membersToAdd=returnItem.membersToAdd.concat(m_membersHierarchy(md2[i+1],hierarchy));
				return returnItem;
			}
		}
	}
	//this return statement must be here in case no or-state is found at the current level
	return returnItem;
}

/*As long as no tags in md1 and md2 have yet been found to match, continues to search for a matching tag*/
function stateChangeFindParent(md1,md2, returnItem, hierarchy)
/*TO DO: make sure we don't have to check for md2 having multiple components, check all of them against all of md1*/
{  
	if (md1[0][2]==="with"){
		hierarchy=[md1[0][0]];
	}
	else{
		hierarchy.push(md1[0][0]);
	}
	if (md1[0][0]===""){
		//the members for this tag, if there are any, were added using the with operator
		returnItem.withMembers1 = returnItem.withMembers1.concat(md1[0][1]);
	}
	if (md2[0][0]===""){
		var length=md2.length;
		for (var i=1;i<length;i++){
			stateChangeFindParent(md1,md2[i],returnItem,hierarchy);
		}
		//the members for this tag, if there are any, were added using the with operator
		returnItem.withMembers2 = returnItem.withMembers2.concat(md2[0][1]);
	}
	else if (md1[0][0]===md2[0][0]){
		//matched the parent tag, must descend the trees
		returnItem.matched=true;
		returnItem = stateChangeDescend(md1,md2,returnItem,hierarchy);
		return returnItem;
	}
	else{
		//have not yet found any matching tag
		var length=md1.length;
		for (var i=1;i<length;i++){
			stateChangeFindParent(md1[i],md2,returnItem,hierarchy);
		}
	}
	return returnItem;
}

/*Enacts state change in accordance with Plaid semantics, transitioning obj1 to obj2, essentially obj1 <- obj2; fixes trees and members both; checks unique tags and unique members; no return value*/
function m_stateChange(obj1,obj2){
	var md1=obj1.tree;
	var md2=obj2.tree;
	var returnItem={
		matched:false,
		membersToAdd:[],
		membersToRemove:[],
		tree:md1,
		members1:obj1.membersUnique(),
		withMembers1:[],
		withMembers2:[]
  }

	//ensure that the state (to which the object is changing) has unique members
	var unique = m_checkUniqueMembers(md2,[],[]);
	if (unique['unique']===false) {
		throw "Error: State change violates unique members by attempting to change to a state that contains "+unique['member']+" twice.";
	}

	var returnItem=stateChangeFindParent(md1,md2,returnItem,[]);
	if (returnItem.matched===false){
		throw "Error: Attempting to change to an undeclared state (" + md2[1][0][0] + ")"; 
		//target dimension does not yet exist in the object to be changed
		var md2Length=md2.length;
		for (var i=1;i<md2Length;i++){
			returnItem.tree.push(md2[i].clone());
		}
		returnItem.membersToAdd=m_membersHierarchy(md2,[]);
	}

	var add=returnItem.membersToAdd;
	var remove=returnItem.membersToRemove;
	var members=returnItem.members1;
	var addLength=add.length;
	var removeLength=remove.length;
	var membersLength=returnItem.members1.length;

	//check unique members
	for (var j=0;j<addLength;j++){
		for (var i=0;i<membersLength;i++){
			if (add[j]['memberName']===members[i]['memberName']){
				//a member to be added is already present in the object; this is appropriate if the member is present in the member's own hierarchy, or if the member is being removed
				//because both md1 and md2 must be well-formed at the start, if memberName is on the remove list, we know that it is appropriate to add it, because it can only be removed if the current branch is removing all old x members, or if it is itself in a path that is permitted to have x
				if (has(remove,add[j]['memberName'])===false){
					if (has(add[j]['hierarchy'],members[i]['tag'])===false) {
						throw "Error: state change violates unique members by attempting to add "+add[j]['memberName']+" to item that already contains "+add[j]['memberName'];
					}
				}
			}
		}
	}

	//check unique tags
	var tags=obj1.tags();
	var tagsLength=tags.length;
	for (var j=0;j<tagsLength;j++){
		if(count(tags,tags[j])>1){
			throw "Error: state change violates unique tags by containing tag "+tags[j]+" twice";
		}
	}

	//check that members not associated with tags are not present in both states, throw error if they are
	var wm1 = returnItem.withMembers1;
	var wm2 = returnItem.withMembers2;

	if (wm2.length>0) {
		var wm1Length=wm1.length;
		for (var j=0;j<wm1Length;j++){
			if (has(wm2,wm1[j])){
				throw "Error: state change violates unique members by attempting to add "+wm1[j]+" to item that already contains "+wm1[j];
			}
		}
		//since no error, the members from md2 should be added
		add=add.concat(wm2);
		//must also add these members to the tree 
		md1.push([["",wm2,"with"]]); 
	}

	//must update add because you may have concatenated new members to add
	var addLength=add.length;
	var removeLength=remove.length;

	//remove the members that need to be removed
	for (var j=0;j<removeLength;j++){
		//document.write("remove "+remove[j]+"<br>");
		if(Plaid.isCallbackOnPassivate(remove[j])){
			var result = obj1.executeMethodChain(remove[j]);
			if(result !== false){
				obj1[remove[j]]();
			}
		}
		delete obj1[remove[j]];
	}

	//add the members that need to be added
	for (var j=0;j<addLength;j++){
		//document.write("add "+add[j]['memberName']+"<br>");
		var mn = add[j]['memberName'];

		addMember(obj1,mn,obj2[mn]);
		
		if(Plaid.isCallbackOnActivate(mn)){
			var result = obj1.executeMethodChain(mn);
			if(result !== false){
				obj1[mn]();
			}
		}
	}
}

/*adds memberName as a member to obj, assigns memberName's value to be memberValue; this method handles the case where a member and not a state is passed in*/
m_stateChangeMember = function(obj, memberName,memberValue) {
	//check unique members
	//because this member is being added at the top level, there is no way for it to share a hierarchy with any other member, and thus this must be the only member in the object with its name
	var members1=obj.members();
	var members1Length=members1.length;
	for (var j=0;j<members1Length;j++){
		if(members1[j]===memberName){
			throw "Error: state change operation violates unique members by attempting to add member "+members1[j]+" to state that already contains "+members1[j];
		}
	} 
	addMember(obj,memberName,memberValue);

	obj.tree.push([["",[memberName],"with"]]);  
}

/*adds memberName as a member to obj, assigns memberName's value to be memberValue; this method handles the case where a member and not a state is passed in, and the member is given no value, and is only declared*/
m_stateChangeMemberNoValue = function(obj, memberName) {
	var md1=obj.tree;

	//check unique members
	//because this member is being added at the top level, there is no way for it to share a hierarchy with any other member, and thus this must be the only member in the object with its name
	var members1=obj.members();
	var members1Length=members1.length;
	for (var j=0;j<members1Length;j++){
		if(members1[j]===memberName){
			throw "Error: state change operation violates unique members by attempting to add member "+members1[j]+" to state that already contains "+members1[j];
		}
	} 

	obj.tree.push([["",[memberName],"with"]]);
}

/*Returns true if item is contained in array, false if it is not*/
function has(array, item){
	return false;
	var length=array.length;
	for (var i=0;i<length;i++){
		if (array[i]===item){
			return true;
		}
	}
	return false;
}

/*Counts the number of appearances of item in array, returns count*/
function count(array, item){
	var length=array.length;
	var itemCount=0;
	for (var i=0;i<length;i++){
		if (array[i]===item){
			itemCount++;
		}
	}
	return itemCount;
}

/*Returns true if the object on which the function is called contains the given tag, else false*/
PlaidObject.prototype.match = function(tag) {
	return m_match(this.tree,tag);
}

/*Returns an array of the tags of the object on which the function is called*/
PlaidObject.prototype.tags=function() {
	return m_tags(this.tree);
}

/*Returns an array of the members of the object on which the function is called*/
PlaidObject.prototype.members=function() {
	return m_members(this.tree);
}

/*Returns an array of the members of the object on which the function is called; each member is stored in a memberItem, containing the member's name and a list of the tags in the hierarchy called tagList*/
PlaidObject.prototype.membersUnique=function() {
	return m_membersUnique(this.tree);
}

/*Enacts state change according to current Plaid semantics (June 2011), equivalent to this <- state ;  transitions the object on which it is called to the state that is passed in*/
PlaidObject.prototype.stateChange=function(state) {
	return m_stateChange(this,state);
}

/*Enacts state change according to current Plaid semantics (June 2011), equivalent to this <- state ;  transitions the object on which it is called to the state that is passed in; this method handles state transition when only a member and not a state is passed in*/
PlaidObject.prototype.stateChangeMember=function(member, value) {
	return m_stateChangeMember(this,member,value);
}

/*Enacts state change according to current Plaid semantics (June 2011), equivalent to this <- state ;  transitions the object on which it is called to the state that is passed in; this method handles state transition when only a member and not a state is passed in, and the member is declared without a value*/
PlaidObject.prototype.stateChangeMemberNoValue=function(member) {
	return m_stateChangeMemberNoValue(this,member);
}

/*Transitions the object on which it is called into exactly the state of the state that is passed in; removes all current members and tags, adds all members and tags of state*/
PlaidObject.prototype.replace=function(state) {

	//ensure that the state (to which the object is changing) has unique members
	var unique = m_checkUniqueMembers(state.tree,[],[]);
	if (unique['unique']===false) {
		throw "Error: Replace operation violates unique members by attempting to change to a state that contains "+unique['member']+" twice.";
	}

	//check unique tags
	var tags=state.tags();
	var tagsLength=tags.length;
	for (var j=0;j<tagsLength;j++){
		if(count(tags,tags[j])>1){
			throw "Error: Replace operation violates unique tags by containing tag "+tags[j]+" twice";
		}
	}


	var add=state.members();
	var remove=this.members();
	var addLength=add.length;
	var removeLength=remove.length;

	for (var j=0;j<addLength;j++){
		addMember(this,add[j],state[add[j]]);
	}
	for (var j=0;j<removeLength;j++){
		delete this[remove[j]];
	}
	this.tree=state.tree.clone();

}

/*Returns a PlaidState object with the same tree and members as the object on which it is called*/
PlaidObject.prototype.freeze=function() {
	var obj=new PlaidState();
	obj.setTree(this.tree.clone());
	var i;
	//right now this copies any method that is not the ones listed in the first condition below
	for (i in this) {
		if ( i==="tree" || i==="match" || i==="tags" || i==="members" || i=="membersUnique" || i=="stateChange" || i==="stateChangeMember" || i==="stateChangeMemberNoValue" || i==="replace" || i==="freeze" || i==="clone" || i==="begetObj" || i==="executeMethodChain") {
			continue;
		}
		else {
			addMember(obj,i,this[i]);
		}
	}
	return obj;
}

/*a helper function that adds member to obj1, giving it the name memberName;  currently just copies all members and adds the copy;  in future, could be made more efficient*/
function addMember(obj1,memberName,member){
	//    if (typeof member == "object") {
	if (member instanceof PlaidObject) {
    obj1[memberName]=member.clone();
  } 
  else {
    obj1[memberName] = member;
  }
}
