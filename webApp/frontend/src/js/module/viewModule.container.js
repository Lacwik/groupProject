import React, { Component } from 'react';
import { moduleRepository } from '../factory/moduleRepository.factory';
import { VIEW_MODULE_SWITCH_STATES } from './viewModuleSwitchStates.enum';
import ModuleFormSwitch from './components/moduleFormSwitch.component';
import EditModuleForm from './components/editModuleForm.component';
import ViewModule from './components/viewModule.component';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import { CenterFocusStrong } from '@material-ui/icons';


class ViewModuleContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            moduleList: [],
            activeModuleId: undefined,
            error: '',
            viewForm: VIEW_MODULE_SWITCH_STATES.VIEW,
            dialog_open: false

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


    onSwitchForm = (viewForm) => {
        this.setState(state => ({ ...state, viewForm }));
    }


    onCloseDialog = () => {
        this.setState({dialog_open: false})
    }


    onClickModule = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_open: true});
    }


    companyModulesListRender() {
        return (
            <React.Fragment>

            <Dialog open={this.state.dialog_open} onClose={this.onCloseDialog} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Edycja</DialogTitle>
                <DialogContent>
                    <EditModuleForm id={this.state.activeModuleId} onSubmit={moduleModel => this.onEditModule(moduleModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>



            <table>
            <thead>
                <tr>
                    <td>
                        {"W tym rzędzie powinno sie znaleźć info podglądowe modułu"}
                    </td>
                    <td>
                        {this.state.activeModuleId ? <ViewModule id={this.state.activeModuleId} full_info={false}></ViewModule> : "Brak modułów do wyświetlenia"}
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
                            <Button 
                                variant="contained"
                                color="primary"
                                onClick= {() => this.onClickModule(item.id)}
                            >Edytuj
                            </Button>
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