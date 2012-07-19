function forkJoin(level) {
    var isZero = level == 0;
    if ( isZero == false )  {
        var nextLevel = level - 1;
        forkJoin(nextLevel);
        forkJoin(nextLevel);
    }
}

forkJoin(28)