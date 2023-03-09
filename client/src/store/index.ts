import {createStore} from 'vuex';
import axios from "axios";

const API_BASE_URL = 'http://localhost:8080/';

const store = createStore({
    state() {
        return {
            rtl: false,
            path: '/',
            isAuthenticated: false,
            token: null,
            user: null
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
        }
    },

    actions: {
        async login({commit}, {email, password}) {
            // send login request to backend
            axios.post(API_BASE_URL + 'login', {
                email: email,
                password: password
            }).then(response => {
                console.log(response);

                // save token to local storage
                const {token} = response.data;
                localStorage.setItem('token', token);

                // set authentication status in store
                commit('setToken', token);
                commit('setIsAuthenticated', true);
            }).catch(error => {
                console.log(error);
            });
        },
        async register({commit}, {name, email, password}) {
            // send registration request to backend
            axios.post(API_BASE_URL + 'register', {
                name: name,
                email: email,
                password: password
            }).then(response => {
                console.log(response);

                // save token to local storage
                const {token} = response.data;
                localStorage.setItem('token', token);

                // set authentication status in store
                commit('setToken', token);
                commit('setIsAuthenticated', true);
            }).catch(error => {
                console.log(error);
            });
        },
        async getProtectedData({state}) {
            // send request to backend with authorization header
            axios.get(API_BASE_URL + 'protected', {
                headers: {
                    Authorization: `Bearer ${state.token}`
                }
            }).then(response => {
                console.log(response);

                // save user to store
                const {user} = response.data;
                // @ts-ignore
                commit('setUser', user);
            }).catch(error => {
                console.log(error);
            });
        },
    },
});

export default store;