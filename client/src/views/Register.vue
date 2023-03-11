<template>
    <div class="hero min-h-screen" style="background-image: url(../../src/assets/images/hero_img_1.jpg);">
        <div class="hero-overlay bg-opacity-60"></div>
        <div class="hero-content text-center text-neutral-content">
            <div class="card flex-shrink-0 w-[460px] shadow-2xl bg-base-100">
                <h1 class="text-3xl font-bold text-primary mt-4">
                    REG<span class="text-secondary">ISTER</span>
                </h1>
                <div class="h-1 w-20 bg-primary rounded-full mx-auto my-1"></div>
                <form action="" method="post">
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
                                <span class="label-text">Full name</span>
                            </label>
                            <input
                                type="text"
                                placeholder="Full name"
                                class="input input-bordered"
                                v-model="name"
                            />
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
                                <span class="label-text">Phone</span>
                            </label>
                            <input
                                type="tel"
                                placeholder="Phone"
                                class="input input-bordered"
                                v-model="phone"
                            />
                        </div>
                        <div class="form-control grid grid-cols-2 gap-2">
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
                            </div>
                            <div class="form-control">
                                <label class="label">
                                    <span class="label-text">Confirm password</span>
                                </label>
                                <input
                                    type="password"
                                    placeholder="Confirm password"
                                    class="input input-bordered"
                                    v-model="confirmPassword"
                                />
                            </div>
                        </div>
                        <div class="form-control mt-4">
                            <button class="btn btn-primary" @click.prevent="register">Register</button>
                        </div>
                        <label class="label">
              <span class="label-text">
                Already have an account? <router-link to="/login" class="text-primary">Login</router-link>
              </span>
                        </label>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import {useStore} from "vuex";
import {computed} from "vue";

export default {
    name: "Register",
    data() {
        return {
            name: "",
            email: "",
            phone: "",
            password: "",
            confirmPassword: ""
        };
    },
    setup() {
        const store = useStore();

        return {
            rtl: computed(() => store.state.rtl),
            message: computed(() => store.state.auth_error)
        }
    },
    methods: {
        register() {
            if (this.validateForm()) {
                this.$store.dispatch("register", {
                    name: this.name,
                    email: this.email,
                    password: this.password
                });
            }
        },
        validateForm() {
            if (this.name === "") {
                this.$store.commit("setAuthError", "Name is required");
                return false;
            }
            if (this.email === "") {
                this.$store.commit("setAuthError", "Email is required");
                return false;
            }
            if (this.phone === "") {
                this.$store.commit("setAuthError", "Phone is required");
                return false;
            }
            if (this.password === "") {
                this.$store.commit("setAuthError", "Password is required");
                return false;
            }
            if (this.password !== this.confirmPassword) {
                this.$store.commit("setAuthError", "Passwords do not match");
                return false;
            }
            return true;
        }
    },
}
</script>

<style scoped>

</style>