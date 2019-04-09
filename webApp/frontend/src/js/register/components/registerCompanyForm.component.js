import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';

class RegisterCompanyForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            name: '',
            lastName: '',
            companyName: '',
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
    

    onChangeCompanyName = e => {
        const { value: companyName } = e.target;

        this.setState(state => ({ ...state, companyName }));
    }

    onSubmit = () => {
        const { password, email, name, lastName, companyName } = this.state;

        this.props.onSubmit({ password, email, name, lastName, companyName});
    }
    

    renderErrorMessageIfExists = () => {
        const { errorMessage } = this.props;

        if (errorMessage) {
            return (
                <Paper elevation={1} className="error-box">
                    <b>Błąd: </b>
                    {errorMessage}
                </Paper>
            );
        }

        return undefined;
    }

    render() {
        const {
            email,
            password,
            name,
            lastName,
            companyName,
        } = this.state;

        return (
            <form id="register-user-form" className="register-form" onSubmit={e => e.preventDefault()}>
                {this.renderErrorMessageIfExists()}
                <TextField
                    label="Nazwa firmy"
                    value={companyName}
                    onChange={this.onChangeCompanyName}
                    type="text"
                    fullWidth
                >Firma
                </TextField>
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
                    label="Hasło"
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
                >Zarejestruj się</Button>
            </form>                
        );
    }
}

RegisterCompanyForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default RegisterCompanyForm;