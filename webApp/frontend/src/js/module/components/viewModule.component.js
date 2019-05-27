import React, { Component } from 'react';
import PropTypes, { any } from 'prop-types';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { connect } from 'react-redux';

class ViewModule extends Component {
    constructor(props) {
        super(props);

        this.state = {
            module: any,
            full_info: false,
        };
    }

    componentDidMount(){
        moduleRepository.getModuleById(this.props.id).then(
            response => this.setState({ module: response })
        ).then(() => this.setState({full_info: this.props.full_info})
        )
    }

    render() {
        const name = this.state.module.name;
        const power = this.state.module.power;
        const vegetables = this.state.module.vegetables;
        const resources = this.state.module.resources;
        const leftovers = this.state.module.leftovers;
        const full_info = this.state.full_info;
    
        let fullInfo;
        if (full_info) {
            fullInfo = (
                <React.Fragment>
                    <li key="name-view-vegetables">
                        {vegetables}
                    </li>
                    <li key="name-view-resources">
                        {resources}
                    </li>
                    <li key="name-view-leftovers">
                        {leftovers}
                    </li>
                </React.Fragment>
            );
        }
        return (
            <React.Fragment>
                
                <ul>

                    <li key="name-view-module">
                        <h2>{name}</h2>
                    </li>
                    <li key="name-view-power">
                        Moc: {power}
                    </li>
                   
                    {fullInfo}                  
                    
                    
                </ul>
            </React.Fragment>
        );
    }
}

ViewModule.propTypes = {
    isUserLogged: PropTypes.bool.isRequired, // from parent
}

const mapStateToProps = store => ({
    isUserLogged: store.isUserLogged,
});

export default connect(mapStateToProps)(ViewModule);