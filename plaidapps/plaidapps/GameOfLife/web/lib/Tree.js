function CaseOfNode(tag,memberList,parentNode){
  this.tag=tag;
  this.memberList=memberList;
  this.isWith=false;
  this.children=[];
  if (typeof(parentNode)!='undefined'){
    parentNode.children.push(this);
  }
}

function WithNode(tag,memberList,parentNode){
  this.tag=tag;
  this.memberList=memberList;
  this.isWith=true;
  this.children=[];
  if (typeof(parentNode)!='undefined'){
    parentNode.children.push(this);
  }
}

function Tree(){
  this.root=new WithNode("",[]);
}

Tree.prototype.toMetadata = function(currRoot) {
  currRoot = typeof(currRoot) != 'undefined' ? currRoot : this.root;
  var type;
  if (currRoot.isWith==true){
    type="with";
  }
  else {
    type="";
  }
  var listToReturn=[[currRoot.tag,currRoot.memberList,type]];
  var length=currRoot.children.length;
  for (var i=0;i<length;i++){
    listToReturn.push(this.toMetadata(currRoot.children[i]));
  }
  return listToReturn;
}
