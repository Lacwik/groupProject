import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';


class AddStageForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            modulesModels: [],
            allModules: [],
        };
    }

    componentDidMount(){
        Promise.all([
            moduleRepository.getCompanyModules(), 
            moduleRepository.getDefaultModules()
        ])
        .then( ([companyModules, defaultModules]) => {
            this.setState({
                allModules: [...companyModules, ...defaultModules].map(v => ({ ...v, value: v.id, label: v.name }))
            });

        })
    }


    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }


    onChangeModules = e => {
        const modulesModels = e;

        this.setState(state => ({ ...state, modulesModels }));
    }


    onSubmit = () => {
        const { name, modulesModels } = this.state;

        this.props.onSubmit({ name, modulesModels });
    }


    render() {

        const {
            name, 
            modulesModels
        } = this.state;

        return (
            <form id="stage-edit-form" className="stage-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}

                <TextField
                    label="Name"
                    value={name}
                    onChange={this.onChangeName}
                    type="text"
                    fullWidth
                    required
                >Nazwa
                </TextField>
                
                <p></p>

                <Select 
                    closeMenuOnSelect={false}
                    components={makeAnimated()}
                    value={modulesModels}
                    isMulti
                    options={this.state.allModules}
                    onChange={this.onChangeModules}
                    />
                <p></p>
                
                <Button 
                    variant="contained"
                    color="primary"
                    className="stage-add__button"
                    onClick={this.onSubmit}
                >Zapisz
                </Button>
            </form>                
        );
    }
}

AddStageForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default AddStageForm;