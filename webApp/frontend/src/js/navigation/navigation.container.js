import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Home, GroupAdd, AccountBox } from '@material-ui/icons';
import '../../css/navigation.css';

class NavigationContainer extends Component {
  render() {
    return (
        <aside className="main-wrapper__sidebar">
            <ul className="navigation">
                <li key="nav-home">
                    <Home style={{ color: '#aaaaaa' }} fontSize="large" />
                    <Link to="/">Home</Link>
                </li>
                <li key="nav-register">
                    <AccountBox style={{ color: '#aaaaaa' }} fontSize="large" />
                    <Link to="/login">Zaloguj się</Link>
                </li>
                <li key="nav-login">
                    <GroupAdd style={{ color: '#aaaaaa' }} fontSize="large" />
                    <Link to="/register">Zarejestruj się</Link>
                </li>
            </ul>
        </aside>
    );
  }
}

export default NavigationContainer;
