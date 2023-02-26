import { createApp } from 'vue'
import './style.css'
import router from './router/router'
import App from './App.vue'
import { createPinia } from "pinia";
import { useMainStore } from "../src/admin/stores/main.js";
import { useStyleStore } from "../src/admin/stores/style.js";
import { darkModeKey, styleKey } from  "../src/admin/js/config";

import "../src/admin/css/main.css";

/* Init Pinia */
const pinia = createPinia();


/* Init Pinia stores */
const mainStore = useMainStore(pinia);
const styleStore = useStyleStore(pinia);

/* Fetch sample data */
mainStore.fetch("clients");
mainStore.fetch("history");

/* App style */
styleStore.setStyle(localStorage[styleKey] ?? "basic");

/* Dark mode */
if (
    (!localStorage[darkModeKey] &&
        window.matchMedia("(prefers-color-scheme: dark)").matches) ||
    localStorage[darkModeKey] === "1"
) {
    styleStore.setDarkMode(true);
}

/* Default title tag */
const defaultDocumentTitle = "Admin One Vue 3 Tailwind";

/* Set document title from route meta */
router.afterEach((to) => {
    document.title = to.meta?.title
        ? `${to.meta.title} — ${defaultDocumentTitle}`
        : defaultDocumentTitle;
});

/* Create Vue app */
createApp(App).use(router).use(pinia).mount("#app");
