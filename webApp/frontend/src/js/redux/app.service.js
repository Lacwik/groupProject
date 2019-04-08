import { store } from './app.store';
import { Types } from './reducerActions.constant';

export const setJWT = jwt => {
    store.dispatch({
        action: Types.SET_JWT,
        jwt,
    });
}

export const setCurrentLoggedUserEmail = email => {
    store.dispatch({
        action: Types.SET_EMAIL,
        email,
    });
}

export const setIsUserLogged = value => {
    store.dispatch({
        action: Types.SET_IS_USER_LOGGED,
        value,
    });
}

export const setApplicationUserRole = role => {
    store.dispatch({
        action: Types.SET_USER_APP_ROLE,
        role,
    });
}

export const isUserLogged = () => {
    const value = store.getState().isUserLogged;
}
