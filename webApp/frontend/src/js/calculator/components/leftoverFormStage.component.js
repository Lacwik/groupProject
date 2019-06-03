import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ViewResourceFormForLineStages from './viewResourceFormForLineStages.component';
import { stageRepository } from '../../factory/stageRepository.factory';
import { TextField } from '@material-ui/core';

class LeftoverFormStage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            leftovers: [],
        }
    }
    componentDidMount() {
        stageRepository.getLeftoversForStage(this.props.stage.id) 
            .then((leftovers) => this.setState(state => ({ ...state, leftovers })));;
    }

    renderLeftovers() {
        return this.state.leftovers.map(resource => (
                <div style={{ marginBottom: '.5em' }} key={resource.id}>
                    <TextField
                        type="number"
                        required
                        fullWidth
                        label={`${ViewResourceFormForLineStages.capitalizeFirstLetter(resource.name)} [kg]`}
                        onChange={e => this.props.onChangeLeftover(this.props.stage.id, resource.id, { value: e.target.value, unit: 'kg' })}
                    />
                </div>
        ));
    }

    render() {
        return (
            <React.Fragment>
                <h3>Konfiguracja resztek</h3>
                {this.renderLeftovers()}
            </React.Fragment>
        )
    }
}

LeftoverFormStage.propTypes = {
    stage: PropTypes.object,
    onChangeLeftover: PropTypes.func,
};

export default LeftoverFormStage;