import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Calculator from './components/calculator.component';
import { lineRepository } from '../factory/lineRepository.factory';
import '../../css/calculator.css';
import { handleError } from '../api/handleErrors.service';

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
    
    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.props.JWT}`,
        };
    }

    onCalculate = data => {
        console.log({ data });
        return fetch('http://localhost:8090/calculcator', {
            method: 'POST',
            body: JSON.stringify(data),
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying calculate carbon print. ", err);
                return Promise.reject(err);
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
                    onCalculate={data => this.onCalculate(data)}
                />
            </div>
        )
    }

}
CalculatorContainer.propTypes = {
    JWT: PropTypes.string,
};

const mapStateToProps = store => ({
    JWT: store.JWT,
});
export default connect(mapStateToProps)(CalculatorContainer);