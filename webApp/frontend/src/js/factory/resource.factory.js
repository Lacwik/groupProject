import ResourcesRepository from "../api/resource.repository";
import { store } from '../redux/app.store';


export const resourceRepository = new ResourcesRepository(store);