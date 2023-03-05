
import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            rtl: false,
            path: '/'
        };
    },

    mutations: {
        setRtl(state: any, value: boolean) {
            state.rtl = value;
        },
        setPath(state, path) {
            state.path = path
        }
    }
});

export default store;