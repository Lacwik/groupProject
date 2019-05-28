import React, { Component } from 'react';
import PropTypes from 'prop-types';
import LineSelect from './lineSelect.component';
import VegetableSelect from './vegetableSelect.component';
import { lineRepository } from '../../factory/lineRepository.factory';
import TextField from '@material-ui/core/TextField';
import ViewResourceFormForLineStages from './viewResourceFormForLineStages.component';
import Select from 'react-select';
import { Button } from '@material-ui/core';

class Calculator extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentLineId: undefined,
            currentVegetableId: undefined,
            vegetables: [],
            lineResources: [],
        }
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

    onChangeVegetable = id => {
        this.setState(state => ({ ...state, currentVegetableId: id }));
    }
    onChangeResource = (id, value) => {
        this.setState(state => ({
            ...state,
            resourcesType: {
                ...state.resourcesType,
                [id]: value,
            },
        }));
    }

    renderFormForChooseResourcesType = () => {
        return this.state.lineResources.map(resource => (
                <li key={resource.id} style={{ marginBottom: '.5em' }}>
                    <Select
                        required
                        options={[]}
                        onChange={value => this.onChangeResource(resource.id, value)}
                        noOptionsMessage={() => `Brak informacji w bazie wiedzy`}
                        placeholder={`Wybierz ${resource.name}`}
                      />
                </li>

            ));
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
        
        console.log({ state: this.state });

        return (
            <form onSubmit={e => this.onSubmit(e)} className="calculactor-wrapper">
                <LineSelect lines={lines} onChange={id => this.onChangeLine(id)} />
                {currentLineId ? <VegetableSelect vegetables={vegetables} onChange={id => this.onChangeVegetable(id)} /> : ''}
                {currentVegetableId ? (
                    <React.Fragment>
                        <TextField
                            required
                            label="Surowiec [kg]"
                        />

                        <TextField
                            required
                            label="Produkt [kg]"
                        />
                        {this.state.lineResources.length !== 0 ? (<h3>Wybierz typy zasobów</h3>) : ''}
                        <ul style={{ padding: 0, margin: 0 }}>
                            {this.renderFormForChooseResourcesType()}
                        </ul>
                        <ViewResourceFormForLineStages line={this.getLine()} />
                        <Button variant="contained" color="primary" style={{ width: '25%', marginTop: '1em' }}>
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
};

export default Calculator;