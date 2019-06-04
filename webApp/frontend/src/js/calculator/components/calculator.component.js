import React, { Component } from 'react';
import PropTypes from 'prop-types';
import LineSelect from './lineSelect.component';
import VegetableSelect from './vegetableSelect.component';
import { lineRepository } from '../../factory/lineRepository.factory';
import TextField from '@material-ui/core/TextField';
import ViewResourceFormForLineStages from './viewResourceFormForLineStages.component';
import Select from 'react-select';
import { stageRepository } from '../../factory/stageRepository.factory';
import { Button } from '@material-ui/core';
import Alert from 'react-s-alert';

class Calculator extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentLineId: undefined,
            currentVegetableId: undefined,
            vegetables: [],
            lineResources: [],
            resourcesType: {},
            stages: {},
            gus: [],
        }

        this.options = {
            position: 'top-right',
            effect: 'slide',
            beep: false,
            timeout: 2000,
            offset: 25
        };
    }

    componentDidMount() {
        return fetch('http://api.gabryelkamil.pl/gus_category')
            .then(data => data.json())
            .then(gus => this.setState(state => ({ ...state, gus })));
    }

    onChangeLine = id => {
        this.setState(state => ({ ...state, currentLineId: id }));
        this.fetchVegetablesForLine(id);
        this.fetchResourcesForLine(id);
    };
   
    getLine = () => {
        return this.props.lines.find(line => line.id === this.state.currentLineId);
    }

    fetchVegetablesForLine(lineId) {
        if (lineId) {
            lineRepository.getVegetablesForLine(lineId)
                .then(vegetables => this.setState(state => ({ ...state, vegetables })));
        }
    }

    fetchResourcesForLine = id => {
        return lineRepository.getResourcesForLine(id)
            .then(resources => this.setState(state => ({ ...state, lineResources: resources })));
    }

    onKeyDown = e => {
        if (e.keyCode == 13) {
            this.onSubmit();
        }
    }

    componentDidUpdate() {
        if (this.state.currentVegetableId) {
            document.addEventListener('keydown', this.onKeyDown);
        } else {
            document.removeEventListener('keydown', this.onKeyDown);
        }
    }

    filterGusById = id => {
        return this.state.gus.filter(({ gus_id }) => gus_id === id);
    }

    componentWillUnmount() {
        document.removeEventListener('keydown', this.onKeyDown);
    }
    

    onChangeVegetable = id => {
        this.setState(state => ({ ...state, currentVegetableId: id }));
    }

    onChangeResource = (id, value) => {
        this.setState(state => ({
            ...state,
            resourcesType: {
                ...state.resourcesType,
                [value.value]: value,
            },
        }));
    }

    mapToSelect = guses => {
        return guses.map(gus => ({
            ...gus,
            label: gus.name_pl,
            value: gus.id,
        }));
    }

    renderFormForChooseResourcesType = () => {
        return this.state.lineResources.map(resource => (
                <li key={resource.id} style={{ marginBottom: '.5em' }}>
                    <Select
                        required
                        options={this.mapToSelect(this.filterGusById(resource.gus))}
                        onChange={value => this.onChangeResource(resource.id, value)}
                        noOptionsMessage={() => `Brak informacji w bazie wiedzy`}
                        placeholder={`Wybierz ${resource.name}`}
                      />
                </li>

            ));
    }

    onChangeDataInStage = stages => {
        this.setState(state => ({
            ...state,
            stages,
        }))
    }

    validateAllStages = state => {
        const {
            stages,
        } = state;

        const { stageModels } = this.getLine();

        return Promise.all([...stageModels.map(({ id }) => {
            return Promise.all([
                stageRepository.getLeftoversForStage(id),
                stageRepository.getResourcesForStage(id),
            ])        
                .then(([leftovers, resources]) => {
                    const stage = stages[id] || { leftovers: {}, resources: {} };
                    const filledStageLeftovers = stage.leftovers;
                    const filledStageResources = stage.resources;

                    if (leftovers.length !== Object.keys(filledStageLeftovers).length) {
                        return Promise.reject();
                    }

                    if (Object.keys(filledStageResources).length !== resources.length) {
                        return Promise.reject();
                    }

                    return Promise.resolve();
                });
        })])
    }

    validateForm = state => {
        const {
            product,
            material,
            resourcesType,
            lineResources,
        } = state;

        if (!product) {
            Alert.error('Podaj wartość produktu', this.options);
            return Promise.reject();
        }

        if (!material) {
            Alert.error('Podaj wartość surowca', this.options);
            return Promise.reject();
        }

        if (Object.keys(resourcesType).length !== lineResources.length) {
            Alert.error('Wybierz typy zasobów', this.options);
            return Promise.reject();
        }

        return this.validateAllStages(state)
                .catch(() => Alert.error('Uzupełnij wszystkie pola w etapach', this.options));
    }

    onSubmit = (e = { preventDefault: () => {} }) => {
        e.preventDefault();

        const state = { ...this.state };
        const { stages } = state;
        
        const clearStages = {};
        Object.keys(stages).forEach(key => {
            const { leftovers, resources } = stages[key];
            Object.keys(leftovers).forEach((key) => (leftovers[key] === '') && delete leftovers[key])
            Object.keys(resources).forEach((key) => (resources[key] === '') && delete resources[key])
            clearStages[key] = { 
                ...stages[key],
                leftovers,
                resources,
            };
        })

        const newState = {
            ...state,
            stages: clearStages,
        };
        

        this.validateForm(newState)
            .then(() => ({
                stages: newState.stages,
                resources: newState.resourcesType,
                product: newState.product,
                material: newState.material,
            }))
            .then(data => this.props.onCalculate(data))
    }

    onChangeProduct = product => {
        this.setState(state => ({
            ...state,
            product: {
                value: product,
                unit: 'kg',
            }
        }))
    }

    onChangeMaterial = material => {
        this.setState(state => ({
            ...state,
            material: {
                value: material,
                unit: 'kg',
            },
        }))
    }

    render() {
        const {
            lines,
        } = this.props;

        const {
            currentLineId,
            vegetables,
            currentVegetableId,
        } = this.state;
        
        return (
            <form onSubmit={e => this.onSubmit(e)} className="calculactor-wrapper">
                <LineSelect lines={lines} onChange={id => this.onChangeLine(id)} />
                {currentLineId ? <VegetableSelect vegetables={vegetables} onChange={id => this.onChangeVegetable(id)} /> : ''}
                {currentVegetableId ? (
                    <React.Fragment>
                        <TextField
                            required
                            type="number"
                            label="Surowiec [kg]"
                            onChange={e => this.onChangeMaterial(e.target.value)}
                        />

                        <TextField
                            type="number"
                            required
                            label="Produkt [kg]"
                            onChange={e => this.onChangeProduct(e.target.value)}
                        />
                        <p></p>
                        {this.state.lineResources.length !== 0 ? (<label style={{ fontSize:17}}>Wybierz typy zasobów: </label>) : ''}
                        <ul style={{ padding: 0, margin: 0 }}>
                            {this.renderFormForChooseResourcesType()}
                        </ul>
                        <ViewResourceFormForLineStages 
                            line={this.getLine()}
                            onChangeDataInStage={stages => this.onChangeDataInStage(stages)}
                            gusCategory={this.state.gus}    
                        />
                        <Button variant="contained" color="primary" style={{ width: '25%', marginTop: '1em' }} onClick={() => this.onSubmit()}>
                            Oblicz     
                        </Button>
                    </React.Fragment>
                ) 
                : ''}
            </form>
        );
    }

}

Calculator.propTypes = {
    lines: PropTypes.array.isRequired,
    onCalculate: PropTypes.func.isRequired,
};

export default Calculator;