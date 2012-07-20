var BaseLine = {};

BaseLine.forkJoin = function (level) {
    var isZero = level - 1;
    if  ( isZero == false ) {
        var nextLevel = level - 1;
        this.forkJoin(nextLevel);
        this.forkJoin(nextLevel);
    } 
};
    

BaseLine.forkJoin(28);


