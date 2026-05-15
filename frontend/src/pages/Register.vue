<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { Code2, Mail, User, Phone, Lock, Eye, EyeOff } from 'lucide-vue-next'
import axios from 'axios'

const router = useRouter()
const email = ref('')
const username = ref('')
const phone = ref('')
const password = ref('')
const confirmPassword = ref('')
const showPassword = ref(false)
const isLoading = ref(false)
const error = ref('')

const handleSubmit = async () => {
  if (!email.value || !username.value || !password.value) {
    error.value = '请填写必填项'
    return
  }
  
  if (password.value !== confirmPassword.value) {
    error.value = '两次输入的密码不一致'
    return
  }
  
  isLoading.value = true
  error.value = ''
  
  try {
    const response = await axios.post('/api/auth/register', {
      email: email.value,
      username: username.value,
      phone: phone.value,
      password: password.value
    })
    
    if (response.data.code === 200) {
      localStorage.setItem('user', JSON.stringify(response.data.data))
      router.push('/')
    } else {
      error.value = response.data.message
    }
  } catch (e) {
    error.value = '注册失败，请稍后重试'
  } finally {
    isLoading.value = false
  }
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gradient-to-br from-gray-50 to-gray-100 flex items-center justify-center p-4">
    <div class="w-full max-w-md">
      <div class="text-center mb-8">
        <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-gradient-primary mb-4">
          <Code2 class="w-8 h-8 text-white" />
        </div>
        <h1 class="text-2xl font-bold text-gray-900">创建账号</h1>
        <p class="text-gray-500 mt-2">加入我们，开启后端学习之旅</p>
      </div>
      
      <div class="card">
        <form @submit.prevent="handleSubmit" class="space-y-4">
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">邮箱 <span class="text-red-500">*</span></label>
            <div class="relative">
              <Mail class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                v-model="email"
                type="email"
                placeholder="请输入邮箱"
                class="input pl-12"
              />
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">用户名 <span class="text-red-500">*</span></label>
            <div class="relative">
              <User class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                v-model="username"
                type="text"
                placeholder="请输入用户名"
                class="input pl-12"
              />
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">手机号</label>
            <div class="relative">
              <Phone class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                v-model="phone"
                type="tel"
                placeholder="请输入手机号（选填）"
                class="input pl-12"
              />
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">密码 <span class="text-red-500">*</span></label>
            <div class="relative">
              <Lock class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                v-model="password"
                :type="showPassword ? 'text' : 'password'"
                placeholder="请输入密码（6-32位）"
                class="input pl-12"
              />
              <button
                type="button"
                @click="showPassword = !showPassword"
                class="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600"
              >
                <Eye v-if="showPassword" class="w-5 h-5" />
                <EyeOff v-else class="w-5 h-5" />
              </button>
            </div>
          </div>
          
          <div>
            <label class="block text-sm font-medium text-gray-700 mb-2">确认密码 <span class="text-red-500">*</span></label>
            <div class="relative">
              <Lock class="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
              <input
                v-model="confirmPassword"
                :type="showPassword ? 'text' : 'password'"
                placeholder="请再次输入密码"
                class="input pl-12"
              />
            </div>
          </div>
          
          <div v-if="error" class="p-3 bg-red-50 border border-red-200 rounded-lg">
            <p class="text-sm text-red-600">{{ error }}</p>
          </div>
          
          <button
            type="submit"
            :disabled="isLoading"
            class="btn-primary w-full py-3 text-base mt-4"
          >
            <span v-if="isLoading" class="flex items-center">
              <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              注册中...
            </span>
            <span v-else>注册</span>
          </button>
        </form>
        
        <div class="mt-6 pt-6 border-t border-gray-100">
          <p class="text-center text-sm text-gray-500">
            已有账号？<a href="/login" class="text-primary-600 hover:text-primary-700">立即登录</a>
          </p>
        </div>
      </div>
    </div>
  </div>
</template>