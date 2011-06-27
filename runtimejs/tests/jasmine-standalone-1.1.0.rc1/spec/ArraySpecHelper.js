

beforeEach(function() {

  this.addMatchers({
    toContainSameElementsAs: function(testArr) {
      if (this.length !== testArr.length) return false;
	for (var i = 0; i < this.length; i++) {
          var idx = testArr.indexOf(this[i]);
          if(idx!=-1) {
            testArr.splice(idx, 1);
          }
          else {
            return false;
          }
        }
      if(testArr.length==0) {
        return true;
      }
      else {
        return false;
      }
    }
  });

  this.addMatchers({
    toHaveSameContentsAs: function(testArr) {
      if (this[0]==testArr[0]){
        return true;
      }
      return false;
    }
  });

  this.addMatchers({
    toHaveSameElementsAs: function(testArr) {
      if (this.length !== testArr.length) {
        return false;
      }
      for (var i = 0; i < this.length; i++) {
        var countNum = count(testArr,this[i]);
        if(countNum!=1) {
          return false;
        }
      }
      return true;
    }
  });

});
