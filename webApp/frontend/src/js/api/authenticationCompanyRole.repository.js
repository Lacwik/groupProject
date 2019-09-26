import { handleError } from './handleErrors.service';

export default class AuthenticationCompanyRoleRepository {
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

    getAllCompanyRoles = company => {
        return fetch(`${this.endpoint}/company-auth/`, {
            method: 'GET',
            headers: this.getHeaders(),
            body: JSON.stringify(company),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to get all user in companies roles. ", err);
                return Promise.reject(err);
            });
    }

    loginUserAsCompanyRole = (companyId, role) => {
        return fetch(`${this.endpoint}/company-auth/${companyId}?role=${role}`, {
            method: 'PUT',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(() => ({ id: companyId, role }))
            .catch(err => {
                console.warn("Caught error while trying to log as company role. ", err);
                return Promise.reject(err);
            });
    }
}