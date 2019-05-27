import React, { Component } from 'react';
import PropTypes from 'prop-types';
import LineSelect from './lineSelect.component';
import VegetableSelect from './vegetableSelect.component';
import { lineRepository } from '../../factory/lineRepository.factory';
import TextField from '@material-ui/core/TextField';

class Calculator extends Component {
    constructor(props) {
        super(props);

        this.state = {
            currentLineId: undefined,
            vegetables: [],
        }
    }

    onChangeLine = id => {
        this.setState(state => ({ ...state, currentLineId: id }));
        this.fetchVegetablesForLine(id);
    };
   

    fetchVegetablesForLine(lineId) {
        if (lineId) {
            lineRepository.getVegetablesForLine(lineId)
                .then(vegetables => this.setState(state => ({ ...state, vegetables })));
        }
    }

    onChangeVegetable = id => {
        this.setState(state => ({ ...state, currentVegetableId: id }));
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
        
        console.log({ state: this.state, props: this.props });

        return (
            <form onSubmit={e => this.onSubmit(e)} className="calculactor-wrapper">
                <LineSelect lines={lines} onChange={id => this.onChangeLine(id)} />
                {currentLineId ? <VegetableSelect vegetables={vegetables} onChange={id => this.onChangeVegetable(id)} /> : ''}
                {true ? (
                    <React.Fragment>
                        <TextField
                            label="Surowiec [kg]"
                        />

                        <TextField
                            label="Produkt [kg]"
                        />
                    </React.Fragment>
                ) 
                : ''}
                {/*
                - lista etapow z linii (definiujemy resourcy z bazy wiedzy)
                - w każdym etapie trzeba uzupełnić występujące tam resoursy
                - każdy etap ma czas działania
            
                */}
            </form>
        );
    }

}

Calculator.propTypes = {
    lines: PropTypes.array.isRequired,
};

export default Calculator;