import { createStore, applyMiddleware } from 'redux';
import logger from 'redux-logger';
import reducer from './app.reducer';
import { APPLICATION_ROLES } from '../constants/applicationRoles.constants';

const initialState = {
    JWT: '',
    userEmail: '',
    isUserLogged: false,
    appUserRole: APPLICATION_ROLES.USER,
};

export const store = createStore(reducer, initialState, applyMiddleware(logger));

