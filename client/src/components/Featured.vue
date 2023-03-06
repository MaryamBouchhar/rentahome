<template>
  <div class="hero bg-base-200">
    <div class="hero-content flex flex-col justify-center">
      <div class="max-w-md text-center">
        <h1 class="text-3xl font-bold">Featured Properties</h1>
        <p class="py-6">Check out our featured properties and find your dream home today!</p>
      </div>
      <!-- Property Cards -->
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        <router-link v-for="property in properties" :key="property.id" :to="'/properties/' + property.id">
          <PropertyCard :property="property"/>
        </router-link>
      </div>

      <!-- view all button -->
      <router-link to="/properties">
        <button class="btn btn-wide">View All</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import PropertyCard from "./PropertyCard.vue";

export default {
  name: "Featured",
  components: {PropertyCard},
  data() {
    return {
      properties: [
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
      ],
      property_categories: [],
      cities: []
    }
  },
}
</script>

<style scoped>

</style>