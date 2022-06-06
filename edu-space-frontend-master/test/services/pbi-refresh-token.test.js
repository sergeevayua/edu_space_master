const assert = require('assert');
const app = require('../../src/app');

describe('\'pbi-refresh-token\' service', () => {
  it('registered the service', () => {
    const service = app.service('pbi-refresh-token');

    assert.ok(service, 'Registered the service');
  });
});
