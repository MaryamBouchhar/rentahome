import {createApp} from "vue";
import {createPinia} from "pinia";

import swal from 'sweetalert';
window.Swal = swal;

import App from "./App.vue";
import router from "./router";
import {useMainStore} from "@/stores/main.js";
import {useStyleStore} from "@/stores/style.js";
import {darkModeKey, styleKey} from "@/config.js";


import "./css/main.css";
import strings from "./assets/strings.json";

/* Init Pinia */
const pinia = createPinia();


/* Create Vue app */
createApp(App)
  .use(router)
  .use(pinia)
  .mount("#app");

/* Init Pinia stores */
const mainStore = useMainStore(pinia);
const styleStore = useStyleStore(pinia);

/* Fetch sample data */

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
const defaultDocumentTitle = "RentaHome Admin Dashboard";

/* Set document title from route meta */
router.afterEach((to) => {
  document.title = to.meta?.title
    ? `${to.meta.title} — ${defaultDocumentTitle}`
    : defaultDocumentTitle;
});
