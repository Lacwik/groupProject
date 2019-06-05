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
            modulesOrder: '',
            modulesModels: [],
            allModules: [],
            avaliableModules: [],
            stageVegetables: [],
        };
    }

    componentDidMount(){
        Promise.all([
            stageRepository.getStageById(this.props.id),
            moduleRepository.getCompanyModules(), 
            moduleRepository.getDefaultModules(),
            stageRepository.getStageVegetables(this.props.id),
        ])
        .then( ([stageModel, companyModules, defaultModules, stageVegetables]) => {
            this.setState({
                name: stageModel.name,
                modulesModels: stageModel.modulesModels.map(v => ({ ...v, value: v.id, label: v.name })),
                allModules: [...companyModules, ...defaultModules].map(v => ({ ...v, value: v.id, label: v.name })),
                stageVegetables: stageVegetables,
                modulesOrder: stageModel.modulesOrder,
            });

        })
    }

    componentDidUpdate(){
        Promise.all([
            stageRepository.getModulesByVegetableList(this.state.stageVegetables),
        ]).then( ([moduleResponse]) => {
            this.setState({ avaliableModules: moduleResponse.map(v => ({ ...v, value: v.id, label: v.name }))})
        })
    }


    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }


    onChangeModules = e => {
        const modulesModels = e;

        this.setState(state => ({ ...state, modulesModels }));
        this.setState({modulesOrder: ''});
        let order = '';
        modulesModels.forEach(element => {
            order = order + element.id.toString() + ';';
        });
        this.setState({modulesOrder: order});
    }


    onSubmit = () => {
        const { name, modulesModels, modulesOrder } = this.state;

        this.props.onSubmit({ name, modulesModels, modulesOrder, id: this.props.id });
    }


    render() {

        const {
            name, 
        } = this.state;
        var modulesModels = this.state.modulesModels;
        const dbOrder = this.state.modulesOrder;

        if(dbOrder){
            if(dbOrder.length !== 0){
                var sortedModulesModels = [];
                var order = dbOrder.split(";");
                order.pop();
                order = order.map(Number);
                var tempModule;
                order.forEach( (value) => {
                    tempModule = modulesModels.find(function(element){
                        return element.id === value
                    });
                    sortedModulesModels.push(tempModule);
                });
                modulesModels = sortedModulesModels;
            }
        }


        return (
            <form id="stage-edit-form" className="stage-edit-form" onSubmit={e => e.preventDefault()}>
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
                    value={modulesModels}
                    isMulti
                    options={this.state.avaliableModules}
                    onChange={this.onChangeModules}
                    placeholder="Wybierz moduły.."
                    maxMenuHeight = {120}
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