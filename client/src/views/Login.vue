<template>
  <div class="hero min-h-screen" style="background-image: url(../../src/assets/images/hero_img_1.jpg);">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
      <div class="card flex-shrink-0 w-[460px] shadow-2xl bg-base-100">
        <h1 class="text-center font-bold text-3xl py-2">Create account</h1>
        <div class="card-body">
          <div class="form-control">
            <label class="label">
              <span class="label-text">Full name</span>
            </label>
            <input type="text" placeholder="Full name" class="input input-bordered"/>
          </div>
          <div class="form-control">
            <label class="label">
              <span class="label-text">Email</span>
            </label>
            <input type="email" placeholder="email" class="input input-bordered"/>
          </div>
          <div class="form-control grid grid-cols-2 gap-2">
            <div class="form-control">
              <label class="label">
                <span class="label-text">Password</span>
              </label>
              <input type="password" placeholder="password" class="input input-bordered"/>
            </div>
            <div class="form-control">
              <label class="label">
                <span class="label-text">Confirm password</span>
              </label>
              <input type="password" placeholder="Confirm password" class="input input-bordered"/>
            </div>
          </div>
          <div class="form-control mt-6">
            <button class="btn btn-primary">Register</button>
          </div>
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