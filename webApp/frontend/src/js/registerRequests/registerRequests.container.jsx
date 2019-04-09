import React, { Component, Fragment } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RegisterCompanyRequestsContainer from './containers/registerCompanyRequests.container';
import RegisterUserRequestsContainer from './containers/registerUserRequests.container';
import { registerRequestRepository } from '../factory/registerRequestRepository.factory';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';
import { setUserRegisterRequests, setCompanyRegisterRequests } from '../redux/app.service';
import '../../css/request.css';

class RegisterRequestsContainer extends Component {
    componentDidUpdate() {
        if (this.shouldRenderRequests()) {
            const { getUserRegisterRequests, getCompanyRegisterRequests } = registerRequestRepository;

            Promise.all([getUserRegisterRequests(), getCompanyRegisterRequests()])
                .then(([userRegisterRequests, companyRegisterRequests]) => {
                    setUserRegisterRequests(userRegisterRequests);
                    setCompanyRegisterRequests(companyRegisterRequests);
                })
                .catch(err => console.error("Caught error while trying to fetch register requests.", err));
        }
    }

    shouldRenderRequests() {
        const { isUserLogged, appUserRole } = this.props;

        console.log('Props: ', this.props);

        return isUserLogged && appUserRole === APPLICATION_ROLES.SUPER_ADMIN;
    }

    renderRequestsListOrForbiddenMessage() {
        if (this.shouldRenderRequests()) {
            return (
                <div className="requests-container">
                    <RegisterUserRequestsContainer />
                    <RegisterCompanyRequestsContainer />
                </div>
            );
        }

        return (
            <div className="wrapper-content">
                <p>Nie masz uprawnień, aby widzieć ten zasób.</p>
            </div>
        )
    }

    render() {
        return this.renderRequestsListOrForbiddenMessage();
    }
}

RegisterRequestsContainer.propTypes = {
    isUserLogged: PropTypes.bool.isRequired,
    appUserRole: PropTypes.oneOf(Object.values(APPLICATION_ROLES)),
}


const mapStateToProps = store => ({
    isUserLogged: store.isUserLogged,
    appUserRole: store.appUserRole,
});

export default connect(mapStateToProps)(RegisterRequestsContainer);