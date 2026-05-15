<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Code2, Menu, X, User, LogOut } from 'lucide-vue-next'

defineProps<{
  isLoggedIn: boolean
  user: any
}>()

const router = useRouter()
const isMenuOpen = ref(false)

const navItems = [
  { name: '首页', path: '/' },
  { name: '题库', path: '/questions' },
  { name: '实习', path: '/jobs' },
  { name: '面经', path: '/experiences' },
  { name: 'AI助手', path: '/ai' },
  { name: '社区', path: '/community' },
  { name: '学习', path: '/learning' },
]

const toggleMenu = () => {
  isMenuOpen.value = !isMenuOpen.value
}

const logout = () => {
  localStorage.removeItem('user')
  router.push('/login')
}
</script>

<template>
  <nav class="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-sm border-b border-gray-100">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex items-center justify-between h-16">
        <div class="flex items-center space-x-3">
          <div class="flex items-center justify-center w-10 h-10 rounded-xl bg-gradient-primary">
            <Code2 class="w-5 h-5 text-white" />
          </div>
          <span class="text-xl font-bold text-gradient">后端练习生</span>
        </div>

        <div class="hidden md:flex items-center space-x-1">
          <a
            v-for="item in navItems"
            :key="item.path"
            :href="item.path"
            class="px-4 py-2 text-sm font-medium text-gray-600 hover:text-primary-600 hover:bg-primary-50 rounded-lg transition-all duration-200"
          >
            {{ item.name }}
          </a>
        </div>

        <div class="flex items-center space-x-3">
          <template v-if="isLoggedIn">
            <button
              @click="router.push('/profile')"
              class="flex items-center space-x-2 px-4 py-2 text-sm font-medium text-gray-600 hover:text-primary-600 hover:bg-primary-50 rounded-lg transition-all duration-200"
            >
              <User class="w-4 h-4" />
              <span>{{ user?.username || '用户' }}</span>
            </button>
            <button
              @click="logout"
              class="flex items-center space-x-2 px-4 py-2 text-sm font-medium text-gray-600 hover:text-red-500 hover:bg-red-50 rounded-lg transition-all duration-200"
            >
              <LogOut class="w-4 h-4" />
              <span>退出</span>
            </button>
          </template>
          <template v-else>
            <button
              @click="router.push('/login')"
              class="px-4 py-2 text-sm font-medium text-gray-600 hover:text-primary-600 hover:bg-primary-50 rounded-lg transition-all duration-200"
            >
              登录
            </button>
            <button
              @click="router.push('/register')"
              class="btn-primary text-sm px-4 py-2"
            >
              注册
            </button>
          </template>

          <button
            @click="toggleMenu"
            class="md:hidden p-2 rounded-lg hover:bg-gray-100 transition-colors"
          >
            <Menu v-if="!isMenuOpen" class="w-5 h-5 text-gray-600" />
            <X v-else class="w-5 h-5 text-gray-600" />
          </button>
        </div>
      </div>

      <div
        v-if="isMenuOpen"
        class="md:hidden py-4 border-t border-gray-100 animate-fade-in"
      >
        <div class="flex flex-col space-y-1">
          <a
            v-for="item in navItems"
            :key="item.path"
            :href="item.path"
            @click="isMenuOpen = false"
            class="px-4 py-2 text-sm font-medium text-gray-600 hover:text-primary-600 hover:bg-primary-50 rounded-lg transition-all duration-200"
          >
            {{ item.name }}
          </a>
        </div>
      </div>
    </div>
  </nav>
</template>