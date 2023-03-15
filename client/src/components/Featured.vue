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


      },

    },
    mounted() {
      this.getProperties();

    }}
</script>




 
  