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
  import "./HomeView.css"

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
