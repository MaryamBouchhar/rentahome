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
            token: localStorage.getItem('token') || null,
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
                    commit('setAuthError', null);
                    commit('setUser', response.data.client);

                    let redirect = router.currentRoute.value.query.redirect || '/';

                    // @ts-ignore
                    router.push(redirect)
                } else {
                    commit('setAuthError', response.data.message);
                }
            }).catch(error => {
                console.log(error);
                commit('setAuthError', error.message);
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
                    commit('setAuthError', null);
                    commit('setUser', response.data.client);

                    let redirect = router.currentRoute.value.query.redirect || '/';
                    // @ts-ignore
                    router.push(redirect)
                } else {
                    commit('setAuthError', response.data.message);
                }
            }).catch(error => {
                console.log(error);
                commit('setAuthError', error.message);
            });
        },
        async logout({commit}) {
            // remove token from local storage
            localStorage.removeItem('token');

            // set authentication status in store
            commit('setToken', null);
            commit('setIsAuthenticated', false);
            commit('setUser', null);

            router.push('/');

            axios.post(API_BASE_URL + 'finish', {
                token: store.state.token
            }).then(response => {
                console.log(response);
            }).catch(error => {
                console.log(error);
            });
        },
        async checkSession({commit}) {
            if(store.state.token != '' || !isNaN(store.state.token)) {
            // @ts-ignore
            axios.post(API_BASE_URL + 'check', {
                token: store.state.token
            }).then(response => {
                if (response.data.success) {
                    // set authentication status in store
                    commit('setIsAuthenticated', true);
                    commit('setAuthError', null);
                    commit('setUser', response.data.client);

                    let redirect = router.currentRoute.value.query.redirect || '/';
                    // @ts-ignore
                    router.push(redirect)
                } else {
                    // remove token from local storage
                    localStorage.removeItem('token');

                    // set authentication status in store
                    commit('setToken', null);
                    commit('setIsAuthenticated', false);
                    commit('setUser', null);
                }
            }).catch(error => {
                console.log(error);
                commit('setToken', null);
                commit('setIsAuthenticated', false);
                commit('setUser', null);
            });
        }
        },
        uploadAvatar({commit}, {avatar}) {

        }
    },
});

export default store;