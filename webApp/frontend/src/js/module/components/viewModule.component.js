import React, { Component } from 'react';
import PropTypes, { any } from 'prop-types';
import { moduleRepository } from '../../factory/moduleRepository.factory';
import { connect } from 'react-redux';
import { Settings } from '@material-ui/icons';

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
                    <li key="name-view-power">
                        <b>Moc:</b> <label className="items">{power} </label>
                    </li> 
                    <li key="name-view-vegetables view-list">
                    
                        <b>{"Warzywa:"}</b>
                        <ul>
                            {vegetables.map(item => (
                                <li className="items" key={item.id}>
                                    {item.name}
                                </li>
                            ))}
                        </ul>
                    </li>
                    <li key="name-view-resources view-list">
                        <b>{"Zasoby:"}</b>
                        <ul>
                            {resources.map(item => (
                                <li className="items" key={item.id}>
                                    {item.name}
                                </li>
                            ))}
                        </ul>
                    </li>
                    <li key="name-view-leftovers view-list">
                        <b>{"Odpady:"}</b>
                        <ul>
                            {leftovers.map(item => (
                                <li className="items" key={item.id}>
                                    {item.name}
                                </li>
                            ))}
                        </ul>
                    </li>
                </React.Fragment>
            );
        }
        return (
            <React.Fragment>
                
                <ul style={{ width: '100%' }}>

                    <li key="name-view-module">
                        <h1 style={{ textAlign: 'center', width: '100%' }}>{name}</h1>
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