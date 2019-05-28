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
        Promise.all([lineRepository.getCompanyLines(), lineRepository.getDefaultLines()])
        .then(([company, defaultLines]) => {
            this.setState({
                lines: [...company, ...defaultLines],
            });

        });
    }

    render() {
        const {
            lines,
        } = this.state;

        return (
            <div className="wrapper-content">
                <h2>Kalkulator śladu węglowego</h2>
                <Calculator
                    lines={lines}
                />
            </div>
        )
    }

}

export default CalculatorContainer;