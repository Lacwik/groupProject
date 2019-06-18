import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class AuthenticationRepository {
    constructor(store) {
        this.store = store;
    }

    getHeaders = () => {
        return {
            'Content-Type': 'application/json'
        };
    }

    loginUser = (email, password) => {
        return fetch(`${endpoint}/auth/login`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify({
                email, password,
            })
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to login user. ", err);
                return Promise.reject(err);
            });
    }

    registerUser = user => {
        return fetch(`${endpoint}/auth/register/user`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(user),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to register user. ", err);
                return Promise.reject(err);
            });
    }

    registerCompany = company => {
        return fetch(`${this.endpoint}/auth/register/company`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(company),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to register company. ", err);
                return Promise.reject(err);
            });
    }
}