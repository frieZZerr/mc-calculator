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

  const year = new Date().getFullYear()

</script>

<style scoped>

  .footer {
    margin-top: 40px;
    padding: 20px 30px;
    background: #020617;
    border-top: 1px solid #1f2937;
    color: #9ca3af;
  }

  .footer-content {
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 20px;
    flex-wrap: wrap;
  }

  /* LEFT */
  .footer-left h3 {
    color: #fbbf24;
    margin-bottom: 4px;
  }

  .footer-left p {
    font-size: 13px;
  }

  /* LINKS */
  .footer-links {
    display: flex;
    gap: 16px;
  }

  .footer-links a {
    color: #d1d5db;
    text-decoration: none;
    font-size: 14px;
    transition: color 0.2s;
  }

  .footer-links a:hover {
    color: #fbbf24;
  }

  /* RIGHT */
  .footer-right {
    font-size: 13px;
  }

  /* RESPONSIVE */
  @media (max-width: 768px) {
    .footer-content {
      flex-direction: column;
      text-align: center;
    }
  }

</style>
