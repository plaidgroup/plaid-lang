var Plaid = (function() {
	var Plaid = {
		/* registered keyword for PlaidState and PlaidObject */
		keyword: "__plaid__",
		superclass: "__super__",
		/* extend class */
		extend:  function (s, c) {
			function f(){};
			f.prototype = s.prototype;
			c.prototype = new f();
			c.prototype[Plaid.superclass] = s.prototype;
			c.prototype[Plaid.superclass].constructor = s;
			c.prototype.constructor = c;
			return c;
		},
		/* deep_clone object */
		clone: function(obj) {
			var clone = new obj.constructor();
			for(var prop in obj) {
				if(prop == Plaid.superclass) {
					clone[Plaid.superclass] = obj[prop];
				} else if(obj[prop] && typeof obj[prop] == "object"){
					clone[prop] = Plaid.clone(obj[prop]);
				} else {
					clone[prop] = obj[prop];
				}
			}
			return clone;
		},
		/* True and False states */
		returnTrue: function() { return true; },
		plaidNewState_True: {
			instantiate: function() { return Plaid.returnTrue(); }
		},
		returnFalse: function() { return false; },
		plaidNewState_False: {
			instantiate: function() { return Plaid.returnFalse(); }
		},
		/* None state */
		plaidNewState_None: {
			instantiate: function() { return Plaid.returnNull(); }
		},
		returnNull: function() { return null; },
		/* Array state */
		plaidNewState_Array: {
			instantiate: function() { return Plaid.returnArray(); }
		},
		returnArray: function() { return new Plaid.Array(); },
		/* Date state */
		plaidNewState_Date: {
			instantiate: function() { return Plaid.returnNewDate(); }
		},
		returnObject: function() { return new Object(); }, 
		returnNewDate: function() {return new Date();},

		/* if function */
		if$plaid: function(bool) {
			if (bool===true) {
				var whenTrue = function(clause) {
					var value = clause();
					return value;
				};
				var returnVal = whenTrue;
			}
			else if (bool===false) {
				var whenFalse = function(clause) {
					return undefined;
				};
				var returnVal = whenFalse;
			}
			return returnVal;
		},

		/* ifElse function */
		ifElse: function(bool) {
			var returnVal;
			if (bool===true) {
				var whenTrue = function(trueClause) {
					var whenFalse = function(falseClause) {
						var value = trueClause();
						return value;
					};
					return whenFalse;
				};
				returnVal = whenTrue;
			}
			else if (bool===false) {
				var whenTrue = function(trueClause) {
					var whenFalse = function(falseClause) {
						var value = falseClause();
						return value;
					};
					return whenFalse;
				};
				returnVal = whenTrue;
			}
			return returnVal;
		},

		/* while function */
		while$plaid: function(cond) {
			var whileClause = function(body) {	
				while (cond()){
					var result=body();
				}
				return result;
			};
			return whileClause;
		},

		/* determine callback function from name */
		isCallback: function(name) {
			return this.isCallbackOnActivate(name) || this.isCallbackOnPassivate(name);
		},
		isCallbackOnActivate: function(name) {
			return name.indexOf("on") == 0;
		},
		isCallbackOnPassivate: function(name) {
			return name.indexOf("off") == 0;
		}
	};


	/* Array specialized for Plaid */
	Plaid.Array = Plaid.extend(Array, function(){
		this[Plaid.superclass].constructor();

		/* initialization */
		if(arguments.length == 1 && typeof arguments[0] == "number") {
			this.length = arguments[0];
		} else {
			for(var i = 0; i < arguments.length; i++){
				this.push(arguments[i]);
			}
		}

		/* remove all elements of array */
		this.clear = function() {
			while(this.length > 0){
				this.pop();
			}
		}

		/* apply funtion to elements until function return true */
		this.until = function(f) {
			for(var i = 0; i < this.length; i++) {
				var done = f(this.get(i), i);
				if(done) {
					return true;
				}
			}
			return false;
		}

		/* apply function to all elements */
		this.each = function(f) {
			for(var i = 0; i < this.length; i++) {
				f(this.get(i), i);
			}
		}

		/* returns index of itme if it is contained in array, -1 if it is not*/
		this.indexOf =  function(item) {
			var idx = -1;
			this.until(function(elm, i) {
				if (elm === item){
					idx = i
					return true;
				}
				return false;
			});

			return idx;
		}

		/* returns number of item in array */
		this.count = function(item) {
			var count = 0;
			this.each(function(elm) {
				if(elm === item){
					count++;
				}
			});
			return count;
		}

		/* returns new concatinated array */
		this.concat = function() {
			var a = new Plaid.Array();
			this.each(function(elm) {
				a.push(elm);
			});
			
			for(var i = 0; i < arguments.length; i++){
				var arg = arguments[i];
				if(arg instanceof Plaid.Array){
					arg.each(function(elm){
						a.push(elm);
					});
				} else if(arg){
					a.push(arg);
				}
			}
			return a;
		}

		/* append element(s) to the tail of this array (works like a bang method of concat) */
		this.append = function() {
			var a = this;
			for(var i = 0; i < arguments.length; i++){
				var arg = arguments[i];
				if(arg instanceof Plaid.Array){
					arg.each(function(elm){
						a.push(elm);
					});
				} else {
					a.push(arg);
				}
			}
		}

		/* returns sliced array from start to end - 1. objects are not cloned */
		this.slice = function(start, end) {
			var array = new Plaid.Array();
			if(!end) {
				end = this.length;
			}
			for(var i = start; i < end; i++) {
				array.push(this.get(i));
			}
			return array;
		}

		/* returns the first element of this array */
		this.first = function() {
			return this[0];
		}

		/* returns the last element of this array */
		this.last = function() {
			return this[this.length-1];
		}

		/* Anything declared in Plaid as an Array should be able to use all the methods available to JavaScript arrays */
		this.get = function(i){
			return this[i];
		}

		this.set = function(i, value){
			this[i]=value;
		}

		this.getArrayWithSize = function(size){
			return new Plaid.Array(size);
		}
	});

	/* Abstract Class which has common methods of concrete classes (PlaidState and PlaidObject) */
	Plaid.AbstractPlaidBase = Plaid.extend(Object, function(){
		this.begetObj = function() {
			function F() {};
			F.prototype = Plaid.clone(this);
			return new F();
		};
		/*set the metadata */
		this.setMetadata = function(metadata) {
			this[Plaid.keyword].metadata = metadata;
		};

		/* get the metadata */
		this.getMetadata = function() {
			return this[Plaid.keyword].metadata;
		};

		/* returns an array of all the fields and methods of the object on which the function is called */
		this.members = function() {
			return this.getMetadata().members();
		};

		/* returns an array of the tags of the object on which the function is called */
		this.tags = function() {
			return this.getMetadata().tags();
		}

		/* a helper function that adds member to object, giving it the name memberName;  currently just copies all members and adds the copy;  in future, could be made more efficient */
		this.addMember = function(memberName, member){
			if (member == null) {
				this[memberName] = null;
			}	else if (typeof member == "object") {
				this[memberName] = Plaid.clone(member);
			} else if (Plaid.isCallback(memberName)) {
				this.addCallback(memberName, member);
			}	else {
				this[memberName] = member;
			}
		}

		/* copies members from obj */
		this.copyMembers = function(obj){
			for (var prop in obj) {
				if (!obj.hasOwnProperty(prop)) {
					/* no instance specific data is inherited, thus just skip them */
					continue;
				} 
				if(prop == Plaid.keyword){
					/* Plaid specific data should be treated specifically */
					continue;
				}
				this.addMember(prop, obj[prop]);
			}

			var current = this[Plaid.keyword].prepend;
			var additional = obj[Plaid.keyword].prepend;
			for(var prop in additional) {
				if(current[prop] == undefined){
					current[prop] = additional[prop];
				} else {
					current[prop] = additional[prop].concat(current[prop]);
				}
			}
			return;
		}

		/* returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag names in its hierarchy */
		this.membersHierarchy = function() {
			return this.getMetadata().membersHierarchy(new Plaid.Array());
		}

		/* check the uniqueness of members in this object */
		this.checkUniqueMembers = function(message) {
			var unique =  this.getMetadata().checkUniqueMembers(new Plaid.Array(), new Plaid.Array());
			if (unique['unique']===false) {
				throw message.replace("$1", unique['member']);
			}
		}

		/* check the uniqueness of tags in this object */
		this.checkUniqueTags = function(message) {
			var tags = this.tags();
			tags.each(function(tag, i) {
				if(tags.slice(i+1).count(tag) >= 1) {
					throw message.replace("$1", tag);
				}
			});
		}

		this.addCallback = function(name, callback) {
			var prepend = this[Plaid.keyword].prepend;
			if(prepend[name] == undefined){
				prepend[name] = new Plaid.Array();
			}
			
			if(prepend[name].indexOf(callback) == -1) {
				prepend[name].unshift(callback);
			}
		}
	});

	/* Abstract Class which has class methods of PlaidState */
	Plaid.AbstractPlaidState = Plaid.extend(Plaid.AbstractPlaidBase, function(){
		this[Plaid.superclass].constructor();

		/* returns a PlaidObject created from the PlaidState on which the function is called */
		this.instantiate = function() {
			// ensure that the state being instantiated has unique members
			this.checkUniqueMembers("Error: Instantiate violates unique members by attempting to create a state that contains $1 twice.");

			// check unique tags
			this.checkUniqueTags("Error: Attempt to instantiate violates unique tags by containing tag $1 twice.");

			var obj = new Plaid.PlaidObject(Plaid.clone(this.getMetadata()));
			obj.copyMembers(this);
			
			return obj;
		}

		/* returns a new state composed of both the state on which the function was called and the state that was passed in (should be object?) */
		this.with$plaid = function(state) {
			var obj = Plaid.clone(this);

			obj.copyMembers(state);
			var children = state.getMetadata().children();
			obj.getMetadata().addChildren(Plaid.clone(children));

			return obj;
		}

		/* returns a copy of the state on which it was called, copy lacks the member that was passed in */
		this.remove = function(member) {
			var obj = Plaid.clone(this);
			
			if(obj.getMetadata().remove(member) === false) {
				throw "Error: attempt to remove a member "+member+" that does not exist in the state";
				return;
			}
			obj[member] = undefined;
			return obj;
		}

		/* returns a copy of the state on which it was called, in copy member has the name newName */
		this.rename = function(member, newName) {
			var obj = Plaid.clone(this);

			if(obj.getMetadata().rename(member,newName) === false){
				throw "Error: attempt to rename a member "+member+" that does not exist in the state";
				return;
			}
			obj.addMember(newName, obj[member]);
			obj[member] = undefined;
			return obj;
		}

		/* returns a copy of the state on which it was called, in copy member has the value passed in; the member is associated with the tag passed in */
		this.specialize = function(tag, member, value) {
			var obj = Plaid.clone(this);

			/* if the member is callback, add to prepend and return */
			if(Plaid.isCallback(member)){
				obj.addCallback(member, value);
				return obj;
			}

			var entry = obj.getMetadata().findEntryByTag(tag);
			if(!entry) {
				return obj;
			}
			
			if (entry.members().indexOf(member) < 0){
				entry.addMember(member);
			}
			obj.addMember(member, value);

			return obj;
		}

		/* returns a new state composed of both the state on which the function was called and the member passed in, assigns memberName's value to be memberValue; this method handles the case where a member and not a state is passed in */
		this.withMember = function(memberName,memberValue) {
			var obj = Plaid.clone(this);

			obj.addMember(memberName, memberValue);
			obj.getMetadata().addWithEntry("", new Plaid.Array(memberName));
			
			return obj;
		}

		this.withMemberNoValue = function(memberName) {
			return this.withMember(memberName);
		}
	});

	/* Concrete Class for State */
	Plaid.PlaidState = Plaid.extend(Plaid.AbstractPlaidState, function(){
		this[Plaid.superclass].constructor();

		this[Plaid.keyword] = {};
		this[Plaid.keyword].metadata = (new Plaid.tree.Tree()).toMetadata();
		this[Plaid.keyword].prepend={};
	});

	/* Abstract Class which has class methods of PlaidObject */
	Plaid.AbstractPlaidObject = Plaid.extend(Plaid.AbstractPlaidBase,	function(){
		this[Plaid.superclass].constructor();

		/* returns true if the object on which the function is called contains the given tag, else false */
		this.match = function(tag) {
			return this.getMetadata().match(tag);
		}

		/* returns an array of the members of the object on which the function is called; each member is stored in a memberItem, containing the member's name and a list of the tags in the hierarchy called tagList */
		this.membersUnique = function() {
			return this.getMetadata().membersUnique();
		}

		/* transitions the object on which it is called into exactly the state of the state that is passed in; removes all current members and tags, adds all members and tags of state */
		this.replace = function(state) {
			//ensure that the state (to which the object is changing) has unique members
			state.checkUniqueMembers("Error: Replace operation violates unique members by attempting to change to a state that contains $1 twice.");

			//check unique tags
			state.checkUniqueTags("Error: Replace operation violates unique tags by containing tag $1 twice");

			var s = this;
			this.members().each(function(member){
				s[member] = undefined;
			});

			state.members().each(function(member){
				s.addMember(member, state[member]);
			});

			this.setMetadata(Plaid.clone(state.getMetadata()));
		}

		/* returns a PlaidState object with the same metadata and members as the object on which it is called */
		this.freeze = function() {
			var state = new Plaid.PlaidState();

			for (var prop in this) {
				if (this.hasOwnProperty(prop)) {
					state.addMember(prop, this[prop]);
				}
			}
			return state;
		}

		/* enacts state change in accordance with Plaid semantics, transitioning this to state which is passed, essentially this <- state; fixes trees and members both; checks unique tags and unique members; no return value */
		this.stateChange = function(state) {
			var returnItem={
				matched : false,
				membersToAdd : new Plaid.Array(),
				membersToRemove : new Plaid.Array(),
				metadata : this.getMetadata(),
				members1 : this.membersUnique(),
				withMembers1 : new Plaid.Array(),
				withMembers2 : new Plaid.Array()
			}

			//ensure that the state (to which the object is changing) has unique members
			state.checkUniqueMembers("Error: State change violates unique members by attempting to change to a state that contains $1 twice.");

			var returnItem = this.stateChangeFindParent(state, returnItem);
			if (returnItem.matched === false){
				//target dimension does not yet exist in the object to be changed
				returnItem.metadata.append(state.getMetadata().children());
				returnItem.membersToAdd = state.membersHierarchy();
			}

			var add = returnItem.membersToAdd;
			var remove = returnItem.membersToRemove;
			var members = returnItem.members1;

			//check unique members
			add.each(function(addItem) {
				members.each(function(member) {
					if(Plaid.isCallback(addItem.name)) {
						return;
					}
					if(addItem.name === member.name){
						//a member to be added is already present in the object; this is appropriate if the member is present in the member's own hierarchy, or if the member is being removed
						//because both md1 and md2 must be well-formed at the start, if memberName is on the remove list, we know that it is appropriate to add it, because it can only be removed if the current branch is removing all old x members, or if it is itself in a path that is permitted to have x
						if(remove.indexOf(addItem.name) < 0) {
							if(addItem.hierarchy.indexOf(member.tag) < 0){
								throw "Error: state change violates unique members by attempting to add " + addItem.name +" to item that already contains "+ addItem.name;
							}
						}
					}
				});
			});

			//check unique tags
			this.checkUniqueTags("Error: state change violates unique tags by containing tag $1 twice");

			//check that members not associated with tags are not present in both states, throw error if they are
			var wm1 = returnItem.withMembers1;
			var wm2 = returnItem.withMembers2;

			if (wm2.length > 0) {
				wm1.each(function(member){
					if(wm2.indexOf(member) >= 0) {
						throw "Error: state change violates unique members by attempting to add " + member + " to item that already contains " + member;
					}
				});

				//since no error, the members from md2 should be added
				add.append(wm2);
				//must also add these members to the tree 
				md1.addWithEntry("", wm2);
			}

			var s = this;
			//remove the members that need to be removed
			remove.each(function(member) {
				if(Plaid.isCallbackOnPassivate(member)){
					var result = s.executeMethodChain(member);
					if(result !== false && s[member.name]){
						s[member]();
					}
				}
				s[member] = undefined;
			});

			//add the members that need to be added
			add.each(function(member) {
				s.addMember(member.name, state[member.name]);
				if(Plaid.isCallback(member.name) && state[Plaid.keyword].prepend[member.name]){
					s[Plaid.keyword].prepend[member.name] = state[Plaid.keyword].prepend[member.name].concat(s[Plaid.keyword].prepend[member.name]);
				}
				if(Plaid.isCallbackOnActivate(member.name)){
					var result = s.executeMethodChain(member.name);
					if(result !== false && s[member.name]) {
						s[member.name]();
					}
				}
			});
		}
		
		/* enacts state change according to current Plaid semantics (June 2011), equivalent to this <- state ;  transitions the object on which it is called to the state that is passed in; this method handles state transition when only a member and not a state is passed in */
		this.stateChangeMember = function(memberName, memberValue) {
			//check unique members
			//because this member is being added at the top level, there is no way for it to share a hierarchy with any other member, and thus this must be the only member in the object with its name
			if(this.members().indexOf(memberName) >= 0){
				throw "Error: state change operation violates unique members by attempting to add member " + memberName + " to state that already contains " + memberName;
			}

			this.addMember(memberName, memberValue);
			this.getMetadata().addWithEntry("", new Plaid.Array(memberName));
		}

		this.stateChangeMemberNoValue = function(memberName) {
			this.stateChangeMember(memberName);
		}

		this.stateChangeFindParent = function(state, returnItem) {
 			return this.getMetadata().stateChangeFindParent(state.getMetadata(), returnItem, new Plaid.Array());
		}

		/* call functions in call back chain; stop calling when a function returns other than false */
		this.executeMethodChain = function(name) {
			var chain = this[Plaid.keyword].prepend[name];
			
			if(chain == undefined){
				return true;
			}

			var prevented = chain.until(function(fn) {
				var result = fn();
				if(result === false) {
					return true;
				}
				return false;
			});

			/* "return false" means "prevent next method call"  */
			return !prevented;
		}
	});


	Plaid.PlaidObject = Plaid.extend(Plaid.AbstractPlaidObject, function(metadata){
		this[Plaid.superclass].constructor();

		/* instance properties */
		this[Plaid.keyword] = {};
		this[Plaid.keyword].metadata = metadata;
		this[Plaid.keyword].prepend = {};
	});


  /* state tree */
	Plaid.tree = {};
	Plaid.tree.Tree = Plaid.extend(Object, function(){
		this.root = new Plaid.tree.WithNode("", new Plaid.Array());

		this.toMetadata = function() {
			return this.root.toMetadata();
		};
	});

	/* AbstractNode has common functions and variables */
	Plaid.tree.AbstractNode = Plaid.extend(Object, function(tag, memberList, parentNode, isWith){
		this.tag = tag;
		this.memberList = memberList;
		this.isWith = isWith;
		this.children = new Plaid.Array();
		if (typeof(parentNode) != 'undefined'){
			parentNode.children.push(this);
		}

		this.toMetadata = function() {
			var metadata = new Plaid.metadata.Metadata(this.createEntry());

			this.children.each(function(child){
				metadata.addChild(child.toMetadata());
			});

			return metadata;
		}
	});

	/* with node */
	Plaid.tree.WithNode = Plaid.extend(Plaid.tree.AbstractNode, function(tag, memberList, parentNode) {
		this[Plaid.superclass].constructor.call(this, tag, memberList, parentNode, true);

		this.createEntry = function(){
			return new Plaid.metadata.WithEntry(this.tag, this.memberList);
		}
	});

	/* caseof node */
	Plaid.tree.CaseOfNode = Plaid.extend(Plaid.tree.AbstractNode, function(tag, memberList, parentNode) {
		this[Plaid.superclass].constructor.call(this, tag, memberList, parentNode, false);

		this.createEntry = function(){
			return new Plaid.metadata.CaseOfEntry(this.tag, this.memberList);
		}
	});


	/* metadata of the state*/
	Plaid.metadata = {};

	Plaid.metadata.Metadata = Plaid.extend(Plaid.Array, function(entry) {
		this[Plaid.superclass].constructor();
		this.push(entry);
		
		/* the first element of this array is entry of this metadata */
		this.entry = function(){
			return this.first();
		}
		/* the following elements are the descendant metadatas */
		this.children = function() {
			return this.slice(1);
		}

		this.setChild = function(i, child) {
			this[i+1] = child;
		}

		this.addChild = function(child) {
			this.push(child);
			return this;
		}

		this.addChildren = function(children) {
			this.append(children);
			return this;
		}

		this.addWithEntry = function(tag, memberList) {
			this.push(new Plaid.metadata.Metadata(new Plaid.metadata.WithEntry(tag, memberList)));
		}
		
		/* returns an array of the tags */
		this.tags = function() {
			var array = new Plaid.Array();
			this.each(function(entry){
				array.append(entry.tags());
			});
			return array;
		}

		/* return an array of the members */
		this.members = function() {
			var array = new Plaid.Array();
			this.each(function(entry){
				array.append(entry.members());
			});
			return array;
		}

		/* find entry by tag name, return undefined if not found */
		this.findEntryByTag = function(tag) {
			if(this.entry().tag == tag) {
				return this.entry();
			}

			var result = undefined;
			this.children().until(function(child) {
				result = child.findEntryByTag(tag);
				if(result != undefined) {
					return true;
				}
				return false;
			});
			return result;
		}

		/* remove the member. return true if the member exists; return false otherwise */
		this.remove = function(member){
			var removed = this.until(function(entry){
				return entry.remove(member);
			});
			return removed;
		}

		/* rename member as newName. return true if the member exists; return false otherwise */
		this.rename = function(member, newName) {
			var renamed = this.until(function(entry){
				return entry.rename(member, newName);
			});
			return renamed;
		}

		/* tests whether the tag is included; returns true if yes, false if no; includes and-state tags*/
		this.match = function(tag) {
			var matched = this.until(function(entry) {
				return entry.match(tag);
			});
			return matched;
		}
		
		/* returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag name with which it is associated */
		this.membersUnique = function() {
			var array = new Plaid.Array();
			this.each(function(entry) {
				array.append(entry.membersUnique());
			});
			return array;
		}

		/* returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag names in its hierarchy */
		this.membersHierarchy = function(hierarchy) {
			var array = new Plaid.Array();
			this.each(function(entry) {
				array.append(entry.membersHierarchy(hierarchy));
			});
			return array;
		}

		/* returns an object with true in field 'unique' if the tree passed in reflects a state that has unique members, false otherwise; if unique members is false, the member that caused the conflict is in field 'member' */
		this.checkUniqueMembers = function(hierarchy,itemList){
			var itemToReturn = {};
			
			var ret = this.until(function(entry){
				itemToReturn = entry.checkUniqueMembers(hierarchy, itemList);
				return !itemToReturn['unique'];
			});

			return itemToReturn;
		}

		/* find mutual tag with this and md */
		this.stateChangeFindParent = function(md, returnItem, hierarchy) {
			/*TO DO: make sure we don't have to check for md having multiple components, check all of them against all of this*/
			if (this.entry().isWithEntry()){
				hierarchy= new Plaid.Array(this.entry().tag);
			}
			else{
				hierarchy.push(this.entry().tag);
			}

			if (this.entry().tag === ""){
				//the members for this tag, if there are any, were added using the with operator
				returnItem.withMembers1.append(this.entry().members());
			}

			if (md.entry().tag === ""){
				var t = this;
				md.children().each(function(child) {
					t.stateChangeFindParent(child, returnItem, hierarchy);
				});

				//the members for this tag, if there are any, were added using the with operator
				returnItem.withMembers2.append(md.entry().members());
			}
			else if (this.entry().tag === md.entry().tag){
				//matched the parent tag, must descend the trees
				returnItem.matched=true;
				returnItem = this.stateChangeDescend(md, returnItem, hierarchy);
				return returnItem;
			}
			else{
				//have not yet found any matching tag
				this.children().each(function(child){
					child.stateChangeFindParent(md, returnItem, hierarchy);
				});
			}
			return returnItem;
		}

		/* Once a matching tag has been found, descends the two trees to find any tags that do not match, make appropriate modifications to state */
		this.stateChangeDescend = function(md, returnItem, hierarchy) {
			var t = this;
			//becuase this function has been called, we know that this.entry().tag is equal to md.entry().tag
			if (this.entry().isWithEntry()){
				hierarchy = new Plaid.Array(this.entry().tag);
			}
			else{
				hierarchy.push(this.entry().tag);
			}

			if(this.length === 1 && md.length === 1){
				//this branch already matches
				return returnItem;
			}

			var foundMatches = new Plaid.Array();
			for (var i = 0; i < md.children().length; i++){
				foundMatches.push(false);
			}

			/* the nested for loops below continue matching and-states, descending down matching ones; if one of md's and-states is not found in this, it is ignored; or-states are also matched if the same or-state is present in both this and md */
			md.children().each(function(childOfMd, idx) {
				t.children().until(function(childOfThis) {
					if(childOfMd.entry().tag === childOfThis.entry().tag){
						foundMatches.set(idx, true);
						returnItem = childOfThis.stateChangeDescend(childOfMd, returnItem, hierarchy);
						return true;
					}
					return false;
				});
			});

			/* there could also exist differing or-states in md1 and md2; if an or-state needs to be changed, it is identified below */
			var children = t.children();			
			var result = md.children().until(function(childOfMd) {
				return children.until(function(childOfThis, idx) {
					if(childOfMd.entry().type === childOfThis.entry().type
						 && childOfMd.entry() instanceof Plaid.metadata.CaseOfEntry
						 && childOfMd.entry().tag !== childOfThis.entry().tag){
						returnItem.membersToAdd.append(childOfMd.membersHierarchy(hierarchy));
						returnItem.membersToRemove.append(childOfThis.members());
						t.setChild(idx, Plaid.clone(childOfMd));
						
						/* if an or-state was found that needs to be changed, it is not possible for an or-state to have to be added, since a state can only be in one or state at any given time */
						return true;
					}
					return false;
				});
			});

			if(result) {
				return returnItem;
			}

			//it is also possible that an or-state must be added (is not present in md1, but is present in md2); this would not be found above
			var result = md.children().until(function(child, idx){
				if(foundMatches.get(idx) == false) {
					t.addChild(Plaid.clone(child));
					returnItem.membersToAdd.append(child.membersHierarchy(hierarchy));
					return true;
				}
				return false;
			});
			if(result) {
				return returnItem;
			}

			//this return statement must be here in case no or-state is found at the current level
			return returnItem;
		}
	});
	
	Plaid.metadata.MemberWithTag = Plaid.extend(Object, function(name, tag) {
		this.name = name;
		this.tag = tag;
	});

	Plaid.metadata.MemberWithHierarchy = Plaid.extend(Object, function(name, hierarchy) {
		this.name = name;
		this.hierarchy = hierarchy;
	});

	Plaid.metadata.AbstractEntry = Plaid.extend(Object, function(tag, memberList, type) {
		this.tag = tag;
		this.memberList = memberList;
		this.type = type;

		/* returns tag as an array; empty array if it is null string */
		this.tags = function() {
			var array = new Plaid.Array();
			if(this.tag !== "") {
				array.push(this.tag);
			}
			return array;
		}

		/* retruns an array of members */
		this.members = function() {
			return this.memberList;
		}

		/* add new member to this entry */
		this.addMember = function(member) {
			this.memberList.push(member);
		}

		/* remove the member if exists and return true; return false otherwise*/
		this.remove = function(member) {
			var idx = this.memberList.indexOf(member);
			if(idx >= 0) {
				this.memberList.splice(idx, 1);
				return true;
			}
			return false;
		}

		/* rename member as newName if exists and  return true; return false otherwise */
		this.rename = function(member, newName) {
			var idx = this.memberList.indexOf(member);
			if(idx >= 0) {
				this.memberList[idx] = newName;
				return true;
			}
			return false;
		}

		/* tests whether the tag is included; returns true if yes, false if no*/
		this.match = function(tag) {
			return this.tag === tag;
		}
		
		/* returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag name with which it is associated */
		this.membersUnique = function() {
			var tag = this.tag;
			var array = new Plaid.Array();
			this.memberList.each(function(entry) {
				array.push(new Plaid.metadata.MemberWithTag(entry, tag));
			});
			return array;
		}

		/* returns an array of all the fields and methods of the state that is reflected in the current metadata, bundled in items containing both member name and the tag names in its hierarchy */
		this.membersHierarchy = function(hierarchy) {
			var array = new Plaid.Array();
			
			if(this.isWithEntry()) {
				hierarchy.clear();
				hierarchy.push(this.tag);
			} else {
				hierarchy.push(this.tag);
			}

			this.memberList.each(function(entry) {
				array.push(new Plaid.metadata.MemberWithHierarchy(entry, Plaid.clone(hierarchy)));
			});
			return array;
		}

		/* returns an object with true in field 'unique' if the tree passed in reflects a state that has unique members, false otherwise; if unique members is false, the member that caused the conflict is in field 'member' */
		this.checkUniqueMembers = function(hierarchy, itemList) {
			var tag = this.tag;
			var itemToReturn = {
				unique: true,
				member: ""
			}

			if(this.type == "with") {
				hierarchy.clear();
				hierarchy.push(this.tag);
			} else {
				hierarchy.push(this.tag);
			}

			this.memberList.until(function(member) {
				//check if this item is already in the memberList
				itemList.until(function(item) {
					if(Plaid.isCallback(item.name)){
						return false;
					}
					if(item.name == member) {
						if(hierarchy.indexOf(item.tag) == -1){
							itemToReturn = { unique: false, member: item.name };
							return true;
						}
						return false;
					}
				});
				
				if(itemToReturn['unique']){
					// item is not in the memberList
					itemList.push(new Plaid.metadata.MemberWithTag(member, tag));
					return false;
				} else {
					// item is in the memberList
					return true;
				}
			});
			
			return itemToReturn;
		}

	});

	Plaid.metadata.WithEntry = Plaid.extend(Plaid.metadata.AbstractEntry, function(tag, memberList) {
		this[Plaid.superclass].constructor.call(this, tag, memberList, "with");

		this.isWithEntry = function() {
			return true;
		}
	});

	Plaid.metadata.CaseOfEntry = Plaid.extend(Plaid.metadata.AbstractEntry, function(tag, memberList) {
		this[Plaid.superclass].constructor.call(this, tag, memberList, "");

		this.isWithEntry = function() {
			return false;
		}
	});

	return Plaid;
})();

if (!window) { var window = {}; }
if (!window.console) { window.console = {}; }
if (!window.console.log) { window.console.log = function() {};}

if (typeof exports !== 'undefined') { exports.Plaid = Plaid; }

