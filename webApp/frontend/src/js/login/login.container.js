import React, { Component } from 'react';
import LoginForm from './components/loginForm.component';
import { authenticationRepository } from '../factory/authenticationRepository.factory';
import '../../css/login.css';



class LoginContainer extends Component {
    onLoginUser(email, password) {
        return authenticationRepository.loginUser(email, password)
            .then(result => console.log("Logged: " + result))
            .catch(console.warn);
    }

    render() {
        return (
            <LoginForm onSubmit={(email, password) => this.onLoginUser(email, password)} />                
        );
    }
}

export default LoginContainer;