<template>
  <div>
    <div class="border-b border-t p-5">
      <h1 class="text-2xl font-bold text-center">
        {{ $t('wishlist.wishlist') }}
      </h1>
      <p class="text-center text-gray-500">
        Here you can find all the properties you have added to your wishlist.
      </p>
    </div>
  </div>

  <div v-if="isLoaded" class="flex flex-1">
    <div :class="rtl ? 'flex-row-reverse' : 'flex-row'"
         class="flex grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 p-5">
      <PropertyCard
          v-for="property in properties"
          :key="property.id"
          :property="property"
      />
    </div>
  </div>
  <div v-else>
    <div class="flex flex-1 justify-center items-center">
      <div class="loader ease-linear rounded-full border-4 border-t-4 border-gray-200 h-12 w-12 mb-4"></div>
    </div>
  </div>
  <Footer/>
</template>

<script>

import {useStore} from 'vuex';
import {computed} from "vue";
import PropertyCard from "../components/PropertyCard.vue";
import Footer from "../components/Footer.vue";
import axios from "axios";

export default {
  name: 'Wishlist',
  components: {
    PropertyCard,
    Footer
  },
  setup() {
    const store = useStore();

    return {
      rtl: computed(() => store.state.rtl),
      user: computed(() => store.state.user)
    }
  },
  data() {
    return {
      selectedCategory: null,
      properties: [],
      isLoaded: false
    }
  },
  methods: {
    async getProperties() {
      axios.get(`http://localhost:8080/api/wishlist/${this.user.id}`)
          .then(response => {
            this.properties = response.data
            //get each property's image
            this.getPropertyImage()
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
    },
  },
  mounted() {
    this.getProperties();
    this.isLoaded = true;
  }
}
</script>

<style scoped>
.overflow-x-scroll::-webkit-scrollbar {
  width: 5px;
  height: 5px;
}

.overflow-x-scroll::-webkit-scrollbar-thumb {
  @apply bg-primary;
}
</style>
