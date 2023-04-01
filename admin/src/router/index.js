import {createRouter, createWebHistory} from "vue-router";
import store from "../stores/index"

const routes = [
  {
    // Document title tag
    // We combine it with defaultDocumentTitle set in `src/main.js` on router.afterEach hook
    meta: {
      title: "Login"
    },

    path: "/",
    requireAuth: false,
    name: "login",
    component: import("@/views/LoginView.vue")
  },
  {
    meta: {
      title: "Profile"
    },
    path: "/profile",
    name: "profile",
    requireAuth: true,
    component: () => import("@/views/ProfileView.vue")
  },
  {
    meta: {
      title: "Dashboard"
    },
    path: "/dashboard",
    name: "dashboard",
    requireAuth: true,
    component: () => import("@/views/HomeView.vue")
  },
  {
    meta: {
      title: "Properties"
    },
    path: "/properties",
    name: "properties",
    requireAuth: true,
    component: () => import("@/views/PropertiesView.vue")
  },
  {


    meta: {
      title: "AddProperty"
    },
    path: "/add-property",
    name: "addproperty",
    requireAuth: true,
    component: () => import("@/views/NewPropertyView.vue")
  },

  {
    meta: {
      title: "UpdateProperty"
    },
    path: "/update-property/:id",
    name: "updateproperty",
    requireAuth: true,
    component: () => import("@/views/UpdatePropertyView.vue")
  },
  {
    meta: {
      title: "Booking Requests"
    },
    path: "/booking_request",
    name: "booking_request",
    requireAuth: true,
    component: () => import("@/views/ReservationView.vue")
  },

  {
    meta: {
      title: "Admin"
    },
    path: "/admins",
    name: "admins",
    requireAuth: true,
    component: () => import("@/views/AdminView.vue")
  },
  {
    meta: {
      title: "Clients"
    },
    path: "/clients",
    name: "clients",
    requireAuth: true,
    component: () => import("@/views/ClientsView.vue")
  },
  {
    meta: {
      title: "AddAdmin"
    },
    path: "/add-admin",
    name: "addadmin",
    requireAuth: true,
    component: () => import("@/views/NewAdminView.vue")
  },

  {
    meta: {
      title: "AddClient"
    },
    path: "/add-client",
    name: "addclient",
    requireAuth: true,
    component: () => import("@/views/NewClientView.vue")
  },

  {
    meta: {
      title: "UpdateAdmin"
    },
    path: "/update-admin/:id",
    name: "updateadmin",
    requireAuth: true,
    component: () => import("@/views/UpdateAdminView.vue")
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    return savedPosition || {top: 0};
  }
});

router.beforeEach((to, from, next) => {
  store.commit('setPath', to.path)
  next();
})

export default router;
