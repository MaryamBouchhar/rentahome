<template>
  <div>
    <Hero/>
    <Featured/>
  </div>
</template>

<script>
import Hero from "../components/client/Hero.vue";
import Featured from "../components/client/Featured.vue";

export default {
  name: "Home",
  components: {Hero, Featured}
}
</script>

<style scoped>

</style>