import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';

// @ts-ignore
// @ts-ignore
const routes: RouteRecordRaw[] = [

    //TODO: Add admin routes here


    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('../views/LoginView.vue')
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
