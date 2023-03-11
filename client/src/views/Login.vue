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
                        <!-- error alert -->
                        <div v-show="message"
                             class="alert alert-error shadow-lg my-2"
                        >
                            <div>
                                <svg @click="$store.commit('setAuthError', null)"
                                    xmlns="http://www.w3.org/2000/svg" class="stroke-current flex-shrink-0 h-6 w-6"
                                     fill="none" viewBox="0 0 24 24">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z"/>
                                </svg>
                                <span>{{ message }}</span>
                            </div>
                        </div>
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
                            <button class="btn btn-primary" @click.prevent="login">Login</button>
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

import {useStore} from 'vuex';
import {computed} from 'vue';

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
            rtl: computed(() => store.state.rtl),
            message: computed(() => store.state.auth_error)
        }
    },
    methods: {
        login() {
            this.$store.dispatch('login', {
                email: this.email,
                password: this.password
            })
        }
    },
}
</script>

<style scoped>

</style>