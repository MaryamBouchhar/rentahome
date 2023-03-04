<template>

</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "NotFound",
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
}
</script>

<style scoped>

</style>