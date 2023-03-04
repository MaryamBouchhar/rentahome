import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';
import store from "../store";

// @ts-ignore
const routes: RouteRecordRaw[] = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/Home.vue')
    },

    {
        path: '/about',
        name: 'About',
        component: () => import('../views/About.vue')
    },

    {
        path: '/contact',
        name: 'Contact',
        component: () => import('../views/Contact.vue')
    },

    {
        path: '/profile',
        name: 'Profile',
        component: () => import('../views/Profile.vue')
    },

    {
        path: '/wishlist',
        name: 'Wishlist',
        component: () => import('../views/Wishlist.vue')
    },

    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('../views/NotFound.vue')
    },
    //TODO: Property routes
    {
        path: '/',
        name: 'Properties',
        children: [
            {
                path: '/properties',
                name: 'Properties',
                component: () => import('../views/Properties.vue')
            },
            {
                path: '/properties/:id',
                name: 'Property',
                component: () => import('../views/PropertyDetails.vue')
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    store.commit('setPath', to.path)
    next();
})

// @ts-ignore
export default router;
