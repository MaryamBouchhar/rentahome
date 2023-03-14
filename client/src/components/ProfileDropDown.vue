<template>
    <div :class="rtl ? 'dropdown' : 'dropdown dropdown-end'"
         class="ml-2">
        <label tabindex="0" class="btn btn-ghost btn-circle avatar">
            <div class="w-10 rounded-full">
                <img src="https://i.pravatar.cc/300"/>
            </div>
        </label>
        <ul tabindex="0"
            :class="rtl ? 'dropdown dropdown-end' : 'dropdown'"
            class="mt-3 p-2 shadow menu menu-compact dropdown-content bg-base-100 rounded-box w-52">
            <li>
                <router-link
                    to="/profile"
                    class="flex flex-row items-center"
                    :class="{ 'flex-row-revers' : rtl }, { 'active' : path === '/profile' }"
                >
                    <i class='bx bx-user bx-sm'></i>{{ $t('navbar.profile') }}
                </router-link>
            </li>
            <li>
                <router-link
                    to="/wishlist"
                    class="flex flex-row items-center"
                    :class="{ 'flex-row-revers' : rtl }, { 'active' : path === '/wishlist' }"
                >
                    <i class='bx bx-heart bx-sm'></i>{{ $t('navbar.wishlist') }}
                </router-link>
            </li>
            <li>
                <a class="flex flex-row items-center">
                    <i class='flex bx bx-cog bx-sm'></i>{{ $t('navbar.settings') }}
                </a>
            </li>
            <li @click="logout">
                <a>
                    <i class='flex bx bx-power-off bx-sm'></i>{{ $t('navbar.logout') }}
                </a>
            </li>
        </ul>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "ProfileDropDown",
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl),
            path: computed(() => store.state.path),
            logout: () => {
                store.dispatch('logout');
            }
        }
    }
}
</script>