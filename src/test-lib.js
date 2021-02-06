var fibonacci = function(num) {
    var a = 1;
    var b = 0;
    var temp;
    while (num >= 0) {
        temp = a;
        a = a + b;
        b = temp;
        num--;
    }
    return b;
}
