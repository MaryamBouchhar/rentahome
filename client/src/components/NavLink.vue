<template>
    <li class="navbar-link py-1">
        <router-link
            :class="path === to ? 'btn btn-primary' : 'btn btn-ghost'"
            :to="to"
        >
            {{ label }}
        </router-link>
    </li>
</template>

<script setup>

import {computed} from 'vue'
import {useStore} from "vuex";

const props = defineProps({
    to: {
        type: String,
        required: true
    },
    label: {
        type: String,
        required: true
    }
})

const store = useStore();
const path = computed(() => {
    return store.state.path;
})

</script>

<style scoped>

</style>