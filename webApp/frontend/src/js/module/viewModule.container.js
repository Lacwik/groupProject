import React, { Component } from 'react';
import { moduleRepository } from '../factory/moduleRepository.factory';
import EditModuleForm from './components/editModuleForm.component';
import AddModuleForm from './components/addModuleForm.component';
import ViewModule from './components/viewModule.component';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { BorderColor, Visibility, DeleteForever  } from '@material-ui/icons';


class ViewModuleContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            moduleList: [],
            activeModuleId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false,
            dialog_create: false,
            dialog_delete: false,
        }
    }

    componentDidMount(){
        moduleRepository.getCompanyModules().then(
            response => this.setState({moduleList: response, activeModuleId: response ? response[0].id : undefined}),
        )
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditModule = (moduleModel) => {
        return moduleRepository.editModule(moduleModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCreateModule = (moduleModel) => {
        console.log(moduleModel)
        return moduleRepository.createModule(moduleModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onDeleteModule = (id) => {
        return moduleRepository.deleteModule(id)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
        this.setState({dialog_create: false});
        this.setState({dialog_delete: false});
    }


    onClickModule_edit = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_edit: true});
    }

    onClickModule_show = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_show: true});
    }

    onClickModule_create = () => {
        this.setState({dialog_create: true});
    }

    onClickModule_delete = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_delete: true});
    }


    companyModulesListRender() {
        return (
            <React.Fragment>

            <Dialog open={this.state.dialog_edit} onClose={this.onCloseDialog} aria-labelledby="dialog-edit-module">
                <DialogTitle id="dialog-edit-module">Edycja</DialogTitle>
                <DialogContent>
                    <EditModuleForm id={this.state.activeModuleId} onSubmit={moduleModel => this.onEditModule(moduleModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_create} onClose={this.onCloseDialog} aria-labelledby="dialog-create-module">
                <DialogTitle id="dialog-create-module">Nowy moduł</DialogTitle>
                <DialogContent>
                    <AddModuleForm onSubmit={moduleModel => this.onCreateModule(moduleModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_delete} onClose={this.onCloseDialog} aria-labelledby="dialog-delete-module">
                <DialogTitle id="dialog-delete-module">Usuń moduł</DialogTitle>
                <DialogContent>
                    Czy na pewno chcesz trwale usunąć moduł: 
                    <b><ViewModule id={this.state.activeModuleId} full_info={false}></ViewModule></b>
                    <Button onClick={() => this.onDeleteModule(this.state.activeModuleId)} color="secondary">
                    Tak, usuń wybrany moduł
                    </Button>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-module">
                <DialogContent>
                    <ViewModule id={this.state.activeModuleId} full_info={true}></ViewModule>
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
                                className="fab-module-head"
                                onClick={() => this.onClickModule_create()}
                            ><AddIcon />
                            </Fab>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <ul>
                        {this.state.moduleList.map(item => (
                            <li key={item.id}>
                                <ViewModule id={item.id} full_info={false}></ViewModule>
                                <br />
                                <Fab 
                                    color="secondary"
                                    aria-label="Delete" 
                                    onClick= {() => this.onClickModule_delete(item.id)}
                                ><DeleteForever />
                                </Fab>
                                <Fab 
                                    color="primary" 
                                    aria-label="Edit" 
                                    onClick= {() => this.onClickModule_edit(item.id)}
                                ><BorderColor />
                                </Fab>
                                <Fab 
                                    color="primary" 
                                    aria-label="Show" 
                                    onClick= {() => this.onClickModule_show(item.id)}
                                ><Visibility />
                                </Fab>
                            </li>
                        ))}
                    </ul>
                    </tr>
                </tbody>
            </table>
            </React.Fragment>
        );
      }


    render() {
        return (
            <div className="view-module-container">
                <div className="wrapper-content"> 
                    {this.companyModulesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewModuleContainer;