import React, { Component } from 'react';
import { Switch, Route } from 'react-router-dom';
import WelcomeContainer from '../welcome/welcome.container';

class RouterNavigation extends Component {
  render() {
    return (
      <Switch>
        <Route exact path='/' component={WelcomeContainer}/>
      </Switch>
    );
  }
}

export default RouterNavigation;