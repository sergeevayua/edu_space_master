// Initializes the `datapush` service on path `/datapush`
const createService = require('./datapush.class.js');
const hooks = require('./datapush.hooks');

module.exports = function (app) {
  
  const paginate = app.get('paginate');

  const options = {
    paginate
  };

  // Initialize our service with any options it requires
  app.use('/node-api/datapush', createService(options));

  // Get our initialized service so that we can register hooks
  const service = app.service('node-api/datapush');

  service.hooks(hooks);
};
