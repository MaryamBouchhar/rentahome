import {createStore} from 'vuex';
import axios from "axios";
import router from "../router";

const API_BASE_URL = 'http://localhost:8080/api/auth/';

const store = createStore({
    state() {
        return {
            rtl: false,
            path: '/',
            isAuthenticated: false,
            token: localStorage.getItem('token') || '',
            user: null,
            auth_error: null,
        };
    },

    mutations: {
        setRtl(state: any, value: boolean) {
            state.rtl = value;
        },
        setPath(state, path) {
            state.path = path
        },
        setToken(state, token) {
            state.token = token;
        },
        setIsAuthenticated(state, isAuthenticated) {
            state.isAuthenticated = isAuthenticated;
        },
        setUser(state, user) {
            state.user = user;
        },
        setAuthError(state, error) {
            state.auth_error = error;
            setTimeout(() => {
                state.auth_error = null;
            }, 3000)
        }
    },

    getters: {
        isAuthenticated(state) {
            return state.isAuthenticated;
        },
        user(state) {
            return state.user;
        },
        authError(state) {
            return state.auth_error;
        }
    },

    actions: {
        async login({commit}, {email, password}) {
            // send login request to backend
            axios.post(API_BASE_URL + 'login', {
                email: email,
                password: password
            }).then(response => {
                if (response.data.success) {
                    // save token to local storage
                    const {accessToken} = response.data;
                    localStorage.setItem('token', accessToken);

                    // set authentication status in store
                    commit('setToken', accessToken);
                    commit('setIsAuthenticated', true);

                    let redirect = router.currentRoute.value.query.redirect || '/';

                    // @ts-ignore
                    router.push(redirect)
                } else {
                    commit('setAuthError', response.data.message);
                }
            }).catch(error => {
                console.log(error);
                commit('setAuthError', error.response.data.message);
            });
        },
        async register({commit}, {name, email, password, phone}) {
            // send registration request to backend
            axios.post(API_BASE_URL + 'register', {
                name: name,
                email: email,
                password: password,
                phone: phone
            }).then(response => {
                if (response.data.success) {
                    // save token to local storage
                    const {accessToken} = response.data;
                    localStorage.setItem('token', accessToken);

                    // set authentication status in store
                    commit('setToken', accessToken);
                    commit('setIsAuthenticated', true);

                    let redirect = router.currentRoute.value.query.redirect || '/';
                    // @ts-ignore
                    router.push(redirect)
                } else {
                    commit('setAuthError', response.data.message);
                }
            }).catch(error => {
                console.log(error);
                commit('setAuthError', error.response.data.message);
            });
        },
        async getProtectedData({state}) {
            // send request to backend with authorization header
            axios.get(API_BASE_URL + 'auth/protected', {
                headers: {
                    Authorization: `Bearer ${state.token}`
                }
            }).then(response => {
                // save user to store
                const {user} = response.data;
                // @ts-ignore
                commit('setUser', user);
            }).catch(error => {
                console.log(error);
            });
        },
        async logout({commit}) {
            // remove token from local storage
            localStorage.removeItem('token');

            // set authentication status in store
            commit('setToken', '');
            commit('setIsAuthenticated', false);
            commit('setUser', null);

            router.push('/');
        }
    },
});

export default store;