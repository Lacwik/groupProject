import { handleError } from './handleErrors.service';

export default class StageRepository {
    constructor(store) {
        this.store = store;
    }

    // endpoint = 'http://172.30.149.96:8090'
    endpoint = `http://localhost:8090`


    getHeaders = () => {
        return {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.store.getState().JWT}`,
        };
    }

    createStage = stage => {
        return fetch(`${this.endpoint}/stage`, {
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
        return fetch(`${this.endpoint}/stage/${stage.id}`, {
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
        return fetch(`${this.endpoint}/stage/${id}`, {
            method: 'DELETE',
            headers: this.getHeaders(),
            body: JSON.stringify(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to delete stage. ", err));
    }

    getStageById = (id = '') => {
        return fetch(`${this.endpoint}/stage/${id}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get stage by search value. ", err));
    }

    getCompanyStages = () => {
        return fetch(`${this.endpoint}/stage/company`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get company stages. ", err));
    }

    getDefaultStages = () => {
        return fetch(`${this.endpoint}/stage/default`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get default stages. ", err));
    }

    
    getLeftoversForStage = stageId => {
        return fetch(`${this.endpoint}/stage/leftovers/${stageId}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line leftovers. ", err));
    }

    
    getResourcesForStage = stageId => {
        return fetch(`${this.endpoint}/stage/resources/${stageId}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line leftovers. ", err));
    }


    getStageVegetables = stageId => {
        return fetch(`${this.endpoint}/stage/vegetables/${stageId}`, {
            method: 'GET',
            headers: this.getHeaders(),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => console.warn("Caught error while trying to get line vegetables. ", err));
    }


    getModulesByVegetableList = vegetablesList => {
        return fetch(`${this.endpoint}/module/vegetables/modules`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(vegetablesList),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to create stage. ", err);
                return Promise.reject(err);
            });
    }

    getStagesByVegetableList = vegetablesList => {
        return fetch(`${this.endpoint}/stage/vegetables/stages`, {
            method: 'POST',
            headers: this.getHeaders(),
            body: JSON.stringify(vegetablesList),
        })
            .then(response => handleError(response))
            .then(response => response.json())
            .catch(err => {
                console.warn("Caught error while trying to create stage. ", err);
                return Promise.reject(err);
            });
    }
}