import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RegisterCompanyRequest from '../components/registerCompanyRequest.component.1';
import { registerRequestRepository } from '../../factory/registerRequestRepository.factory';

class RegisterCompanyRequestsContainer extends Component {
    onActiveCompany = id => {
        registerRequestRepository.activeCompanyRegisterRequest(id)
            .then(() => this.props.update())
            .catch(err => console.error('No i mamy error: ', err));
    }

    renderListOfCompanyRequests() {
        const { companyRegisterRequests } = this.props;
        
        return companyRegisterRequests.map(({ companyModel, userProfileModel }) => (
            <RegisterCompanyRequest
                key={companyModel.id}
                name={userProfileModel.name}
                lastName={userProfileModel.lastName}
                email={userProfileModel.email}
                companyName={companyModel.name}
                onActive={() => this.onActiveCompany(companyModel.id)}
            />
        ));
    }

    render() {
        return (
            <div className="wrapper-content">
            <h2>Lista nieaktywnych firm</h2>
                {this.renderListOfCompanyRequests()}
            </div>
        );               
    }
}


RegisterCompanyRequestsContainer.propTypes = {
    companyRegisterRequests: PropTypes.array.isRequired,
    update: PropTypes.func.isRequired,
}


const mapStateToProps = store => ({
    companyRegisterRequests: store.companyRegisterRequests,
});


export default connect(mapStateToProps)(RegisterCompanyRequestsContainer);
