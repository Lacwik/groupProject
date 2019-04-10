import { handleError } from './handleErrors.service';

export default class UsersRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    getUsersBy = (searchValue = '') => {
        return fetch(`http://localhost:8090/user?search=${searchValue}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get users by search value. ", err));
    }
}