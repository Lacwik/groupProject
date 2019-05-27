import React, { Component } from 'react';
import PropTypes, { number, string, any } from 'prop-types';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import Paper from '@material-ui/core/Paper';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { vegetableRepository } from '../../factory/vegetable.factory';
import { resourceRepository } from '../../factory/resource.factory';
import { leftoverRepository } from '../../factory/leftover.factory';

// import Input from '@material-ui/core/Input';
// import InputLabel from '@material-ui/core/InputLabel';
// import MenuItem from '@material-ui/core/MenuItem';
// import ListItemText from '@material-ui/core/ListItemText';
// import Checkbox from '@material-ui/core/Checkbox';
import Select from 'react-select'
import makeAnimated from 'react-select/lib/animated';


class EditModuleForm extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            power: 0,
            vegetables: [],
            resources: [],
            leftovers: [],
            allVegetables: [],
            allResources: [],
            allLeftovers: [],
        };
    }

    componentDidMount(){
        moduleRepository.getModuleById(this.props.id).then(
            response => this.setState({ 
                name: response.name,
                power: response.power,
                vegetables: response.vegetables.map(v => ({ ...v, value: v.id, label: v.name })),
                resources: response.resources.map(v => ({ ...v, value: v.id, label: v.name })),
                leftovers: response.leftovers.map(v => ({ ...v, value: v.id, label: v.name })),
            })
        ).then(
            vegetableRepository.getAllVegetables().then(
                response => this.setState({
                    allVegetables: response.map(v => ({ ...v, value: v.id, label: v.name }))
                })
            ),
            resourceRepository.getAllResources().then(
                response => this.setState({
                    allResources: response.map(v => ({ ...v, value: v.id, label: v.name }))
                })
            ),
            leftoverRepository.getAllLeftovers().then(
                response => this.setState({
                    allLeftovers: response.map(v => ({ ...v, value: v.id, label: v.name }))
                })
            )
        )
        

    }


    renderAvailableVegetables() {
        return Object.keys({ ...this.state.allVegetables }).map(item => {
            return (
                <option
                    key={item.id}
                    value={item.name}
                >{item.name}
                </option>
            );
        })
    }

    onChangeName = e => {
        const { value: name } = e.target;

        this.setState(state => ({ ...state, name }));
    }

    onChangePower = e => {
        const { value: power } = e.target;

        this.setState(state => ({ ...state, power }));
    }

    onChangeVegetables = e => {
        const vegetables = e;

        this.setState(state => ({ ...state, vegetables }));
    }

    onChangeResources = e => {
        const resources = e;

        this.setState(state => ({ ...state, resources }));
    }

    onChangeLeftovers = e => {
        const leftovers = e;

        this.setState(state => ({ ...state, leftovers }));
    }


    onSubmit = () => {
        const { name, power, vegetables, resources, leftovers } = this.state;

        this.props.onSubmit({ name, power, vegetables, resources, leftovers, id: this.props.id });
    }


    render() {

        const {
            name, 
            power, 
            vegetables, 
            resources, 
            leftovers
        } = this.state;

        //console.log({ props: this.props, state: this.state });
        return (
            <form id="module-edit-form" className="module-edit-form" onSubmit={e => e.preventDefault()}>
                {this.props.errorMessage && <Paper className="error-box">{this.props.errorMessage}</Paper>}
                <TextField
                    label="Name"
                    value={name}
                    onChange={this.onChangeName}
                    type="text"
                    fullWidth
                >Nazwa
                </TextField>
                <TextField
                    label="Power"
                    value={power}
                    onChange={this.onChangePower}
                    type="text"
                    fullWidth
                >Moc
                </TextField>
                <p></p>
                <Select 
                    closeMenuOnSelect={false}
                    components={makeAnimated()}
                    value={vegetables}
                    isMulti
                    options={this.state.allVegetables}
                    onChange={this.onChangeVegetables}
                    />
                <p></p>
                <Select 
                    closeMenuOnSelect={false}
                    components={makeAnimated()}
                    value={resources}
                    isMulti
                    options={this.state.allResources}
                    onChange={this.onChangeResources}
                    />
                <p></p>
                <Select 
                    closeMenuOnSelect={false}
                    components={makeAnimated()}
                    value={leftovers}
                    isMulti
                    options={this.state.allLeftovers}
                    onChange={this.onChangeLeftovers}
                    />
                <p></p>
                <Button 
                    variant="contained"
                    color="primary"
                    className="module-edit__button"
                    onClick={this.onSubmit}
                >Zapisz
                </Button>
            </form>                
        );
    }
}

EditModuleForm.propTypes = {
    onSubmit: PropTypes.func.isRequired,
};

export default EditModuleForm;