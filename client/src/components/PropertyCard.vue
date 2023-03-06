<template>
  <div
      class="card w-full bg-base-100 shadow-xl">
    <figure class="relative h-52">
      <img :src="property.image" :alt="property.title" class="card-image object-cover w-full h-full"/>
    </figure>
    <div class="card-body">
      <div class="flex justify-between">
        <h2 class="card-title">
          {{ property.title }}
          <div class="badge badge-secondary"> {{ property.status }}</div>
        </h2>
      </div>
      <h2 class="font-bold text-xl text-green-400">${{ property.price }}</h2>
      <p>{{ property.description }}</p>
      <div class="card-actions justify-end">
        <div class="badge badge-outline" v-for="category in property.categories" :key="category">
          {{ category }}
        </div>
      </div>
      <div class="flex flex-row justify-between">
        <div class="rating rating-sm">
          <input class="mask mask-star-2 bg-orange-400" v-for="star in property.rating" :key="star"/>
        </div>
        <span class="wishlist" @click="toggleWishlist">
          <i class='bx bxs-heart bx-sm'></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import 'boxicons'

export default {
  name: "PropertyCard",
  props: {
    property: {
      type: Object,
      required: true
    }
  },
  setup() {
    const toggleWishlist = () => {
      const wishlist = document.querySelector('.wishlist i')
      wishlist.classList.toggle('bx-heart')
    }

    return {
      toggleWishlist
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