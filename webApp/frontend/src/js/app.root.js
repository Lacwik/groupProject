import React, { Component } from 'react';
import RouterNavigation from './navigation/routerNavigation.container';
import '../css/app.css';
import NavigationContainer from './navigation/navigation.container';

class App extends Component {
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
