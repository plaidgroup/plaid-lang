<html>
<body>
<script type="text/javascript">
<!--

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
   var memberList=[];
   if (md1.length>0){
      var memberList=md1[0][1];
   }
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
               md1[j]=md2[i];
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
      for (var i=0;i<md2Length;i++){
         returnItem.tree.push(md2);
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

PlaidObject.prototype.stateChange=function(obj) {
   return m_stateChange(this,obj);
}

var metadata3=[["",[]],[["car",[],"with"],[["drivingStatus",[],"with"],[["driving",["speed","acceleration","stopDriving"],""],[["brakingStatus",[],"with"],[["braking",["stopBraking"],""],[["speedIncreasing",[],""]]]],[["directionStatus",[],"with"],[["turningLeft",["straight","turnRight"],""]]]]],[["cleanStatus",[],"with"],[["clean",["getDirty"],""]]]]];

var metadata4=[["",[]],[["car",[],"with"],[["drivingStatus",[],"with"],[["driving",["speed","acceleration","stopDriving"],""],[["brakingStatus",[],"with"],[["notBraking",["startBraking"],""],[["speedIncreasing",[],""]]]],[["directionStatus",[],"with"],[["turningLeft",["straight","turnRight"],""]]]]],[["cleanStatus",[],"with"],[["clean",["getDirty"],""]]]]];

//document.write(equal(metadata3,metadata4)+"<BR>");
//document.write(match(metadata4,"drivingStatus")+"<BR>");
//document.write(members(metadata3)+"<BR>");

var obj1=new PlaidObject(metadata3);
var obj2=new PlaidObject(metadata4);

obj2.stateChange(obj1);
for (item in obj2){
   document.write(item+"<BR>")
}
document.write(obj2.tree);

//-->
</script>
</body>
</html>