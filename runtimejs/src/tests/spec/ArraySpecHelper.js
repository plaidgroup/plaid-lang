

beforeEach(function() {

  this.addMatchers({
    toContainSameElementsAs: function(testArr) {
      if (this.actual.length !== testArr.length) return false;
	for (var i = 0; i < this.actual.length; i++) {
          var idx = testArr.indexOf(this.actual[i]);
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
    toHaveSameElementsAs: function(testArr) {
      if (this.actual.length !== testArr.length) {
        return false;
      }
      for (var i = 0; i < this.actual.length; i++) {
        var countNum = count(testArr,this.actual[i]);
        if(countNum!=1) {
          return false;
        }
      }
      return true;
    }
  });

});
