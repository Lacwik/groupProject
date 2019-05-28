import React, { Component } from 'react';
import { moduleRepository } from '../factory/moduleRepository.factory';
import EditModuleForm from './components/editModuleForm.component';
import ViewModule from './components/viewModule.component';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';


class ViewModuleContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            moduleList: [],
            activeModuleId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false
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
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false})
    }


    onClickModule_edit = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_edit: true});
    }

    onClickModule_show = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_show: true});
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

                <ul>
                    {this.state.moduleList.map(item => (
                        <li key={item.id}>
                            <ViewModule id={item.id} full_info={false}></ViewModule>
                            <br />
                            <Button 
                                variant="contained"
                                color="primary"
                                onClick= {() => this.onClickModule_edit(item.id)}
                            >Edycja
                            </Button>
                            <Button 
                                variant="contained"
                                color="primary"
                                onClick= {() => this.onClickModule_show(item.id)}
                            >Podgląd
                            </Button>
                        </li>
                    ))}
                </ul>

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