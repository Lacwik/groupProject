import React, { Component } from 'react';
import PropTypes, { number, string, any } from 'prop-types';
import { Redirect } from 'react-router-dom';
import { stageRepository } from '../../factory/stageRepository.factory';

class ViewStage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            stage: stageRepository.getStageById(props.id)
        };
    }

    render() {

        const name = this.state.stage.name;
        const outsourced = this.state.stage.outsourced;
        const company_id = this.state.stage.company_id;

        return (
            <React.Fragment>
                <ul>
                    <li key="name-view-stage">
                        {name}
                    </li>
                    <li key="outsourced-view-stage">
                        {outsourced}
                    </li> 
                    <li key="company_id-view-stage">
                        {company_id}
                    </li>
                </ul>
            </React.Fragment>
        );
    }
}

export default ViewModule;