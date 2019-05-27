import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Switch, Route } from 'react-router-dom';
import WelcomeContainer from '../welcome/welcome.container';
import LoginContainer from '../login/login.container';
import RegisterContainer from '../register/register.container';
import RegisterRequestsContainer from '../registerRequests/registerRequests.container';
import LogoutContainer from '../login/logout.container';
import DashboardContainer from '../dashboard/dashboard.container';
import AddUsersContainer from '../company/addUsers.container';
import AddModuleContainer from '../module/addModule.container';
import ViewModuleContainer from '../module/viewModule.container';
import CalculatorContainer from '../calculator/calculator.container';

class RouterNavigation extends Component {
  render() {
    return (
      <Switch>
        <Route exact path='/' component={this.props.isUserLogged ? DashboardContainer : WelcomeContainer} />
        <Route path='/login' component={LoginContainer} />
        <Route path='/register' component={RegisterContainer} />
        <Route path='/admin/requests' component={RegisterRequestsContainer} />
        <Route path='/logout' component={LogoutContainer} />
        <Route path='/company/add-member' component={AddUsersContainer} />
        <Route path='/module/add-module' component={AddModuleContainer} />
        <Route path='/module/display-module' component={ViewModuleContainer} />
        <Route path='/calculator' component={CalculatorContainer} />
      </Switch>
    );
  }
}

RouterNavigation.propTypes = {
  isUserLogged: PropTypes.bool.isRequired,
}

const mapStateToProps = store => ({
  isUserLogged: store.isUserLogged,
});

export default connect(mapStateToProps)(RouterNavigation);