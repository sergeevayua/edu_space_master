const axios = require('axios');
const qs = require('querystring');

const config = {
  headers: {
    'Content-Type': 'application/application/json'
  }
}

/* eslint-disable no-unused-vars */
class Service {
  constructor (options) {
    this.options = options || {};
  }
  
  async create (data, params) {
    
    return new Promise((resolve, reject) => {
      axios.post(data.url, data.params, config)
        .then((res) => {
          resolve(res.data);
        })
        .catch((error) => {
          reject(error);
        });
    });
  }
  
}

module.exports = function (options) {
  return new Service(options);
};

module.exports.Service = Service;
