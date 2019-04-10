import { Types } from './reducerActions.constant';

const toReducer = (state = {}, action) => {
    switch(action.type) {
        case Types.SET_JWT:
            return {
                ...state,
                JWT: action.JWT,
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
            };

        case Types.SET_REGISTER_USER_REQUESTS:
            return {
                ...state,
                userRegisterRequests: action.requests,
            };

        case Types.SET_REGISTER_COMPANY_REQUESTS:
            return {
                ...state,
                companyRegisterRequests: action.requests,
            };

        case Types.SET_COMPANY_ROLES:
            return {
                ...state,
                companyRoles: action.roles,
            };

        case Types.SET_USERS:
            return {
                ...state,
                users: action.users,
            };

        case Types.SET_COMPANY_WORKING_FOR:
            return {
                ...state,
                companyWorkingFor: action.company,
            };

        case Types.SET_IS_WORKING_FOR_COMPANY:
            return {
                ...state,
                isWorkingForCompany: action.value,
            };

        default:
            return state;
    }
}

export default toReducer;