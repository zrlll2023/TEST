<script setup lang="ts">
import { ref } from 'vue'
import { User, Mail, Phone, GraduationCap, Briefcase, Settings, Edit3, BookOpen, Award, TrendingUp } from 'lucide-vue-next'

const user = ref({
  id: 1,
  username: 'Java小白',
  email: 'javabai@example.com',
  phone: '138****8888',
  avatar: 'J',
  school: '某985高校',
  major: '计算机科学与技术',
  grade: '大三',
  skills: 'Java, Spring Boot, MySQL, Redis',
  githubUrl: 'https://github.com/javabai',
  bio: '热爱技术，正在努力学习Java后端开发，目标是进入大厂实习。',
  targetCompany: '字节跳动、阿里巴巴、腾讯',
})

const stats = ref([
  { icon: BookOpen, label: '已做题目', value: '156' },
  { icon: Award, label: '面试次数', value: '8' },
  { icon: TrendingUp, label: '学习天数', value: '45' },
])

const applications = ref([
  { id: 1, company: '字节跳动', position: '后端开发实习生', status: 'pending', date: '2024-03-15' },
  { id: 2, company: '阿里巴巴', position: 'Java开发实习生', status: 'passed', date: '2024-03-10' },
  { id: 3, company: '腾讯', position: '后端开发实习生', status: 'rejected', date: '2024-03-08' },
])

const isEditing = ref(false)

const toggleEdit = () => {
  isEditing.value = !isEditing.value
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="card mb-6">
        <div class="flex flex-col md:flex-row items-center md:items-start space-y-4 md:space-y-0 md:space-x-6">
          <div class="w-24 h-24 rounded-full bg-gradient-primary flex items-center justify-center text-white text-3xl font-bold">
            {{ user.avatar }}
          </div>
          <div class="flex-1">
            <div class="flex items-center space-x-3 mb-2">
              <h1 class="text-2xl font-bold text-gray-900">{{ user.username }}</h1>
              <button @click="toggleEdit" class="p-2 rounded-lg hover:bg-gray-100 text-gray-500 transition-colors">
                <Edit3 class="w-5 h-5" />
              </button>
              <button class="p-2 rounded-lg hover:bg-gray-100 text-gray-500 transition-colors">
                <Settings class="w-5 h-5" />
              </button>
            </div>
            <p class="text-gray-600 mb-4">{{ user.bio }}</p>
            <div class="flex flex-wrap gap-4">
              <div class="flex items-center space-x-2 text-sm text-gray-500">
                <GraduationCap class="w-4 h-4" />
                <span>{{ user.school }} · {{ user.major }}</span>
              </div>
              <div class="flex items-center space-x-2 text-sm text-gray-500">
                <Briefcase class="w-4 h-4" />
                <span>{{ user.grade }}</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
        <div
          v-for="stat in stats"
          :key="stat.label"
          class="card text-center"
        >
          <component :is="stat.icon" class="w-8 h-8 mx-auto text-primary-600 mb-2" />
          <div class="text-2xl font-bold text-gray-900">{{ stat.value }}</div>
          <div class="text-sm text-gray-500">{{ stat.label }}</div>
        </div>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div class="card">
          <h3 class="font-semibold text-gray-900 mb-4">个人信息</h3>
          <div class="space-y-4">
            <div class="flex items-center space-x-3">
              <Mail class="w-5 h-5 text-gray-400" />
              <span class="text-gray-600">{{ user.email }}</span>
            </div>
            <div class="flex items-center space-x-3">
              <Phone class="w-5 h-5 text-gray-400" />
              <span class="text-gray-600">{{ user.phone }}</span>
            </div>
            <div class="flex items-center space-x-3">
              <GraduationCap class="w-5 h-5 text-gray-400" />
              <span class="text-gray-600">{{ user.school }}</span>
            </div>
            <div class="flex items-center space-x-3">
              <Briefcase class="w-5 h-5 text-gray-400" />
              <span class="text-gray-600">{{ user.major }}</span>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">技能标签</label>
              <div class="flex flex-wrap gap-2">
                <span
                  v-for="skill in user.skills.split(', ')"
                  :key="skill"
                  class="badge bg-primary-100 text-primary-700 text-xs"
                >
                  {{ skill }}
                </span>
              </div>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">目标公司</label>
              <div class="flex flex-wrap gap-2">
                <span
                  v-for="company in user.targetCompany.split('、')"
                  :key="company"
                  class="badge bg-gray-100 text-gray-700 text-xs"
                >
                  {{ company }}
                </span>
              </div>
            </div>
          </div>
        </div>

        <div class="card">
          <h3 class="font-semibold text-gray-900 mb-4">投递记录</h3>
          <div class="space-y-3">
            <div
              v-for="app in applications"
              :key="app.id"
              class="p-4 rounded-xl bg-gray-50"
            >
              <div class="flex items-center justify-between mb-1">
                <span class="font-medium text-gray-900">{{ app.company }}</span>
                <span :class="[
                  'badge text-xs',
                  app.status === 'pending' ? 'bg-yellow-100 text-yellow-700' :
                  app.status === 'passed' ? 'bg-green-100 text-green-700' :
                  'bg-red-100 text-red-700'
                ]">
                  {{ app.status === 'pending' ? '待面试' : app.status === 'passed' ? '通过' : '未通过' }}
                </span>
              </div>
              <div class="text-sm text-gray-600">{{ app.position }}</div>
              <div class="text-xs text-gray-400 mt-1">{{ app.date }}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>