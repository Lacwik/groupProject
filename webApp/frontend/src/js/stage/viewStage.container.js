import React, { Component } from 'react';
import { stageRepository } from '../factory/stageRepository.factory';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import ViewStage from './components/viewStage.component'


class ViewStageContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            stageList: [],
            activeStageId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false
        }
    }

    componentDidMount(){
        stageRepository.getCompanyStages().then(
            response => this.setState({stageList: response, activeStageId: response ? response[0].id : undefined}),
        )
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditStage = (stageModel) => {
        return stageRepository.editStage(stageModel)
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
    }


    onClickStage_edit = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_edit: true});
    }

    onClickStage_show = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_show: true});
    }


    companyStagesListRender() {
        return (
            <React.Fragment>

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

                <ul>
                    {this.state.stageList.map(item => (
                        <li key={item.id}>
                            <ViewStage id={item.id} full_info={false}></ViewStage>
                            <br />
                            <Button 
                                variant="contained"
                                color="primary" 
                                onClick= {() => this.onClickStage_show(item.id)}
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
            <div className="view-stage-container">
                <div className="wrapper-content"> 
                    {this.companyStagesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewStageContainer;