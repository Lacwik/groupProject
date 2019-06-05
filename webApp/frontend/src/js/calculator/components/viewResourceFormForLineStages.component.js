import React, { Component } from 'react';
import PropTypes from 'prop-types';
import ResourceFormStage from './resourceFormStage.component';
import LeftoverFormStage from './leftoverFormStage.component';
import TimeFormStage from './timeFormStage.component';
import moment from 'moment';
import {GroupWork } from '@material-ui/icons';


class ViewResourceFormForLineStages extends Component {
    static capitalizeFirstLetter = str => {
        if (str.length > 0) {
            const newStr = [...str];
            newStr[0] = newStr[0].toUpperCase();
            return newStr.join('');
        }

        return str;
    }
    static getNextHour = m => {
        m.hour(m.hour() + 1)
        return m;
    }
    static initAllStagesData = stageModels => {
        const stages = {};
        stageModels.forEach(stage => stages[stage.id] = {
            id: stage.id,
            duration: ViewResourceFormForLineStages.getNextHour(moment()).diff(moment()),
            resources: {},
            leftovers: {},
        });
    
        return stages;
    }


    constructor(props) {
        super(props);

        this.state = {
            stages: ViewResourceFormForLineStages.initAllStagesData(props.line.stageModels),
        }
    }

    onChangeDuration = (id, duration) => {
        const { state } = this;
        const newState = {
            ...state,
            stages: {
                ...state.stages,
                [id]: {
                    ...state.stages[id],
                    duration,
                },
            },
        };

        this.setState(newState);
        this.onChangeStage(newState);
    }


    onChangeResourceOnStage = (stageId, resourceId, value) => {
        const { state } = this;
        const newState = {
            ...state,
            stages: {
                ...state.stages,
                [stageId]: {
                    ...state.stages[stageId],
                    resources: {
                        ...state.stages[stageId].resources,
                        [resourceId]: value,
                    },
                },
            },
        };

        this.setState(newState);
        this.onChangeStage(newState);
    }

    onChangeLeftoverOnStage = (stageId, leftoverId, value) => {
        const { state } = this;
        const newState = {
            ...state,
            stages: {
                ...state.stages,
                [stageId]: {
                    ...state.stages[stageId],
                    leftovers: {
                        ...state.stages[stageId].leftovers,
                        [leftoverId]: value,
                    },
                },
            },
        };

        this.setState(newState);
        this.onChangeStage(newState);
    }

    onChangeStage = state => {
        this.props.onChangeDataInStage(state.stages);
    }


    renderViewResourceAndLeftoversForms() {
        const { stagesOrder: order } = this.props.line;
        
        let stageModels = [];
        if (order) {
            const ids = order.split(';').filter(Boolean);
            ids.forEach(id => {
                stageModels.push(this.props.line.stageModels.find(stage => stage.id === id));
            });
        } else {
            stageModels = this.props.line.stageModels;
        }


        return this.props.line.stageModels.map(stage => (
            <li key={stage.id}>
                
                <div className="form-stage">
                <div className="icon"><GroupWork  style={{ color: '#66aaee', fontSize:"55px"}} fontSize="large" /></div>
                    <h2>Konfiguracja etapu: {stage.name.toUpperCase()}</h2>
                    <TimeFormStage
                        stage={stage}
                        onChangeDuration={(id, duration) => this.onChangeDuration(id, duration)}
                    />
                    <div style={{ marginTop: '.5em' }} />
                    <ResourceFormStage
                        stage={stage}
                        gusCategory={this.props.gusCategory}
                        onChangeResource={(stageId, resourceId, value) => this.onChangeResourceOnStage(stageId, resourceId, value)}
                    />
                    <div style={{ marginTop: '.5em' }} />
                    <LeftoverFormStage
                        stage={stage}
                        onChangeLeftover={(stageId, leftOverId, value) => this.onChangeLeftoverOnStage(stageId, leftOverId, value)}
                    />
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
    onChangeDataInStage: PropTypes.func.isRequired,
    gusCategory: PropTypes.array.isRequired,
};

export default ViewResourceFormForLineStages;