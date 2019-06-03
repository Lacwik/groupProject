import React, { Component } from 'react';
import { stageRepository } from '../factory/stageRepository.factory';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import ViewStage from './components/viewStage.component'
import EditStageForm from './components/editStageForm.component'
import AddStageForm from './components/addStageForm.component'
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { BorderColor, Visibility, DeleteForever, GroupWork } from '@material-ui/icons';


class ViewStageContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            stageList: [],
            defaultStageList: [],
            activeStageId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false,
            dialog_create: false,
            dialog_delete: false,
        }
    }

    componentDidMount(){

        Promise.all([
            stageRepository.getCompanyStages(), 
            stageRepository.getDefaultStages(),
        ])
        .then( ([companyStages, defaultStages]) => {
            this.setState({
                stageList: companyStages,
                defaultStageList: defaultStages
            });

        })
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditStage = (stageModel) => {
        return stageRepository.editStage(stageModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }

    onCreateStage = (stageModel) => {
        return stageRepository.createStage(stageModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    } 

    onDeleteStage = (id) => {
        return stageRepository.deleteStage(id)
            .then(window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    } 


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
        this.setState({dialog_create: false});
        this.setState({dialog_delete: false});
    }


    onClickStage_edit = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_edit: true});
    }

    onClickStage_show = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_show: true});
    }

    onClickStage_delete = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_delete: true});
    }

    onClickStage_create = () => {;
        this.setState({dialog_create: true});
    }


    companyStagesListRender() {
        return (
            <React.Fragment>

            <Dialog open={this.state.dialog_edit} onClose={this.onCloseDialog} aria-labelledby="dialog-edit-stage">
                <DialogContent>
                    <EditStageForm id={this.state.activeStageId} onSubmit={stageModel => this.onEditStage(stageModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_create} onClose={this.onCloseDialog} aria-labelledby="dialog-create-stage">
                <DialogTitle id="dialog-create-satge">Nowy etap</DialogTitle>
                <DialogContent>
                    <AddStageForm onSubmit={satgeModel => this.onCreateStage(satgeModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_delete} onClose={this.onCloseDialog} aria-labelledby="dialog-delete-stage">
                <DialogTitle id="dialog-delete-stage">Usuń etap</DialogTitle>
                <DialogContent>
                    Czy na pewno chcesz trwale usunąć etap: 
                    <b><ViewStage id={this.state.activeStageId} full_info={false}></ViewStage></b>
                    <Button onClick={() => this.onDeleteStage(this.state.activeStageId)} color="secondary">
                    Tak, usuń wybrany etap
                    </Button>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>



            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-stage">
                <DialogContent>
                    <ViewStage id={this.state.activeStageId} full_info={true}></ViewStage>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Powrót
                    </Button>
                </DialogActions>
            </Dialog>

            <table>
                <thead>
                    <tr>
                        <td>
                            <Fab 
                                color="secondary" 
                                aria-label="Add" 
                                className="fab-stage-head" 
                                onClick={() => this.onClickStage_create()}
                            ><AddIcon />
                            </Fab>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <ul>
                                {this.state.stageList.map(item => (
                                    <li key={item.id}>
                                        <ViewStage id={item.id} full_info={false}></ViewStage>
                                        <br />
                                        <Fab 
                                            color="secondary"
                                            aria-label="Delete" 
                                            onClick= {() => this.onClickStage_delete(item.id)}
                                        ><DeleteForever />
                                        </Fab>
                                        <Fab 
                                            color="primary" 
                                            aria-label="Edit" 
                                            onClick= {() => this.onClickStage_edit(item.id)}
                                        ><BorderColor />
                                        </Fab>
                                        <Fab 
                                            color="primary" 
                                            aria-label="Show" 
                                            onClick= {() => this.onClickStage_show(item.id)}
                                        ><Visibility />
                                        </Fab>
                                    </li>
                                ))}
                            </ul>
                        </td>
                        <td>
                            <ul>
                                {this.state.defaultStageList.map(item => (
                                    <li key={item.id}>
                                        <ViewStage id={item.id} full_info={false}></ViewStage>
                                        <br />
                                        <Fab 
                                            color="secondary"
                                            aria-label="Delete" 
                                            onClick= {() => this.onClickStage_delete(item.id)}
                                        ><DeleteForever />
                                        </Fab>
                                        <Fab 
                                            color="primary" 
                                            aria-label="Edit" 
                                            onClick= {() => this.onClickStage_edit(item.id)}
                                        ><BorderColor />
                                        </Fab>
                                        <Fab 
                                            color="primary" 
                                            aria-label="Show" 
                                            onClick= {() => this.onClickStage_show(item.id)}
                                        ><Visibility />
                                        </Fab>
                                    </li>
                                ))}
                            </ul>
                        </td>
                    </tr>
                </tbody>
            </table>
            </React.Fragment>
        );
      }


    render() {
        return (
            <div className="view-stage-container">
                <div className="wrapper-content"> 
                <GroupWork style={{ color: '#232323;', fontSize:"55px", border:"#69ff72", background:"#69ff72", borderRadius:"3px" }} fontSize="large" />
                <h1 className="header"> Dostępne etapy </h1>
                    {this.companyStagesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewStageContainer;