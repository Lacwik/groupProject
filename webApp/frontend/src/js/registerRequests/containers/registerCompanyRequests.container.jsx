import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RegisterCompanyRequest from '../components/registerCompanyRequest.component.1';

class RegisterCompanyRequestsContainer extends Component {
    onActiveCompany = id => {
        console.log('Active company: ', id);
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
}


const mapStateToProps = store => ({
    companyRegisterRequests: store.companyRegisterRequests,
});


export default connect(mapStateToProps)(RegisterCompanyRequestsContainer);
