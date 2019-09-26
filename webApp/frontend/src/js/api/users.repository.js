import { handleError } from './handleErrors.service';

export default class UsersRepository {
    constructor(store) {
        this.store = store;
    }

    endpoint = 'http://172.30.149.96:8090'
    // endpoint = `http://localhost:8090`


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getUsersBy = (searchValue = '') => {
        return fetch(`${this.endpoint}/user?search=${searchValue}&companyId=${this.store.getState().companyWorkingFor.id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get users by search value. ", err));
    }

    
}