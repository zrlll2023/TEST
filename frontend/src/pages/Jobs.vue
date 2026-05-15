<script setup lang="ts">
import { ref } from 'vue'
import { Briefcase, MapPin, Clock, DollarSign, Filter, Heart, ExternalLink, Building2 } from 'lucide-vue-next'

const jobs = ref([
  { id: 1, company: '字节跳动', title: '后端开发实习生', location: '北京', experience: '不限', education: '本科', salary: '200-300/天', tags: ['Java', 'Spring Boot', 'MySQL'], views: 1234 },
  { id: 2, company: '阿里巴巴', title: 'Java开发实习生', location: '杭州', experience: '3个月', education: '本科', salary: '250-350/天', tags: ['Java', '分布式', '中间件'], views: 987 },
  { id: 3, company: '腾讯', title: '后端开发实习生', location: '深圳', experience: '不限', education: '本科', salary: '220-320/天', tags: ['Java', 'Redis', 'MQ'], views: 856 },
  { id: 4, company: '美团', title: 'Java后端实习生', location: '北京', experience: '6个月', education: '本科', salary: '180-280/天', tags: ['Java', 'Spring Cloud', 'MySQL'], views: 723 },
  { id: 5, company: '京东', title: '后端开发实习生', location: '北京', experience: '不限', education: '硕士', salary: '200-300/天', tags: ['Java', '微服务', 'Redis'], views: 654 },
  { id: 6, company: '网易', title: 'Java开发实习生', location: '杭州', experience: '3个月', education: '本科', salary: '180-280/天', tags: ['Java', 'Spring', 'MySQL'], views: 589 },
])

const searchQuery = ref('')
const selectedLocation = ref('')
const selectedExperience = ref('')

const locations = ['全部', '北京', '上海', '杭州', '深圳', '广州']
const experiences = ['全部', '不限', '3个月', '6个月', '1年']

const handleApply = (job: any) => {
  alert(`正在申请 ${job.company} - ${job.title}`)
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-2xl font-bold text-gray-900">实习资讯</h1>
          <p class="text-gray-600 text-sm mt-1">最新Java后端实习岗位</p>
        </div>
        <div class="flex items-center space-x-4">
          <div class="relative">
            <input
              v-model="searchQuery"
              type="text"
              placeholder="搜索职位..."
              class="input pl-10 w-64"
            />
          </div>
        </div>
      </div>

      <div class="flex flex-wrap items-center gap-4 mb-6">
        <div class="flex items-center space-x-2">
          <Filter class="w-4 h-4 text-gray-500" />
          <span class="text-sm text-gray-600">筛选：</span>
        </div>
        
        <select v-model="selectedLocation" class="input w-32 text-sm">
          <option v-for="loc in locations" :key="loc" :value="loc === '全部' ? '' : loc">
            {{ loc }}
          </option>
        </select>
        
        <select v-model="selectedExperience" class="input w-32 text-sm">
          <option v-for="exp in experiences" :key="exp" :value="exp === '全部' ? '' : exp">
            {{ exp }}
          </option>
        </select>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <div
          v-for="job in jobs"
          :key="job.id"
          class="card hover:-translate-y-1 transition-transform"
        >
          <div class="flex items-start justify-between mb-3">
            <div class="flex items-center space-x-3">
              <div class="w-10 h-10 rounded-lg bg-gradient-primary flex items-center justify-center">
                <Building2 class="w-5 h-5 text-white" />
              </div>
              <div>
                <h3 class="font-semibold text-gray-900">{{ job.company }}</h3>
                <p class="text-sm text-gray-600">{{ job.title }}</p>
              </div>
            </div>
            <button class="p-2 rounded-lg hover:bg-red-50 text-gray-400 hover:text-red-500 transition-colors">
              <Heart class="w-5 h-5" />
            </button>
          </div>

          <div class="space-y-2 mb-4">
            <div class="flex items-center text-sm text-gray-500">
              <MapPin class="w-4 h-4 mr-2" />
              {{ job.location }}
            </div>
            <div class="flex items-center text-sm text-gray-500">
              <Clock class="w-4 h-4 mr-2" />
              {{ job.experience }}经验 · {{ job.education }}
            </div>
            <div class="flex items-center text-sm">
              <DollarSign class="w-4 h-4 mr-2 text-green-600" />
              <span class="font-semibold text-green-600">{{ job.salary }}</span>
            </div>
          </div>

          <div class="flex flex-wrap gap-2 mb-4">
            <span
              v-for="tag in job.tags"
              :key="tag"
              class="badge bg-gray-100 text-gray-600 text-xs"
            >
              {{ tag }}
            </span>
          </div>

          <div class="flex items-center justify-between pt-4 border-t border-gray-100">
            <span class="text-xs text-gray-400">{{ job.views }} 人浏览</span>
            <button @click="handleApply(job)" class="btn-primary text-sm flex items-center">
              <ExternalLink class="w-4 h-4 mr-1" />
              立即投递
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>