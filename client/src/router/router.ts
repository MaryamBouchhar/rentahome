import {RouteRecordRaw, createRouter, createWebHashHistory} from 'vue-router';

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

    {
        path: '/contact',
        name: 'Contact',
        component: () => import('../admin/views/FormsView.vue')
    }
    //TODO: Add admin routes here


];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

export default router;
