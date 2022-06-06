// Initializes the `pbi-get-token` service on path `/pbi-get-token`
const createService = require('./pbi-get-token.class.js');
const hooks = require('./pbi-get-token.hooks');

module.exports = function (app) {
  
  const paginate = app.get('paginate');

  const options = {
    paginate
  };

  // Initialize our service with any options it requires
  app.use('/node-api/pbi-get-token', createService(options));

  // Get our initialized service so that we can register hooks
  const service = app.service('node-api/pbi-get-token');

  service.hooks(hooks);
};
