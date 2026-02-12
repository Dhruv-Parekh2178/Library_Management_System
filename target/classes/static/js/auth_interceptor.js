const TOKEN_KEY = 'JWT';
const LOGIN_URL = '/library/login';


function getToken() {
    return localStorage.getItem(TOKEN_KEY);
}

function logout() {
    localStorage.removeItem(TOKEN_KEY);
    window.location.href = LOGIN_URL;
}

(function () {
    const originalFetch = window.fetch;

    window.fetch = function (url, options = {}) {
        const token = getToken();

        if (token) {
            options.headers = options.headers || {};
            options.headers['Authorization'] = 'Bearer ' + token;
        }

        return originalFetch(url, options).then(res => {
            if (res.status === 401 || res.status === 403) {
                logout();
                throw new Error('Unauthorized');
            }
            return res;
        });
    };
})();

window.Auth = {
    getToken,
    logout
};
