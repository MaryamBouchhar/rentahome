import { createStore } from 'vuex';

const store = createStore({
    state() {
        return {
            rtl: false
        };
    }
});

export default store;