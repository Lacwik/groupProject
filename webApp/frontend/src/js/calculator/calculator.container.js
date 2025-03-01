import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import Calculator from './components/calculator.component';
import { lineRepository } from '../factory/lineRepository.factory';
import '../../css/calculator.css';
import { handleError } from '../api/handleErrors.service';
import { Exposure } from '@material-ui/icons';

class CalculatorContainer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            lines: [],
        };
    }

    endpoint = 'http://172.30.149.96:8090'
    // endpoint = `http://localhost:8090`
   
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
        const m2 = {
            ...data,
            resources: Object.values(data.resources),
            stages: [
                ...Object.values(data.stages).map(stage => ({
                    ...stage,  
                    duration: stage.duration / 1000,
                    // leftovers: Object.keys(stage.leftovers).map(key => ({ ...stage.leftovers[key], id: key })),
                    // resources: Object.keys(stage.resources).map(key => ({ ...stage.resources[key], id: key })),
                })),
            ],
        }

        return fetch(`${this.endpoint}/calculator`, {
            method: 'POST',
            body: JSON.stringify(m2),
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(() => this.props.history.push('/statistics'))
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
            <div className="header-icon"><Exposure  style={{ color: '#ffcc66', fontSize:"55px" }} fontSize="large" /></div>
            <div className="header"> Kalkulator śladu węglowego </div>
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