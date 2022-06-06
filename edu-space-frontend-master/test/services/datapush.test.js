const assert = require('assert');
const app = require('../../src/app');

describe('\'datapush\' service', () => {
  it('registered the service', () => {
    const service = app.service('datapush');

    assert.ok(service, 'Registered the service');
  });
});
