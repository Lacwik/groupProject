import React, { Component } from 'react';
import { Redirect } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import LoginForm from './components/loginForm.component';
import { authenticationRepository } from '../factory/authenticationRepository.factory';
import '../../css/login.css';
import { setCurrentLoggedUserEmail, setJWT, setIsUserLogged } from '../redux/app.service';


class LoginContainer extends Component {
    onLoginUser(email, password) {
        return authenticationRepository.loginUser(email, password)
            .then(({ email, JWT }) => {
                setCurrentLoggedUserEmail(email);
                setJWT(JWT);
                setIsUserLogged(true);
                return { email, JWT };
            })
            .catch(console.warn);
    }

    render() {
        if (this.props.isUserLogged) {
            return <Redirect to='/' />
        }

        return (
            <LoginForm onSubmit={(email, password) => this.onLoginUser(email, password)} />                
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