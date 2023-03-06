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
          <router-link v-for="property in properties" :key="property.id"
                       :to="{name: 'PropertyDetails', params: {id: property.id}}">
            <PropertyCard :property="property"/>
          </router-link>
        </div>
      </div>
    </div>
  </div>
  <Footer/>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import 'boxicons'
import PropertyCard from "../components/PropertyCard.vue";
import Footer from "../components/Footer.vue";

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

const properties = ref([
  {
    id: 1,
    title: 'House in Toronto',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    categories: ['House', 'Toronto'],
    city: 'Toronto',
    image: 'https://picsum.photos/500/300?random=1',
  },
  {
    id: 2,
    title: 'Apartment in Ottawa',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    categories: ['Apartment', 'Ottawa'],
    city: 'Ottawa',
    image: 'https://picsum.photos/500/300?random=2',
  },
  {
    id: 3,
    title: 'Condo in Montreal',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    categories: ['Condo', 'Montreal'],
    city: 'Montreal',
    image: 'https://picsum.photos/500/300?random=3',
  },
  {
    id: 4,
    title: 'Townhouse in Vancouver',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    categories: ['Townhouse', 'Vancouver'],
    city: 'Vancouver',
    image: 'https://picsum.photos/500/300?random=4',
  },
  {
    id: 5,
    title: 'Villa in Calgary',
    price: 1000,
    rating: 2,
    status: 'For Rent',
    categories: ['Villa', 'Calgary'],
    city: 'Calgary',
    image: 'https://picsum.photos/500/300?random=5',
  },
  {
    id: 6,
    title: 'Bungalow in Edmonton',
    price: 1000,
    rating: 4,
    status: 'For Rent',
    categories: ['Bungalow', 'Edmonton'],
    city: 'Edmonton',
    image: 'https://picsum.photos/500/300?random=6',
  },
]);
</script>

<style scoped>

</style>