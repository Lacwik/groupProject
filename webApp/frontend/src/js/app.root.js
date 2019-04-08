import React, { Component } from 'react';
import RouterNavigation from './router/routerNavigation.container';
import HeaderContainer from './header/header.container';
import '../css/app.css';

class App extends Component {
  render() {
    return (
      <div class="main-wrapper">
        <aside class="main-wrapper__sidebar">
          e
        </aside>
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
