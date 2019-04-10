import CompanyRepository from '../api/company.repository';
import { store } from '../redux/app.store';


export const companyRepository = new CompanyRepository(store);