globalThis.QUnit = exports.QUnit;

const newLine = os.platform == 'win32' ? '\r\n' : '\n';

QUnit.log(details => {
  if (details.result) {
	let message = '\'' + details.name + '\' is PASSED, ' + details.message;
  } else {
    let message = '\'' + details.name + '\' is FAILED';
    message += ', actual: [' + details.actual + '], expected: [' + details.expected + ']';
    console.error('stack trace');
    console.error('===========');
    console.error(details.source);
  }
});

QUnit.done(details => {
  console.info('Total  : ', details.total);
  console.info('Passed : ', details.passed);
  console.info('Failed : ', details.failed);
  console.info('Time   : ', details.runtime, 'ms');
});

QUnit.start();
