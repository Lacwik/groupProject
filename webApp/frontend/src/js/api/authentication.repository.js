export default class AuthenticationRepository {
    constructor(store) {
        this.store = store;
    }

    getHeaders = () => {
        return {
            'Content-Type': 'application/json'
        };
    }

    loginUser = (email, password) => {
        return fetch('http://localhost:8090/auth/login', {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify({
                email, password,
            })
        }).then(JSON.parse)
        .catch(err => console.warn("Caught error while trying to login user. ", err));
    }    

    registerUser = user => {
        return fetch('http://localhost:8090/auth/register/user', {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(user),
        })
        .then(JSON.parse)
        .catch(err => console.warn("Caught error while trying to register user", err));
    }

    registerCompany = company => {
        return fetch('http://localhost:8090/auth/register/company', {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(company),
        })
        .then(JSON.parse)
        .catch(err => console.warn("Caught error while trying to register company", err));
    }
}