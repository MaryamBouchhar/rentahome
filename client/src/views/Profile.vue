<template>
  <div class="hero h-[240px]" style="background-image: url('../../../src/assets/hero_img.jpeg')">
    <div class="hero-overlay bg-opacity-60"></div>
    <div class="hero-content text-center text-neutral-content">
      <div class="max-w-md">
        <h1 class="mb-5 text-5xl font-bold">{{ $t('profile.my_profile') }}</h1>
        <div class="flex justify-center space-x-2 mb-5">
          <div class="badge badge-outline">{{ $t('tags.most_trusted') }}</div>
          <div class="badge badge-outline">{{ $t('tags.best_prices') }}</div>
          <div class="badge badge-outline">{{ $t('tags.modern_design') }}</div>
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
  <form action="">
    <div class="container my-20 mx-auto px-20 grid grid-cols-1 md:grid-cols-2 gap-4 md:gap-8 lg:gap-6">
      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg font-bold text-lg">{{ $t('profile.first_name') }}</span>
        </label>
        <input type="text" :placeholder="$t('profile.placeholder.first_name')" :value="$t('profile.placeholder.first_name')"
               class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.last_name') }}</span>
        </label>
        <input type="text" :placeholder="$t('profile.placeholder.last_name')" :value="$t('profile.placeholder.last_name')"
               class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.email') }}</span>
        </label>
        <input type="text" :placeholder="$t('profile.placeholder.email')" :value="$t('profile.placeholder.email')"
               class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.phone') }}</span>
        </label>
        <input type="text" :placeholder="$t('profile.placeholder.phone')" :value="$t('profile.placeholder.phone')"
               class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.old_password') }}</span>
        </label>
        <input type="password" :placeholder="$t('profile.placeholder.old_password')" class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.new_password') }}</span>
        </label>
        <input type="password" :placeholder="$t('profile.placeholder.new_password')" class="input input-primary input-bordered w-full"/>
      </div>

      <div class="form-control w-full flex flex-col content-center items-center">
        <label class="label">
          <span class="label-text font-bold text-lg">{{ $t('profile.confirm_password') }}</span>
        </label>
        <input type="password" :placeholder="$t('profile.placeholder.confirm_password')" class="input input-primary input-bordered w-full"/>
      </div>

      <button class="btn btn-wide w-full col-span-full">{{ $t('profile.update') }}</button>
    </div>
  </form>
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