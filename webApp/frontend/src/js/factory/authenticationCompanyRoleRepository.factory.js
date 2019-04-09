import { store } from '../redux/app.store';
import AuthenticationCompanyRoleRepository from '../api/authenticationCompanyRole.repository';


export const authenticationCompanyRoleRepository = new AuthenticationCompanyRoleRepository(store);