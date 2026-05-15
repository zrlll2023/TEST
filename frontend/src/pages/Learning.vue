<script setup lang="ts">
import { ref } from 'vue'
import { BookOpen, CheckCircle, Clock, Target, BarChart3, TrendingUp } from 'lucide-vue-next'

const learningPaths = ref([
  { id: 1, name: 'Java基础入门', target: '零基础入门', duration: 30, difficulty: '简单', progress: 60 },
  { id: 2, name: 'Spring Boot实战', target: '掌握企业级开发', duration: 45, difficulty: '中等', progress: 40 },
  { id: 3, name: 'MySQL进阶', target: '数据库优化专家', duration: 30, difficulty: '中等', progress: 20 },
  { id: 4, name: 'Redis高级应用', target: '缓存架构师', duration: 20, difficulty: '困难', progress: 0 },
])

const weeklyData = ref({
  labels: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
  hours: [2.5, 3.0, 1.5, 4.0, 2.0, 3.5, 2.0],
})

const skillProgress = ref([
  { name: 'Java', progress: 85 },
  { name: 'Spring Boot', progress: 75 },
  { name: 'MySQL', progress: 70 },
  { name: 'Redis', progress: 60 },
])

const recentActivities = ref([
  { id: 1, type: 'completed', title: '完成了「Java多线程」章节', time: '2小时前' },
  { id: 2, type: 'started', title: '开始学习「Spring Boot自动配置」', time: '昨天' },
  { id: 3, type: 'completed', title: '完成了「MySQL索引优化」练习', time: '2天前' },
])
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-2xl font-bold text-gray-900">学习路径</h1>
          <p class="text-gray-600 text-sm mt-1">制定学习计划，追踪学习进度</p>
        </div>
        <button class="btn-primary">创建学习计划</button>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
        <div class="card">
          <div class="flex items-center space-x-3">
            <div class="w-12 h-12 rounded-xl bg-blue-100 flex items-center justify-center">
              <BookOpen class="w-6 h-6 text-blue-600" />
            </div>
            <div>
              <div class="text-2xl font-bold text-gray-900">4</div>
              <div class="text-sm text-gray-500">学习路径</div>
            </div>
          </div>
        </div>
        
        <div class="card">
          <div class="flex items-center space-x-3">
            <div class="w-12 h-12 rounded-xl bg-green-100 flex items-center justify-center">
              <CheckCircle class="w-6 h-6 text-green-600" />
            </div>
            <div>
              <div class="text-2xl font-bold text-gray-900">12</div>
              <div class="text-sm text-gray-500">已完成任务</div>
            </div>
          </div>
        </div>
        
        <div class="card">
          <div class="flex items-center space-x-3">
            <div class="w-12 h-12 rounded-xl bg-purple-100 flex items-center justify-center">
              <Clock class="w-6 h-6 text-purple-600" />
            </div>
            <div>
              <div class="text-2xl font-bold text-gray-900">18h</div>
              <div class="text-sm text-gray-500">本周学习</div>
            </div>
          </div>
        </div>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div class="lg:col-span-2">
          <div class="card mb-6">
            <h3 class="font-semibold text-gray-900 mb-4 flex items-center">
              <Target class="w-5 h-5 mr-2 text-primary-600" />
              我的学习路径
            </h3>
            <div class="space-y-4">
              <div
                v-for="path in learningPaths"
                :key="path.id"
                class="p-4 rounded-xl bg-gray-50 hover:bg-gray-100 transition-colors"
              >
                <div class="flex items-center justify-between mb-2">
                  <div>
                    <h4 class="font-medium text-gray-900">{{ path.name }}</h4>
                    <p class="text-sm text-gray-500">{{ path.target }} · {{ path.duration }}天 · {{ path.difficulty }}</p>
                  </div>
                  <span class="text-sm font-medium text-primary-600">{{ path.progress }}%</span>
                </div>
                <div class="w-full bg-gray-200 rounded-full h-2">
                  <div
                    :style="{ width: path.progress + '%' }"
                    class="bg-primary-600 h-2 rounded-full transition-all"
                  ></div>
                </div>
              </div>
            </div>
          </div>

          <div class="card">
            <h3 class="font-semibold text-gray-900 mb-4 flex items-center">
              <BarChart3 class="w-5 h-5 mr-2 text-primary-600" />
              本周学习时长
            </h3>
            <div class="flex items-end justify-between h-40 space-x-2">
              <div
                v-for="(hour, index) in weeklyData.hours"
                :key="index"
                class="flex-1 flex flex-col items-center"
              >
                <div
                  :style="{ height: (hour / 4.5 * 100) + '%' }"
                  class="w-full bg-gradient-to-t from-primary-500 to-primary-300 rounded-t-lg transition-all"
                ></div>
                <span class="text-xs text-gray-500 mt-2">{{ weeklyData.labels[index] }}</span>
              </div>
            </div>
          </div>
        </div>

        <div>
          <div class="card mb-6">
            <h3 class="font-semibold text-gray-900 mb-4 flex items-center">
              <TrendingUp class="w-5 h-5 mr-2 text-primary-600" />
              技能进度
            </h3>
            <div class="space-y-4">
              <div v-for="skill in skillProgress" :key="skill.name">
                <div class="flex items-center justify-between mb-1">
                  <span class="text-sm font-medium text-gray-700">{{ skill.name }}</span>
                  <span class="text-sm text-gray-500">{{ skill.progress }}%</span>
                </div>
                <div class="w-full bg-gray-200 rounded-full h-2">
                  <div
                    :style="{ width: skill.progress + '%' }"
                    :class="[
                      'h-2 rounded-full transition-all',
                      skill.progress >= 80 ? 'bg-green-500' :
                      skill.progress >= 60 ? 'bg-blue-500' :
                      skill.progress >= 40 ? 'bg-yellow-500' : 'bg-red-500'
                    ]"
                  ></div>
                </div>
              </div>
            </div>
          </div>

          <div class="card">
            <h3 class="font-semibold text-gray-900 mb-4">最近活动</h3>
            <div class="space-y-3">
              <div
                v-for="activity in recentActivities"
                :key="activity.id"
                class="flex items-start space-x-3"
              >
                <div :class="[
                  'w-8 h-8 rounded-full flex items-center justify-center flex-shrink-0',
                  activity.type === 'completed' ? 'bg-green-100 text-green-600' : 'bg-blue-100 text-blue-600'
                ]">
                  <CheckCircle class="w-4 h-4" />
                </div>
                <div class="flex-1">
                  <p class="text-sm text-gray-700">{{ activity.title }}</p>
                  <p class="text-xs text-gray-400">{{ activity.time }}</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>