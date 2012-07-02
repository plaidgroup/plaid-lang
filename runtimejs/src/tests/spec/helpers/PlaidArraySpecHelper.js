beforeEach(function() {
	this.addMatchers({
		toHaveSameElements: function(testArr){
			if (this.actual.length !== testArr.length) {
        return false;
      }
      for (var i = 0; i < this.actual.length; i++) {
        if(this.actual[i] != testArr[i]){
          return false;
        }
      }
      return true;
    }
	});
});
