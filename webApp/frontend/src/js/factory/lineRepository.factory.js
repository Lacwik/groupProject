import LineRepository from "../api/line.repository";
import { store } from '../redux/app.store';


export const lineRepository = new LineRepository(store);