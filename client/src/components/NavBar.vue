<template>
    <div :class="{'flex-row-reverse' : rtl}"
         class="navbar bg-base-100">
        <div :class="{'flex-row-reverse' : rtl}"
             class="flex flex-row basis-1/4">
            <div :class="rtl ? 'dropdown dropdown-end' : 'dropdown'">
                <label tabindex="0" class="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                         stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                              d="M4 6h16M4 12h8m-8 6h16"/>
                    </svg>
                </label>
                <ul tabindex="0"
                    :class="rtl ? 'lg:flex-row-reverse dropdown dropdown-end' : 'dropdown lg:flex-row'"
                    class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52">
                    <NavLink to="/" :label="$t('navbar.home')" class="my-1"/>
                    <NavLink to="/properties" :label="$t('navbar.properties')" class="my-1"/>
                    <NavLink to="/about" :label="$t('navbar.about')" class="my-1"/>
                    <NavLink to="/contact" :label="$t('navbar.contact')" class="my-1"/>
                </ul>
            </div>
            <router-link to="/">
                <a class="btn btn-ghost normal-case text-xl">
                    Renta<span class="text-primary">Home</span>
                </a>
            </router-link>
        </div>
        <div class="flex justify-center hidden lg:flex lg:basis-1/2">
            <ul :class="rtl ? 'lg:flex-row-reverse dropdown dropdown-end' : 'dropdown lg:flex-row'"
                class="md:menu md:menu-horizontal p-0">
                <NavLink to="/" :label="$t('navbar.home')" class="mx-1"/>
                <NavLink to="/properties" :label="$t('navbar.properties')" class="mx-1"/>
                <NavLink to="/about" :label="$t('navbar.about')" class="mx-1"/>
                <NavLink to="/contact" :label="$t('navbar.contact')" class="mx-1"/>
            </ul>
        </div>
        <div class="flex basis-1/2 shrink-0 justify-end lg:hidden"></div>
        <div :class="{'flex-row-reverse' : rtl}"
             class="flex flex-row justify-end basis-1/4">
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
                            :class="{ 'flex-row-revers' : rtl }"
                        >
                            <i class='bx bx-user bx-sm'></i>{{ $t('navbar.profile') }}
                        </router-link>
                    </li>
                    <li>
                        <a class="flex flex-row items-center">
                            <i class='flex bx bx-cog bx-sm'></i>{{ $t('navbar.settings') }}
                        </a>
                    </li>
                    <li>
                        <a>
                            <i class='flex bx bx-power-off bx-sm'></i>{{ $t('navbar.logout') }}
                        </a>
                    </li>
                </ul>
            </div>
            <ThemeDropDown/>
            <Languages/>
        </div>
    </div>
</template>

<script>
import NavLink from "./NavLink.vue";
import ThemeDropDown from "./ThemeDropDown.vue";
import Languages from "./Languages.vue";
import 'boxicons';
import languages from "../assets/languages.json"
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "NavBar",
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
    data() {
        return {
            languages: languages,
        }
    },
    components: {
        ThemeDropDown,
        NavLink,
        Languages
    },
}
</script>

<style scoped>

</style>