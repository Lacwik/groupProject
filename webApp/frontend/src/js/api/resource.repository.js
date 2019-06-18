import { handleError } from './handleErrors.service';

export default class ResourcesRepository {
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

    getAllResources = () => {
        return fetch(`${this.endpoint}/resource/all`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get resources ", err));
    }

    getResourceById = (id = '') => {
        return fetch(`${this.endpoint}/resource/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get resource by search value. ", err));
    }

    
}