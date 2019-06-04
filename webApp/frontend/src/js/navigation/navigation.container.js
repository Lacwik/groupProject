import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Home, GroupAdd, AccountBox, ExitToApp, List, Add, Settings, GroupWork, LinearScale, Build } from '@material-ui/icons';
import '../../css/navigation.css';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';
import { COMPANY_ROLES } from '../constants/companyRoles.constants';

class NavigationContainer extends Component {
    renderLinksIfNotLogged() {
        if (!this.props.isUserLogged) {
            return (
                <React.Fragment>
                    <li key="nav-register">
                        <Link to="/login" >
                        <AccountBox style={{ color:'#e8eef4' }} fontSize="large" />
                        </Link>
                        <Link to="/login" >Zaloguj się</Link>
                    </li>
                    <li key="nav-login">
                        <Link to="/register">
                        <GroupAdd style={{ color: '#e8eef4' }} fontSize="large" />
                        </Link>
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
                    <li key="nav-modules">
                        <Link to="/module/display-module">
                        <Settings style={{ color: '#23d8fc' }} fontSize="large" />
                        </Link>
                        <Link to="/module/display-module">Moduły</Link>
                    </li>
                    <li key="nav-stages">
                        <Link to="/stage/display-stage">
                        <GroupWork style={{ color: '#7744cc' }} fontSize="large" />
                        </Link>
                        <Link to="/stage/display-stage">Etapy</Link>
                    </li>
                    <li key="nav-lines">
                        <Link to="/line/display-line">
                        <LinearScale style={{ color: '#77ccdd' }} fontSize="large" />
                        </Link>
                        <Link to="/line/display-line">Linie</Link>
                    </li>
                    <li key="nav-logout">
                        <Link to="/line/display-line">
                        <ExitToApp style={{ color: '#e8eef4' }} fontSize="large" />
                        </Link>
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
                        <Link to="/company/add-member">
                        <Add style={{ color: '#e8eef4' }} fontSize="large" />
                        </Link>
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
                        <Link to="/calculator">
                        <Build style={{ color: '#ffbb88' }} fontSize="large" />
                        </Link>
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
                        <Link to="/admin/requests">
                        <List style={{ color: '#e8eef4' }} fontSize="large" />
                        </Link>
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
                        <Link to="/">
                        <Home style={{ color: '#e8eef4' }} fontSize="large" />
                        </Link>
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
