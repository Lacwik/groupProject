import LeftoversRepository from "../api/leftover.repository";
import { store } from '../redux/app.store';


export const leftoverRepository = new LeftoversRepository(store);