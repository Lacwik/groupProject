import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class LineRepository {
    constructor(store) {
        this.store = store;
    }

    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    createLine = line => {
        return fetch(`${endpoint}/line`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(line),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to create line. ", err);
                return Promise.reject(err);
            });
    }

    editLine = line => {
        return fetch(`${endpoint}/line/${line.id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
            body: JSON.stringify(line),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to edit line. ", err);
                return Promise.reject(err);
            });
    }

    deleteLine = (id = '') => {
        return fetch(`${endpoint}/line/${id}`, {
            method: 'DELETE',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to delete line. ", err));
    }

    getLineById = (id = '') => {
        return fetch(`${endpoint}/line/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line by search value. ", err));
    }

    getCompanyLines = () => {
        return fetch(`${endpoint}/line/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get company lines. ", err));
    }

    getLineVegetables = lineId => {
        return fetch(`${endpoint}/line/vegetables/${lineId}`, {
                method: 'GET',
                headers: this.getHeaders(),
            })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line stages. ", err));
    }
            
    getDefaultLines = () => {
        return fetch(`${endpoint}/line/default`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get default lines. ", err));
    }

    getLineStages = (id = '') => {
        return fetch(`${endpoint}/line/stages/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line stages. ", err));
    }

    getLineModules = (id = '') => {
        return fetch(`${endpoint}/line/modules/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line modules. ", err));
    }

    
    
    getResourcesForLine = lineId => {
        return fetch(`${endpoint}/line/resources/${lineId}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line leftovers. ", err));
    }

}