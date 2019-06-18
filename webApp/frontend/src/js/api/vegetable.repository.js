import { handleError } from './handleErrors.service';

export default class VegetablesRepository {
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

    getAllVegetables = () => {
        return fetch(`${this.endpoint}/vegetable/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get vegetables ", err));
    }

    getVegetableById = (id = '') => {
        return fetch(`${this.endpoint}/vegetable/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get vegetable by search value. ", err));
    }

    
}