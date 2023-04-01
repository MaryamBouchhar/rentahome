<template>
  <div
      class="card w-full bg-base-100 shadow-xl">
    <figure class="relative h-52">
      <router-link :key="property.id" :to="'/properties/' + property.id">
        <img :src="property.image" :alt="property.category" class="card-image object-cover w-full h-full"/>
      </router-link>
    </figure>
    <div class="card-body">
      <div class="flex justify-between">
        <h2 class="card-title">
          <router-link :key="property.id" :to="'/properties/' + property.id">
            {{ property.category }} in {{ property.location.city }}
          </router-link>
          <div v-if="property.status === 'Available'" class="badge badge-success">{{ property.status }}</div>
          <div v-else class="badge badge-error">{{ property.status }}</div>
        </h2>
      </div>
      <h2 class="font-bold text-xl text-green-400">${{ property.price }}</h2>
      <p>{{ property.description }}</p>
      <div class="card-actions justify-end">
        <div class="badge badge-outline">{{ property.category }}</div>
      </div>
      <div class="flex flex-row justify-between">
        <div class="rating rating-sm">
          <input class="mask mask-star-2 bg-orange-400" v-for="star in property.rating" :key="star"/>
        </div>
        <span class="wishlist" @click="addItemToWishlist(property.id)">
          <i :class="{ 'bx bxs-heart bx-sm': is_favorite, 'bx bx-heart bx-sm': !is_favorite }"></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import 'boxicons'
import axios from "axios";
import {useStore} from "vuex";
import {computed} from "vue";

export default {
  name: "PropertyCard",
  props: {
    property: {
      type: Object,
      required: true
    },

  },
  setup() {
    const store = useStore();

    return {
      user: computed(() => store.state.user)
    }
  },
  data() {
    return {
      WishList_API_BASE_URL: "http://localhost:8080/api/wishlist/",
      is_favorite: false
    }
  },
  methods: {
    toggleWishlist() {
      this.is_favorite = !this.is_favorite
    },
    async addItemToWishlist(propertyId) {
      this.toggleWishlist()
      axios.post(this.WishList_API_BASE_URL, {
        propertyId: propertyId,
        userId: this.user.id
      })
          .then(response => {
            console.log(response.data())
            this.is_favorite = true
          })
          .catch(error => {
            console.log(error)
          })


    }
  }
}
</script>

<style scoped>
.wishlist i:hover {
  cursor: pointer;
}

.wishlist i {
  color: red;
}

.card:hover {
  transform: scale(1.01);
  transition: all 0.3s ease-in-out;
}

.card-image {
  transition: all 0.3s ease-in-out;
}

.card-image:hover {
  cursor: pointer;
  scale: 1.1;
}
</style>