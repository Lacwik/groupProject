export const handleError = response => {
    if (!response)
    return Promise.reject('Nieznany błąd.');

    if (response.status === 401) {
        return Promise.reject('Podałeś złe dane lub Twoje konto jest nieaktywne.');
    }

    if (response.status === 403) {
        return Promise.reject('Nie masz uprawnień, aby zobaczyć ten zasób.');
    }

    if (response.status >= 300) {
        return Promise.reject(response.message)
    }
    return response;
}