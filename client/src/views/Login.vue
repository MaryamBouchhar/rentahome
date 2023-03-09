<template>
    <div class="hero min-h-screen" style="background-image: url(../../src/assets/images/hero_img_1.jpg);">
        <div class="hero-overlay bg-opacity-60"></div>
        <div class="hero-content text-center text-neutral-content">
            <div class="card flex-shrink-0 w-[460px] shadow-2xl bg-base-100">
                <h1 class="text-3xl font-bold text-primary mt-4">
                    LOG<span class="text-secondary">IN</span>
                </h1>
                <div class="h-1 w-20 bg-primary rounded-full mx-auto my-1"></div>
                <form action="">
                    <div class="card-body">
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Email</span>
                            </label>
                            <input
                                type="email"
                                placeholder="Email"
                                class="input input-bordered"
                                v-model="email"
                            />
                        </div>
                        <div class="form-control">
                            <label class="label">
                                <span class="label-text">Password</span>
                            </label>
                            <input
                                type="password"
                                placeholder="Password"
                                class="input input-bordered"
                                v-model="password"
                            />
                            <label class="label">
                                <span class="label-text">
                                    <router-link to="/forgot-password"
                                                 class="text-primary">Forgot password?</router-link>
                                </span>
                            </label>
                        </div>
                        <div class="form-control mt-4">
                            <button class="btn btn-primary" type="submit">Login</button>
                        </div>
                        <label class="label">
              <span class="label-text">
                Don't have an account? <router-link to="/register" class="text-primary">Register</router-link>
              </span>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

import { useStore } from 'vuex';
import { computed } from 'vue';

export default {
    name: "Login",
    data() {
        return {
            email: '',
            password: ''
        }
    },
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl)
        }
    },
    methods: {
        login() {
            this.$store.dispatch('login', {
                email: this.email,
                password: this.password
            }).then(() => {
                this.$router.push('/profile');
            }).catch((error) => {
                console.log(error);
            });
        }
    }
}
</script>

<style scoped>

</style>