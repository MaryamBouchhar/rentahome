// @ts-ignore
import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            rtl: false
        };
    },

    mutations: {
        setRtl(state: any, value: boolean) {
            state.rtl = value;
        }
    }
});

export default store;