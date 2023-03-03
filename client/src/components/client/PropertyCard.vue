<template>
  <div class="card hover:cursor-pointer w-96 bg-base-100 shadow-xl w-full">
    <figure>
      <img src="../../../src/assets/hero_img.jpeg" alt="Shoes"/>
    </figure>
    <div class="card-body">
      <div class="flex justify-between">
        <h2 class="card-title">
          Shoes!
          <div class="badge badge-secondary">NEW</div>
        </h2>
        <h2 class="font-bold text-xl text-green-400">1220$</h2>
      </div>
      <p>If a dog chews shoes whose shoes does he choose?</p>
      <div class="card-actions justify-end">
        <div class="badge badge-outline">Fashion</div>
        <div class="badge badge-outline">Products</div>
      </div>
      <div class="flex flex-row justify-between">
        <div class="rating rating-sm">
          <input class="mask mask-star-2 bg-orange-400"/>
          <input class="mask mask-star-2 bg-orange-400"/>
          <input class="mask mask-star-2 bg-orange-400"/>
          <input class="mask mask-star-2 bg-orange-400"/>
          <input class="mask mask-star-2 bg-orange-400"/>
        </div>
        <span class="wishlist">
          <i class='bx bxs-heart bx-sm'></i>
        </span>
      </div>
    </div>
  </div>
</template>

<script>
import 'boxicons'

export default {
  name: "PropertyCard"
}
</script>

<style scoped>
.wishlist i:hover {
  cursor: pointer;
}

.wishlist i {
  color: red;
}
</style>