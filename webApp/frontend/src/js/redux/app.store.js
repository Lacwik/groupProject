import { createStore, applyMiddleware } from 'redux';
import logger from 'redux-logger';
import reducer from './app.reducer';

const initialState = {
    JWT: '',
    userEmail: '',
    isUserLogged: false,
};

export const store = createStore(reducer, initialState, applyMiddleware(logger));

