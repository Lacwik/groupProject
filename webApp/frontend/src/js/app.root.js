import React, { Component } from 'react';
import RouterNavigation from './navigation/routerNavigation.container';
import '../css/app.css';
import NavigationContainer from './navigation/navigation.container';
import { AUTH_KEYS } from './constants/authentication.constants';
import { setJWT, setCurrentLoggedUserEmail, setIsUserLogged, setApplicationUserRole } from './redux/app.service';

class App extends Component {
  componentDidMount() {
    const JWT = localStorage.getItem(AUTH_KEYS.ACCESS_TOKEN);
    if (JWT) {
      const email = localStorage.getItem(AUTH_KEYS.EMAIL);
      const role = localStorage.getItem(AUTH_KEYS.ROLE);

      setJWT(JWT);
      setCurrentLoggedUserEmail(email);
      setIsUserLogged(true);
      setApplicationUserRole(role);
    }
  }
  
  render() {
    return (
      <div className="main-wrapper">
        <NavigationContainer />
        <main className="main-wrapper__container">
          <RouterNavigation />
        </main>
      </div>
    );
  }
}

export default App;
