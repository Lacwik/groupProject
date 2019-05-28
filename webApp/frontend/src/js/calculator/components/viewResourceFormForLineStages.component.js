import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ResourceFormStage from './resourceFormStage.component';
import LeftoverFormStage from './leftoverFormStage.component';
import TimeFormStage from './timeFormStage.component';

class ViewResourceFormForLineStages extends Component {
    renderViewResourceAndLeftoversForms() {

        return this.props.line.stageModels.map(stage => (
            <li key={stage.id}>
                
                <div className="form-stage">
                    <h2>Konfiguracja etapu: {stage.name.toUpperCase()}</h2>
                    <TimeFormStage stage={stage} />
                    <div style={{ marginTop: '.5em' }} />
                    <ResourceFormStage stage={stage} />
                    <div style={{ marginTop: '.5em' }} />
                    <LeftoverFormStage stage={stage} />
                </div>
            </li>
        ));
    }

    render() {
        return (
            <ul style={{ margin: 0, padding: 0 }}>
                {this.renderViewResourceAndLeftoversForms()}
            </ul>
        );
    }

}

ViewResourceFormForLineStages.propTypes = {
    line: PropTypes.object,
};

export default ViewResourceFormForLineStages;