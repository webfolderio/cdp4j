/// <reference path="qunit.d.ts" />

var QUnit = exports.QUnit;

let counter = 0;

const newLine = os.platform == 'win32' ? '\r\n' : '\n';

QUnit.log(details => {
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

QUnit.done(details => {
  console.info('Total  : ', details.total);
  console.info('Passed : ', details.passed);
  console.info('Failed : ', details.failed);
  console.info('Time   : ', details.runtime, 'ms');
});

const { test, module } = QUnit;

module('My Test Suite', (module) => {
  module.before(assert => console.info('starting'));
  module.after(assset => console.info('finished'));

  test('test equal', assert => {
    assert.equal(2, 2);
  });

  test('test not equal', assert => {
    assert.notEqual(2, 2);
  });	
});

QUnit.load();
