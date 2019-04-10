import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import AddUsersSearchbar from './components/addUsersSearchbar.component';
import { usersRepository } from '../factory/usersRepository.factory';
import { companyRepository } from '../factory/companyRepository.factory';
import '../../css/company.css';
import { setUsers, removeUserById } from '../redux/app.service';
import AddUsers from './components/addUsers.component';
import AddUserForm from './components/addUserForm.component';

class AddUsersContainer extends Component {
    constructor() {
        super();

        this.state = {
            revisionClear: 0,
        }
    }
    onSearchUser = value => {
        usersRepository.getUsersBy(value)
            .then(users => setUsers(users));
    }

    onAddMemberToCompany = (id, role) => {
        companyRepository.addUserToCompany(id, role)
            .then(() => removeUserById(id));
    }

    onCreateCompanyMember = user => {
        companyRepository.createMemberToCompany(user)
            .then(() => this.setState(state => ({ ...state, revisionClear: state.revisionClear + 1 })))
    }

    render() {
        return (
            <div className="requests-container">
                <div className="wrapper-content">
                    <h2>Utwórz pracownika dla firmy</h2>
                    <AddUserForm onSubmit={user => this.onCreateCompanyMember(user)} errorMessage="" revision={this.state.revisionClear} />
                </div>
                <div className="wrapper-content">
                    <h2>Dodaj pracownika do firmy</h2>
                    <AddUsersSearchbar onSearch={value => this.onSearchUser(value)} />
                    <AddUsers users={this.props.users} onAddMemberToCompany={(id, role) => this.onAddMemberToCompany(id, role)} />
                </div>
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