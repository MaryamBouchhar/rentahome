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
</template>

<script setup>
import {useStore} from "vuex";
import {computed} from "vue";

const store = useStore();

const rtl = computed(() => store.state.rtl)
</script>