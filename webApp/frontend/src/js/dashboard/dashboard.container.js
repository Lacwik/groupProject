import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { authenticationCompanyRoleRepository } from '../factory/authenticationCompanyRoleRepository.factory';
import { setCompanyRoles } from '../redux/app.service';
import Dashboard from './components/dashboard.component';
import '../../css/dashboard.css';

class DashboardContainer extends Component {
    componentDidMount() {
        authenticationCompanyRoleRepository.getAllCompanyRoles()
            .then(res => setCompanyRoles(res));
    }

    render() {
        return (
            <div className="wrapper-content">
              <h3>Zacznij pracę jako </h3>
              <Dashboard companyRoles={this.props.companyRoles} />
            </div>
        );
    }
}



DashboardContainer.propTypes = {
    companyRoles: PropTypes.array.isRequired,
}

const mapStateToProps = state => ({
    companyRoles: state.companyRoles,
});

export default connect(mapStateToProps)(DashboardContainer);
