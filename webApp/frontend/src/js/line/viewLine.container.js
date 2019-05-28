import React, { Component } from 'react';
import { lineRepository } from '../factory/lineRepository.factory';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import ViewLine from './components/viewLine.component';
import EditLineForm from './components/editLineForm.component';


class ViewLineContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            linesList: [],
            activeLineId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false
        }
    }

    componentDidMount(){
        lineRepository.getCompanyLines().then(
            response => this.setState({linesList: response, activeLineId: response ? response[0].id : undefined}),
        )
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditLine = (lineModel) => {
        return lineRepository.editLine(lineModel)
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
    }


    onClickLine_edit = (id) => {
        this.setState({activeLineId: id});
        this.setState({dialog_edit: true});
    }

    onClickLine_show = (id) => {
        this.setState({activeLineId: id});
        this.setState({dialog_show: true});
    }


    companyLinesListRender() {
        return (
            <React.Fragment>


            <Dialog open={this.state.dialog_edit} onClose={this.onCloseDialog} aria-labelledby="dialog-edit-line">
                <DialogContent>
                    <EditLineForm id={this.state.activeLineId} onSubmit={lineModel => this.onEditLine(lineModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-line">
                <DialogContent>
                    <ViewLine id={this.state.activeLineId} full_info={true}></ViewLine>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Powrót
                    </Button>
                </DialogActions>
            </Dialog>

                <ul>
                    {this.state.linesList.map(item => (
                        <li key={item.id}>
                            <ViewLine id={item.id} full_info={false}></ViewLine>
                            <br />
                            <Button 
                                variant="contained"
                                color="primary" 
                                onClick= {() => this.onClickLine_edit(item.id)}
                            >Edycja
                            </Button>
                            <Button 
                                variant="contained"
                                color="primary" 
                                onClick= {() => this.onClickLine_show(item.id)}
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
            <div className="view-line-container">
                <div className="wrapper-content"> 
                    {this.companyLinesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewLineContainer;