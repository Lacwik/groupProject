import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import { stageRepository } from '../../factory/stageRepository.factory';
import { lineRepository } from '../../factory/lineRepository.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';


class EditLineForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            stagesOrder: '',
            stageModels: [],
            allStages: [],
            lineVegetables: [],
            avaliableStages: [],
        };
    }

    componentDidMount(){
        Promise.all([
            stageRepository.getCompanyStages(), 
            stageRepository.getDefaultStages(), 
            lineRepository.getLineById(this.props.id),
            lineRepository.getLineVegetables(this.props.id),
        ])
        .then( ([companyStages, defaultStages, lineModel, vegetables]) => {
            this.setState({
                allStages: [...companyStages, ...defaultStages].map(v => ({ ...v, value: v.id, label: v.name })),
                name: lineModel.name,
                stageModels: lineModel.stageModels.map(v => ({ ...v, value: v.id, label: v.name })),
                lineVegetables: vegetables,
            });

        })
    }

    componentDidUpdate(){
        Promise.all([
            stageRepository.getStagesByVegetableList(this.state.lineVegetables),
        ]).then( ([stagesResponse]) => {
            this.setState({ avaliableStages: stagesResponse.map(v => ({ ...v, value: v.id, label: v.name }))})
        })
    }


    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }


    onChangeStages = e => {
        const stageModels = e;

        this.setState(state => ({ ...state, stageModels }));
        this.setState({stagesOrder: ''});
        let order = '';
        stageModels.forEach(element => {
            order = order + element.id.toString() + ';'; 
        });
        this.setState({stagesOrder: order});
    }


    onSubmit = () => {
        const { name, stageModels, stagesOrder } = this.state;

        this.props.onSubmit({ name, stageModels, stagesOrder, id: this.props.id });
    }


    render() {

        const {
            name, 
            stageModels
        } = this.state;

        return (
            <form id="line-edit-form" className="line-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}

                <TextField
                    label="Nazwa"
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
                    value={stageModels}
                    isMulti
                    options={this.state.allStages}
                    onChange={this.onChangeStages}
                    placeholder="Wybierz etapy.."
                    maxMenuHeight = {90}
                    />
                <p></p>
                
                <Button 
                    variant="contained"
                    color="primary"
                    className="line-edit__button"
                    onClick={this.onSubmit}
                >Zapisz
                </Button>
            </form>                
        );
    }
}

EditLineForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default EditLineForm;