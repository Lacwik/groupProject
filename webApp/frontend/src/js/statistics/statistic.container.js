import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { BarChart } from '@material-ui/icons';
import { handleError } from '../api/handleErrors.service';
import { setStatistics } from '../redux/app.service';
import { TextField } from '@material-ui/core';
import PieChart from 'react-minimal-pie-chart';


class StatisticContainer extends Component {
    constructor(props) {
        super(props);

        console.log('Propsy container', { props });
        const stats = [];
        Object.keys(props.statistics).forEach(key => {
            stats.push(...props.statistics[key]);
        })

        console.log({ stats }, "statysy");
        this.state = {
            statistic:stats.find(({ id }) => id === parseInt(props.match.params.id)),
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

    componentDidMount() {
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
            console.log('resource: ', { resource });
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
        return (
            <React.Fragment>
                Odpady:
                {
                    leftovers.map(leftover => {
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
            </React.Fragment>
        )
    }

    renderChart(modules, time, stageEnergyResource) {
        let sumupModulesValue = 0;
        modules.forEach(({ power }) => sumupModulesValue += power);


        if (stageEnergyResource && stageEnergyResource.value) {
            const values = modules.map(({ power, name }) => ({
                title: name,
                value: parseFloat(power * stageEnergyResource.value / sumupModulesValue).toFixed(2),
                color: `#${Math.floor(Math.random() * 16777215).toString(16)}`,
            }));

            let sumValues = 0;
            values.forEach(({ value }) => {
                sumValues += parseFloat(value);
            });

            const data = values.map(val => ({
                ...val,
                value: val.value / sumValues * 100,
            }));

            return (
                <PieChart
                    cx={50}
                    cy={50}
                    radius={50}
                    data={data}
                />
            );
        }

        return 'Etap nie zawiera modułu zużywającego energię elektryczną';
    }

    renderStatisticStage(statisticsStages) {
        return statisticsStages.map(({ carbonPrint, stage, stageLeftovers, stageResources, time, statisticsStageModules }) => {
            const date = new Date(null);
            date.setSeconds(time); // specify value for SECONDS here
            const result = date.toISOString().substr(11, 8);
            return (
                <div key={stage.id} className="form-stage " style={{ display: 'flex' }}>
                    <div>
                        <h2>Etap: {stage.name} </h2>
                        <div>
                            <ul key={stage.id} >
                                <li>Ślad węglowy: {carbonPrint}</li>
                                <li>Czas działania etapu: {result}h</li>
                                <li>Zasoby: {this.renderResources(stageResources)}</li>
                                <li>{stageLeftovers.length !== 0 ? this.renderLeftovers(stageLeftovers) : ''}</li>
                            </ul>
                        </div>
                    </div>
                    <div>{this.renderChart(statisticsStageModules, time, stageResources.find(({ resource }) => resource.gus === "024"))}</div>

                </div>
            )
        });
    }
    sumLeftovers(statisticsStages) {
        let sum = 0;
        statisticsStages.forEach(({ stageLeftovers }) => stageLeftovers.forEach(({ value }) => sum += value));

        return sum;
    }

    renderStatistic() {
        if (this.state.statistic) {
            const { statistic } = this.state;
            const { line, vegetable, statisticsStages, productWeight, materialWeight, carbonPrint } = statistic;

            return (
                <ul>
                    <li><h2>Linia: <label  style={{ textTransform: 'uppercase'}}>{line.name}</label></h2></li>
                    <li><h2>Warzywo:  <label  style={{ textTransform: 'uppercase'}}>{vegetable.name}</label></h2></li>
                    <li><h2>Ślad węglowy: {carbonPrint}</h2></li>
                    <li><h2>Surowiec: {materialWeight}kg</h2></li>
                    <li><h2>Produkt: {productWeight}kg</h2></li>
                    <li><h2>Suma odpadów: {this.sumLeftovers(statisticsStages)}kg</h2></li>
                    <li className="resource-mini-stage" style={{ width: '100%' }}>{this.renderStatisticStage(statisticsStages)}</li>
                </ul>
            )
        }

        return '';

    }

    render() {
        return (
            <div className="wrapper-content">
                <div className="header-icon"><BarChart style={{ color: '#f4428f', fontSize: "55px" }} fontSize="large" /></div>
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