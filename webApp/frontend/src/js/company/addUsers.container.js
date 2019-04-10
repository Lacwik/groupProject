import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import AddUsersSearchbar from './components/addUsersSearchbar.component';
import { usersRepository } from '../factory/usersRepository.factory';
import '../../css/company.css';
import { setUsers } from '../redux/app.service';

class AddUsersContainer extends Component {
    onSearchUser = value => {
        usersRepository.getUsersBy(value)
            .then(users => setUsers(users));
    }

    render() {
        console.log("users: ", this.props.users);
        return (
            <div className="wrapper-content">
                <h2>Dodaj pracownika do firmy</h2>
                <AddUsersSearchbar onSearch={value => this.onSearchUser(value)} />
            </div>
        );
    }
}

AddUsersContainer.propTypes = {
    users: PropTypes.array.isRequired,
}

const mapStateToProps = store => ({
    users: store.users,
});

export default connect(mapStateToProps)(AddUsersContainer);