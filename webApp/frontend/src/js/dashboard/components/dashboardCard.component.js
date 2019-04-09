import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import { COMPANY_ROLES } from '../../constants/companyRoles.constants';

class DashboardCard extends Component {
    mapRoleToName() {
        return COMPANY_ROLES[this.props.role];
    }

    render() {
        const { name, id, role } = this.props;

        return (
            <Card className="dashboard-card">
                <CardContent>
                    <h4 className="dashboard-card__title">{name}</h4>
                    <p className="dashboard-card__role">{this.mapRoleToName()}</p>     
                </CardContent>
                <CardActions>
                    <Button size="small" 
                    variant="contained"
                    color="primary">Rozpocznij pracę</Button>
                </CardActions>
            </Card>
        );
    }
}

DashboardCard.propTypes = {
    name: PropTypes.string.isRequired,
    id: PropTypes.number.isRequired,
    role: PropTypes.string.isRequired,
}

export default DashboardCard;