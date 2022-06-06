const pbiGetToken = require('./pbi-get-token/pbi-get-token.service.js');
const apiGetToken = require('./api-get-token/api-get-token.service.js');
const datapush = require('./datapush/datapush.service.js');
// eslint-disable-next-line no-unused-vars
module.exports = function (app) {
  app.configure(pbiGetToken);
  app.configure(apiGetToken);
  app.configure(datapush);
};
