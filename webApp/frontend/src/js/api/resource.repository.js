import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class ResourcesRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getAllResources = () => {
        return fetch(`${endpoint}/resource/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get resources ", err));
    }

    getResourceById = (id = '') => {
        return fetch(`${endpoint}/resource/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get resource by search value. ", err));
    }

    
}