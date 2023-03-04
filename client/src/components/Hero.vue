<template>
  <swiper
      class="swiper hero h-[360px]"
      :modules="modules"
      :space-between="30"
      :centered-slides="true"
      :navigation="true"
      :pagination="{
      clickable: true
    }"
      :autoplay="{
      delay: 1500,
      disableOnInteraction: false
    }"
  >
    <swiper-slide class="slide" v-for="image in images" :key="image">
      <div class="hero-overlay bg-opacity-60 absolute inset-0"></div>
      <img :src="image" alt="hero image" class="w-full h-full object-cover">
    </swiper-slide>

    <!-- content -->
    <div class="hero-content text-center text-neutral-content absolute inset-0 flex justify-center items-center">
      <div class="max-w-md">
        <h1 class="mb-5 text-5xl font-bold">You can trust us</h1>
        <p class="mb-5 text-4xl">with your choice of housing</p>
        <div class="flex justify-center space-x-2 mb-5">
          <div class="badge badge-outline">Most trusted</div>
          <div class="badge badge-outline">Best prices</div>
          <div class="badge badge-outline">Modern design</div>
        </div>
        <router-link to="/properties">
          <button class="btn btn-primary">start browsing</button>
        </router-link>
      </div>
    </div>
  </swiper>
</template>


<script lang="js">
import {defineComponent} from 'vue'
import {Pagination, Navigation, Autoplay} from 'swiper'
import {Swiper, SwiperSlide} from 'swiper/vue'
import 'swiper/css'
import 'swiper/css/pagination'
import 'swiper/css/navigation'

export default defineComponent({
  name: 'swiper-example-autoplay',
  title: 'Autoplay',
  url: import.meta.url,
  components: {
    Swiper,
    SwiperSlide
  },
  setup() {
    return {
      modules: [Pagination, Navigation, Autoplay]
    }
  },
  data() {
    return {
      images: [
        'https://source.unsplash.com/random?sig=1',
        'https://source.unsplash.com/random?sig=2',
        'https://source.unsplash.com/random?sig=3',
        'https://source.unsplash.com/random?sig=4',
      ]
    }
  }
})
</script>

<style scoped>
.swiper {
  position: relative;
}

.hero-overlay {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.8);
}

.hero-content {
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

@media (max-width: 712px) {
  .hero-content {
    display: none;
  }
}
</style>