function ShellSort(elements, gaps) {
	for (var gapN = 0; gapN < gaps.length; gapN++) {
		var gap = gaps[gapN];
		for (var i = 0; i < elements.length; i++) {
			var temp = elements[i];
			var j = i;
			for (; j >= gap && elements[j-gap] > temp; j -= gap) {
				elements[j] = elements[j-gap];
			}
			elements[j] = temp;
		}
	}
}

function benchmark(handle) {
	var n = handle.getProperty("nElems");
	var random = handle.getProperty("Random");
	var elems = new Array();
	for (var i = 0; i < n; i++) {
		elems.push(random.nextInt());
	}
	var gaps = new Array(701,301, 132,57,23,10,4,1);
	handle.startTimer();
	ShellSort(elems, gaps);
	handle.stopTimer();
}