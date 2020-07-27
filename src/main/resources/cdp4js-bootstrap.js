let window;

(function() {
  window = Function('return this')();  // window <- global object
  window.self = window;

  // init location
  window.location = { };
  window.location.search = '';
  window.location.protocol = 'file:';

  // init CommonJS exports support
  window.exports = { };

  // init timer support

  if (typeof globalThis.Timer !== 'undefined') {
    globalThis.timer = new Timer();
    globalThis.setTimeout = window.setTimeout = timer.setTimeout;
    globalThis.clearTimeout = window.clearTimeout = timer.clearTimeout;
    globalThis.setInterval = window.setInterval = timer.setInterval;
    globalThis.clearInterval = window.clearInterval = timer.clearInterval;
  }
})();
