import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Alert from 'react-s-alert';
import { TextField } from '@material-ui/core';
import moment from 'moment';

class TimeFormStage extends Component {
    static getNextHourDate() {
        const date = moment();
        date.hours(date.hours() + 1);

        return date;
    }

    constructor(props) {
        super(props);

        this.format = 'YYYY-MM-DDThh:mm';
        this.state = {
            startDate: moment().format(this.format),
            endDate: TimeFormStage.getNextHourDate().format(this.format),
        }
    }

    calculateDuration = (startDate, endDate) => {
        const diff = moment(endDate).diff(moment(startDate));
        this.props.onChangeDuration(this.props.stage.id, diff);
    }


    onChangeStart = startDate => {
        if (moment(startDate).isBefore(moment(this.state.endDate))) {
            this.setState(state => ({
                ...state,
                startDate: moment(startDate).format(this.format),
            }))
            return this.calculateDuration(startDate, this.state.endDate);
        }

        
        Alert.error('Data początkowa nie może być większa od daty końcowej!', {
            position: 'top-right',
            effect: 'slide',
            beep: false,
            timeout: 2000,
            offset: 25
        });
    }

    onChangeEnd = endDate => {
        if (moment(endDate).isAfter(moment(this.state.startDate))) {
            this.setState(state => ({
                ...state,
                endDate: moment(endDate).format(this.format),
            }))
    
            return this.calculateDuration(this.state.startDate, endDate);
        }

        
        Alert.error('Data końcowa nie może być mniejsza od daty początkowej!', {
            position: 'top-right',
            effect: 'slide',
            beep: false,
            timeout: 2000,
            offset: 25
        });
        
    }

    render() {
        return (
            <React.Fragment>
                <h3>Przedział czasowy pracy etapu: </h3>
                <TextField
                    required
                    label={"Data początkowa"}
                    type="datetime-local"
                    onChange={e => this.onChangeStart(e.target.value)}
                    value={this.state.startDate}
                />
                <TextField
                    required
                    label={"Data końcowa"}
                    onChange={e => this.onChangeEnd(e.target.value)}
                    type="datetime-local"
                    value={this.state.endDate}
                />
            </React.Fragment>
        )
    }
}

TimeFormStage.propTypes = {
    stage: PropTypes.object,
    onChangeDuration: PropTypes.func.isRequired,
};

export default TimeFormStage;