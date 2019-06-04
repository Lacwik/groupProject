import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { vegetableRepository } from '../../factory/vegetable.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';
import { stageRepository } from '../../factory/stageRepository.factory';
import { Sort } from '@material-ui/icons';


class AddStageForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            modulesOrder: '',
            modulesModels: [],
            avaliableModules: [],
            allModules: [],
            allVegetables: [],
            selectedVegetables: [],
            isVegetableSelected: false,
            dialog_show: false,
        };
    }

    componentDidMount(){
        Promise.all([
            moduleRepository.getCompanyModules(), 
            moduleRepository.getDefaultModules(),
            vegetableRepository.getAllVegetables(),
        ])
        .then( ([companyModules, defaultModules, vegetables]) => {
            this.setState({
                allModules: [...companyModules, ...defaultModules].map(v => ({ ...v, value: v.id, label: v.name })),
                allVegetables: vegetables.map(v => ({ ...v, value: v.id, label: v.name }))
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
        this.setState({modulesOrder: ''});
        let order = '';
        modulesModels.forEach(element => {
            order = order + element.id.toString() + ';'; 
        });
        this.setState({modulesOrder: order});
        console.log(modulesModels)
        console.log(order)
    }

    setModulesOrder = (value) => {
        let order =  this.state.modulesOrder;
        order += value.id.toString() + ';';
        

        this.setState({modulesOrder: order}) ;
        
    }

    onCloseDialog = () => {
        this.setState({dialog_show: false});
    }

    onClickQuickView = () => {
        this.setState({dialog_show: true});
    }

    onChangeVegetables = e => {
        const selectedVegetables = e;

        this.setState(state => ({ ...state, selectedVegetables }));
        if (selectedVegetables.length !== 0) {
            this.setState({
                isVegetableSelected: true,
               // avaliableModules: this.state.allModules.filter(this.moduleContainsVegetables)
            })
            
        }
        else {
            this.setState({isVegetableSelected: false})
        }
        console.log(this.state.avaliableModules)
    }


    onSubmit = () => {
        const { name, modulesModels, modulesOrder } = this.state;

        this.props.onSubmit({ name, modulesModels, modulesOrder });
    }



    viewDialogs = () => {
        return(
            <React.Fragment>
                <Dialog 
                open={this.state.dialog_show} 
                onClose={this.onCloseDialog} 
                aria-labelledby="dialog-quick-view"
                >
                    <DialogTitle id="dialog-quick-view dialog-header">Szybki podgląd</DialogTitle>
                    <DialogContent>
                        <h2>{this.state.name}</h2>
                        <br></br>

                    </DialogContent>
                    <DialogActions>
                        <Button onClick={this.onCloseDialog} color="primary">
                        Powrót
                        </Button>
                    </DialogActions>
                </Dialog>
            </React.Fragment>
        )
    }


    render() {

        const {
            name, 
            modulesModels,
            avaliableModules,
            selectedVegetables,
            allVegetables,
            isVegetableSelected,
        } = this.state;

        let modulesByVegetable;
        if (isVegetableSelected) {
            modulesByVegetable = (
                <React.Fragment>
                    Dostępne moduły:
                    <Select 
                        closeMenuOnSelect={false}
                        components={makeAnimated()}
                        value={modulesModels}
                        isMulti
                        options={this.state.allModules}
                        onChange={this.onChangeModules}
                        placeholder = "Wybierz moduły.."
                        maxMenuHeight = {60}
                    />
                    <br></br>
                    * kolejność dodawania ma znaczenie
                    <p></p>
                </React.Fragment>
            )
        }

        return (


            <form id="stage-edit-form" className="stage-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}

                {this.viewDialogs()}
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
                    value={selectedVegetables}
                    isMulti
                    options={allVegetables}
                    onChange={this.onChangeVegetables}
                    placeholder="Wybierz warzywo.."
                    maxMenuHeight = {100}
                    />
                <p></p>

                {modulesByVegetable}
                
                <Button 
                    variant="outlined"
                    color="default"
                    className="stage-quick-view__button"
                    onClick={this.onClickQuickView}
                ><Sort size="small" />
                </Button>

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