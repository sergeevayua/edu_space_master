const assert = require('assert');
const app = require('../../src/app');

describe('\'api-get-token\' service', () => {
  it('registered the service', () => {
    const service = app.service('api-get-token');

    assert.ok(service, 'Registered the service');
  });
});
