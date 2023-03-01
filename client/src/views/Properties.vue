<template>
  <div class="container">
    <h1 class="w-full text-center font-bold text-2xl my-5">
      Properties
    </h1>
    <!-- TODO: Add filter options in sidebar -->
    <div class="flex flex-col lg:flex-row">
      <div class="filter-bar w-full lg:w-1/4">
        <h1 class="text-xl font-bold">Filter</h1>
      </div>
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
      </div>
    </div>
  </div>
</template>

<script setup>
import PropertyCard from "../components/client/PropertyCard.vue";
import 'axios'
</script>

<style scoped>

</style>