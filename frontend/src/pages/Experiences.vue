<script setup lang="ts">
import { ref } from 'vue'
import { MessageSquare, Heart, Eye, Calendar, Star, ThumbsUp } from 'lucide-vue-next'

const experiences = ref([
  { id: 1, company: '字节跳动', position: '后端开发', title: '2024字节跳动后端实习面经', result: '已拿到offer', difficulty: 4, views: 1234, likes: 234, comments: 56, date: '2024-03-15' },
  { id: 2, company: '阿里巴巴', position: 'Java开发', title: '阿里P6后端开发面试记录', result: '一面通过', difficulty: 5, views: 987, likes: 187, comments: 43, date: '2024-03-10' },
  { id: 3, company: '腾讯', position: '后端开发', title: '腾讯WXG后端面试总结', result: '二面准备中', difficulty: 4, views: 856, likes: 156, comments: 32, date: '2024-03-08' },
  { id: 4, company: '美团', position: 'Java后端', title: '美团后端实习面试经验', result: '已拿到offer', difficulty: 3, views: 723, likes: 123, comments: 28, date: '2024-03-05' },
])

const currentExperience = ref(null)

const selectExperience = (exp: any) => {
  currentExperience.value = exp
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-2xl font-bold text-gray-900">面经分享</h1>
          <p class="text-gray-600 text-sm mt-1">真实面试经验分享，助你备战面试</p>
        </div>
        <button class="btn-primary">发布面经</button>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div class="lg:col-span-2 space-y-4">
          <div
            v-for="exp in experiences"
            :key="exp.id"
            @click="selectExperience(exp)"
            :class="[
              'card cursor-pointer',
              currentExperience?.id === exp.id ? 'ring-2 ring-primary-500' : ''
            ]"
          >
            <div class="flex items-start justify-between mb-3">
              <div class="flex items-center space-x-3">
                <div class="w-12 h-12 rounded-xl bg-gradient-primary flex items-center justify-center text-white font-bold">
                  {{ exp.company.charAt(0) }}
                </div>
                <div>
                  <h3 class="font-semibold text-gray-900">{{ exp.company }}</h3>
                  <p class="text-sm text-gray-600">{{ exp.position }}</p>
                </div>
              </div>
              <span :class="[
                'badge text-xs',
                exp.result.includes('offer') ? 'bg-green-100 text-green-700' :
                exp.result.includes('通过') ? 'bg-blue-100 text-blue-700' :
                'bg-yellow-100 text-yellow-700'
              ]">
                {{ exp.result }}
              </span>
            </div>

            <h4 class="font-medium text-gray-900 mb-3">{{ exp.title }}</h4>

            <div class="flex items-center space-x-4 text-sm text-gray-500 mb-3">
              <div class="flex items-center">
                <Star class="w-4 h-4 mr-1 text-yellow-500" />
                {{ exp.difficulty }}星难度
              </div>
              <div class="flex items-center">
                <Calendar class="w-4 h-4 mr-1" />
                {{ exp.date }}
              </div>
            </div>

            <div class="flex items-center justify-between pt-3 border-t border-gray-100">
              <div class="flex items-center space-x-4 text-sm text-gray-400">
                <span class="flex items-center">
                  <Eye class="w-4 h-4 mr-1" />
                  {{ exp.views }}
                </span>
                <span class="flex items-center">
                  <Heart class="w-4 h-4 mr-1" />
                  {{ exp.likes }}
                </span>
                <span class="flex items-center">
                  <MessageSquare class="w-4 h-4 mr-1" />
                  {{ exp.comments }}
                </span>
              </div>
              <button class="btn-outline text-sm">查看详情</button>
            </div>
          </div>
        </div>

        <div class="lg:col-span-1">
          <div class="card sticky top-24">
            <h3 class="font-semibold text-gray-900 mb-4 flex items-center">
              <ThumbsUp class="w-5 h-5 mr-2 text-primary-600" />
              热门面经
            </h3>
            <div class="space-y-3">
              <div
                v-for="(exp, index) in experiences.slice(0, 4)"
                :key="exp.id"
                class="flex items-start space-x-3 p-3 rounded-lg hover:bg-gray-50 cursor-pointer transition-colors"
              >
                <span :class="[
                  'w-6 h-6 rounded-full flex items-center justify-center text-xs font-bold',
                  index < 3 ? 'bg-primary-100 text-primary-600' : 'bg-gray-100 text-gray-600'
                ]">
                  {{ index + 1 }}
                </span>
                <div class="flex-1">
                  <h4 class="text-sm font-medium text-gray-900 truncate">{{ exp.title }}</h4>
                  <p class="text-xs text-gray-500">{{ exp.company }} · {{ exp.views }}阅读</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>