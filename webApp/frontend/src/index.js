import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import { store } from './js/redux/app.store';
import * as serviceWorker from './serviceWorker';
import App from './js/app.root';
import './css/index.css';
import Alert from 'react-s-alert';
 
import 'react-s-alert/dist/s-alert-default.css';
import 'react-s-alert/dist/s-alert-css-effects/slide.css';

ReactDOM.render(
        <Provider store={store}>
            <BrowserRouter>
                <App />
                <Alert stack={{limit: 3}} />
            </BrowserRouter>
        </Provider>
    , document.getElementById('root'));

serviceWorker.unregister();
