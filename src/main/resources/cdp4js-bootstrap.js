let window, self, global;

(function() {
  globalThis = self = window = Function('return this')();  // window <- global object
  window.self = self;

  // init location
  window.location = { };
  window.location.search = '';
  window.location.protocol = 'file:';

  // init CommonJS exports support
  window.exports = { };

  // init timer support
  const timer = new Timer();
  window.setTimeout = timer.setTimeout;
  window.clearTimeout = timer.clearTimeout;
  window.setInterval = timer.setInterval;
  window.clearInterval = timer.clearInterval;
})();
