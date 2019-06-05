import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { BarChart } from '@material-ui/icons';
import { handleError } from '../api/handleErrors.service';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import uuid from 'uuid/v4';
import { Redirect} from 'react-router-dom';  
import { setStatistics } from '../redux/app.service';

class StatisticsContainer extends Component {
    constructor() {
        super();

        this.state = {
            statistics: [],
            alreadyFetch: false,
        };
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
                    this.setState({ alreadyFetch: true });
                    setStatistics(statistics);
                })
                .catch(err => {
                    console.warn("Caught error while trying to get company's statistics. ", err);
                    return Promise.reject(err);
                });
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
                    this.setState({ alreadyFetch: true });
                    setStatistics(statistics);
                })
                .catch(err => {
                    console.warn("Caught error while trying to get company's statistics. ", err);
                    return Promise.reject(err);
                });
        }


    }

    renderStatistics() {
        return this.props.statistics.map(({ carbonPrint, line, vegetable, id }) => (
            <li key={uuid().toString()} style={{ width: '25%', marginRight: '1em' }}>
                <Card style={{background: '#ffcc66', cursor: 'pointer' }} onClick={() => this.props.history.push(`/statistic/${id}`)}>
                    <CardContent>
                        <h4 className="dashboard-card__title" style={{ fontSize: '1rem' }}>Linia: {line.name} <br /> Warzywo: {vegetable.name}</h4>
                        <p style={{ textAlign: 'center' }}>Obliczony ślad węglowy: <b>{carbonPrint}</b></p>
                    </CardContent>
                </Card>
            </li>
        ))
    }

    render() {
        return (
            <div className="wrapper-content">
                <div className="header-icon"><BarChart style={{ color: '#ffcc66', fontSize: "55px" }} fontSize="large" /></div>
                <div className="header"> Statystyki </div>
                <ul style={{ display: 'flex' }}>
                    {this.renderStatistics()}
                </ul>
            </div>
        );
    }
}
StatisticsContainer.propTypes = {
    companyIdWorkingFor: PropTypes.number,
    JWT: PropTypes.string,
    statistics: PropTypes.array,
}

const mapStateToProps = store => ({
    companyIdWorkingFor: store.companyWorkingFor.id,
    JWT: store.JWT,
    statistics: store.statistics,
});

export default connect(mapStateToProps)(StatisticsContainer);