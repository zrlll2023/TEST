<script setup lang="ts">
import { ref } from 'vue'
import { MessageSquare, Heart, MessageCircle, Share2, User, Image } from 'lucide-vue-next'

const posts = ref([
  { id: 1, userId: 1, username: 'Java小白', avatar: 'J', title: '关于Java面试的一些心得分享', content: '最近参加了几家大厂的面试，总结了一些面试经验，希望能帮助到大家...', tags: ['Java', '面试'], views: 1234, likes: 234, comments: 56, time: '2小时前' },
  { id: 2, userId: 2, username: '后端进阶', avatar: 'H', title: 'Spring Boot性能优化实践', content: '分享一些在实际项目中使用的Spring Boot性能优化技巧...', tags: ['Spring Boot', '性能优化'], views: 987, likes: 187, comments: 43, time: '5小时前' },
  { id: 3, userId: 3, username: '架构师之路', avatar: 'J', title: '微服务架构设计思考', content: '在设计微服务架构时需要考虑的几个关键因素...', tags: ['微服务', '架构'], views: 856, likes: 156, comments: 32, time: '昨天' },
])

const newPostContent = ref('')

const createPost = () => {
  alert('帖子发布成功！')
  newPostContent.value = ''
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-2xl font-bold text-gray-900">技术社区</h1>
          <p class="text-gray-600 text-sm mt-1">与开发者一起交流学习</p>
        </div>
      </div>

      <div class="card mb-6">
        <div class="flex items-start space-x-4">
          <div class="w-10 h-10 rounded-full bg-gradient-primary flex items-center justify-center text-white font-bold flex-shrink-0">
            U
          </div>
          <div class="flex-1">
            <textarea
              v-model="newPostContent"
              placeholder="分享你的想法..."
              class="w-full border-none resize-none focus:outline-none text-gray-700"
              rows="3"
            ></textarea>
            <div class="flex items-center justify-between mt-4">
              <div class="flex items-center space-x-4">
                <button class="p-2 rounded-lg hover:bg-gray-100 text-gray-500 transition-colors">
                  <Image class="w-5 h-5" />
                </button>
                <button class="p-2 rounded-lg hover:bg-gray-100 text-gray-500 transition-colors">
                  <MessageSquare class="w-5 h-5" />
                </button>
              </div>
              <button @click="createPost" class="btn-primary">发布</button>
            </div>
          </div>
        </div>
      </div>

      <div class="space-y-6">
        <div
          v-for="post in posts"
          :key="post.id"
          class="card"
        >
          <div class="flex items-start space-x-4">
            <div :class="['w-10 h-10 rounded-full flex items-center justify-center text-white font-bold flex-shrink-0', post.avatar === 'J' ? 'bg-blue-500' : 'bg-green-500']">
              {{ post.avatar }}
            </div>
            <div class="flex-1">
              <div class="flex items-center space-x-2 mb-2">
                <span class="font-medium text-gray-900">{{ post.username }}</span>
                <span class="text-sm text-gray-400">{{ post.time }}</span>
              </div>
              
              <h3 class="text-lg font-semibold text-gray-900 mb-2">{{ post.title }}</h3>
              <p class="text-gray-600 mb-4">{{ post.content }}</p>
              
              <div class="flex flex-wrap gap-2 mb-4">
                <span
                  v-for="tag in post.tags"
                  :key="tag"
                  class="badge bg-primary-100 text-primary-700 text-xs"
                >
                  {{ tag }}
                </span>
              </div>
              
              <div class="flex items-center space-x-6 text-sm text-gray-500">
                <button class="flex items-center hover:text-primary-600 transition-colors">
                  <Heart class="w-4 h-4 mr-1" />
                  {{ post.likes }}
                </button>
                <button class="flex items-center hover:text-primary-600 transition-colors">
                  <MessageCircle class="w-4 h-4 mr-1" />
                  {{ post.comments }}
                </button>
                <button class="flex items-center hover:text-primary-600 transition-colors">
                  <Share2 class="w-4 h-4 mr-1" />
                  分享
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>