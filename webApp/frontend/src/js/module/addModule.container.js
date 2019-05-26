import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { moduleRepository } from '../factory/moduleRepository.factory';
import AddModuleForm from './components/addModuleForm.component';

class AddModuleContainer extends Component {
    constructor() {
        super();

        this.state = {
            revisionClear: 0,
        }
    }

    onCreateModule = module => {
        moduleRepository.createModule(module)
            .then(() => this.setState(state => ({ ...state, revisionClear: state.revisionClear + 1 })))
    }

    render() {
        return (
            <div className="add-module-container">
                <div className="wrapper-content">
                    <AddModuleForm onSubmit={module => this.onCreateModule(module)} errorMessage="" revision={this.state.revisionClear} />
                </div>
            </div>
        );
    }
}

AddModuleContainer.propTypes = {
    users: PropTypes.array.isRequired, // from parent
}

const mapStateToProps = store => ({
    users: store.users,
});

export default connect(mapStateToProps)(AddModuleContainer);