import VegetablesRepository from "../api/vegetable.repository";
import { store } from '../redux/app.store';


export const vegetableRepository = new VegetablesRepository(store);