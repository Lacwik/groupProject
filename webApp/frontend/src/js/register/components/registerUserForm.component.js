import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

class RegisterUserForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            name: '',
            lastName: '',
        };
    }

    onChangeEmail = e => {
        const { value: email } = e.target;

        this.setState(state => ({ ...state, email }));
    }

    onChangePassword = e => {
        const { value: password } = e.target;

        this.setState(state => ({ ...state, password }));
    }

    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }
    

    onChangeLastName = e => {
        const { value: lastName } = e.target;

        this.setState(state => ({ ...state, lastName }));
    }

    onSubmit = () => {
        const { password, email, name, lastName } = this.state;

        this.props.onSubmit({ password, email, name, lastName });
    }

    render() {
        const {
            email,
            password,
            name,
            lastName,
        } = this.state;

        return (
            <form id="register-user-form" className="register-form" onSubmit={e => e.preventDefault()}>
                <h2 className="register-form__title">Zarejestruj się jako użytkownik</h2>
                <TextField
                    label="Imię"
                    value={name}
                    onChange={this.onChangeName}
                    type="text"
                    fullWidth
                >Imię
                </TextField>
                <TextField
                    label="Nazwisko"
                    value={lastName}
                    onChange={this.onChangeLastName}
                    type="text"
                    fullWidth
                >Nazwisko
                </TextField>
                <TextField
                    label="Email"
                    value={email}
                    onChange={this.onChangeEmail}
                    type="email"
                    fullWidth
                >Email
                </TextField>
                <TextField
                    label="Password"
                    fullWidth
                    value={password}
                    onChange={this.onChangePassword}
                    type="password"
                >Hasło
                </TextField>
                <Button 
                    variant="contained"
                    color="primary"
                    className="register__button"
                    onClick={this.onSubmit}
                    fullWidth
                >Zarejestruj się</Button>
            </form>                
        );
    }
}

RegisterUserForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default RegisterUserForm;