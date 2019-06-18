import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class RegisterRequestRepository {
    constructor(store) {
        this.store = store;
    }

    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }


    getUserRegisterRequests = () => {
        return fetch(`${endpoint}/request/user`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get register user's requests. ", err));
    }

    getCompanyRegisterRequests = () => {
        return fetch(`${endpoint}/request/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get register companies' requests. ", err));
    }

    activeUserRegisterRequest = id => {
        return fetch(`${endpoint}/request/user/${id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .catch(err => console.warn("Caught error while trying to active user's register request. ", err));
    }

    activeCompanyRegisterRequest = id => {
        return fetch(`${endpoint}/request/company/${id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .catch(err => console.warn("Caught error while trying to active company's register request. ", err));
    }

}