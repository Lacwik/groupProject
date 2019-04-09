import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import LoginForm from './components/loginForm.component';
import { authenticationRepository } from '../factory/authenticationRepository.factory';
import '../../css/login.css';
import { setCurrentLoggedUserEmail, setJWT, setIsUserLogged, setApplicationUserRole } from '../redux/app.service';
import { AUTH_KEYS } from '../constants/authentication.constants';


class LoginContainer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            error: '',
        };
    }

    onLoginUser(email, password) {
        this.setState(state => ({ ...state, error: '' }))

        return authenticationRepository.loginUser(email, password)
            .then(({ email, jwt, role }) => {
                setCurrentLoggedUserEmail(email);
                setJWT(jwt);
                setIsUserLogged(true);
                setApplicationUserRole(role);

                localStorage.setItem(AUTH_KEYS.ACCESS_TOKEN, jwt);
                localStorage.setItem(AUTH_KEYS.EMAIL, email);
                localStorage.setItem(AUTH_KEYS.ROLE, role);

                return { email, jwt, role };
            })
            .catch(err => this.setState(state => ({ ...state, error: err })));
    }

    render() {
        if (this.props.isUserLogged) {
            return <Redirect to='/' />
        }

        return (
            <div className="wrapper-content">
                <LoginForm onSubmit={(email, password) => this.onLoginUser(email, password)} errorMessage={this.state.error} /> 
            </div>               
        );
    }
}

LoginContainer.propTypes = {
    isUserLogged: PropTypes.bool.isRequired,
}

const mapStateToProps = state => ({
    isUserLogged: state.isUserLogged,
});

export default connect(mapStateToProps)(LoginContainer);