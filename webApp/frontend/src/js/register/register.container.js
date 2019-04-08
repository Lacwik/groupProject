import React, { Component } from 'react';
import { authenticationRepository } from '../factory/authenticationRepository.factory';
import '../../css/register.css';
import RegisterUserForm from './components/registerUserForm.component';
import RegisterCompanyForm from './components/registerCompanyForm.component';

class RegisterContainer extends Component {
    onRegisterUser = user => {
        return authenticationRepository.registerUser(user)
            .then(res => console.log('registered user'))
            .catch(console.warn);
    }

    onRegisterCompany = company => {
        return authenticationRepository.registerCompany(company)
            .then(res => console.log('registered company'))
            .catch(console.warn);
    }

    render() {
        return (
            <div class="register-forms-container">
                <RegisterUserForm onSubmit={user => this.onRegisterUser(user)} />
                <RegisterCompanyForm onSubmit={company => this.onRegisterCompany(company)} />
            </div>                
        );
    }
}

export default RegisterContainer;