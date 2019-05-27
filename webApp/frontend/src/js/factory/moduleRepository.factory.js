import ModuleRepository from "../api/module.repository";
import { store } from '../redux/app.store';


export const moduleRepository = new ModuleRepository(store);