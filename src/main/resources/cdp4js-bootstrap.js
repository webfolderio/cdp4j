(function(global) {
    const timer = new Timer();
    global.setTimeout = timer.setTimeout;
    global.cleartimeout = timer.cleartimeout;
    global.setInterval = timer.setInterval;
    global.clearInterval = timer.clearInterval;
})(this);
