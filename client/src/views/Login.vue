<template>
  <div class="hero min-h-screen" style="background-image: url(../../src/assets/images/hero_img_1.jpg);">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
      <div class="card flex-shrink-0 w-[460px] shadow-2xl bg-base-100">
        <div class="card-body">
          <div class="form-control">
            <label class="label">
              <span class="label-text">Email</span>
            </label>
            <input type="email" placeholder="email" class="input input-bordered"/>
          </div>
          <div class="form-control">
            <label class="label">
              <span class="label-text">Password</span>
            </label>
            <input type="password" placeholder="password" class="input input-bordered"/>
            <label class="label">
              <span class="label-text">
                <router-link to="/forgot-password" class="text-primary">Forgot password?</router-link>
              </span>
            </label>
          </div>
          <div class="form-control mt-6">
            <button class="btn btn-primary">Register</button>
          </div>
          <label class="label">
            <span class="label-text">
              Don't have an account? <router-link to="/register" class="text-primary">Register</router-link>
            </span>
          </label>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login"
}
</script>

<style scoped>

</style>