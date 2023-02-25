import {createRouter, createWebHistory} from "vue-router"

const routes = [
    {
        path: "/",
        name: "Home",
        // component: () => import("../views/Home.vue"),
    },
    {
        path: "/about",
        name: "About",
        // component: () => import("../views/About.vue"),
    },
    {
        path: "/services",
        name: "Services",
        // component: () => import("../views/Services.vue"),
    },
    {
        path: "/contact",
        name: "Contact",
        // component: () => import("../views/Contact.vue"),
    },
    {
        path: "/:pathMatch(.*)*",
        name: "NotFound",
        // component: () => import("../views/NotFound.vue"),
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;