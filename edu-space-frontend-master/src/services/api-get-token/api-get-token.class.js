const axios = require('axios');
const qs = require('querystring');

const config = {
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded'
  }
}

// const url = 'https://login.microsoftonline.com/organizations/oauth2/v2.0/token';
const url = 'https://oswaldprodwebapi.azurewebsites.net/api/authentication/getazureoauthdata';

/* eslint-disable no-unused-vars */
class Service {
  constructor (options) {
    this.options = options || {};
  }
  
  async create (data, params) {
  
    const config = {
      headers: {
        'Content-Type': 'application/json'
      }
    };
  
  
    return new Promise((resolve, reject) => {
      axios.post(url, qs.stringify(data), config)
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
