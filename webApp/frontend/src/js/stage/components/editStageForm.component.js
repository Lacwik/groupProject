import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { stageRepository } from '../../factory/stageRepository.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';


class EditStageForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            modules: [],
            allModules: [],
        };
    }

    componentDidMount(){
        stageRepository.getStageById(this.props.id).then(
            response => this.setState({ 
                name: response.name,
                modules: response.modules.map(v => ({ ...v, value: v.id, label: v.name })),
            })
        ).then(
            moduleRepository.getCompanyModules().then(
                response => this.setState({
                    allModules: response.map(v => ({ ...v, value: v.id, label: v.name }))
                })
            )
        )
    }


    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }


    onChangeModules = e => {
        const modules = e;

        this.setState(state => ({ ...state, modules }));
    }


    onSubmit = () => {
        const { name, modules } = this.state;

        this.props.onSubmit({ name, modules, id: this.props.id });
    }


    render() {

        const {
            name, 
            modules
        } = this.state;

        //console.log({ props: this.props, state: this.state });
        return (
            <form id="stage-edit-form" className="stage-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}

                <TextField
                    label="Name"
                    value={name}
                    onChange={this.onChangeName}
                    type="text"
                    fullWidth
                >Nazwa
                </TextField>
                
                <p></p>

                <Select 
                    closeMenuOnSelect={false}
                    components={makeAnimated()}
                    value={modules}
                    isMulti
                    options={this.state.allModules}
                    onChange={this.onChangeModules}
                    />
                <p></p>
                
                <Button 
                    variant="contained"
                    color="primary"
                    className="stage-edit__button"
                    onClick={this.onSubmit}
                >Zapisz
                </Button>
            </form>                
        );
    }
}

EditStageForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default EditStageForm;