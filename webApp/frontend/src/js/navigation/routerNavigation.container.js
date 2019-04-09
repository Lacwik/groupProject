import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import WelcomeContainer from '../welcome/welcome.container';
import LoginContainer from '../login/login.container';
import RegisterContainer from '../register/register.container';
import RegisterRequestsContainer from '../registerRequests/registerRequests.container';
import LogoutContainer from '../login/logout.container';

class RouterNavigation extends Component {
  render() {
    return (
      <Switch>
        <Route exact path='/' component={WelcomeContainer} />
        <Route path='/login' component={LoginContainer} />
        <Route path='/register' component={RegisterContainer} />
        <Route path='/admin/requests' component={RegisterRequestsContainer} />
        <Route path='/logout' component={LogoutContainer} />
      </Switch>
    );
  }
}

export default RouterNavigation;