const window = function() {
  this.self = this;

  // init location
  this.location = { };
  this.location.search = '';
  this.location.protocol = 'file:';

  // init NodeJS module support
  this.module = { };
  this.module.exports = { };

  // init CommonJS exports support
  this.exports = this.module.exports;

  // init timer support
  const timer = new Timer();
  this.setTimeout = timer.setTimeout;
  this.cleartimeout = timer.cleartimeout;
  this.setInterval = timer.setInterval;
  this.clearInterval = timer.clearInterval;
}(this);
