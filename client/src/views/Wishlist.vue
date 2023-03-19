<template>
  <div>
    <div class="border-b border-t p-5">
      <h1
          class="text-2xl font-bold text-center"
      >
        {{ $t('wishlist.wishlist') }}
      </h1>
    </div>
    <!-- create a horizontal scroller -->
    <div :class="rtl ? 'flex-row-reverse' : 'flex-row'"
         class="flex overflow-x-scroll scrollbar-hide shadow-md p-2 mt-2 bg-base-100"
    >
      <div
          class="cursor-pointer m-1"
          @click="selectCategory(null)"
      >
                <span
                    class="badge badge-primary badge-lg w-fit"
                    :class="{'btn-outline': selectedCategory !== null}"
                >
                    {{ $t('wishlist.all') }}
                </span>
      </div>
      <!-- categoies badge -->
      <div
          class="cursor-pointer m-1"
          v-for="category in categories"
          :key="category.id"
          @click="selectCategory(category)"
      >
                    <span
                        class="badge badge-primary badge-lg w-fit"
                        :class="{'btn-outline': selectedCategory !== category.id}"
                    >
                        {{ category.name }}
                    </span>
      </div>
    </div>
  </div>

  <div
      v-if="isLoaded"
      class="flex flex-1"
  >
    <div
        :class="rtl ? 'flex-row-reverse' : 'flex-row'"
        class="flex grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 p-5"
    >
      <PropertyCard
          v-for="item in wishlistItems"
          :key="item"
          :property="item.property"
      />
    </div>
  </div>
  <div
      v-else
  >
    <div class="flex flex-1 justify-center items-center">
      <div class="loader ease-linear rounded-full border-4 border-t-4 border-gray-200 h-12 w-12 mb-4"></div>
    </div>
  </div>
  <Footer/>
</template>

<script>

import {useStore} from 'vuex';
import {computed} from "vue";
import Footer from "../components/Footer.vue";
import axios from "axios";
import PropertyCard from "../components/PropertyCard.vue";

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
      categories: [
        {
          id: 1,
          category: 'Appartements',
          description: 'Lorem ipsum dolore...',
          price: 120,
          area: 120,
          status: 'rented',

        }
      ],
      selectedCategory: null,
      wishlistItems: [],
      isLoaded: false
    }
  },
  methods: {
    // selectCategory(category) {
    //   this.isLoaded = false;
    //   this.selectedCategory = category ? category.id : null;
    //   this.getProperties();
    // },
    async getPropertyImage() {
      //get each property's image
      for (let i = 0; i < this.wishlistItems.length; i++) {
        await axios.get('http://localhost:8080/uploads/properties/' + this.wishlistItems.property[i].id + '/first', {responseType: 'arraybuffer'})
            .then(response => {
              console.log("Property Image: ", response.data)
              const imageBlob = new Blob([response.data], {type: 'image/jpeg'});
              this.wishlistItems.property[i].image = URL.createObjectURL(imageBlob)
            })
            .catch(error => {
              console.log(error)
            })
      }
    },
    async getProperties() {

      axios.get(`http://localhost:8080/api/wishlist/${this.user.id}`)
          .then(response => {
            this.wishlistItems = response.data
            this.getPropertyImage()
            console.log(this.wishlistItems)
          })
          .catch(error => {
            console.log(error)
          })
      // if (this.selectedCategory === null) {
      //   let props = this.categories.map(category => category.properties).flat();
      //   this.isLoaded = true;
      //   this.properties = props;
      // } else {
      //   let props = this.categories.find(category => category.id === this.selectedCategory).properties;
      //   this.isLoaded = true;
      //   this.properties = props;
      // }
    }
  },
  mounted() {
    // console.log(this.categories);
    // console.log(this.selectedCategory);
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
