import React, { Component } from 'react';
import PropTypes from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import { stageRepository } from '../../factory/stageRepository.factory';
import { vegetableRepository } from '../../factory/vegetable.factory';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';
import { Sort } from '@material-ui/icons';


class CreateLineForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            stagesOrder: '',
            stageModels: [],
            allStages: [],
            allVegetables: [],
            avaliableStages: [],
            selectedVegetables: [],
            isVegetableSelected: false,
            dialog_show: false,
        };
    }

    componentDidMount(){
        Promise.all([
            stageRepository.getCompanyStages(), 
            stageRepository.getDefaultStages(),
            vegetableRepository.getAllVegetables(),
        ])
        .then( ([companyStages, defaultStages, vegetables]) => {
            this.setState({
                allStages: [...companyStages, ...defaultStages].map(v => ({ ...v, value: v.id, label: v.name })),
                allVegetables: vegetables.map(v => ({ ...v, value: v.id, label: v.name }))
            });

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
            })
            Promise.all([
                stageRepository.getStagesByVegetableList(selectedVegetables),
            ]).then( ([stagesResponse]) => {
                this.setState({avaliableStages: stagesResponse.map(v => ({ ...v, value: v.id, label: v.name })) });
            })
            
        }
        else {
            this.setState({isVegetableSelected: false})
        }
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


    onSubmit = () => {
        const { name, stageModels, stagesOrder } = this.state;

        this.props.onSubmit({ name, stageModels, stagesOrder });
    }


    render() {

        const {
            name, 
            stageModels,
            selectedVegetables,
            allVegetables,
            isVegetableSelected,
            avaliableStages
        } = this.state;


        let stagesByVegetable;
        if (isVegetableSelected) {
            stagesByVegetable = (
                <React.Fragment>
                    Dostępne etapy:
                    <Select 
                        closeMenuOnSelect={false}
                        components={makeAnimated()}
                        value={stageModels}
                        isMulti
                        options={avaliableStages}
                        onChange={this.onChangeStages}
                        placeholder = "Wybierz etapy.."
                        maxMenuHeight = {60}
                    />
                    <br></br>
                    * kolejność dodawania ma znaczenie
                    <p></p>
                </React.Fragment>
            )
        }


        return (
            <form id="line-edit-form" className="line-edit-form" onSubmit={e => e.preventDefault()}>
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
                    maxMenuHeight = {90}
                    />
                <p></p>

                {stagesByVegetable}

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