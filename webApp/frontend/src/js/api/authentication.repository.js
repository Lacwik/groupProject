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
}