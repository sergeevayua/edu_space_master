// Initializes the `api-get-token` service on path `/api-get-token`
const createService = require('./api-get-token.class.js');
const hooks = require('./api-get-token.hooks');

module.exports = function (app) {
  
  const paginate = app.get('paginate');

  const options = {
    paginate
  };

  // Initialize our service with any options it requires
  app.use('/node-api/api-get-token', createService(options));

  // Get our initialized service so that we can register hooks
  const service = app.service('node-api/api-get-token');

  service.hooks(hooks);
};
