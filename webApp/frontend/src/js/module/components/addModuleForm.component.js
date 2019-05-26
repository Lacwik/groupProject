import React, { Component } from 'react';
import PropTypes, { number, string } from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Select from '@material-ui/core/Select';
import { VEGETABLES } from '../../constants/vegetables.constants';
import { RESOURCES } from '../../constants/resources.constants';

class AddModuleForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: string,
            loss: number,
            waste: number,
            time: number,
        };
    }

    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }

    onChangeLoss = e => {
        const { value: loss } = e.target;

        this.setState(state => ({ ...state, loss }));
    }

    onChangeWaste = e => {
        const { value: waste } = e.target;

        this.setState(state => ({ ...state, waste }));
    }

    onChangeTime = e => {
        const { value: time } = e.target;

        this.setState(state => ({ ...state, time }));
    }

    onChangeFuel = e => {
        const { value: resource } = e.target;

        this.setState(state => ({ ...state, resource }));
    }


    onChangeResource = e => {
        const { value: resource } = e.target;

        this.setState(state => ({ ...state, resource }));
    }

    onSubmit = () => {
        const { name, loss, waste, time, resource, vegetables } = this.state;

        this.props.onSubmit(name, loss, waste, time, resource, vegetables);
    }

    renderAvailableVegetables() {
        return Object.keys({ ...VEGETABLES }).map(key => {
            const value = VEGETABLES[key];
            return (
                <option
                    key={key}
                    value={key}
                >{value}
                </option>
            );
        })
    }

    renderAvailableResources() {
        return Object.keys({ ...RESOURCES }).map(key => {
            const value = RESOURCES[key];
            return (
                <option
                    key={key}
                    value={key}
                >{value}
                </option>
            );
        })
    }

    render() {
        const {
            name, 
            loss, 
            waste, 
            time, 
        } = this.state;

        return (
            <form id="add-module-form" className="add-module-form" onSubmit={e => e.preventDefault()}>
                <h2 className="add-module-form__title">Stwórz moduł</h2>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}
                <TextField
                    label="nazwa etapu"
                    value={name}
                    onChange={this.onChangeName}
                    type="name"
                    fullWidth
                >Nazwa modułu
                </TextField>
                <TextField
                    label="loss"
                    fullWidth
                    value={loss}
                    onChange={this.onChangeLoss}
                    type={Number}
                >Generowana strata
                </TextField>
                <TextField
                    label="waste"
                    fullWidth
                    value={waste}
                    onChange={this.onChangeWaste}
                    type={Number}
                >Odpady
                </TextField>
                <TextField
                    label="time"
                    fullWidth
                    value={time}
                    onChange={this.onChangeTime}
                    type={Number}
                >Czas działania
                </TextField>
                <Select
                    label="warzywa"
                    native
                    fullWidth
                    onChange={this.onChangeVegetables}
                >Warzywa
                    {this.renderAvailableVegetables()}
                </Select>
                <Select
                    label="paliwo"
                    native
                    fullWidth
                    onChange={this.onChangeResource}
                >Paliwo
                    {this.renderAvailableResources()}
                </Select>
                <Button 
                    variant="contained"
                    color="primary"
                    className="form-add-module__button"
                    onClick={this.onSubmit}
                >Zapisz</Button>
            </form>             
        );
    }
}

AddModuleForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
    errorMessage: PropTypes.string.isRequired,
};

export default AddModuleForm;