<template>
  <div class="navbar bg-base-100 shadow-md">
    <div class="navbar-start">
      <div class="dropdown">
        <label tabindex="0" class="btn btn-ghost lg:hidden">
          <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h8m-8 6h16"/>
          </svg>
        </label>
        <ul tabindex="0" class="menu menu-compact dropdown-content mt-3 p-2 shadow bg-base-100 rounded-box w-52">
          <NavLink to="/" label="Home"/>
          <NavLink to="/about" label="About" class="my-1"/>
          <NavLink to="/skills" label="Skills" class="my-1"/>
          <NavLink to="/projects" label="Projects" class="my-1"/>
          <NavLink to="/services" label="Services" class="my-1"/>
          <NavLink to="/contact" label="Contact" class="my-1"/>
        </ul>
      </div>
      <router-link to="/">
        <a class="btn btn-ghost normal-case text-xl">
          Renta<span class="text-primary">Home</span>
        </a>
      </router-link>
    </div>
    <div class="navbar-center hidden lg:flex">
      <ul class="menu menu-horizontal p-0">
        <NavLink to="/" label="Home" class="mx-1"/>
        <NavLink to="/properties" label="Properties" class="mx-1"/>
        <NavLink to="/about" label="About" class="mx-1"/>
        <NavLink to="/contact" label="Contact" class="mx-1"/>
      </ul>
    </div>
    <div class="navbar-end">
      <div class="form-control">
        <input type="text" placeholder="Search..." class="input input-bordered"/>
      </div>
      <div class="dropdown dropdown-end ml-2">
        <label tabindex="0" class="btn btn-ghost btn-circle avatar">
          <div class="w-10 rounded-full">
            <img src="https://i.pravatar.cc/300"/>
          </div>
        </label>
        <ul tabindex="0" class="mt-3 p-2 shadow menu menu-compact dropdown-content bg-base-100 rounded-box w-52">
          <li><a>Profile</a></li>
          <li><a>Settings</a></li>
          <li><a>Logout</a></li>
        </ul>
      </div>
      <ThemeDropDown/>
    </div>
  </div>
</template>

<script>
import NavLink from "./NavLink.vue";
import ThemeDropDown from "./ThemeDropDown.vue";

export default {
  name: "NavBar",
  components: {ThemeDropDown, NavLink}
}
</script>

<style scoped>

</style>