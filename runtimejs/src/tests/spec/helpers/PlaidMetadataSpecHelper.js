beforeEach(function() {
	var compareEntry = function(entry1, entry2) {
		if(entry1.tag != entry2.tag){
			return false;
		}
		if(!compareMemberList(entry1.members(), entry2.members())){
			return false;
		}
		if(entry1.type != entry2.type) {
			return false;
		}
		return true;
	}

	var compareMemberList = function(list1, list2) {
		if (list1.length != list2.length) {
        return false;
      }
      for (var i = 0; i < list1.length; i++) {
        if(list1[i] != list2[i]){
          return false;
        }
      }
      return true;
	}

	var compareMetadata = function(md1, md2) {
		if(!compareEntry(md1.entry(), md2.entry())){
			return false;
		}
		for(var i = 0; i < md1.children().length; i++){
			compareMetadata(md1.children().get(i), md2.children().get(i));
		};
		return true;
	}

	this.addMatchers({
		toHaveSameMetadata: function(that){
			if(this.actual.length != that.length) {
				return false;
			}
			return compareMetadata(this.actual, that);
    }
	});
});
