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

</script>

<style>

.navbar {
  width: 100%;
  background-color: #1a1a1a;
  color: white;
  padding: 1rem 2rem;
  box-shadow: 0 2px 6px rgba(0,0,0,0.2);
  position: sticky;
  top: 0;
  z-index: 1000;
}

.navbar-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1280px;
  margin: 0 auto;
}

.logo a {
  font-size: 1.5rem;
  font-weight: bold;
  color: white;
  text-decoration: none;
}

.nav-links {
  list-style: none;
  display: flex;
  gap: 1.5rem;
  margin: 0;
  padding: 0;
}

.nav-links li a {
  color: white;
  text-decoration: none;
  font-weight: 500;
  transition: color 0.2s;
}

.nav-links li a:hover {
  color: #ffbc0d;
}

@media (max-width: 600px) {
  .nav-links {
    display: none;
  }
}

</style>
