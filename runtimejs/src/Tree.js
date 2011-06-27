function Node(tag,memberList,withOrCaseOfType,parentNode){
  this.tag=tag;
  this.memberList=memberList;
  this.type=withOrCaseOfType;
  this.children=[];
  if (typeof(parentNode)!='undefined'){
    parentNode.children.push(this);
  }
}

function Tree(){
  this.root=new Node("",[],"with");
}

Tree.prototype.toMetadata = function(currRoot) {
  currRoot = typeof(currRoot) != 'undefined' ? currRoot : this.root;
  var type="";
  if (currRoot.type=="with"){
    type="with";
  }
  var listToReturn=[[currRoot.tag,currRoot.memberList,type]];
  var length=currRoot.children.length;
  for (var i=0;i<length;i++){
    listToReturn.push(this.toMetadata(currRoot.children[i]));
  }
  return listToReturn;
}
