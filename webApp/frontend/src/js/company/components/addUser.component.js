import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Paper from '@material-ui/core/Paper';
import IconButton from '@material-ui/core/IconButton';
import { Add } from '@material-ui/icons';
import InputLabel from '@material-ui/core/InputLabel';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import { COMPANY_ROLES } from '../../constants/companyRoles.constants';

class AddUser extends Component {
    constructor(props) {
        super(props);

        this.state = {
            role: COMPANY_ROLES.EXPERT,
        }

    }
    renderCompanyRoles() {
        return Object.keys({...COMPANY_ROLES}).map(key => {
            const value = COMPANY_ROLES[key];
            return (
                <option
                    key={key}
                    value={key}
                >{value}
                </option>
            );
        })
    }

    onChangeRole = e => {
        const { value: role } = e.target;

        this.setState(state => ({ ...state, role }));
    }

    onAdd = () => {
        this.props.onAdd(this.props.id, this.state.role);
    }

    render() {
        return (
            <Paper className="add-user-row">
                <p>
                    {this.props.name} {this.props.lastName} {this.props.email}
                </p>
                <div>
                    <Select
                        native
                        onChange={this.onChangeRole}
                    >
                        {this.renderCompanyRoles()}
                    </Select>
                    <IconButton aria-label="Dodaj" className="add-user-button" onClick={this.onAdd}>
                        <Add />
                    </IconButton>
                </div>
            </Paper>
        )
    }
}

AddUser.propTypes = {
    name: PropTypes.string.isRequired,
    lastName: PropTypes.string.isRequired,
    id: PropTypes.number.isRequired,
    email: PropTypes.string.isRequired,
    onAdd: PropTypes.func.isRequired,
}

export default AddUser;