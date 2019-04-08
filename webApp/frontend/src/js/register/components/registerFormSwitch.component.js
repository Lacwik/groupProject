import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import { REGISTER_FORMS_SWITCH_STATES } from '../registerFormsSwitchStates.enum';

class RegisterFormsSwitch extends Component {
    constructor(props) {
        super(props);

        this.state = {
            value: REGISTER_FORMS_SWITCH_STATES.USER,
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
                    <FormControlLabel value={REGISTER_FORMS_SWITCH_STATES.USER} control={<Radio />} label="Użytkownik" />
                    <FormControlLabel value={REGISTER_FORMS_SWITCH_STATES.COMPANY} control={<Radio />} label="Firma" />
                </RadioGroup>
            </FormControl>
        );
    }
}

RegisterFormsSwitch.propTypes = {
    switchForms: PropTypes.func.isRequired,
};

export default RegisterFormsSwitch;