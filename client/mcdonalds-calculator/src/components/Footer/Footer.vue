<template>

  <footer class="footer">
    <div class="footer-content">

      <!-- Left -->
      <div class="footer-left">
        <h3>McCalculator</h3>
        <p>Track your McDonald's macros with precision.</p>
      </div>

      <!-- Center -->
      <div class="footer-links">
        <router-link to="/">Home</router-link>
        <router-link to="/calculator">Calculator</router-link>
      </div>

      <!-- Right -->
      <div class="footer-right">
        <p>© {{ year }} McCalculator</p>
      </div>

    </div>
  </footer>

</template>

<script setup>

    import "./Footer.css"

    const year = new Date().getFullYear()

</script>
