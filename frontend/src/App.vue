<script setup lang="ts">
import { ref, onMounted } from 'vue'
import Navbar from './components/Navbar.vue'
import Footer from './components/Footer.vue'

const isLoggedIn = ref(false)
const currentUser = ref(null)

onMounted(() => {
  const user = localStorage.getItem('user')
  if (user) {
    currentUser.value = JSON.parse(user)
    isLoggedIn.value = true
  }
})
</script>

<template>
  <div class="min-h-screen flex flex-col">
    <Navbar :is-logged-in="isLoggedIn" :user="currentUser" />
    
    <main class="flex-1">
      <router-view />
    </main>
    
    <Footer />
  </div>
</template>