/// <reference path="qunit.d.ts" />

const { expect, test, module } = QUnit;

module('My Test Suite', (module) => {
  module.before(assert => console.info('starting'));
  module.after(assset => console.info('finished'));

  test('test equal', assert => {
    assert.equal(2, 2);
  });

  test('test not equal', assert => {
    assert.notEqual(2, 2);
  });

  test("two async calls", assert => {
    assert.expect(2);
    var done1 = assert.async();
    var done2 = assert.async();
    setTimeout(() => {
      assert.ok(true, "test resumed from async operation 1");
      done1();
    }, 500);
    setTimeout(() => {
      assert.ok(true, "test resumed from async operation 2");
      done2();
    }, 150);
  });

});
