<template>
  <div class="hero h-[360px]" style="background-image: url('../../../src/assets/hero_img.jpeg')">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
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
  </div>
</template>

<script>
export default {
  name: "Hero",
  methods: {
    autoplay() {
      const images = [
        "../../../src/assets/hero_img.jpeg",
        "../../../src/assets/our_story.png",
      ];
      let i = 0;
      setInterval(() => {
        document.querySelector(".hero").style.backgroundImage = `url(${images[i]})`;
        i++;
        if (i >= images.length) {
          i = 0;
        }
      }, 2000);
    },
  },

  mounted() {
    this.autoplay();
  },
};
// make the hero autoplay images
</script>

<style scoped>
.hero{
  transition: background-image 1s ease-in-out;
}
</style>