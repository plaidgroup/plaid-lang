describe("Array", function(){
	var target;
	beforeEach(function(){
		target = new Plaid.Array();
	});

  it("should have prototype chain", function(){
		expect(target instanceof Array).toBeTruthy();
		expect(target instanceof Plaid.Array).toBeTruthy();
	});

	it("should have initial array when created", function(){
		expect(target.length).toEqual(0);
		for(var i = 0; i < target.length; i++){
			console.log(target[i]);
		}
	});

	describe("initialized with length", function(){
		beforeEach(function(){
			target = new Plaid.Array(5);
		});

		it("should have appropriate length", function(){
			expect(target.length).toEqual(5);
		});

		it("should have undefined elements", function(){
			expect(target[0]).not.toBeDefined();
			expect(target[1]).not.toBeDefined();
			expect(target[2]).not.toBeDefined();
			expect(target[3]).not.toBeDefined();
			expect(target[4]).not.toBeDefined();
		});
	});

	describe("initialized with value", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2, 3, 4, 5);
		});
		
		it("should have appropriate length", function(){
			expect(target.length).toEqual(5);
		});

		it("should have appropriate elements", function(){
			expect(target[0]).toEqual(1);
			expect(target[1]).toEqual(2);
			expect(target[2]).toEqual(3);
			expect(target[3]).toEqual(4);
			expect(target[4]).toEqual(5);
		});
	});

	describe("clear", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
		});
		it("should remove all elements in array", function(){
			expect(target.length).toEqual(2);
			target.clear();
			expect(target.length).toEqual(0);
		});
	});

	describe("until", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2, 3, 4, 5);
		});

		it("should apply function to all elements when no elements return false", function(){
			var array = new Plaid.Array();
			target.until(function(elm){
				array.push(elm*2);
				return false;
			});
			expect(array).toHaveSameElements(new Plaid.Array(2, 4, 6, 8, 10));
		});

		it("should return false when all elements are processed", function(){
			var result = target.until(function(elm){
				return false;
			});
			expect(result).toBeFalsy();
		});

		it("should apply function to elements until function returns false", function(){
			var array = new Plaid.Array();
			target.until(function(elm){
				array.push(elm*2);
				if(elm == 4) {
					return true;
				}
				return false;
			});
			expect(array).toHaveSameElements(new Plaid.Array(2, 4, 6, 8));
		});

		it("should return false when function returns false", function(){
			var result = target.until(function(elm){
				if(elm == 4) {
					return true;
				}
				return false;
			});
			expect(result).toBeTruthy();
		});

	});
	
	describe("each", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
		});

		it("should apply function to each elements", function(){
			var array = new Plaid.Array();
			target.each(function(elm){
				array.push(elm*2);
			});
			expect(array[0]).toEqual(2);
			expect(array[1]).toEqual(4);
		});

		it("should be apply function to each elements with index", function(){
			var array = new Plaid.Array();
			target.each(function(elm, i){
				array.push(elm*i);
			});
			expect(array[0]).toEqual(0);
			expect(array[1]).toEqual(2);
		});
	});

	describe("indexOf", function(){
		beforeEach(function() {
			target = new Plaid.Array(1, 2);
		});
		
		it("should return index when the element is contained", function(){
			expect(target.indexOf(1)).toEqual(0);
			expect(target.indexOf(2)).toEqual(1);
		});

		it("should return -1 when the element is not contained", function(){
			expect(target.indexOf(3)).toEqual(-1);
			expect(target.indexOf("1")).toEqual(-1);
		});
	});

	describe("count", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2, 1, 2, 3, 4, 5);
		});

		it("should return number of item in the array", function(){
			expect(target.count(1)).toEqual(2);
			expect(target.count(4)).toEqual(1);
			expect(target.count(6)).toEqual(0);
		});
	});

	describe("slice", function(){
		beforeEach(function(){
			target = new Plaid.Array(0, 1, 2, 3, 4, 5);
		});

		it("should return sliced array", function(){
			var result = target.slice(1, 4);
			expect(result.length).toEqual(3);
			expect(result).toHaveSameElements(new Plaid.Array(1, 2, 3));
		});

		it("should return array including all the following elements when second argument is not specified ", function(){
			var result = target.slice(1);
			expect(result.length).toEqual(5);
			expect(result).toHaveSameElements(new Plaid.Array(1, 2, 3, 4, 5));
		});
	});

	describe("concat", function(){
		var target2;
		var target3;
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
			target2 = new Plaid.Array(1, 2);
			target3 = new Plaid.Array(1, 2, 3, 4);
		});

		it("should return concatinated array", function(){
			target = target.concat(target2);
			var result = new Plaid.Array(1, 2, 1, 2);
			expect(target).toHaveSameElements(result);
		});

		it("should accept multiple arguments", function(){
			target = target.concat(target2, target3);
			var result = new Plaid.Array(1, 2, 1, 2, 1, 2, 3, 4);
			expect(target).toHaveSameElements(result);
		});

		it("shild accept arguments other than array", function(){
			target = target.concat(target2, 3, 4);
			var result = new Plaid.Array(1, 2, 1, 2, 3, 4);
			expect(target).toHaveSameElements(result);
		});
	});

	describe("append", function(){
		var target2;
		var target3;
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
			target2 = new Plaid.Array(1, 2);
			target3 = new Plaid.Array(1, 2, 3, 4);
		});

		it("should return concatinated array", function(){
			target.append(target2);
			var result = new Plaid.Array(1, 2, 1, 2);
			expect(target).toHaveSameElements(result);
		});

		it("should accept multiple arguments", function(){
			target.append(target2, target3);
			var result = new Plaid.Array(1, 2, 1, 2, 1, 2, 3, 4);
			expect(target).toHaveSameElements(result);
		});

		it("shild accept arguments other than array", function(){
			target.append(target2, 3, 4);
			var result = new Plaid.Array(1, 2, 1, 2, 3, 4);
			expect(target).toHaveSameElements(result);
		});
	});

	describe("get", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
		});

		it("should return indexed object", function(){
			expect(target.get(0)).toEqual(1);
			expect(target.get(1)).toEqual(2);
		});
	});

	describe("set", function(){
		beforeEach(function(){
			target = new Plaid.Array(1, 2);
		});

		it("should change indexed object", function(){
			target.set(0, 3);
			expect(target[0]).toEqual(3);

			target.set(1, 4);
			expect(target[1]).toEqual(4);
		});
	});

	describe("getArrayWithSize", function(){
		it("should return array with specific size", function(){
			var array = target.getArrayWithSize(5);
			expect(array.length).toEqual(5);
		});
	});
});