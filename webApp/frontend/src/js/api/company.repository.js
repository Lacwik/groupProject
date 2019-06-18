import { handleError } from './handleErrors.service';
import { endpoint } from '../constants/configuration.constants'

export default class CompanyRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    addUserToCompany = (userId, role) => {
        return fetch(`${endpoint}/company/${this.store.getState().companyWorkingFor.id}`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify({ userId, companyRole: role.toUpperCase() }),
        })
            .then(response => handleError(response))
            .catch(err => console.warn("Caught error while trying to add member to company. ", err));
    }

    createMemberToCompany = user => {
        return fetch(`${endpoint}/user/member`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify({
                ...user,
                companyRole: user.role.value.toUpperCase(),
                companyId: this.store.getState().companyWorkingFor.id,
            }),
        })
            .then(response => handleError(response))
            .catch(err => console.warn("Caught error while trying to create member for company. ", err));
    }
}