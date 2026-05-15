<script setup lang="ts">
import { Code2, Github, Mail, Heart } from 'lucide-vue-next'

const currentYear = new Date().getFullYear()

const links = {
  product: [
    { name: '面试题库', href: '/questions' },
    { name: '实习资讯', href: '/jobs' },
    { name: '面经分享', href: '/experiences' },
    { name: 'AI助手', href: '/ai' },
  ],
  resources: [
    { name: '学习路径', href: '/learning' },
    { name: '技术社区', href: '/community' },
    { name: '个人中心', href: '/profile' },
  ],
  about: [
    { name: '关于我们', href: '#' },
    { name: '联系我们', href: '#' },
    { name: '加入团队', href: '#' },
  ],
}
</script>

<template>
  <footer class="bg-gray-900 text-gray-300">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-12">
      <div class="grid grid-cols-1 md:grid-cols-4 gap-8">
        <div class="md:col-span-1">
          <div class="flex items-center space-x-3 mb-4">
            <div class="flex items-center justify-center w-10 h-10 rounded-xl bg-gradient-primary">
              <Code2 class="w-5 h-5 text-white" />
            </div>
            <span class="text-xl font-bold text-white">后端练习生</span>
          </div>
          <p class="text-sm text-gray-400 mb-4">
            专注于Java后端开发实习的综合服务平台，助力每一位开发者实现职业梦想。
          </p>
          <div class="flex space-x-4">
            <a href="#" class="p-2 rounded-lg bg-gray-800 hover:bg-gray-700 transition-colors">
              <Github class="w-5 h-5" />
            </a>
            <a href="#" class="p-2 rounded-lg bg-gray-800 hover:bg-gray-700 transition-colors">
              <Mail class="w-5 h-5" />
            </a>
          </div>
        </div>

        <div>
          <h3 class="text-white font-semibold mb-4">产品服务</h3>
          <ul class="space-y-2">
            <li v-for="link in links.product" :key="link.name">
              <a :href="link.href" class="text-sm text-gray-400 hover:text-white transition-colors">
                {{ link.name }}
              </a>
            </li>
          </ul>
        </div>

        <div>
          <h3 class="text-white font-semibold mb-4">学习资源</h3>
          <ul class="space-y-2">
            <li v-for="link in links.resources" :key="link.name">
              <a :href="link.href" class="text-sm text-gray-400 hover:text-white transition-colors">
                {{ link.name }}
              </a>
            </li>
          </ul>
        </div>

        <div>
          <h3 class="text-white font-semibold mb-4">关于我们</h3>
          <ul class="space-y-2">
            <li v-for="link in links.about" :key="link.name">
              <a :href="link.href" class="text-sm text-gray-400 hover:text-white transition-colors">
                {{ link.name }}
              </a>
            </li>
          </ul>
        </div>
      </div>

      <div class="border-t border-gray-800 mt-8 pt-8 text-center">
        <p class="text-sm text-gray-500 flex items-center justify-center space-x-1">
          <span>&copy; {{ currentYear }} 后端练习生. All rights reserved.</span>
          <Heart class="w-4 h-4 text-red-400" />
        </p>
      </div>
    </div>
  </footer>
</template>