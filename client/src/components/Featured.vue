<template>
  <div class="hero bg-base-200">
    <div class="hero-content flex flex-col justify-center">
      <div class="max-w-md text-center">
        <h1 class="text-3xl font-bold">Featured Properties</h1>
        <p class="py-6">Check out our featured properties and find your dream home today!</p>
      </div>
      <!-- Property Cards -->
      <div class="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        <PropertyCard v-for="property in properties" :key="property.id" :property="property"/>
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
import axios from 'axios';
export default {
  name: "Featured",
  components: {PropertyCard},

    data() {
      return {
        properties: [],
        selectedProperty:{

        },
        isModalActive:false,
        PROPERTY_API_BASE_URL: `http://localhost:8080/manage-properties/properties`,
      };

    },
    methods: {

      async getProperties() {
        await axios.get(this.PROPERTY_API_BASE_URL)
            .then(response => {
              this.properties = response.data
              this.properties.forEach(property => {
                property.publish_date = new Date(property.publish_date).toLocaleDateString();
              })
            })

            .catch(error => console.log(error))
        console.log(this.properties);


      }
    },
    mounted() {
      this.getProperties();

    }


  /*data() {
    return {
      properties: [
        {
          id: 1,
          title: 'House in Toronto',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 4,
          status: 'For Rent',
          category: 'House',
          city: 'Toronto',
          image: 'https://picsum.photos/500/300?random=1',
        },
        {
          id: 2,
          title: 'Apartment in Ottawa',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 4,
          status: 'For Rent',
          category: 'Apartment',
          city: 'Ottawa',
          image: 'https://picsum.photos/500/300?random=2',
        },
        {
          id: 3,
          title: 'Condo in Montreal',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 4,
          status: 'For Rent',
          category: 'Condo',
          city: 'Montreal',
          image: 'https://picsum.photos/500/300?random=3',
        },
        {
          id: 4,
          title: 'Townhouse in Vancouver',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 4,
          status: 'For Rent',
          category: 'Townhouse',
          city: 'Vancouver',
          image: 'https://picsum.photos/500/300?random=4',
        },
        {
          id: 5,
          title: 'Villa in Calgary',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 2,
          status: 'For Rent',
          category: 'Villa',
          city: 'Calgary',
          image: 'https://picsum.photos/500/300?random=5',
        },
        {
          id: 6,
          title: 'Bungalow in Edmonton',
          description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed euismod...',
          price: 1000,
          rating: 4,
          status: 'For Rent',
          category: 'Bungalow',
          city: 'Edmonton',
          image: 'https://picsum.photos/500/300?random=6',
        },
      ],
      property_categories: [],
      cities: []
    }
  },*/
}
</script>

<style scoped>

</style>