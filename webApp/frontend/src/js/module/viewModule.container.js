import React, { Component } from 'react';
import { moduleRepository } from '../factory/moduleRepository.factory';
import EditModuleForm from './components/editModuleForm.component';
import AddModuleForm from './components/addModuleForm.component';
import ViewModule from './components/viewModule.component';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { BorderColor, Visibility, DeleteForever, Settings } from '@material-ui/icons';
// import { Carousel } from 'react-responsive-carousel';
// import "react-responsive-carousel/lib/styles/carousel.min.css";
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
  
class ViewModuleContainer extends Component {
    constructor() {
        super();
        
        this.state = {
            moduleList: [],
            defaultModulesList: [],
            activeModuleId: undefined,
            error: '',
            dialog_edit: false,
            dialog_show: false,
            dialog_create: false,
            dialog_delete: false,
        }
    }

    componentDidMount(){
        moduleRepository.getCompanyModules().then(
            response => this.setState({moduleList: response, activeModuleId: response.length !== 0 ? response[0].id : undefined}),
        ).then(
            moduleRepository.getDefaultModules().then(
                response => this.setState({defaultModulesList: response})
            )
        )
    }


    setErrorMessage = error => {
        this.setState(state => ({ ...state, error }));
    }


    onEditModule = (moduleModel) => {
        return moduleRepository.editModule(moduleModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCreateModule = (moduleModel) => {
        return moduleRepository.createModule(moduleModel)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onDeleteModule = (id) => {
        return moduleRepository.deleteModule(id)
            .then( window.location.reload() )
            .catch(err => this.setErrorMessage(err));
    }


    onCloseDialog = () => {
        this.setState({dialog_edit: false});
        this.setState({dialog_show: false});
        this.setState({dialog_create: false});
        this.setState({dialog_delete: false});
    }


    onClickModule_edit = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_edit: true});
    }

    onClickModule_show = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_show: true});
    }

    onClickModule_create = () => {
        this.setState({dialog_create: true});
    }

    onClickModule_delete = (id) => {
        this.setState({activeModuleId: id});
        this.setState({dialog_delete: true});
    }


    viewDialogs = () => {
        return (
            <React.Fragment>

            <Dialog open={this.state.dialog_edit} onClose={this.onCloseDialog} aria-labelledby="dialog-edit-module" className="dialog">
                <DialogTitle id="dialog-edit-module dialog-header">Edytuj moduł</DialogTitle>
                <DialogContent>
                    <EditModuleForm id={this.state.activeModuleId} onSubmit={moduleModel => this.onEditModule(moduleModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_create} onClose={this.onCloseDialog} aria-labelledby="dialog-create-module" className="dialog">
                <DialogTitle id="dialog-create-module dialog-header">Nowy moduł</DialogTitle>
                <DialogContent>
                    <AddModuleForm onSubmit={moduleModel => this.onCreateModule(moduleModel)} errorMessage={this.state.error} />
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_delete} onClose={this.onCloseDialog} aria-labelledby="dialog-delete-module" className="dialog delete" >  
                <DialogTitle id="dialog-delete-module dialog-header">Usuń moduł</DialogTitle>
                <DialogContent>
                    Czy na pewno chcesz trwale usunąć moduł: 
                    <b><ViewModule id={this.state.activeModuleId} full_info={false}></ViewModule></b>
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => this.onDeleteModule(this.state.activeModuleId)} color="secondary">
                    Tak, usuń wybrany moduł
                    </Button>
                    <Button onClick={this.onCloseDialog} color="primary">
                    Anuluj
                    </Button>
                </DialogActions>
            </Dialog>

            <Dialog open={this.state.dialog_show} onClose={this.onCloseDialog} aria-labelledby="dialog-show-module" className="dialog">
                <DialogContent>
                    <ViewModule id={this.state.activeModuleId} full_info={true}></ViewModule>
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

    viewModuleInCarousel = (item) => {
        return (
            <React.Fragment>
                <ViewModule id={item.id} full_info={false}></ViewModule>
                <br />
                <Fab 
                    color="secondary"
                    aria-label="Delete" 
                    onClick= {() => this.onClickModule_delete(item.id)}
                ><DeleteForever />
                </Fab>
                <Fab 
                    color="primary" 
                    aria-label="Edit" 
                    onClick= {() => this.onClickModule_edit(item.id)}
                ><BorderColor />
                </Fab>
                <Fab 
                    color="primary" 
                    aria-label="Show" 
                    onClick= {() => this.onClickModule_show(item.id)}
                ><Visibility />
                </Fab>
                <br></br>

            </React.Fragment>
        )
    }


    companyModulesListRender() {
        return (
            <React.Fragment>

            {this.viewDialogs()}

            <Fab 
                color="secondary" 
                aria-label="Add" 
                className="fab-module-head button add"
                onClick={() => this.onClickModule_create()}
            ><AddIcon />
            </Fab>
            <p></p>
           <h3 class="elements-type"> Moduły należące do firmy:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.moduleList.map(item => (
                        <div key={item.id} style={{background: 'white'}}>
                            {this.viewModuleInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>
            <h3 class="elements-type">Moduły domyślne:</h3>
            <br></br>
            <Carousel 
                swipeable={false}
                draggable={false}
                responsive={responsive}
                ssr={true}
                slidesToSlide={2}
                infinite={true}
                keyBoardControl={true}
                containerClass="carousel-container"
                deviceType={this.props.deviceType}
                itemClass="carousel-item-padding-40-px carousel-item"
                >
                    {this.state.defaultModulesList.map(item => (
                        <div key={item.id} style={{background: 'white'}}>
                            {this.viewModuleInCarousel(item)}
                        </div>
                    ))}
            </Carousel>
            <p></p>
            
            </React.Fragment>
        );
      }


    render() {
        return (
            <div className="view-module-container">
            <div className="wrapper-content"> 
            <div className="header-icon"><Settings  style={{ color: '#9988ff', fontSize:"55px" }} fontSize="large" /></div>
            <div className="header"> Dostępne moduły </div>
            <div className="columns">
                
            </div>
            {this.companyModulesListRender()}
            </div>
        </div>
        );
    }
}


export default ViewModuleContainer;