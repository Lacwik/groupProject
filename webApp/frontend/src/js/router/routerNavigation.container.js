import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import WelcomeContainer from '../welcome/welcome.container';
import LoginContainer from '../login/login.container';
import RegisterContainer from '../register/register.container';

class RouterNavigation extends Component {
  render() {
    return (
      <Switch>
        <Route exact path='/' component={WelcomeContainer} />
        <Route path='/login' component={LoginContainer} />
        <Route path='/register' component={RegisterContainer} />
      </Switch>
    );
  }
}

export default RouterNavigation;