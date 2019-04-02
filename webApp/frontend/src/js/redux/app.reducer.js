import { Types } from './reducerActions.constant';

const toReducer = (state = {}, action) => {
    switch(action.type) {
        case Types.MOCK_ACTION:
            return {
                ...state,
                mock: action.value,
            };
        default:
            return state;
    }
}

export default toReducer;