<template>
  <div class="hero h-[240px]" style="background-image: url('../../../src/assets/hero_img.jpeg')">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
      <div class="max-w-md">
        <h1 class="mb-5 text-5xl font-bold">My Profile</h1>
        <div class="flex justify-center space-x-2 mb-5">
          <div class="badge badge-outline">Most trusted</div>
          <div class="badge badge-outline">Best prices</div>
          <div class="badge badge-outline">Modern design</div>
        </div>
        <div class="avatar online">
          <div class="w-24 rounded-full ring ring-primary ring-offset-base-100 ring-offset-2">
            <img src="https://i.pravatar.cc/300"/>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- details -->
  <div class="container mt-20 mx-auto grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-8 lg:gap-12">
    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">First Name</span>
      </label>
      <input type="text" placeholder="Jhon Smilga" value="Jhon Smilga" class="input input-bordered w-full"/>
    </div>

    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">Last Name</span>
      </label>
      <input type="text" placeholder="Jhon Smilga" value="Jhon Smilga" class="input input-bordered w-full"/>
    </div>

    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">Email</span>
      </label>
      <input type="text" placeholder="Jhon Smilga" value="Jhon Smilga" class="input input-bordered w-full"/>
    </div>

    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">Phone</span>
      </label>
      <input type="text" placeholder="Jhon Smilga" value="Jhon Smilga" class="input input-bordered w-full"/>
    </div>

    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">Old password</span>
      </label>
      <input type="password" placeholder="Old password" class="input input-bordered w-full"/>
    </div>

    <div class="form-control w-full flex flex-col content-center items-center">
      <label class="label">
        <span class="label-text">New password</span>
      </label>
      <input type="password" placeholder="New password" class="input input-bordered w-full"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Profile"
}
</script>

<style lang="css" scoped>
.hero {
  position: relative;
}

.avatar {
  position: absolute;
  bottom: -3rem;
  left: 50%;
  transform: translateX(-50%);
}
</style>