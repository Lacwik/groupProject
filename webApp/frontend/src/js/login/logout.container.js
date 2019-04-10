import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';
import { setJWT, setCurrentLoggedUserEmail, setIsUserLogged, setApplicationUserRole, setCompanyWorkingFor, setIsWorkingForCompany } from '../redux/app.service';
import { AUTH_KEYS } from '../constants/authentication.constants';

class LogoutContainer extends Component {
    constructor() {
        super();

        this.state = {
            shouldRedirect: false,
        };
    }

    componentDidMount() {
        this.logout();
    }

    redirect() {
        return <Redirect to='/' />
    }

    logout() {
        setCurrentLoggedUserEmail('');
        setJWT('');
        setIsUserLogged(false);
        setApplicationUserRole(APPLICATION_ROLES.USER);
        setCompanyWorkingFor({
            id: undefined,
            role: undefined,
            name: '',
        });
        setIsWorkingForCompany(false);

        localStorage.setItem(AUTH_KEYS.ACCESS_TOKEN, '');
        localStorage.setItem(AUTH_KEYS.EMAIL, '');
        localStorage.setItem(AUTH_KEYS.ROLE, APPLICATION_ROLES.USER);
        localStorage.setItem(AUTH_KEYS.COMPANY_LOGGED, '');
        localStorage.setItem(AUTH_KEYS.COMPANY_WORKING_FOR, '');

        setTimeout(() => this.setState(state => ({ ...state, shouldRedirect: true })), 1500);
    }
    
    render() {
        return (
            <div className="wrapper-content">
                Zostałeś wylogowany. Za chwilę zostaniesz przekierowany na stronę główną.
                {this.state.shouldRedirect && this.redirect()}
            </div>            
        );
    }
}
export default LogoutContainer;