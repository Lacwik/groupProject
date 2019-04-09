import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RegisterUserRequest from '../components/registerUserRequest.component';
import { registerRequestRepository } from '../../factory/registerRequestRepository.factory';

class RegisterUserRequestsContainer extends Component {
    onActiveUser(id) {
        registerRequestRepository.activeUserRegisterRequest(id)
            .then(result => console.log('Udalo sie', result))
            .catch(err => console.error('No i mamy error: ', err));
    }

    renderListOfUserRequests() {
        const { userRegisterRequests } = this.props;
        
        return userRegisterRequests.map(({ id, name, lastName, email }) => (
            <RegisterUserRequest
                id={id}
                name={name}
                lastName={lastName}
                key={id}
                email={email}
                onActive={() => this.onActiveUser(id)}
            />
        ));
    }

    render() {
        return (
            <div className="wrapper-content">
            <h2>Lista nieaktywnych użytkowników</h2>
                {this.renderListOfUserRequests()}
            </div>
        );               
    }
}

RegisterUserRequestsContainer.propTypes = {
    userRegisterRequests: PropTypes.array.isRequired,
}


const mapStateToProps = store => ({
    userRegisterRequests: store.userRegisterRequests,
});


export default connect(mapStateToProps)(RegisterUserRequestsContainer);