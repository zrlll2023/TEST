<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { Code2, BookOpen, Briefcase, MessageSquare, Bot, Trophy, TrendingUp, Users, ChevronRight } from 'lucide-vue-next'

const stats = ref([
  { icon: Users, value: '10,000+', label: '注册用户' },
  { icon: BookOpen, value: '5,000+', label: '面试题目' },
  { icon: Briefcase, value: '2,000+', label: '实习岗位' },
  { icon: MessageSquare, value: '1,500+', label: '面经分享' },
])

const features = ref([
  {
    icon: BookOpen,
    title: '海量题库',
    description: '覆盖Java基础、框架、数据库、算法等面试高频知识点',
    color: 'bg-blue-500',
  },
  {
    icon: Briefcase,
    title: '实习资讯',
    description: '聚合各大厂实习岗位，一键投递，追踪进度',
    color: 'bg-green-500',
  },
  {
    icon: MessageSquare,
    title: '面经分享',
    description: '真实面经分享，AI智能总结，助你备战面试',
    color: 'bg-purple-500',
  },
  {
    icon: Bot,
    title: 'AI助手',
    description: 'AI简历分析、模拟面试、代码评审，全方位辅导',
    color: 'bg-orange-500',
  },
])

const recentQuestions = ref([
  { id: 1, title: 'Java多线程并发编程详解', category: 'Java基础', difficulty: 'medium' },
  { id: 2, title: 'Spring Boot自动配置原理', category: 'Spring', difficulty: 'hard' },
  { id: 3, title: 'MySQL索引优化策略', category: '数据库', difficulty: 'medium' },
  { id: 4, title: 'Redis数据结构与应用场景', category: 'Redis', difficulty: 'easy' },
])

const trendingExperiences = ref([
  { id: 1, company: '字节跳动', position: '后端开发', result: '已拿到offer', views: 1234 },
  { id: 2, company: '阿里巴巴', position: 'Java开发', result: '一面通过', views: 892 },
  { id: 3, company: '腾讯', position: '后端开发', result: '二面准备中', views: 756 },
])

onMounted(() => {})
</script>

<template>
  <div class="min-h-screen pt-16">
    <section class="relative overflow-hidden bg-gradient-to-br from-primary-600 via-primary-700 to-accent-600">
      <div class="absolute inset-0 opacity-10">
        <div class="absolute top-10 left-10 w-72 h-72 bg-white rounded-full blur-3xl"></div>
        <div class="absolute bottom-10 right-10 w-96 h-96 bg-white rounded-full blur-3xl"></div>
      </div>
      
      <div class="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-20">
        <div class="text-center text-white">
          <div class="inline-flex items-center justify-center w-16 h-16 rounded-2xl bg-white/20 backdrop-blur-sm mb-6">
            <Code2 class="w-8 h-8" />
          </div>
          
          <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold mb-6">
            后端练习生
          </h1>
          
          <p class="text-lg md:text-xl text-white/80 max-w-2xl mx-auto mb-8">
            专注于Java后端开发实习的综合服务平台<br />
            题库练习、面经分享、AI辅导，助你斩获心仪offer
          </p>
          
          <div class="flex flex-col sm:flex-row items-center justify-center space-y-4 sm:space-y-0 sm:space-x-4">
            <a href="/questions" class="btn-primary text-lg px-8 py-4 rounded-xl">
              开始刷题
            </a>
            <a href="/jobs" class="btn-secondary text-lg px-8 py-4 rounded-xl bg-white/10 text-white hover:bg-white/20">
              查看实习
            </a>
          </div>
        </div>
      </div>
    </section>

    <section class="py-16 bg-white">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="grid grid-cols-2 md:grid-cols-4 gap-6">
          <div
            v-for="stat in stats"
            :key="stat.label"
            class="text-center p-6 rounded-2xl bg-gray-50 hover:bg-gray-100 transition-all duration-300"
          >
            <component :is="stat.icon" class="w-8 h-8 mx-auto text-primary-600 mb-3" />
            <div class="text-2xl md:text-3xl font-bold text-gray-900">{{ stat.value }}</div>
            <div class="text-sm text-gray-500 mt-1">{{ stat.label }}</div>
          </div>
        </div>
      </div>
    </section>

    <section class="py-16 bg-gray-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="text-center mb-12">
          <h2 class="text-3xl font-bold text-gray-900 mb-4">核心功能</h2>
          <p class="text-gray-600">一站式后端开发学习平台</p>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
          <div
            v-for="feature in features"
            :key="feature.title"
            class="card group hover:-translate-y-1"
          >
            <div :class="[feature.color, 'w-12 h-12 rounded-xl flex items-center justify-center mb-4 group-hover:scale-110 transition-transform']">
              <component :is="feature.icon" class="w-6 h-6 text-white" />
            </div>
            <h3 class="text-lg font-semibold text-gray-900 mb-2">{{ feature.title }}</h3>
            <p class="text-sm text-gray-600">{{ feature.description }}</p>
          </div>
        </div>
      </div>
    </section>

    <section class="py-16 bg-white">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex items-center justify-between mb-8">
          <div>
            <h2 class="text-2xl font-bold text-gray-900">热门题目</h2>
            <p class="text-gray-600 text-sm mt-1">精选高频面试题</p>
          </div>
          <a href="/questions" class="flex items-center text-primary-600 hover:text-primary-700 font-medium">
            查看全部 <ChevronRight class="w-4 h-4 ml-1" />
          </a>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
          <div
            v-for="question in recentQuestions"
            :key="question.id"
            class="flex items-center justify-between p-4 rounded-xl bg-gray-50 hover:bg-gray-100 transition-colors cursor-pointer"
          >
            <div>
              <h3 class="font-medium text-gray-900">{{ question.title }}</h3>
              <div class="flex items-center space-x-2 mt-1">
                <span class="badge bg-gray-200 text-gray-700 text-xs">{{ question.category }}</span>
                <span
                  :class="[
                    'badge text-xs',
                    question.difficulty === 'easy' ? 'bg-green-100 text-green-700' :
                    question.difficulty === 'medium' ? 'bg-yellow-100 text-yellow-700' :
                    'bg-red-100 text-red-700'
                  ]"
                >
                  {{ question.difficulty === 'easy' ? '简单' : question.difficulty === 'medium' ? '中等' : '困难' }}
                </span>
              </div>
            </div>
            <ChevronRight class="w-5 h-5 text-gray-400" />
          </div>
        </div>
      </div>
    </section>

    <section class="py-16 bg-gray-50">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div class="flex items-center justify-between mb-8">
          <div>
            <h2 class="text-2xl font-bold text-gray-900">热门面经</h2>
            <p class="text-gray-600 text-sm mt-1">真实面试经验分享</p>
          </div>
          <a href="/experiences" class="flex items-center text-primary-600 hover:text-primary-700 font-medium">
            查看全部 <ChevronRight class="w-4 h-4 ml-1" />
          </a>
        </div>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div
            v-for="exp in trendingExperiences"
            :key="exp.id"
            class="card hover:-translate-y-1"
          >
            <div class="flex items-center justify-between mb-3">
              <span class="font-semibold text-gray-900">{{ exp.company }}</span>
              <span :class="[
                'badge text-xs',
                exp.result.includes('offer') ? 'bg-green-100 text-green-700' :
                exp.result.includes('通过') ? 'bg-blue-100 text-blue-700' :
                'bg-yellow-100 text-yellow-700'
              ]">
                {{ exp.result }}
              </span>
            </div>
            <div class="text-sm text-gray-600 mb-3">{{ exp.position }}</div>
            <div class="flex items-center text-xs text-gray-400">
              <TrendingUp class="w-3 h-3 mr-1" />
              {{ exp.views }} 阅读
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="py-16 bg-gradient-to-r from-primary-600 to-accent-600">
      <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center text-white">
        <Trophy class="w-12 h-12 mx-auto mb-4" />
        <h2 class="text-3xl font-bold mb-4">开启你的后端学习之旅</h2>
        <p class="text-white/80 mb-8">
          加入10000+开发者的行列，一起成长，一起进步
        </p>
        <div class="flex flex-col sm:flex-row items-center justify-center space-y-4 sm:space-y-0 sm:space-x-4">
          <a href="/register" class="btn-primary text-lg px-8 py-4 rounded-xl bg-white text-primary-600 hover:bg-gray-100">
            免费注册
          </a>
          <a href="/questions" class="btn-secondary text-lg px-8 py-4 rounded-xl bg-white/10 text-white hover:bg-white/20">
            浏览题库
          </a>
        </div>
      </div>
    </section>
  </div>
</template>