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
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';

const responsive = {
    desktop: {
      breakpoint: { max: 3000, min: 1024 },
      items: 3
    },
    tablet: {
      breakpoint: { max: 1024, min: 464 },
      items: 2
    },
    mobile: {
      breakpoint: { max: 464, min: 0 },
      items: 1
    }
  };

class ViewLineContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            linesList: [],
            defaultLinesList: [],
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
            response => this.setState({linesList: response, activeLineId: response.length !== 0 ? response[0].id : undefined}),
        ).then(
            lineRepository.getDefaultLines().then(
                response => this.setState({defaultLinesList: response})
            )
        )
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditLine = (lineModel) => {
        return lineRepository.editLine(lineModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCreateLine = (lineModel) => {
        return lineRepository.createLine(lineModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onDeleteLine = (id) => {
        return lineRepository.deleteLine(id)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
        this.setState({dialog_create: false});
        this.setState({dialog_delete: false});
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
    
    viewDialogs = () => {
        return(
            <React.Fragment>

            <Dialog open={this.state.dialog_edit} onClose={this.onCloseDialog} aria-labelledby="dialog-edit-line" className="dialog">
            <DialogTitle id="dialog-edit-line">Edytuj linię</DialogTitle>
                <DialogContent>
                    <EditLineForm id={this.state.activeLineId} onSubmit={lineModel => this.onEditLine(lineModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_create} onClose={this.onCloseDialog} aria-labelledby="dialog-create-line" className="dialog">
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


            <Dialog open={this.state.dialog_delete} onClose={this.onCloseDialog} aria-labelledby="dialog-delete-line" className="dialog delete">
                <DialogTitle id="dialog-delete-stage">Usuń linię</DialogTitle>
                <DialogContent>
                    Czy na pewno chcesz trwale usunąć linię produkcyjną: 
                    <b><ViewLine id={this.state.activeLineId} full_info={false}></ViewLine></b>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => this.onDeleteLine(this.state.activeLineId)} color="secondary">
                    Tak, usuń wybraną linię
                    </Button>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-line" className="dialog">
                <DialogContent>
                    <ViewLine id={this.state.activeLineId} full_info={true}></ViewLine>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Powrót
                    </Button>
                </DialogActions>
            </Dialog>
            </React.Fragment>
        )
    }

    viewLineInCarousel = (item) => {
        return(
            <React.Fragment>
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
            </React.Fragment>
        )
    }


    companyLinesListRender() {
        return (
            <React.Fragment>


            {this.viewDialogs()}

            <Fab 
                color="secondary" 
                aria-label="Add" 
                className="fab-line-head button add" 
                onClick={() => this.onClickLine_create()}
            ><AddIcon />
            </Fab>
            <p></p>
            <h3 class="elements-type">Linie należące do firmy:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={this.state.linesList.length<3 ? false:true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.linesList.map(item => (
                        <div key={item.id} style={{background: 'white', padding: '1em 0', boxSizing: 'border-box' }}>
                            {this.viewLineInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>
            <h3 class="elements-type">Linie domyślne:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={this.state.defaultLinesList.length<3 ? false:true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.defaultLinesList.map(item => (
                        <div key={item.id} style={{background: 'white', padding: '1em 0', boxSizing: 'border-box' }}>
                            {this.viewLineInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>
            </React.Fragment>
        );
      }


    render() {
        return (
            <div className="view-line-container">
                <div className="wrapper-content"> 
                <div className="header-icon"><LinearScale  style={{ color: '#aaddbb', fontSize:"55px" }} fontSize="large" /></div>
                <div className="header"> Dostępne linie </div>
                    {this.companyLinesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewLineContainer;