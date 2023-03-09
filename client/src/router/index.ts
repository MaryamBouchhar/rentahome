import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';
import store from '../store';

interface RouteInterface extends RouteRecordRaw {
    requireAuth: boolean;
}

const routes: RouteInterface[] = [
    {
        path: '/',
        name: 'Home',
        requireAuth: false,
        component: () => import('../views/Home.vue')
    },
    {
        path: '/login',
        name: 'Login',
        requireAuth: false,
        component: () => import('../views/Login.vue')
    },
    {
        path: '/register',
        name: 'Register',
        requireAuth: false,
        component: () => import('../views/Register.vue')
    },
    {
        path: '/about',
        name: 'About',
        requireAuth: false,
        component: () => import('../views/About.vue')
    },

    {
        path: '/contact',
        name: 'Contact',
        requireAuth: false,
        component: () => import('../views/Contact.vue')
    },

    {
        path: '/profile',
        name: 'Profile',
        requireAuth: true,
        component: () => import('../views/Profile.vue')
    },

    {
        path: '/wishlist',
        name: 'Wishlist',
        requireAuth: true,
        component: () => import('../views/Wishlist.vue')
    },

    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        requireAuth: false,
        component: () => import('../views/NotFound.vue')
    },
    //TODO: Property routes
    {
        path: '/',
        name: 'Properties',
        requireAuth: false,
        children: [
            {
                path: '/properties',
                name: 'Properties',
                requireAuth: false,
                component: () => import('../views/Properties.vue')
            },
            {
                path: '/properties/:id',
                name: 'PropertyDetails',
                requireAuth: false,
                component: () => import('../views/PropertyDetails.vue')
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    // @ts-ignore
    routes
});

router.beforeEach((to, from, next) => {
    // @ts-ignore
    const route = routes.find(route => route.path === to.path);
    // @ts-ignore
    const requireAuth = route.requireAuth || false;
    const isAuthenticated = store.getters.isAuthenticated;
    // @ts-ignore
    if (requireAuth && !isAuthenticated) {
        store.commit('setPath', '/login')
        next('/login');
    } else {
        store.commit('setPath', to.path)
        next();
    }
});

export default router;
