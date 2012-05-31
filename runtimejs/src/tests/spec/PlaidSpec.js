describe("Plaid", function() {
	var target;
	describe("global methods", function() {
		describe("utilities", function() {
			describe("returnTrue", function() {
				it("should return true", function(){
					expect(Plaid.returnTrue()).toBeTruthy();
				});
			});
			describe("clone", function(){
				it("should return new object that has same properties with cloned objects", function(){
					var target = { a: 1, b: "b", f: function(){}, o:{a: 1, b: "b", f: function(){}}};
					var clone = Plaid.clone(target);
					
					expect(clone == target).toBeFalsy();
					expect(clone.a == target.a).toBeTruthy();
					expect(clone.b == target.b).toBeTruthy();
					expect(clone.f == target.f).toBeTruthy();
					
					expect(clone.o == target.o).toBeFalsy();
					expect(clone.o.a == target.o.a).toBeTruthy();
					expect(clone.o.b == target.o.b).toBeTruthy();
					expect(clone.o.f == target.o.f).toBeTruthy();
				});

				it("should return new array that contains same values with cloned objects", function(){
					var target = [1, "1", function(){}, {a:1, b:"b", f:function(){}}];
					var clone = Plaid.clone(target);
					
					expect(clone == target).toBeFalsy();
					expect(clone[0] == target[0]).toBeTruthy();
					expect(clone[1] == target[1]).toBeTruthy();
					expect(clone[2] == target[2]).toBeTruthy();
					
					expect(clone[3] == target[3]).toBeFalsy();
					expect(clone[3].a == target[3].a).toBeTruthy();
					expect(clone[3].b == target[3].b).toBeTruthy();
					expect(clone[3].f == target[3].f).toBeTruthy();
				});

				it("should return new object with same prototype chain", function(){
					var Parent = Plaid.extend(Object, function(){});
					var Child = Plaid.extend(Parent, function(){});
					var target = new Child();
					var clone = Plaid.clone(target);
					expect(clone instanceof Child).toBeTruthy();
					expect(clone instanceof Parent).toBeTruthy();
					expect(clone.prototype == target.prototype).toBeTruthy();
				});
			});
			describe("true state", function() {
				it("should return true", function(){
					expect(Plaid.plaidNewState_True.instantiate()).toBeTruthy();
				});
			});
			describe("returnFalse", function() {
				it("should return false", function(){
					expect(Plaid.returnFalse()).toBeFalsy();
				});
			});
			describe("false state", function() {
				it("should return false", function(){
					expect(Plaid.plaidNewState_False.instantiate()).toBeFalsy();
				});
			});
			describe("retrunNull", function() {
				it("should return null", function(){
					expect(Plaid.returnNull()).toBeNull();
				});
			});
			describe("null state", function() {
				it("should return null", function(){
					expect(Plaid.plaidNewState_None.instantiate()).toBeNull();
				});
			});
			describe("retrunArray", function(){
				it("should return array", function(){
					var array = Plaid.returnArray();
					expect(array.length).toEqual(0);
					expect(array instanceof Plaid.Array).toBeTruthy()
					expect(array instanceof Array).toBeTruthy()
				});
			});
			describe("array state", function(){
				it("should return array", function(){
					var array = Plaid.plaidNewState_Array.instantiate();
					expect(array.length).toEqual(0);
					expect(array instanceof Plaid.Array).toBeTruthy()
					expect(array instanceof Array).toBeTruthy()
				});
			});
			describe("retrunDate", function(){
				it("should return date", function(){
					expect(Plaid.returnNewDate() instanceof Date).toBeTruthy()
				});
			});
			describe("", function(){
				it("should return date", function(){
					expect(Plaid.plaidNewState_Date.instantiate() instanceof Date).toBeTruthy()
				});
				
			});

		});
		
		describe("if$plaid", function() {
			var f = Plaid.if$plaid;
			var clause = function() { return 1; };
			it("should execute function when condition is true", function(){
				expect((f(true))(clause)).toEqual(1);
			});
			it("should not execute function when condition is false", function(){
				expect((f(false))(clause)).not.toBeDefined();
			});
			// it("should return undefined when condition is not boolean", function(){
			// 	expect((f(undefined))).not.toBeDefined();
			// });
			// it("should return undefined when condition is not boolean", function(){
			// 	expect((f(1))).not.toBeDefined();
			// });
		});

		describe("ifElse", function(){
			var f = Plaid.ifElse;
			var trueClause = function() { return 1; };
			var falseClause = function() { return -1; };
			it("should execute true clause when condition is true", function(){
				expect(f(true)(trueClause)(falseClause)).toEqual(1);
			});
			it("should execute false clause when condition is false", function(){
				expect(f(false)(trueClause)(falseClause)).toEqual(-1);
			});
			// it("should return undefined when condition is not boolean", function(){
			// 	expect(f(undefined)).not.toBeDefined();
			// });
			// it("should return undefined when condition is not boolean", function(){
			// 	expect(f(1)).not.toBeDefined();
			// });
		});

		describe("while$plaid", function(){
			it("should execute clause 3 times", function(){
				var cnt = 0;
				var cond = function() { return cnt < 3; };
				var body = function() { return ++cnt; };
				expect(Plaid.while$plaid(cond)(body)).toEqual(3);
			});
		});

		describe("callback", function(){
			describe("isCallback", function(){
				it("should be true if method name has callback prefix of activate", function(){
					expect(Plaid.isCallback("onState")).toBeTruthy();
				});
				it("should be true if method name has callback prefix of passivate", function(){
					expect(Plaid.isCallback("offState")).toBeTruthy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallback("stateOn")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallback("stateOff")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallback("song")).toBeFalsy();
				});
			});

			describe("isCallbackOnActivate", function(){
				it("should be true if method name has callback prefix of activate", function(){
					expect(Plaid.isCallbackOnActivate("onState")).toBeTruthy();
				});
				it("should be false if method name has callback prefix of passivate", function(){
					expect(Plaid.isCallbackOnActivate("offState")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallbackOnActivate("stateOn")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallbackOnActivate("stateOff")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallbackOnActivate("song")).toBeFalsy();
				});
			});

			describe("isCallbackOnPassivate", function(){
				it("should be false if method name has callback prefix of activate", function(){
					expect(Plaid.isCallbackOnPassivate("onState")).toBeFalsy();
				});
				it("should be true if method name has callback prefix of passivate", function(){
					expect(Plaid.isCallbackOnPassivate("offState")).toBeTruthy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallbackOnPassivate("stateOn")).toBeFalsy();
				});
				it("should be false if method name does not have callback prefix", function(){
					expect(Plaid.isCallbackOnPassivate("stateOff")).toBeFalsy();
				});
			});
		});
	});
});
