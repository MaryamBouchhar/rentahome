<template>
  <div>
    <h1 class="w-full text-center font-bold text-2xl my-5">
      Properties
    </h1>
    <!-- TODO: Add filter options in sidebar -->
    <div class="flex flex-col lg:flex-row">
      <div class="filter-bar w-full lg:w-1/4 px-4">
        <!-- Search -->
        <div class="form-control mb-2">
          <div class="input-group">
            <input type="text" placeholder="Search…" class="input input-bordered"/>
            <button class="btn btn-square">
              <i class='bx bx-search bx-sm'></i>
            </button>
          </div>
        </div>

        <!-- Filter by type -->
        <label class="label font-bold">
          <span class="label-text">Filter by category</span>
        </label>
        <select class="select select-primary w-full max-w-xs">
          <option disabled selected>Filter by category</option>
          <option v-for="category in property_categories" :key="category">{{ category }}</option>
        </select>
        <!-- Filter by City -->
        <label class="label font-bold">
          <span class="label-text">Filter by city</span>
        </label>
        <select class="select select-primary w-full max-w-xs">
          <option disabled selected>Filter by city</option>
          <option v-for="city in cities" :key="city">{{ city }}</option>
        </select>

        <!-- Filter by price -->
        <label class="label font-bold">
          <span class="label-text">Filter by price</span>
        </label>
        <div class="flex justify-between gap-2">
          <input type="number" placeholder="Min" min="1" class="input input-bordered w-1/2"/>
          <input type="number" placeholder="Max" min="1" class="input input-bordered w-1/2"/>
        </div>

        <!-- Filter by rating -->
        <label class="label font-bold">
          <span class="label-text">Filter by rating</span>
        </label>
        <div class="rating">
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400" checked/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
          <input type="radio" name="rating-2" class="mask mask-star-2 bg-orange-400"/>
        </div>
      </div>
      <div class="flex flex-col w-full content-center mx-4">
        <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-2 flex justify-center mx-auto">
          <PropertyCard/>
          <PropertyCard/>
          <PropertyCard/>
          <PropertyCard/>
          <PropertyCard/>
          <PropertyCard/>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import 'boxicons'
import PropertyCard from "../components/client/PropertyCard.vue";

const property_categories = ref([
  'House',
  'Apartment',
  'Condo',
  'Townhouse',
  'Villa',
  'Bungalow',
])

const cities = ref([
  'Toronto',
  'Ottawa',
  'Montreal',
  'Vancouver',
  'Calgary',
  'Edmonton',
  'Winnipeg',
])
</script>

<style scoped>

</style>