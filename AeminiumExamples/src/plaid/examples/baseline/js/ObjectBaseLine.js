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

var bl = new BaseLine();
bl.forkJoin(28);


