import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Home, GroupAdd, AccountBox, ExitToApp, List, Add, Settings, SettingsApplications, Build } from '@material-ui/icons';
import '../../css/navigation.css';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';
import { COMPANY_ROLES } from '../constants/companyRoles.constants';

class NavigationContainer extends Component {
    renderLinksIfNotLogged() {
        if (!this.props.isUserLogged) {
            return (
                <React.Fragment>
                    <li key="nav-register">
                        <AccountBox style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/login">Zaloguj się</Link>
                    </li>
                    <li key="nav-login">
                        <GroupAdd style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/register">Zarejestruj się</Link>
                    </li>
                </React.Fragment>
            );
        }
    }

    renderLinksIfLogged() {
        if (this.props.isUserLogged) {
            return (
                <React.Fragment>
                    <li key="nav-module">
                        <Settings style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/module/add-module">Dodaj Moduł</Link>
                    </li>
                    <li key="nav-module-display">
                        <SettingsApplications style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/module/display-module">Wyświetl moduły</Link>
                    </li>
                    <li key="nav-logout">
                        <ExitToApp style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/logout">Wyloguj się</Link>
                    </li>
                </React.Fragment>
            )
        }
    }

    renderLinksIfUserIsLoggedAsAdminJobRole() {
        if (this.props.isUserLogged && this.props.isWorkingForCompany && this.props.companyWorkingFor.role === 'ADMIN') {
            return (
                <React.Fragment>
                    <li key="nav-company-add-user">
                        <Add style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/company/add-member">Dodaj pracownika</Link>
                    </li>
                </React.Fragment>
            ) 
        }
    }

    isCurrentLoggedUserHasRoleSuperAdmin() {
        return this.props.appUserRole === APPLICATION_ROLES.SUPER_ADMIN && this.props.isUserLogged;
    }

    isCurrentLoggedUserIsMemberOfCompany() {
        const {
            isUserLogged,
            isWorkingForCompany,
            companyWorkingFor,
        } = this.props;

        const { role } = companyWorkingFor;

        return isUserLogged && isWorkingForCompany;
    }

    renderLinksIfLoggedAsMemberOfCompany() {
        if (this.isCurrentLoggedUserIsMemberOfCompany()) {
            return (
                <React.Fragment>
                    <li key="calculator">
                        <Build style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/calculator">Kalkulator</Link>
                    </li>
                </React.Fragment>
            )
        }

        return '';
    }

    renderLinksIfSuperAdminLogged() {
        if (this.isCurrentLoggedUserHasRoleSuperAdmin()) {
            return (
                <React.Fragment>
                    <li key="nav-admin-requests">
                        <List style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/admin/requests">Zgłoszenia</Link>
                    </li>
                </React.Fragment>
            )
        }
    }

    render() {
        return (
            <aside className="main-wrapper__sidebar">
                <ul className="navigation">
                    <li key="nav-home">
                        <Home style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/">Strona główna</Link>
                    </li>
                    {this.renderLinksIfUserIsLoggedAsAdminJobRole()}
                    {this.renderLinksIfNotLogged()}
                    {this.renderLinksIfSuperAdminLogged()}
                    {this.renderLinksIfLoggedAsMemberOfCompany()}
                    {this.renderLinksIfLogged()}
                </ul>
            </aside>
        );
    }
}

NavigationContainer.propTypes = {
    isUserLogged: PropTypes.bool.isRequired,
    isWorkingForCompany: PropTypes.bool.isRequired,
    companyWorkingFor: PropTypes.object.isRequired,
    appUserRole: PropTypes.oneOf(Object.values(APPLICATION_ROLES)),
}

const mapStateToProps = state => ({
    isUserLogged: state.isUserLogged,
    appUserRole: state.appUserRole,
    isWorkingForCompany: state.isWorkingForCompany,
    companyWorkingFor: state.companyWorkingFor,
});

export default connect(mapStateToProps)(NavigationContainer);
