import React, { Component } from 'react';
import Calculator from './components/calculator.component';
import { lineRepository } from '../factory/lineRepository.factory';
import '../../css/calculator.css';

class CalculatorContainer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            lines: [],
        };
    }
   
    componentDidMount() {
        lineRepository.getCompanyLines()
            .then(lines => this.setState(state => ({ ...state, lines })));
    }

    render() {
        console.log({ state: this.state });
        const {
            lines,
        } = this.state;

        return (
            <div className="wrapper-content">
                <h3>Kalkulator śladu węglowego</h3>
                <Calculator
                    lines={lines}
                />
            </div>
        )
    }

}

export default CalculatorContainer;