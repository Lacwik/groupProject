import React, { Component } from 'react';
import PropTypes from 'prop-types';
import Select from 'react-select';
import { stageRepository } from '../../factory/stageRepository.factory';
import { TextField } from '@material-ui/core';

class ResourceFormStage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            leftovers: [],
            resources: [],
        }
    }
    componentDidMount() {
        Promise.all([
            stageRepository.getLeftoversForStage(this.props.stage.id),
            stageRepository.getResourcesForStage(this.props.stage.id),
        ])        
            .then(([leftovers, resources]) => this.setState(state => ({ ...state, leftovers, resources })));;
    }

    renderResources() {
        return this.state.resources.map(resource => (
            <div className="resource-form-mini" key={resource.id} style={{ marginBottom: '.5em' }}>
                <TextField
                    required
                    placeholder={`Wpisz ilość ${resource.name}`}
                    label={resource.name}
                />
            </div>
        ));
    }

    render() {
        return (
            <React.Fragment>
                <h3>Konfiguracja zasobów</h3>
                {this.renderResources()}
            </React.Fragment>
        )
    }
}

ResourceFormStage.propTypes = {
    stage: PropTypes.object,
};

export default ResourceFormStage;