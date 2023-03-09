import {RouteRecordRaw, createRouter, createWebHistory} from 'vue-router';
import store from '../store';

// @ts-ignore
interface RouteInterface extends RouteRecordRaw {
    requireAuth: boolean;
}

const routes: RouteInterface[] = [
    {
        // @ts-ignore
        path: '/',
        name: 'Home',
        requireAuth: false,
        component: () => import('../views/Home.vue')
    },

    {
        // @ts-ignore
        path: '/login',
        name: 'Login',
        requireAuth: false,
        component: () => import('../views/Login.vue')
    },

    {
        // @ts-ignore
        path: '/register',
        name: 'Register',
        requireAuth: false,
        component: () => import('../views/Register.vue')
    },
    
    {
        // @ts-ignore
        path: '/about',
        name: 'About',
        requireAuth: false,
        component: () => import('../views/About.vue')
    },

    {
        // @ts-ignore
        path: '/contact',
        name: 'Contact',
        requireAuth: false,
        component: () => import('../views/Contact.vue')
    },

    {
        // @ts-ignore
        path: '/profile',
        name: 'Profile',
        requireAuth: true,
        component: () => import('../views/Profile.vue')
    },

    {
        // @ts-ignore
        path: '/wishlist',
        name: 'Wishlist',
        requireAuth: true,
        component: () => import('../views/Wishlist.vue')
    },

    {
        // @ts-ignore
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        requireAuth: false,
        component: () => import('../views/NotFound.vue')
    },
    //TODO: Property routes
    {
        // @ts-ignore
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
