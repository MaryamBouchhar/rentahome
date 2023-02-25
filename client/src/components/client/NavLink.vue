<template>
  <li id="navbar-link">
    <router-link :to="to">{{ label }}</router-link>
  </li>
</template>

<script setup>
defineProps({
  to: {
    type: String,
    required: true
  },
  label: {
    type: String,
    required: true
  }
})
</script>

<style scoped>

</style>