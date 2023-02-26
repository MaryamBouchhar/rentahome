<template>
  <div>
    <Hero/>
    <Featured/>
    <Footer/>
  </div>
</template>

<script>
import Hero from "../components/client/Hero.vue";
import Featured from "../components/client/Featured.vue";
import Footer from "../components/client/Footer.vue";

export default {
  name: "Home",
  components: {Hero, Featured, Footer}
}
</script>

<style scoped>

</style>