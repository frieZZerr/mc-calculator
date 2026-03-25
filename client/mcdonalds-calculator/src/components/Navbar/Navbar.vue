<template>

  <nav class="navbar">
    <div class="navbar-container">

      <div class="logo">
        <router-link to="/">McCalculator</router-link>
      </div>

      <ul class="nav-links">
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/calculator">Calculator</router-link></li>
      </ul>

    </div>
  </nav>

</template>

<script setup>

  import "./Navbar.css"

</script>
