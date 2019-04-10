import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import AddUsersSearchbar from './components/addUsersSearchbar.component';
import { usersRepository } from '../factory/usersRepository.factory';
import { companyRepository } from '../factory/companyRepository.factory';
import '../../css/company.css';
import { setUsers } from '../redux/app.service';
import AddUsers from './components/addUsers.component';

class AddUsersContainer extends Component {
    onSearchUser = value => {
        usersRepository.getUsersBy(value)
            .then(users => setUsers(users));
    }

    onAddMemberToCompany = (id, role) => {
        companyRepository.addUserToCompany(id, role)
            .then(() => console.log("Added user to company."));
    }

    render() {
        return (
            <div className="wrapper-content">
                <h2>Dodaj pracownika do firmy</h2>
                <AddUsersSearchbar onSearch={value => this.onSearchUser(value)} />
                <AddUsers users={this.props.users} onAddMemberToCompany={(id, role) => this.onAddMemberToCompany(id, role)} />
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