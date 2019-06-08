import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { BarChart } from '@material-ui/icons';
import { handleError } from '../api/handleErrors.service';
import { setStatistics } from '../redux/app.service';
import moment from 'moment';
import { TextField } from '@material-ui/core';
import { GroupWork } from '@material-ui/icons';


class StatisticContainer extends Component {
    constructor(props) {
        super(props);

        this.state = {
            statistic: props.statistics.find(({ id }) => id === parseInt(props.match.params.id)),
            units: []
        }
    }

    componentDidUpdate() {
        if (this.props.companyIdWorkingFor && !this.state.alreadyFetch) {
            fetch(`http://localhost:8090/company/${this.props.companyIdWorkingFor}/statistics`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${this.props.JWT}`,
                }
            })
                .then(response => handleError(response))
                .then(response => response.json())
                .then(statistics => {
                    this.setState({ alreadyFetch: true, statistic: statistics.find(({ id }) => id === parseInt(this.props.match.params.id)) });
                    setStatistics(statistics);
                })
                .catch(err => {
                    console.warn("Caught error while trying to get company's statistics. ", err);
                    return Promise.reject(err);
                });

            fetch('http://api.gabryelkamil.pl/unit')
                .then(data => data.json())
                .then(units => this.setState(state => ({ ...state, units })));
        }


    }

    renderResources(resources) {
        return resources.map(resource => {
            const unit = this.state.units.find(({ id }) => resource.unitId === id);

            return (
                <div key={resource.id} style={{ marginBottom: '.5em' }}>
                    <TextField
                        type="number"
                        required
                        value={resource.value}
                        disabled
                        label={`${resource.gusName} [${unit ? unit.shortcut : 'Nieznana jednostka'}]`}
                    />
                </div>
            )
        })
    }

    renderLeftovers(leftovers) {
        return leftovers.map(leftover => {
            return (
                <div key={leftover.id} style={{ marginBottom: '.5em' }}>
                    <TextField
                        type="number"
                        required
                        value={leftover.value}
                        disabled
                        label={`${leftover.leftover.name} [kg]`}
                    />
                </div>
            )
        })
    }

    renderStatisticStage(statisticsStages) {
        return statisticsStages.map(({ carbonPrint, stage, stageLeftovers, stageResources, time }) => {
            const date = new Date(null);
            date.setSeconds(time); // specify value for SECONDS here
            const result = date.toISOString().substr(11, 8);
            return (
                <div key={stage.id} className="form-stage ">
                <h2>Etap: {stage.name} </h2>
                <div>
                    <ul key={stage.id} >
                        <li>Ślad węglowy: {carbonPrint}</li>
                        <li>Czas działania etapu: {result}h</li>
                        <li>Zasoby: {this.renderResources(stageResources)}</li>
                        <li>Odpady: {this.renderLeftovers(stageLeftovers)}</li>
                    </ul>
                </div>
                </div>
            )
        });
    }

    renderStatistic() {
        console.log({ state: this.state });
        if (this.state.statistic) {
            const { statistic } = this.state;
            const { line, vegetable, statisticsStages, productWeight, materialWeight, carbonPrint } = statistic;

            return (
                <ul>
                    <li><h2>Linia: {line.name}</h2></li>
                    <li><h2>Warzywo: {vegetable.name}</h2></li>
                    <li><h2>Ślad węglowy: {carbonPrint}</h2></li>
                    <li><h2>Surowiec: {materialWeight}kg</h2></li>
                    <li><h2>Produkt: {productWeight}kg</h2></li>
                    <li className="resource-mini-stage" style={{width: '100%'}}>{this.renderStatisticStage(statisticsStages)}</li>
                </ul>
            )
        }

        return '';

    }

    render() {
        return (
            <div className="wrapper-content">
                <div className="header-icon"><BarChart style={{ color: '#ffcc66', fontSize: "55px" }} fontSize="large" /></div>
                <div className="header"> Statystyki </div>
                {this.renderStatistic()}
            </div>
        );
    }
}

StatisticContainer.propTypes = {
    statistics: PropTypes.array,
    companyIdWorkingFor: PropTypes.number,
    JWT: PropTypes.string,
}

const mapStateToProps = store => ({
    statistics: store.statistics,
    companyIdWorkingFor: store.companyWorkingFor.id,
    JWT: store.JWT,
});
export default connect(mapStateToProps)(StatisticContainer);