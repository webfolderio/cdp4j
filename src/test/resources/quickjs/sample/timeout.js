var QUnit = exports.QUnit;

QUnit.log(function(details) {
  if ( ! details.result ) {
    let message = '\'' + details.name + '\' is FAILED';
        message += ', actual: [' + details.actual + '], expected: [' + details.expected + ']';
        console.error(message + '\n');
        console.error('stack trace');
        console.error('===========');
        console.error(details.source);
    }
});

QUnit.done(function(details) {
  console.info('Total  : ', details.total);
  console.info('Passed : ', details.passed);
  console.info('Failed : ', details.failed);
  console.info('Time   : ', details.runtime, 'ms');
});

QUnit.test('hello test', function(assert) {
  assert.ok(2 == 2, 'Passed!');
  assert.ok(1 == 2, 'Passed!');
});

QUnit.load();
