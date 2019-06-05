import React, { Component } from 'react';
import { any } from 'prop-types';
import { lineRepository } from '../../factory/lineRepository.factory';
import ViewStage from '../../stage/components/viewStage.component'
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import { GroupWork } from '@material-ui/icons';

class ViewLine extends Component {
    constructor(props) {
        super(props);

        this.state = {
            line: any,
            stagesOrder: '',
            full_info: false,
            dialog_show: false,
            activeStageId: undefined,
        };
    }

    componentDidMount(){
        Promise.all([
            lineRepository.getLineById(this.props.id),
        ]).then( ([lineModel]) => {
            this.setState({
                stagesOrder: lineModel.stagesOrder,
                full_info: this.props.full_info,
                line: lineModel,
            })
        })
    }

    onCloseDialog = () => {
        this.setState({dialog_show: false});
    }

    onClickStage_show = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_show: true});
    }


    render() {

        const name = this.state.line.name;
        var stageModels = this.state.line.stageModels;
        const full_info = this.state.full_info;
        const dbOrder = this.state.stagesOrder;

        if(dbOrder){
            if(dbOrder.length !== 0){
                var sortedStagesModels = [];
                var order = dbOrder.split(";");
                order.pop();
                order = order.map(Number);
                var tempStage;
                order.forEach( (value) => {
                    tempStage = stageModels.find(function(element){
                        return element.id === value
                    });
                    sortedStagesModels.push(tempStage);
                });
                stageModels = sortedStagesModels;
            }
        }

        let fullInfo;
        if (full_info) {
            fullInfo = (
                <React.Fragment>
                    <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-module">
                        <DialogContent>
                            <ViewStage id={this.state.activeStageId} full_info={true}></ViewStage>
                        </DialogContent>
                        <DialogActions>
                            <Button onClick={this.onCloseDialog} color="primary">
                            Powrót
                            </Button>
                        </DialogActions>
                    </Dialog>

                    <li key="name-view-stages">
                        <b>{"Etapy:"}</b>
                        <p></p>
                        <ul>
                            {stageModels.map(item => (
                                <li className="elements" key={item.id}>
                                    <Button 
                                        className = "view-elements-button"
                                        variant="outlined" 
                                        color="primary" 
                                        size = "small"
                                        onClick={() => this.onClickStage_show(item.id)}
                                    ><GroupWork style={{ color: '#66aaee' }} size="small" />
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

                    <li key="name-view-line">
                        <h1>{name}</h1>
                    </li>

                    {fullInfo}                  

                </ul>
            </React.Fragment>
        );
    }
}

export default ViewLine;