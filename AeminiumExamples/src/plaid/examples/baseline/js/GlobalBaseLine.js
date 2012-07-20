function forkJoin(level) {
    var isZero = level == 0;
    if ( isZero == false )  {
        var nextLevel = level - 1;
        forkJoin(nextLevel);
        forkJoin(nextLevel);
    }
}

var begin = new Date();
forkJoin(28)
var end = new Date();
console.log("" + ((end.getTime()-begin.getTime())/1000.0));