function BaseLine() { 

};        

BaseLine.prototype.forkJoin = function (level) {
    var isZero = level == 0;
    if  ( isZero == false ) {
        var nextLevel = level - 1;
        this.forkJoin(nextLevel);
        this.forkJoin(nextLevel);
    } 
};

var begin = new Date();
var bl = new BaseLine();
bl.forkJoin(28);
var end = new Date();
console.log("Runtime " + ((end.getTime()-begin.getTime())/1000.0) + "s");


