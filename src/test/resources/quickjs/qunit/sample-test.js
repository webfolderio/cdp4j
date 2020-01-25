var QUnit = exports.QUnit;

let counter = 0;

const newLine = os.platform == 'win32' ? '\r\n' : '\n';

QUnit.log(function(details) {
  counter++;
  if (details.result) {
	let message = '\'' + details.name + '\' is PASSED, ' + details.message;
	console.info(counter + '. ' + message + newLine);
  } else {
    let message = '\'' + details.name + '\' is FAILED';
    message += ', actual: [' + details.actual + '], expected: [' + details.expected + ']';
    console.error(counter + '. ' + message + newLine);
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
  assert.ok(1 == 2, 'Failed!');
});

QUnit.load();
