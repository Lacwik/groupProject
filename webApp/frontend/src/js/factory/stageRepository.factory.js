import StageRepository from "../api/stage.repository";
import { store } from '../redux/app.store';


export const stageRepository = new StageRepository(store);