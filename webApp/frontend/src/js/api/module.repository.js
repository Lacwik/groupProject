import { handleError } from './handleErrors.service';

export default class ModuleRepository {
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

    createModule = module => {
        return fetch(`${this.endpoint}/module`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(module),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to create module. ", err);
                return Promise.reject(err);
            });
    }

    editModule = module => {
        return fetch(`${this.endpoint}/module/${module.id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
            body: JSON.stringify(module),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to edit module. ", err);
                return Promise.reject(err);
            });
    }

    deleteModule = (id = '') => {
        return fetch(`${this.endpoint}/module/${id}`, {
            method: 'DELETE',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to delete module. ", err));
    }

    getModuleById = (id = '') => {
        return fetch(`${this.endpoint}/module/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get module by search value. ", err));
    }

    getCompanyModules = () => {
        return fetch(`${this.endpoint}/module/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get company modules. ", err));
    }

    getDefaultModules = () => {
        return fetch(`${this.endpoint}/module/default`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get default modules. ", err));
    }
}