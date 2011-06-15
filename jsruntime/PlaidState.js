<html>
<body>
<script type="text/javascript">
<!--

Object.prototype.clone = function() {
  var obj = (this instanceof Array) ? [] : {};
  var i;
  for (i in this) {
    if (i == 'clone') continue;
    if (this[i] && typeof this[i] == "object") {
      obj[i] = this[i].clone();
    } else obj[i] = this[i]
  } return obj;
}

function PlaidObject(t){
   this.tree=t;

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

/*returns an array of all the tags of the state that is reflected in the current metadata; currently does not include and-state tags*/
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


function stateChangeDescend(md1,md2, returnItem){
   //becuase this function has been called, we know that md1[0][0]=md2[0][0]
   var length1=md1.length;
   var length2=md2.length;
   if(length1===1 && length2===1){
      //this branch already matches
      return returnItem;
   }
   else{
      /*the nested for loops below continue matching and-states, descending down matching ones; if one of md2's and-states is not found in md1, it is ignored; or-states are also matched if the same or-state is present in both md1 and md2*/
      for (var i=1;i<length2;i++){
         for (var j=1;j<length1;j++){
            if (md2[i][0][0]===md1[j][0][0]){
               returnItem=stateChangeDescend(md1[j],md2[i],returnItem);
               break;
            }
         }
      }
      /*there could also exist differing or-states in md1 and md2; if an or-state needs to be changed, it is identified below*/
      for (var i=1;i<length2;i++){
         for (var j=1;j<length1;j++){
            if (md2[i][0][2]===md1[j][0][2] && md2[i][0][2]==="" && md2[i][0][0]!==md1[j][0][0]){
               returnItem.membersToAdd=returnItem.membersToAdd.concat(m_members(md2[i]));
               returnItem.membersToRemove=returnItem.membersToRemove.concat(m_members(md1[j]));
               md1[j]=md2[i].clone();
               return returnItem;
            }
         }
      }
   }
   //this return statement must be here in case no or-state is found at the current level
   return returnItem;
}

/*currently finds all the members that need to removed and added to enact state change*/
function stateChangeFindParent(md1,md2, returnItem)
{  
   if (md2[0][0]===""){
      var length=md2.length;
      for (var i=1;i<length;i++){
          stateChangeFindParent(md1,md2[i],returnItem);
      }
   }
   else if (md1[0][0]===md2[0][0]){
      //matched the parent tag, must descend the trees
      returnItem.matched=true;
      returnItem = stateChangeDescend(md1,md2,returnItem);
      return returnItem;
   }
   else{
      //have not yet found any matching tag
      var length=md1.length;
      for (var i=1;i<length;i++){
          stateChangeFindParent(md1[i],md2,returnItem);
      }
   }
   return returnItem;
}

function m_stateChange(obj1,obj2){
   var md1=obj1.tree;
   var md2=obj2.tree;
   var returnItem={
      matched:false,
      membersToAdd:[],
      membersToRemove:[],
      tree:md1,
      members1:obj1.members
   }

   var returnItem=stateChangeFindParent(md1,md2,returnItem);
   if (returnItem.matched===false){
      //target dimension does not yet exist in the object to be changed
      var md2Length=md2.length;
      for (var i=1;i<md2Length;i++){
         returnItem.tree.push(md2.clone());
      }
      returnItem.membersToAdd=obj2.members;
   }
   document.write(returnItem.membersToAdd+"<BR>");
   document.write(returnItem.membersToRemove+"<BR>");

   var add=returnItem.membersToAdd;
   var remove=returnItem.membersToRemove;
   var members=returnItem.members1;
   var addLength=add.length;
   var removeLength=remove.length;
   var membersLength=returnItem.members1.length;

   //check unique members
   for (var j=0;j<addLength;j++){
      if (has(members,add[j])){
         if (!has(remove,add[j])){
            document.write("Error: state change violates unique members by attempting to add "+add[j]+" to item that already contains "+add[j]+"<br>");
         }
      }
   }

   //check unique tags
   var tags=obj1.tags;
   var tagsLength=tags.length;
   for (var j=0;j<tagsLength;j++){
      if(count(tags,tags[j])>1){
         document.write("Error: state change violates unique tags by containing tag "+tags[j]+" twice.<br>");
      }
   }

   for (var j=0;j<addLength;j++){
      //addMember(obj1,add[j],obj2[add[j]]);
      obj1[add[j]]=obj2[add[j]];
   }
   for (var j=0;j<removeLength;j++){
      delete obj1[remove[j]];
   }
}

function has(array, item){
   var length=array.length;
   for (var i=0;i<length;i++){
      if (array[i]===item){
         return true;
      }
   }
   return false;
}

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

PlaidObject.prototype.match = function(tag) {
   return m_match(this.tree,tag);
}

PlaidObject.prototype.tags=function() {
   return m_tags(this.tree);
}

PlaidObject.prototype.members=function() {
   return m_members(this.tree);
}

PlaidObject.prototype.stateChange=function(state) {
   return m_stateChange(this,state);
}

PlaidObject.prototype.replace=function(state) {
   var add=state.members();
   var remove=this.members();
   var addLength=add.length;
   var removeLength=remove.length;

   for (var j=0;j<addLength;j++){
      //addMember(this,add[j],state[add[j]]);
      this[add[j]]=state[add[j]];
   }
   for (var j=0;j<removeLength;j++){
      delete this[remove[j]];
   }
  this.tree=state.tree;
}

PlaidObject.prototype.freeze=function() {
   var obj=new PlaidState(this.tree.clone());
   var i;
   for (i in this) {
      if (!obj.hasOwnProperty(i) || i==="tree") {
         continue;
      }
      if (this[i] && typeof this[i] == "object") {
         obj[i] = this[i].clone();
      } 
      else {
         obj[i] = this[i];
      }
   }
   return obj;
}

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


function PlaidState(t){
   this.tree=t;
}

PlaidState.prototype.members=function() {
   return m_members(this.tree);
}

PlaidState.prototype.instantiate = function() {
   var obj=new PlaidObject(this.tree.clone());
   copyMembers(obj,this);
   return obj;
}

PlaidState.prototype.remove = function(member) {
   var obj=this.instantiate();
   if(s_remove(obj.tree,member)===false){
      document.write("Error: attempt to remove a member "+member+" that does not exist in the state.<br>");
      return;
   }
   delete obj[member];
   return obj;
}

PlaidState.prototype.rename = function(member, newName) {
   var currMembers=this.members();
   if (has(currMembers,newName)){
      document.write("Error: attempt to rename a member with name "+newName+" which already names a member.<br>");
   }
   var obj=this.instantiate();
   if(s_rename(obj.tree,member,newName)===false){
      document.write("Error: attempt to rename a member "+member+" that does not exist in the state.<br>");
      return;
   }
   addMember(obj, newName, obj[member]);
   delete obj[member];
   return obj;
}

PlaidState.prototype.with = function(state) {
   var obj=new PlaidObject(this.tree.clone());
   var i;
   copyMembers(obj,this);
   copyMembers(obj,state);
   var md2=state.tree;
   var md2Length=md2.length;
      for (var i=1;i<md2Length;i++){
         obj.tree.push(md2.clone());
      }
   return obj;
}

/*copy members of obj2 to obj1*/
function copyMembers(obj1,obj2){
   var i;
   for (i in obj2) {
      if (!obj2.hasOwnProperty(i)||i==="tree") {
         continue;
      }
      if (obj2[i] && typeof obj2[i] == "object") {
         addMember(obj1,i,obj2[i]);
      } 
      else {
         obj1[i] = obj2[i];
      }
   }
   return;
}

function addMember(obj1,memberName,member){
   obj1[memberName]=member.clone();
}

var metadata3=[["",[]],[["car",[],"with"],[["drivingStatus",[],"with"],[["driving",["speed","acceleration","stopDriving"],""],[["brakingStatus",[],"with"],[["braking",["stopBraking"],""]]],[["directionStatus",[],"with"],[["turningLeft",["straight","turnRight"],""]]]]],[["cleanStatus",[],"with"],[["clean",["getDirty"],""]]]]];

var metadata4=[["",[]],[["brakingStatus",[],"with"],[["notBraking",["startBraking"],""],[["speedIncreasing",[],""]]]]];

//document.write(equal(metadata3,metadata4)+"<BR>");
//document.write(match(metadata4,"drivingStatus")+"<BR>");
//document.write(members(metadata3)+"<BR>");

var obj1=new PlaidObject(metadata3);
var obj2=new PlaidState(metadata4);

document.write("<br><br>");
var obj8=new PlaidObject(metadata3);
var obj9=new PlaidState(metadata4);
for (item in obj8){
   document.write(item+"<BR>")
}
document.write(obj8.tree+"<BR>");
obj8.replace(obj9);
for (item in obj8){
   document.write(item+"<BR>")
}
document.write(obj8.tree+"<BR>");
for (item in obj9){
   document.write(item+"<BR>")
}
document.write(obj9.tree+"<BR>");


/*
obj1.stateChange(obj2);
for (item in obj2){
   document.write(item+"<BR>")
}
document.write(obj1.tree+"<BR>");

document.write("<br><br>");
var obj3=new PlaidState(metadata3);
for (item in obj3){
   document.write(item+"<BR>")
}
document.write(obj3.tree+"<BR>");
obj3.remove("speed");
for (item in obj3){
   document.write(item+"<BR>")
}
document.write(obj3.tree+"<BR>");

document.write("<BR><BR>");
obj4=obj3.instantiate();
for (item in obj4){
   document.write(item+"<BR>")
}
document.write(obj4.tree+"<BR>");


document.write("<BR><BR>");
obj5=obj1.with(obj2);
for (item in obj5){
   document.write(item+"<BR>")
}
document.write(obj5.tree+"<BR>");


document.write("<br><br>");
var obj6=new PlaidState(metadata3);
for (item in obj6){
   document.write(item+"<BR>")
}
obj6.speed=5;
document.write(obj6.tree+"<BR>");
var obj7=obj6.rename("speed","hi");
for (item in obj6){
   document.write(item+"<BR>")
}
document.write(obj6.tree+"<BR>");
for (item in obj7){
   document.write(item+"<BR>")
}
document.write(obj7.tree+"<BR>");
*/


//-->
</script>
</body>
</html>