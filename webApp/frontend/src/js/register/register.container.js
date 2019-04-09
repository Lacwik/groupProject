import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';    
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
            error: '',
            redirect: false,
        }
    }
    onRegisterUser = user => {
        return authenticationRepository.registerUser(user)
            .then(() => this.setState(state => ({ ...state, redirect: true })))
            .catch(err => this.setErrorMessage(err));
    }

    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }

    onRegisterCompany = company => {
        return authenticationRepository.registerCompany(company)
            .then(() => this.setState(state => ({ ...state, redirect: true })))
            .catch(err => this.setErrorMessage(err));
    }

    onSwitchForm = registerForm => {
        this.setState(state => ({ ...state, registerForm }));
    }

    renderNeccessaryForm = () => {
        const { registerForm, error } = this.state;
        const { USER, COMPANY } = REGISTER_FORMS_SWITCH_STATES;

        switch (registerForm) {
            case USER:
                return <RegisterUserForm onSubmit={user => this.onRegisterUser(user)} errorMessage={error} />;

            case COMPANY:
                return <RegisterCompanyForm onSubmit={company => this.onRegisterCompany(company)} errorMessage={error} />;

            default:
                console.error("Unknow register form state: " + registerForm);    
                return <RegisterUserForm onSubmit={user => this.onRegisterUser(user)} />;
        }
    }

    redirectAfterRegister() {
        if (this.state.redirect) {
            return <Redirect to='/login' />
        }
    }

    render() {
        console.log('Props: ', this.props);
        return (
            <div className="wrapper-content">
                <div className="register-forms-container">
                    <h2>Zarejestruj się jako</h2>
                    {this.redirectAfterRegister()}
                    <RegisterFormsSwitch switchForms={value => this.onSwitchForm(value)} defaultValue={this.state.registerForm} />
                    {this.renderNeccessaryForm()}                
                </div>  
            </div>              
        );
    }
}


export default RegisterContainer;