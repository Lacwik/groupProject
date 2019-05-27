import { store } from '../redux/app.store';
import LineRepository from "../api/line.repository";


export const lineRepository = new LineRepository(store);