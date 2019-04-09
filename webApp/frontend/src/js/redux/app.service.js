import { store } from './app.store';
import { Types } from './reducerActions.constant';

export const setJWT = JWT => {
    store.dispatch({
        type: Types.SET_JWT,
        JWT,
    });
}

export const setCurrentLoggedUserEmail = email => {
    store.dispatch({
        type: Types.SET_EMAIL,
        email,
    });
}

export const setIsUserLogged = value => {
    store.dispatch({
        type: Types.SET_IS_USER_LOGGED,
        value,
    });
}

export const setApplicationUserRole = role => {
    store.dispatch({
        type: Types.SET_USER_APP_ROLE,
        role,
    });
}

export const setUserRegisterRequests = requests => {
    store.dispatch({
        type: Types.SET_REGISTER_USER_REQUESTS,
        requests,
    });
}

export const setCompanyRegisterRequests = requests => {
    store.dispatch({
        type: Types.SET_REGISTER_COMPANY_REQUESTS,
        requests,
    });
}

export const isUserLogged = () => {
    return store.getState().isUserLogged;
}
