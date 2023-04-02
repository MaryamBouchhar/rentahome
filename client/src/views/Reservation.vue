<template>
    <div class="hero min-h-screen" style="background-image: url(../../src/assets/images/hero_img_1.jpg);">
        <div class="hero-overlay bg-opacity-60"></div>
        <div class="hero-content text-center text-neutral-content">
            <div class="card flex-shrink-0 w-[460px] shadow-2xl bg-base-100">
                <h1 class="text-3xl font-bold text-primary mt-4">
                    RESRVA<span class="text-secondary">TION</span>
                </h1>
                <div class="h-1 w-20 bg-primary rounded-full mx-auto my-1"></div>
                <form action="">
                    <div class="card-body">
                        <p class="text-secondary text-md">Please fill in the form below to reserve the property.</p>
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Start Date</span>
                            </label>
                            <input
                                    type="date"
                                    placeholder="Start Date"
                                    class="input input-bordered"
                                    v-model="start_date"
                            />
                        </div>
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">End Date</span>
                            </label>
                            <input
                                    type="date"
                                    placeholder="End Date"
                                    class="input input-bordered"
                                    v-model="end_date"
                            />
                        </div>
                        <div class="form-control mt-4">
                            <button class="btn btn-primary" @click.prevent="reserve">Reserve</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

import {useStore} from 'vuex';
import {computed} from 'vue';
import axios from "axios";
import moment from "moment";
import swal from 'sweetalert';

export default {
    name: "Login",
    data() {
        return {
            start_date: '',
            end_date: '',
            property: {}
        }
    },
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl),
            message: computed(() => store.state.auth_error),
            user: computed(() => store.state.user)
        }
    },
    methods: {
        async reserve() {
            const start_date_str = moment(this.start_date).format("M/D/YYYY, h:mm:ss a");
            const end_date_str = moment(this.end_date).format("M/D/YYYY, h:mm:ss a");

            const data = {
                client: this.user,
                status: 'Pending',
                id_property: this.$route.params.id,
                start_date: start_date_str,
                end_date: end_date_str,
                created_at: moment().format("M/D/YYYY, h:mm:ss a"),
                updated_at: moment().format("M/D/YYYY, h:mm:ss a")
            }
            await axios.post('http://localhost:8080/manage-reservation/add-reservation', data)
                .then(response => {
                    console.log(response.data)
                    swal({
                        title: "Reservation",
                        text: "Reservation has been made successfully!",
                        icon: "success",
                        button: "OK",
                    }).then(() => {
                        this.$router.push({name: 'Home'})
                    })
                })
                .catch(error => console.log(error))
        },
        async getProperty() {
            await axios.get('http://localhost:8080/manage-properties/properties/' + this.$route.params.id)
                .then(response => {
                    this.property = response.data
                })
                .catch(error => console.log(error))
        }
    },
    mounted() {
    }
}
</script>

<style scoped>

</style>