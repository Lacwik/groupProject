import AuthenticationRepository from "../api/authentication.repository";
import { store } from '../redux/app.store';


export const authenticationRepository = new AuthenticationRepository(store);