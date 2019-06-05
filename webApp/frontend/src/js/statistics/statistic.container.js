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
        }


    }

    renderResources(resources) {
        const stageIds = resources.map(({ stage }) => stage.id);


        const ids = [...new Set(stageIds)];

        const stages = [];
        const uniqueResourcesPerStage = ids.map(id => {
            const stagesResources = resources.filter(({ resources, stage, time, value }) => stage.id === id);
            const time = stagesResources.length > 0 ? stagesResources[0].time : 0;
            const name = stagesResources.length > 0 ? stagesResources[0].stage.name : 'Nieznana nazwa etapu';
            const stage = {
                time,
                name,
                resources: stagesResources.map(({ resource, value }) => ({ id: resource.id, name: resource.name, value, gus: resource.gus }))
            }
            stages.push(stage);

        })

        return stages.map(stage => {
            return (
                <li key={stage.id} style={{ marginRight: '1em' }}>
                    <div className="form-stage" style={{ paddingRight: '8em', position: 'relative' }}>
                        <div className="icon" style={{ float: 'none', position: 'absolute', right: '.5em' }}><GroupWork style={{ color: '#66aaee', fontSize: "55px" }} fontSize="large" /></div>
                        <h3>{stage.name}</h3>
                        <div style={{ marginTop: '.5em' }} />
                        {
                            stage.resources.map(resource => (
                                <div className="resource-form-mini" key={resource.id} style={{ marginBottom: '.5em' }}>
                                    <TextField
                                        type="number"
                                        required
                                        value={resource.value}
                                        disabled
                                        label={`${resource.name}`}
                                    />
                                </div>
                            ))
                        }
                        <div style={{ marginTop: '.5em' }} />
                    </div>
                </li>
            )
        })
    }

    renderStatistic() {
        if (this.state.statistic) {
            console.log({ stat: this.state.statistic });
            const { statistic } = this.state;
            const { line, vegetable, stageResourceValueCM, carbonPrint } = statistic;

            return (
                <ul>
                    <li><h2>Linia: {line.name}</h2></li>
                    <li><h2>Warzywo: {vegetable.name}</h2></li>
                    <li><h2>Ślad węglowy: {carbonPrint}</h2></li>
                    <li><ul style={{ display: 'flex' }}>{this.renderResources(stageResourceValueCM)}</ul></li>
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