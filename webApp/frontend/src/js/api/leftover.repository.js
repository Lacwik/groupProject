import { handleError } from './handleErrors.service';

export default class LeftoversRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getAllLeftovers = () => {
        return fetch(`http://localhost:8090/leftover/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get leftovers ", err));
    }

    getLeftoverById = (id = '') => {
        return fetch(`http://localhost:8090/leftover/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get leftover by search value. ", err));
    }

}