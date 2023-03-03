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
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "Home",
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
    components: {Hero, Featured, Footer}
}
</script>

<style scoped>

</style>