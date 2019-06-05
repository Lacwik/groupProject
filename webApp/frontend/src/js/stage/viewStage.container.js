import React, { Component } from 'react';
import { stageRepository } from '../factory/stageRepository.factory';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogTitle from '@material-ui/core/DialogTitle';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import ViewStage from './components/viewStage.component'
import EditStageForm from './components/editStageForm.component'
import AddStageForm from './components/addStageForm.component'
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import { BorderColor, Visibility, DeleteForever, GroupWork } from '@material-ui/icons';

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

class ViewStageContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            stageList: [],
            defaultStageList: [],
            activeStageId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false,
            dialog_create: false,
            dialog_delete: false,
        }
    }

    componentDidMount(){

        Promise.all([
            stageRepository.getCompanyStages(), 
            stageRepository.getDefaultStages(),
        ])
        .then( ([companyStages, defaultStages]) => {
            this.setState({
                stageList: companyStages,
                defaultStageList: defaultStages
            });

        })
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditStage = (stageModel) => {
        return stageRepository.editStage(stageModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }

    onCreateStage = (stageModel) => {
        return stageRepository.createStage(stageModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    } 

    onDeleteStage = (id) => {
        return stageRepository.deleteStage(id)
            .then(window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    } 


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
        this.setState({dialog_create: false});
        this.setState({dialog_delete: false});
    }


    onClickStage_edit = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_edit: true});
    }

    onClickStage_show = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_show: true});
    }

    onClickStage_delete = (id) => {
        this.setState({activeStageId: id});
        this.setState({dialog_delete: true});
    }

    onClickStage_create = () => {
        this.setState({dialog_create: true});
    }

    viewStageInCarousel = (item) => {
        return (
            <React.Fragment>
                <ViewStage id={item.id} full_info={false}></ViewStage>
                <br />
                <div className="carousel-button">
                <Fab 
                    color="secondary"
                    aria-label="Delete" 
                    onClick= {() => this.onClickStage_delete(item.id)}
                ><DeleteForever />
                </Fab>
                <Fab 
                    color="primary" 
                    aria-label="Edit" 
                    onClick= {() => this.onClickStage_edit(item.id)}
                ><BorderColor />
                </Fab>
                <Fab 
                    color="primary" 
                    aria-label="Show" 
                    onClick= {() => this.onClickStage_show(item.id)}
                ><Visibility />
                </Fab>
                </div>
                <br></br>

            </React.Fragment>
        )
    }

    viewDialogs = () => {
        return (
            <React.Fragment>
                <Dialog 
                open={this.state.dialog_edit} 
                onClose={this.onCloseDialog} 
                aria-labelledby="dialog-edit-stage" 
                className="dialog"
                >
                <DialogTitle id="dialog-edit-stage dialog-header">Edytuj etap</DialogTitle>
                <DialogContent>
                    <EditStageForm id={this.state.activeStageId} onSubmit={stageModel => this.onEditStage(stageModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog 
            open={this.state.dialog_create} 
            onClose={this.onCloseDialog} 
            aria-labelledby="dialog-create-stage" 
            className="dialog"
            fullScreen={true}
            >
                <DialogTitle id="dialog-create-stage dialog-header">Nowy etap</DialogTitle>
                <DialogContent>
                    <AddStageForm onSubmit={satgeModel => this.onCreateStage(satgeModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>


            <Dialog 
            open={this.state.dialog_delete} 
            onClose={this.onCloseDialog} 
            aria-labelledby="dialog-delete-stage" 
            className="dialog delete"
            >
                <DialogTitle id="dialog-delete-stage dialog-header">Usuń etap</DialogTitle>
                <DialogContent id="dialog-delete">
                    Czy na pewno chcesz trwale usunąć etap: 
                    <b><ViewStage id={this.state.activeStageId} full_info={false}></ViewStage></b>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => this.onDeleteStage(this.state.activeStageId)} color="secondary">
                    Tak, usuń wybrany etap
                    </Button>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>



            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-stage" className="dialog">
                <DialogContent>
                    <ViewStage id={this.state.activeStageId} full_info={true}></ViewStage>
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


    companyStagesListRender() {
        return (
            <React.Fragment>

            {this.viewDialogs()}

            <Fab 
                color="secondary" 
                aria-label="Add" 
                className="fab-stage-head button add" 
                onClick={() => this.onClickStage_create()}
            ><AddIcon />
            </Fab>
            <p></p>
            <h3 class="elements-type"> Etapy należące do firmy:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={this.state.stageList.length<3 ? false:true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.stageList.map(item => (
                        <div key={item.id} style={{background: 'white'}}>
                            {this.viewStageInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>
             <h3 class="elements-type">Etapy domyślne:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={this.state.defaultStageList.length<3 ? false:true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.defaultStageList.map(item => (
                        <div key={item.id} style={{background: 'white'}}>
                            {this.viewStageInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>


            </React.Fragment>
        );
      }


    render() {
        return (
            <div className="view-stage-container">
                <div className="wrapper-content"> 
                <div className="header-icon"><GroupWork  style={{ color: '#66aaee', fontSize:"55px" }} fontSize="large" /></div>
                <div className="header"> Dostępne etapy </div>
                    {this.companyStagesListRender()}
                </div>
            </div>
        );
    }
}


export default ViewStageContainer;