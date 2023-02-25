<script setup lang="ts">
import { ref } from "vue";
import NavBar from "./components/NavBar.vue";
</script>

<template>
  <div>
    <NavBar/>
    <router-view v-slot="{ Component, route }">
      <transition name="fade" mode="out-in">
        <div :key="route.name">
          <component :is="Component"></component>
        </div>
      </transition>
    </router-view>
  </div>
</template>

<style scoped>

</style>
