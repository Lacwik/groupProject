import React, { Component } from 'react';
import { authenticationRepository } from '../factory/authenticationRepository.factory';
import '../../css/register.css';
import RegisterUserForm from './components/registerUserForm.component';
import RegisterCompanyForm from './components/registerCompanyForm.component';
import RegisterFormsSwitch from './components/registerFormSwitch.component';
import { REGISTER_FORMS_SWITCH_STATES } from './registerFormsSwitchStates.enum';

class RegisterContainer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            registerForm: REGISTER_FORMS_SWITCH_STATES.USER,
        }
    }
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

    onSwitchForm = registerForm => {
        this.setState(state => ({ ...state, registerForm }));
    }

    renderNeccessaryForm = () => {
        const { registerForm } = this.state;
        const { USER, COMPANY } = REGISTER_FORMS_SWITCH_STATES;

        switch (registerForm) {
            case USER:
                return <RegisterUserForm onSubmit={user => this.onRegisterUser(user)} />;

            case COMPANY:
                return <RegisterCompanyForm onSubmit={company => this.onRegisterCompany(company)} />;

            default:
                console.error("Unknow register form state: " + registerForm);    
                return <RegisterUserForm onSubmit={user => this.onRegisterUser(user)} />;
        }
    }

    render() {
        return (
            <div class="register-forms-container">
                <h2>Zarejestruj się jako</h2>
                <RegisterFormsSwitch switchForms={value => this.onSwitchForm(value)} defaultValue={this.state.registerForm} />
                {this.renderNeccessaryForm()}                
            </div>                
        );
    }
}

export default RegisterContainer;