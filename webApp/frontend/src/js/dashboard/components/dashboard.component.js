import React, { Component } from 'react';
import PropTypes from 'prop-types';
import DashboardCard from './dashboardCard.component';

class Dashboard extends Component {
    renderCompanyRoles() {
        const { companyRoles } = this.props;

        return companyRoles.map(({ role, name, id }) => (
            <DashboardCard
                key={id}
                role={role}
                name={name}
                id={id}
                onLoginAsCompanyRole={(id, role, name) => this.props.onLoginAsCompanyRole(id, role, name)}
                isWorkingFor={this.props.companyWorkingFor.id === id}
            />
        ));
    }


    render() {
        return (
            <div className="dashboard-container">
                {this.renderCompanyRoles()}
            </div>
        )
    }
}

Dashboard.propTypes = {
    companyRoles: PropTypes.array.isRequired,
    onLoginAsCompanyRole: PropTypes.func.isRequired,
    companyWorkingFor: PropTypes.object.isRequired,
}

export default Dashboard;