import React, { Component } from 'react';
import { moduleRepository } from '../factory/moduleRepository.factory';
import { VIEW_MODULE_SWITCH_STATES } from './viewModuleSwitchStates.enum';
import ModuleFormSwitch from './components/moduleFormSwitch.component';
import EditModuleForm from './components/editModuleForm.component';
import ViewModule from './components/viewModule.component';
import Button from '@material-ui/core/Button';


class ViewModuleContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            moduleList: [],
            activeModuleId: undefined,
            error: '',
            viewForm: VIEW_MODULE_SWITCH_STATES.VIEW

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


    onClickModule = (id) => {
        this.setState({activeModuleId: id});
    }


    companyModulesListRender() {
        return (
            <React.Fragment>
            <table>

                <td>
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
                </td>
                <td>
                    {this.state.activeModuleId ? <ViewModule id={this.state.activeModuleId} full_info={false}></ViewModule> : "Brak modułów do wyświetlenia"}
                    
                </td>

            </table>
            </React.Fragment>
        );
      }


    renderNeccessaryForm = () => {
        const { viewForm, error } = this.state;
        const { VIEW, EDIT } = VIEW_MODULE_SWITCH_STATES;

        switch (viewForm) {
            case VIEW:
                return this.companyModulesListRender();

            case EDIT:
                return (
                    <EditModuleForm id={this.state.activeModuleId} onSubmit={moduleModel => this.onEditModule(moduleModel)} errorMessage={error} />
                );

            default:
                console.error("Unknow state: " + viewForm);    
                return this.companyModulesListRender();
        }
    }



    render() {
        return (
            <div className="view-module-container">
                <div className="wrapper-content"> 
                    <ModuleFormSwitch switchForms={value => this.onSwitchForm(value)} defaultValue={this.state.viewForm} />
                    {this.renderNeccessaryForm()}
                </div>
            </div>
        );
    }
}


export default ViewModuleContainer;