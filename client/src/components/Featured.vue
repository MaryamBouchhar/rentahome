<template>
  <div class="hero bg-base-200">
    <div class="hero-content flex flex-col justify-center">
      <div class="max-w-md text-center">
        <h1 class="text-3xl font-bold">Recent Properties</h1>
        <p class="py-6">Check out our latest properties for sale and rent today.</p>
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
import axios from "axios";

export default {
  name: "Featured",
  components: {PropertyCard},
  data() {
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
  },
  methods: {
    async getProperties() {
      await axios.get(import.meta.env.VITE_API_URL + '/manage-properties/recent-properties')
          .then(response => {
            this.properties = response.data
            //add new attribute to each property (title: category + city)
            for (let i = 0; i < this.properties.length; i++) {
              this.properties[i].title = this.properties[i].category + ' in ' + this.properties[i].location.city
            }
            console.log("Properties: ", this.properties)
          })
          .catch(error => {
            console.log(error)
          })
    },
    async getPropertyImage() {
      //get each property's image
      for (let i = 0; i < this.properties.length; i++) {
        await axios.get(import.meta.env.VITE_API_URL + '/uploads/properties/' + this.properties[i].id + '/first', {responseType: 'arraybuffer'})
            .then(response => {
              console.log("Property Image: ", response.data)
              const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
              this.properties[i].image = URL.createObjectURL(imageBlob)
            })
            .catch(error => {
              console.log(error)
            })
      }
    }
  },
  mounted() {
    this.getProperties()
    this.getPropertyImage()
  }
}
</script>

<style scoped>

</style>