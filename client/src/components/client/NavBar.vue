<template>
    <div :class="{'flex-row-reverse' : languages[$i18n.locale].rtl}"
        class="flex flex-row p-5 bg-base-100 shadow-md">
        <div :class="{'flex-row-reverse' : languages[$i18n.locale].rtl}"
            class="flex flex-row basis-1/4">
            <div class="dropdown">
                <label tabindex="0" class="btn btn-ghost lg:hidden">
                    <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24"
                         stroke="currentColor">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                              d="M4 6h16M4 12h8m-8 6h16"/>
                    </svg>
                </label>
                <ul tabindex="0"
                    class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52">
                    <NavLink to="/" v-bind:label="$t('navbar.home')"/>
                    <NavLink to="/properties" v-bind:label="$t('navbar.properties')" class="my-1"/>
                    <NavLink to="/about" v-bind:label="$t('navbar.about')" class="my-1"/>
                    <NavLink to="/contact" v-bind:label="$t('navbar.contact')" class="my-1"/>
                </ul>
            </div>
            <router-link to="/">
                <a class="btn btn-ghost normal-case text-xl">
                    Renta<span class="text-primary">Home</span>
                </a>
            </router-link>
        </div>
        <div :class="{'flex-row-reverse' : languages[$i18n.locale].rtl}"
            class="flex flex-row hidden lg:flex lg:basis-1/2">
            <ul class="menu menu-horizontal p-0">
                <NavLink to="/" label="Home" class="mx-1"/>
                <NavLink to="/properties" label="Properties" class="mx-1"/>
                <NavLink to="/about" label="About" class="mx-1"/>
                <NavLink to="/contact" label="Contact" class="mx-1"/>
            </ul>
        </div>
        <div class="hidden lg:show lg:basis-1/2"></div>
        <div :class="{'flex-row-reverse' : languages[$i18n.locale].rtl}"
            class="flex flex-row basis-1/4">
            <div :class="languages[$i18n.locale].rtl ? 'dropdown' : 'dropdown dropdown-end'"
                class="ml-2">
                <label tabindex="0" class="btn btn-ghost btn-circle avatar">
                    <div class="w-10 rounded-full">
                        <img src="https://i.pravatar.cc/300"/>
                    </div>
                </label>
                <ul tabindex="0"
                    class="mt-3 p-2 shadow menu menu-compact dropdown-content bg-base-100 rounded-box w-52">
                    <li>
                        <router-link to="/profile">
                            <i class='bx bx-user bx-sm'></i>{{ $t('navbar.profile') }}
                        </router-link>
                    </li>
                    <li><a><i class='bx bx-cog bx-sm'></i>{{ $t('navbar.settings') }}</a></li>
                    <li><a><i class='bx bx-power-off bx-sm'></i>{{ $t('navbar.logout') }}</a></li>
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
import languages from "../../assets/languages.json"

export default {
    name: "NavBar",
    data() {
        return {
            languages: languages,
            rtl: false
        }
    },
    components: {
        ThemeDropDown,
        NavLink,
        Languages
    },
    mounted() {
        this.rtl = this.languages[this.$i18n.locale].rtl
    }
}
</script>

<style scoped>

</style>