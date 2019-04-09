import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RegisterUserRequest from '../components/registerUserRequest.component';

class RegisterUserRequestsContainer extends Component {
    onActiveUser(id) {
        console.log('Active user: ', id);
    }

    renderListOfUserRequests() {
        const { userRegisterRequests } = this.props;
        
        return [{ id: 1, name: 'Kamil', lastName: 'Klimek', email: 'qwe@wp.pl' }].map(({ id, name, lastName, email }) => (
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