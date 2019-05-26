import React, { Component } from 'react';
import PropTypes, { any } from 'prop-types';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { connect } from 'react-redux';

class ViewModule extends Component {
    constructor(props) {
        super(props);

        this.state = {
            module: any
        };
    }

    componentDidMount(){
        moduleRepository.getModuleById(this.props.id).then(
            response => this.setState({ module: response })
        )
    }

    render() {
        const name = this.state.module.name;
        const power = this.state.module.power;
        const vegetables = this.state.module.vegetables;
        const resources = this.state.module.resources;
        const leftovers = this.state.module.leftovers;


        return (
            <React.Fragment>
                
                <ul>

                    <li key="name-view-module">
                        <h2>{name}</h2>
                    </li>
                    <li key="name-view-power">
                        Moc: {power}
                    </li>
                    {/* <li key="name-view-vegetables">
                        {vegetables}
                    </li>
                    <li key="name-view-resources">
                        {resources}
                    </li>
                    <li key="name-view-leftovers">
                        {leftovers}
                    </li> */}
                    
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