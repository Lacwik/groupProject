import React, { Component } from 'react';
import { any } from 'prop-types';
import { stageRepository } from '../../factory/stageRepository.factory';
import ViewModule from '../../module/components/viewModule.component'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import { Settings } from '@material-ui/icons';

class ViewStage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            stage: any,
            name: '',
            modulesOrder: '',
            modulesModels: [],
            full_info: false,
            dialog_show: false,
            activeModuleId: undefined,
        };
    }

    componentDidMount(){

        Promise.all([
            stageRepository.getStageById(this.props.id),
        ]).then( ([stageModel]) => {
            this.setState({
                stage: stageModel,
                name: stageModel.name,
                full_info: this.props.full_info,
                modulesOrder: stageModel.modulesOrder,
            })
        })
    }

    onCloseDialog = () => {
        this.setState({dialog_show: false});
    }

    onClickModule_show = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_show: true});
    }


    render() {

        const name = this.state.stage.name;
        var modulesModels = this.state.stage.modulesModels;
        const dbOrder = this.state.modulesOrder;
        const full_info = this.state.full_info;


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

        let fullInfo;
        if (full_info) {
            fullInfo = (
                <React.Fragment>
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

                    <li key="name-view-modules">
                        <b>{"Moduły:"}</b>
                        <p></p>
                        <ul>
                            {modulesModels.map(item => (
                                <li className="elements" key={item.id}>
                                    <Button 
                                        className = "view-elements-button"
                                        variant="outlined" 
                                        color="primary" 
                                        size = "small"
                                        border = "none"
                                        onClick={() => this.onClickModule_show(item.id)}
                                    ><Settings style={{ color: '#9988ff' }} size="small" />
                                    </Button>
                                    {item.name}
                                </li>
                            ))}
                        </ul>
                    </li>

                </React.Fragment>
            );
        }

        return (
            <React.Fragment>
                <ul>

                    <li key="name-view-stage">
                        <h1>{name}</h1>
                    </li>

                    {fullInfo}                  

                </ul>
            </React.Fragment>
        );
    }
}

export default ViewStage;