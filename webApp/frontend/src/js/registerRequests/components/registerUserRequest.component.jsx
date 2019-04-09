import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';

class RegisterUserRequest extends Component {
    render() {
        const {
            name,
            lastName,
            email,
        } = this.props;

        return (
            <Paper className="user-request-box">
                <div className="user-request-container">
                    <ul className="user-requests">
                        <li>
                            <span>Imię: </span>{name}
                        </li>
                        <li>
                            <span>Nazwisko: </span>{lastName}
                        </li>
                        <li>
                            <span>Email: </span>{email}
                        </li>
                    </ul>
                <Button 
                    variant="contained"
                    color="primary"
                    onClick={this.props.onActive}
                >Aktywuj</Button>
                </div>
            </Paper>
        );               
    }
}

RegisterUserRequest.propTypes = {
    email: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    onActive: PropTypes.func.isRequired,
};


export default RegisterUserRequest;