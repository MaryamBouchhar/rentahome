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
      ],
      property_categories: [],
      cities: []
    }
  },
  methods: {
    async getProperties() {
      await axios.get('http://localhost:8080/manage-properties/recent-properties')
          .then(response => {
            this.properties = response.data
            //add new attribute to each property (title: category + city)
            for (let i = 0; i < this.properties.length; i++) {
              this.properties[i].title = this.properties[i].category + ' in ' + this.properties[i].location.city
            }
            //get each property's image
            this.getPropertyImage()
            console.log("Properties: ", this.properties)
          })
          .catch(error => {
            console.log(error)
          })
    },
    async getPropertyImage() {
      //get each property's image
      for (let i = 0; i < this.properties.length; i++) {
        await axios.get('http://localhost:8080/uploads/properties/' + this.properties[i].id + '/first', {responseType: 'arraybuffer'})
            .then(response => {
              console.log("Property Image: ", response.data)
              const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
              this.properties[i].image = URL.createObjectURL(imageBlob)
            })
            .catch(error => {
              console.log(error)
            })
      }
      console.log("Properties: ", this.properties)
    }
  },
  mounted() {
    this.getProperties()
  }
}
</script>

<style scoped>

</style>