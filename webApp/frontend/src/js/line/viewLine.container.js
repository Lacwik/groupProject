import React, { Component } from 'react';
import { lineRepository } from '../factory/lineRepository.factory';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import ViewLine from './components/viewLine.component';
import EditLineForm from './components/editLineForm.component';
import AddLineForm from './components/addLineForm.component';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { BorderColor, Visibility, DeleteForever, LinearScale } from '@material-ui/icons';


class ViewLineContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            linesList: [],
            activeLineId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false,
            dialog_create: false,
            dialog_delete: false,
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


    onCreateLine = (lineModel) => {
        return lineRepository.createLine(lineModel)
            .catch(err => this.setErrorMessage(err));
    }


    onDeleteLine = (id) => {
        return lineRepository.deleteLine(id)
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

    onClickLine_delete = (id) => {
        this.setState({activeLineId: id});
        this.setState({dialog_delete: true});
    }

    onClickLine_create = () => {
        this.setState({dialog_create: true});
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


            <Dialog open={this.state.dialog_create} onClose={this.onCloseDialog} aria-labelledby="dialog-create-line">
                <DialogTitle id="dialog-create-line">Nowa linia produkcyjna</DialogTitle>
                <DialogContent>
                    <AddLineForm onSubmit={lineModel => this.onCreateLine(lineModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_delete} onClose={this.onCloseDialog} aria-labelledby="dialog-delete-line">
                <DialogTitle id="dialog-delete-stage">Usuń linie</DialogTitle>
                <DialogContent>
                    Czy na pewno chcesz trwale usunąć linię produkcyjną: 
                    <b><ViewLine id={this.state.activeLineId} full_info={false}></ViewLine></b>
                    <Button onClick={() => this.onDeleteLine(this.state.activeLineId)} color="secondary">
                    Tak, usuń wybrany etap
                    </Button>
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


            <table>
                <thead>
                    <tr>
                        <td>
                            <Fab 
                                color="secondary" 
                                aria-label="Add" 
                                className="fab-line-head" 
                                onClick={() => this.onClickLine_create()}
                            ><AddIcon />
                            </Fab>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <ul>
                            {this.state.linesList.map(item => (
                                <li key={item.id}>
                                    <ViewLine id={item.id} full_info={false}></ViewLine>
                                    <br />
                                    <Fab 
                                        color="secondary"
                                        aria-label="Delete" 
                                        onClick= {() => this.onClickLine_delete(item.id)}
                                    ><DeleteForever />
                                    </Fab>
                                    <Fab 
                                        color="primary" 
                                        aria-label="Edit" 
                                        onClick= {() => this.onClickLine_edit(item.id)}
                                    ><BorderColor />
                                    </Fab>
                                    <Fab 
                                        color="primary" 
                                        aria-label="Show" 
                                        onClick= {() => this.onClickLine_show(item.id)}
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
            <div className="view-line-container">
                <div className="wrapper-content"> 
                    {this.companyLinesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewLineContainer;