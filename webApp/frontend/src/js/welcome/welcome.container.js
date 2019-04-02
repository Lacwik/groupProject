import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';

class WelcomeContainer extends Component {
    render() {
        return (
            <h1>Welcome to calculator {this.props.value}</h1>
        );
    }
}

WelcomeContainer.propTypes = {
    value: PropTypes.string.isRequired,
};

const mapStateToProps = store => ({
    value: store.mock,
});


export default connect(mapStateToProps)(WelcomeContainer);