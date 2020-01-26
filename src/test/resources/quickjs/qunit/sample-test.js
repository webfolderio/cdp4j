/// <reference path="qunit.d.ts" />

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
