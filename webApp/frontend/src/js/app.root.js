import React, { Component, Fragment } from 'react';
import RouterNavigation from './router/routerNavigation.container';
import HeaderContainer from './header/header.container';
import FooterContainer from './footer/footer.container';

class App extends Component {
  render() {
    return (
      <>
        <HeaderContainer />
        <RouterNavigation />
        <FooterContainer />
      </>
    );
  }
}

export default App;
