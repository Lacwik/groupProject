import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import { stageRepository } from '../../factory/stageRepository.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';


class CreateLineForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            stages: [],
            allStages: [],
        };
    }

    componentDidMount(){
        stageRepository.getCompanyStages().then(
            response => this.setState({
                allStages: response.map(v => ({ ...v, value: v.id, label: v.name }))
            })
        )
    }


    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }


    onChangeStages = e => {
        const stages = e;

        this.setState(state => ({ ...state, stages }));
    }


    onSubmit = () => {
        const { name, stages } = this.state;

        this.props.onSubmit({ name, stages });
    }


    render() {

        const {
            name, 
            stages
        } = this.state;

        //console.log({ props: this.props, state: this.state });
        return (
            <form id="line-edit-form" className="line-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}

                <TextField
                    label="Nazwa"
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
                    value={stages}
                    isMulti
                    options={this.state.allStages}
                    onChange={this.onChangeStages}
                    />
                <p></p>
                
                <Button 
                    variant="contained"
                    color="primary"
                    className="line-create__button"
                    onClick={this.onSubmit}
                >Zapisz
                </Button>
            </form>                
        );
    }
}

CreateLineForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default CreateLineForm;