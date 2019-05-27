import { handleError } from './handleErrors.service';

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

    getCompanyLines = () => {
        return fetch(`http://localhost:8090/line/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get company lines. ", err));
    }

    getVegetablesForLine = lineId => {
        return fetch(`http://localhost:8090/line/vegetables/${lineId}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get vegetables for line. ", err));
    }
}