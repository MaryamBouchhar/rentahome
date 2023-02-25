<script setup lang="ts">
import NavBar from "./components/client/NavBar.vue";
import Hero from "./components/client/Hero.vue";
</script>

<template>
  <div>
    <NavBar/>
    <Hero/>
    <router-view/>
  </div>
</template>

<style scoped>
* {
  font-family: "Roboto Light", sans-serif;
}

/* width */
::-webkit-scrollbar {
  width: 10px;
}

/* Track */
::-webkit-scrollbar-track {
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #504c4c;
  border-radius: 10px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #7a7777;
}

#app {
  user-select: none;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease-out;
}
</style>
