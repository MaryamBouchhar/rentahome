<template>
  <footer class="footer footer-center p-4 mt-4 bg-base-300 text-base-content">
    <div>
      <p>Copyright © {{year}} {{app_name}}. All rights reserved.</p>
    </div>
  </footer>
</template>

<script setup>
//current year
const year = new Date().getFullYear();
const app_name = import.meta.env.VITE_APP_NAME;
</script>

<style scoped>

</style>