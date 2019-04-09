import RegisterRequestRepository from '../api/registerRequest.repository.js';
import { store } from '../redux/app.store';


export const registerRequestRepository = new RegisterRequestRepository(store);