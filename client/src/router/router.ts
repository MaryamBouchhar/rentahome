import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';

// @ts-ignore
// @ts-ignore
const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/Home.vue')
    },
    //TODO: Add client routes here
    {
        path: '/about',
        name: 'About',
        component: () => import('../../src/views/About.vue')
    },
    //TODO: Add admin routes here
    {
        path: '/admin',
        name: 'Admin',
        component: () => import('../admin/views/Login.vue')
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('../views/NotFound.vue')
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
