import React, { Component } from 'react';
import { any } from 'prop-types';
import { stageRepository } from '../../factory/stageRepository.factory';
import ViewModule from '../../module/components/viewModule.component'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import { ChevronRight } from '@material-ui/icons';

class ViewStage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            stage: any,
            full_info: false,
            dialog_show: false,
            activeModuleId: undefined,
        };
    }

    componentDidMount(){
        stageRepository.getStageById(this.props.id).then(
            response => this.setState({ stage: response })
        ).then(() => this.setState({full_info: this.props.full_info})
        )
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
        const modulesModels = this.state.stage.modulesModels;
        const full_info = this.state.full_info;

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
                        <ul>
                            {modulesModels.map(item => (
                                <li key={item.id}>
                                    <Button 
                                        variant="outlined" 
                                        color="primary" 
                                        size = "small"
                                        onClick={() => this.onClickModule_show(item.id)}
                                    ><ChevronRight size="small" />
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