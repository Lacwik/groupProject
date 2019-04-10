import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Select from '@material-ui/core/Select';
import { COMPANY_ROLES } from '../../constants/companyRoles.constants';

class AddUserForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            email: '',
            password: '',
            name: '',
            lastName: '',
            role: "EXPERT",
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
        const { password, email, name, lastName, role } = this.state;

        this.props.onSubmit({ password, email, name, lastName, role });
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
    componentWillReceiveProps(nextProps) {
        if (this.props.revision != nextProps.revision) {
            this.setState({
                email: '',
                password: '',
                name: '',
                lastName: '',
                role: "EXPERT",
            });
        }
    }

    renderCompanyRoles() {
        return Object.keys({ ...COMPANY_ROLES }).map(key => {
            const value = COMPANY_ROLES[key];
            return (
                <option
                    key={key}
                    value={key}
                >{value}
                </option>
            );
        })
    }

    onChangeRole = e => {
        const { value: role } = e.target;

        this.setState(state => ({ ...state, role }));
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
                {this.renderErrorMessageIfExists()}
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

                <Select
                    native
                    fullWidth
                    onChange={this.onChangeRole}
                >
                    {this.renderCompanyRoles()}
                </Select>
                <Button
                    variant="contained"
                    color="primary"
                    className="register__button"
                    onClick={this.onSubmit}
                >Dodaj pracownika</Button>
            </form>
        );
    }
}

AddUserForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
    revision: PropTypes.number.isRequired,
    errorMessage: PropTypes.string.isRequired,
};

export default AddUserForm;