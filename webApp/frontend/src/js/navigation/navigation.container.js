import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { Home, GroupAdd, AccountBox, ExitToApp } from '@material-ui/icons';
import '../../css/navigation.css';

class NavigationContainer extends Component {
    renderLinksIfNotLogged() {
        if (!this.props.isUserLogged) {
            return (
                <React.Fragment>
                    <li key="nav-register">
                        <AccountBox style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/login">Zaloguj się</Link>
                    </li>
                    <li key="nav-login">
                        <GroupAdd style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/register">Zarejestruj się</Link>
                    </li>
                </React.Fragment>
            );
        }
    }

    renderLinksIfLogged() {
        if (this.props.isUserLogged) {
            return (
                <React.Fragment>
                    <li key="nav-logout">
                        <ExitToApp style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/logout">Wyloguj się</Link>
                    </li>
                </React.Fragment>
            )
        }
    }

    render() {
        return (
            <aside className="main-wrapper__sidebar">
                <ul className="navigation">
                    <li key="nav-home">
                        <Home style={{ color: '#aaaaaa' }} fontSize="large" />
                        <Link to="/">Strona główna</Link>
                    </li>
                    {this.renderLinksIfNotLogged()}
                    {this.renderLinksIfLogged()}
                </ul>
            </aside>
        );
    }
}

NavigationContainer.propTypes = {
    isUserLogged: PropTypes.bool.isRequired,
}

const mapStateToProps = state => ({
    isUserLogged: state.isUserLogged,
});

export default connect(mapStateToProps)(NavigationContainer);
