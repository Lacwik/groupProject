import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { authenticationCompanyRoleRepository } from '../factory/authenticationCompanyRoleRepository.factory';
import { setCompanyRoles, setCompanyWorkingFor, setIsWorkingForCompany } from '../redux/app.service';
import Dashboard from './components/dashboard.component';
import '../../css/dashboard.css';
import { AUTH_KEYS } from '../constants/authentication.constants';

class DashboardContainer extends Component {
    componentDidMount() {
        authenticationCompanyRoleRepository.getAllCompanyRoles()
            .then(res => setCompanyRoles(res));
    }

    onLoginAsCompanyRole = (id, role, name) => {
        authenticationCompanyRoleRepository.loginUserAsCompanyRole(id, role)
            .then(() => setCompanyWorkingFor({ id, role, name }))
            .then(() => setIsWorkingForCompany(true))
            .then(() => {
                sessionStorage.setItem(AUTH_KEYS.COMPANY_LOGGED, true);
                sessionStorage.setItem(AUTH_KEYS.COMPANY_WORKING_FOR, JSON.stringify({ id, role, name }));
            });
    }

    render() {
        return (
            <div className="wrapper-content">
                <h3>Zacznij pracę jako </h3>
                <Dashboard
                    companyRoles={this.props.companyRoles}
                    onLoginAsCompanyRole={(id, role, name) => this.onLoginAsCompanyRole(id, role, name)}
                    companyWorkingFor={this.props.companyWorkingFor}
                />
            </div>
        );
    }
}



DashboardContainer.propTypes = {
    companyRoles: PropTypes.array.isRequired,
    companyWorkingFor: PropTypes.object.isRequired,
}

const mapStateToProps = state => ({
    companyRoles: state.companyRoles,
    companyWorkingFor: state.companyWorkingFor,
});

export default connect(mapStateToProps)(DashboardContainer);
