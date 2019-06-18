import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class VegetablesRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getAllVegetables = () => {
        return fetch(`${endpoint}/vegetable/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get vegetables ", err));
    }

    getVegetableById = (id = '') => {
        return fetch(`${endpoint}/vegetable/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get vegetable by search value. ", err));
    }

    
}