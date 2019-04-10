import UsersRepository from '../api/users.repository';
import { store } from '../redux/app.store';


export const usersRepository = new UsersRepository(store);