import { handleError } from './handleErrors.service';

export default class StageRepository {
    constructor(store) {
        this.store = store;
    }


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    createStage = stage => {
        return fetch(`http://localhost:8090/stage`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(stage),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to create stage. ", err);
                return Promise.reject(err);
            });
    }

    editStage = stage => {
        console.log(stage);
        return fetch(`http://localhost:8090/stage/${stage.id}`, {
            method: 'PUT',
            headers: this.getHeaders(),
            body: JSON.stringify(stage),
        })
            .then(response => handleError(response))
            .catch(err => {
                console.warn("Caught error while trying to edit stage. ", err);
                return Promise.reject(err);
            });
    }

    deleteStage = (id = '') => {
        return fetch(`http://localhost:8090/stage/${id}`, {
            method: 'DELETE',
            headers: this.getHeaders(),
            body: JSON.stringify(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to delete stage. ", err));
    }

    getStageById = (id = '') => {
        return fetch(`http://localhost:8090/stage/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get stage by search value. ", err));
    }

    getCompanyStages = () => {
        return fetch(`http://localhost:8090/stage/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get company stages. ", err));
    }

    getDefaultStages = () => {
        return fetch(`http://localhost:8090/stage/default`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get default stages. ", err));
    }
}