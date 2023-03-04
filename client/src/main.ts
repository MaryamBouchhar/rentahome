import { createApp } from 'vue'
import './style.css'
import router from './router'
import App from './App.vue'
import { createI18n } from "vue-i18n";
import store from "./store";

import strings from './assets/strings.json'

// Create VueI18n instance with options
const i18n = createI18n({
    locale: "en", // set locale
    messages: strings, // set locale messages
});

createApp(App)
    .use(router)
    .use(i18n)
    .use(store)
    .mount('#app')
