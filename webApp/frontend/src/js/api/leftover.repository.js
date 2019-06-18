import { handleError } from './handleErrors.service';

export default class LeftoversRepository {
    constructor(store) {
        this.store = store;
    }

    // endpoint = 'http://172.30.149.96:8090'
    endpoint = `http://localhost:8090`


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getAllLeftovers = () => {
        return fetch(`${this.endpoint}/leftover/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get leftovers ", err));
    }

    getLeftoverById = (id = '') => {
        return fetch(`${this.endpoint}/leftover/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get leftover by search value. ", err));
    }

}