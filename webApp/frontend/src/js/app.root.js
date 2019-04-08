import React, { Component } from 'react';
import RouterNavigation from './navigation/routerNavigation.container';
import '../css/app.css';
import NavigationContainer from './navigation/navigation.container';

class App extends Component {
  render() {
    return (
      <div class="main-wrapper">
        <NavigationContainer />
        <main class="main-wrapper__container">
          <div class="wrapper-content">
            <RouterNavigation />
          </div>
        </main>
      </div>
    );
  }
}

export default App;
