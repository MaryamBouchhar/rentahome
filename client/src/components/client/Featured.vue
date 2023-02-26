<template>
  <div class="hero bg-base-200">
    <div class="hero-content text-center flex flex-col justify-center">
      <div class="max-w-md">
        <h1 class="text-3xl font-bold">Featured Properties</h1>
        <p class="py-6">Check out our featured properties and find your dream home today!</p>
      </div>
      <!-- Property Cards -->
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
        <PropertyCard/>
      </div>

      <!-- view all button -->
      <button class="btn btn-wide">View All</button>
    </div>
  </div>
</template>

<script>
import PropertyCard from "./PropertyCard.vue";

export default {
  name: "Featured",
  components: {PropertyCard}
}
</script>

<style scoped>

</style>