import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';

// @ts-ignore
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
        component: () => import('../../src/views/Contact.vue')
    },
    {
        path: '/profile',
        name: 'Profile',
        component: () => import('../../src/views/Profile.vue')
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

// @ts-ignore
export default router;
