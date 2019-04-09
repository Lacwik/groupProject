import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';

class RegisterCompanyRequest extends Component {
    render() {
        const {
            name,
            lastName,
            companyName,
            email,
        } = this.props;

        return (
            <Paper className="user-request-box">
                <div className="user-request-container">
                    <ul className="user-requests">
                        <li>
                            <span>Nazwa firmy: </span>{companyName}
                        </li>
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

RegisterCompanyRequest.propTypes = {
    email: PropTypes.string.isRequired,
    name: PropTypes.string.isRequired,
    companyName: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    onActive: PropTypes.func.isRequired,
};


export default RegisterCompanyRequest;