<template>
  <li class="py-1" id="navbar-link">
    <router-link :to="to">
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