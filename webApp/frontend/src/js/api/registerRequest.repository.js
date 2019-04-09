export default class RegisterRequestRepository {
    constructor(store) {
        this.store = store;
    }

    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    handleError = response => {
        if (!response)
            throw Error(response.message);
            
        if (response.status >= 300) {
            throw Error(response.message)
        }    
        return response;
    }

    getUserRegisterRequests = () => {
        return fetch('http://localhost:8090/request/user', {
            method: 'GET',
            headers: this.getHeaders(),
        })
        .then(response => this.handleError(response))
        .then(response => response.json())
        .catch(err => console.warn("Caught error while trying to get register user's requests. ", err));
    }    

    getCompanyRegisterRequests = () => {
        return fetch('http://localhost:8090/request/company', {
            method: 'GET',
            headers: this.getHeaders(),
        })
        .then(response => this.handleError(response))
        .then(response => response.json())
        .catch(err => console.warn("Caught error while trying to get register companies' requests. ", err));
    }

   
}