import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import { VIEW_MODULE_SWITCH_STATES } from '../viewModuleSwitchStates.enum';

class ModuleFormSwitch extends Component {
    constructor(props) {
        super(props);

        this.state = {
            value: VIEW_MODULE_SWITCH_STATES.VIEW,
        };
    }

    handleChange = e => {
        const { value } = e.target;
        this.setState(state => ({ ...state, value }));

        this.props.switchForms(value);
    }

    render() {
        return (
            <FormControl component="fieldset" >
                <RadioGroup
                    value={this.state.value}
                    onChange={this.handleChange}
                >
                    <FormControlLabel value={VIEW_MODULE_SWITCH_STATES.VIEW} control={<Radio />} label="podgląd" />
                    <FormControlLabel value={VIEW_MODULE_SWITCH_STATES.EDIT} control={<Radio />} label="edycja" />
                </RadioGroup>
            </FormControl>
        );
    }
}

ModuleFormSwitch.propTypes = {
    switchForms: PropTypes.func.isRequired,
};

export default ModuleFormSwitch;