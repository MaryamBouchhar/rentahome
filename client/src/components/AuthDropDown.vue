<template>
  <div class="dropdown dropdown-end">
    <label tabindex="0" class="btn btn-ghost avatar">
      <i class='bx bx-user bx-sm'></i>
    </label>
    <ul tabindex="0" class="mt-3 p-2 shadow menu menu-compact dropdown-content bg-base-100 rounded-box w-52">
      <li>
        <router-link to="/login" class="flex content-start gap-2">
          <i class='bx bx-user bx-sm'></i>
          Login
        </router-link>
      </li>
      <li>
        <router-link to="/register" class="flex content-start gap-2">
          <i class='bx bx-user-plus bx-sm'></i>
          Register
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "AuthDropDown"
}
</script>

<style scoped>

</style>