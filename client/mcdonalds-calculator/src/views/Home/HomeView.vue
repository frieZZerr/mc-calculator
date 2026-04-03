<template>

  <div class="home" @scroll.passive="onScroll" ref="pageRef">

    <!-- HERO -->
    <section class="hero">
      <div
        class="hero-bg"
        :style="{
          transform: `translateY(${bgOffset}px) scale(1.1)`
        }"
      ></div>

      <div class="hero-content">
        <h1>McDonald's Macro Calculator</h1>
        <p>
          Build your meal, track calories, and balance your macros —
          all in one place.
        </p>
        <button @click="goToCalculator">Start Building Your Meal</button>
      </div>
    </section>

    <!-- FEATURES -->
    <section class="features">
      <div class="feature-card">
        <h3>🍔 Build Your Meal</h3>
        <p>Select products and customize quantities easily.</p>
      </div>

      <div class="feature-card">
        <h3>📊 Track Macros</h3>
        <p>Instantly see calories, protein, carbs, and fat totals.</p>
      </div>

      <div class="feature-card">
        <h3>⚡ Fast & Simple</h3>
        <p>Clean interface designed for quick decisions.</p>
      </div>
    </section>

    <!-- CTA -->
    <section class="cta">
      <h2>Ready to plan your meal?</h2>
      <button @click="goToCalculator">Go to Calculator</button>
    </section>

  </div>

</template>

<script setup>

  import { ref, onMounted, onUnmounted } from "vue"
  import { useRouter } from "vue-router"

  const router = useRouter()

  function goToCalculator() {
    router.push("/calculator")
  }

  const bgOffset = ref(0)

  function onScroll() {
    const scrollY = window.scrollY
    bgOffset.value = scrollY * 0.4
  }

  onMounted(() => {
    window.addEventListener("scroll", onScroll, { passive: true })
  })

  onUnmounted(() => {
    window.removeEventListener("scroll", onScroll)
  })

</script>

<style scoped>

  .home {
    font-family: 'Inter', sans-serif;
    color: white;
  }

  /* HERO */
  .hero {
    position: relative;
    height: 100dvh;
    overflow: hidden;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .hero-bg {
    position: absolute;
    inset: 0;
    background-image: url('https://images.unsplash.com/photo-1550547660-d9450f859349');
    background-size: cover;
    background-position: center;
    will-change: transform;
    z-index: 0;
  }

  .hero::after {
    content: "";
    position: absolute;
    inset: 0;
    background: rgba(0,0,0,0.6);
    z-index: 1;
  }

  .hero-content {
    position: relative;
    z-index: 2;
    text-align: center;
    max-width: 600px;
  }

  .hero h1 {
    font-size: 3rem;
    margin-bottom: 16px;
  }

  .hero p {
    font-size: 1.2rem;
    margin-bottom: 24px;
    color: #d1d5db;
  }

  .hero button {
    background: #fbbf24;
    border: none;
    padding: 12px 24px;
    color: black;
    font-weight: bold;
    border-radius: 6px;
    cursor: pointer;
    transition: all 0.2s ease;
  }

  .hero button:hover {
    background: #f59e0b;
    transform: translateY(-2px);
  }

  /* FEATURES */
  .features {
    display: flex;
    gap: 20px;
    padding: 60px 40px;
    background: #111827;
    justify-content: center;
    flex-wrap: wrap;
  }

  .feature-card {
    background: #1f2937;
    padding: 20px;
    border-radius: 10px;
    width: 260px;
    box-shadow: 0 4px 10px rgba(0,0,0,0.3);
    transition: transform 0.2s ease;
  }

  .feature-card:hover {
    transform: translateY(-5px);
  }

  /* CTA */
  .cta {
    text-align: center;
    padding: 80px 20px;
    background: linear-gradient(270deg, #1f2937, #111827);
  }

  .cta h2 {
    margin-bottom: 20px;
  }

  .cta button {
    background: #fbbf24;
    border: none;
    padding: 12px 24px;
    color: black;
    font-weight: bold;
    border-radius: 6px;
    cursor: pointer;
  }

</style>
