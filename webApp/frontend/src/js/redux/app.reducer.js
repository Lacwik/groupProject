import { Types } from './reducerActions.constant';

const toReducer = (state = {}, action) => {
    switch(action.type) {
        case Types.SET_JWT:
            return {
                ...state,
                jwt: action.jwt,
            };
        
        case Types.SET_EMAIL:
            return {
                ...state,
                userEmail: action.email,
            };

        case Types.SET_IS_USER_LOGGED:
            return {
                ...state,
                isUserLogged: action.value,
            };
        case Types.SET_USER_APP_ROLE:
            return {
                ...state,
                appUserRole: action.role,
            }

        default:
            return state;
    }
}

export default toReducer;