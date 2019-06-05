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

export const setUsers = users => {
    store.dispatch({
        type: Types.SET_USERS,
        users,
    });
}

export const setUserRegisterRequests = requests => {
    store.dispatch({
        type: Types.SET_REGISTER_USER_REQUESTS,
        requests,
    });
}

export const setCompanyWorkingFor = company => {
    store.dispatch({
        type: Types.SET_COMPANY_WORKING_FOR,
        company,
    })
}

export const setIsWorkingForCompany = value => {
    store.dispatch({
        type: Types.SET_IS_WORKING_FOR_COMPANY,
        value,
    })
}

export const setCompanyRegisterRequests = requests => {
    store.dispatch({
        type: Types.SET_REGISTER_COMPANY_REQUESTS,
        requests,
    });
}

export const setCompanyRoles = roles => {
    store.dispatch({
        type: Types.SET_COMPANY_ROLES,
        roles,
    });
}

export const removeUserById = id => {
    store.dispatch({
        type: Types.REMOVE_USER_BY_ID,
        id,
    });
}

export const isUserLogged = () => {
    return store.getState().isUserLogged;
}

export const setStatistics = statistics => {
    store.dispatch({
        type: 'SET_STATISTICS',
        statistics,
    })
}