import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router'
import './style.css'
import App from './App.vue'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/', name: 'Home', component: () => import('./pages/Home.vue') },
    { path: '/login', name: 'Login', component: () => import('./pages/Login.vue') },
    { path: '/register', name: 'Register', component: () => import('./pages/Register.vue') },
    { path: '/questions', name: 'Questions', component: () => import('./pages/Questions.vue') },
    { path: '/jobs', name: 'Jobs', component: () => import('./pages/Jobs.vue') },
    { path: '/experiences', name: 'Experiences', component: () => import('./pages/Experiences.vue') },
    { path: '/ai', name: 'AI', component: () => import('./pages/AiAssistant.vue') },
    { path: '/community', name: 'Community', component: () => import('./pages/Community.vue') },
    { path: '/learning', name: 'Learning', component: () => import('./pages/Learning.vue') },
    { path: '/profile', name: 'Profile', component: () => import('./pages/Profile.vue') },
  ]
})

const app = createApp(App)
app.use(router)
app.mount('#app')