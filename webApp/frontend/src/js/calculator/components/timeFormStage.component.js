import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { TextField } from '@material-ui/core';

class TimeFormStage extends Component {
    static getNextHourDate() {
        const date = new Date()
        date.setHours(date.getHours() + 1);

        return date.toISOString().substr(0, 16);
    }
    render() {
        return (
            <React.Fragment>
                <h3>Przedział czasowy pracy etapu: </h3>
                <TextField
                    required
                    label={"Data początkowa"}
                    defaultValue={new Date().toISOString().substr(0, 16)}
                    type="datetime-local"
                />
                <h3>Przedział czasowy pracy etapu: </h3>
                <TextField
                    required
                    label={"Data końcowa"}
                    defaultValue={TimeFormStage.getNextHourDate()}
                    type="datetime-local"
                />
            </React.Fragment>
        )
    }
}

TimeFormStage.propTypes = {
    stage: PropTypes.object,
};

export default TimeFormStage;