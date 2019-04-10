import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Home, GroupAdd, AccountBox, ExitToApp, List } from '@material-ui/icons';
import '../../css/navigation.css';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';

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
                    <li key="nav-logout">
                        <ExitToApp style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/logout">Wyloguj się</Link>
                    </li>
                </React.Fragment>
            )
        }
    }


    renderLinksIfUserIsLoggedAsJobRole() {
        if (this.props.isUserLogged && true) { // role company
            return (
                <React.Fragment>
                    <li key="nav-company-add-user">
                        <List style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/company/add-member">Dodaj pracownika</Link>
                    </li>
                </React.Fragment>
            ) 
        }
    }

    isCurrentLoggedUserHasRoleSuperAdmin() {
        return this.props.appUserRole === APPLICATION_ROLES.SUPER_ADMIN && this.props.isUserLogged;
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
                    {this.renderLinksIfUserIsLoggedAsJobRole()}
                    {this.renderLinksIfNotLogged()}
                    {this.renderLinksIfSuperAdminLogged()}
                    {this.renderLinksIfLogged()}
                </ul>
            </aside>
        );
    }
}

NavigationContainer.propTypes = {
    isUserLogged: PropTypes.bool.isRequired,
    appUserRole: PropTypes.oneOf(Object.values(APPLICATION_ROLES)),
}

const mapStateToProps = state => ({
    isUserLogged: state.isUserLogged,
    appUserRole: state.appUserRole,
});

export default connect(mapStateToProps)(NavigationContainer);
