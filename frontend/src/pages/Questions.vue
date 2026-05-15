<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { BookOpen, Search, Filter, ChevronRight, CheckCircle, XCircle } from 'lucide-vue-next'

const categories = ref([
  { id: 1, name: 'Java基础', count: 580 },
  { id: 2, name: 'Spring框架', count: 320 },
  { id: 3, name: 'MySQL数据库', count: 280 },
  { id: 4, name: 'Redis缓存', count: 150 },
  { id: 5, name: '数据结构', count: 420 },
  { id: 6, name: '算法', count: 380 },
])

const questions = ref([
  { id: 1, title: 'Java中equals和==的区别', category: 'Java基础', difficulty: 'easy', views: 2340, likes: 156 },
  { id: 2, title: 'HashMap的实现原理', category: 'Java基础', difficulty: 'medium', views: 1890, likes: 123 },
  { id: 3, title: 'Spring IoC和DI的理解', category: 'Spring框架', difficulty: 'medium', views: 1650, likes: 98 },
  { id: 4, title: 'MySQL索引的种类和使用场景', category: 'MySQL数据库', difficulty: 'hard', views: 1420, likes: 87 },
  { id: 5, title: 'Redis的五种数据结构', category: 'Redis缓存', difficulty: 'easy', views: 1280, likes: 76 },
  { id: 6, title: '二叉树的遍历方式', category: '数据结构', difficulty: 'medium', views: 1150, likes: 65 },
])

const searchQuery = ref('')
const selectedCategory = ref<number | null>(null)
const selectedDifficulty = ref('')
const currentQuestion = ref(null)
const showAnswer = ref(false)

const difficulties = [
  { value: '', label: '全部难度' },
  { value: 'easy', label: '简单' },
  { value: 'medium', label: '中等' },
  { value: 'hard', label: '困难' },
]

const selectQuestion = (question: any) => {
  currentQuestion.value = question
  showAnswer.value = false
}

const toggleAnswer = () => {
  showAnswer.value = !showAnswer.value
}

onMounted(() => {})
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h1 class="text-2xl font-bold text-gray-900">面试题库</h1>
          <p class="text-gray-600 text-sm mt-1">精选Java后端面试高频题目</p>
        </div>
        <div class="flex items-center space-x-4">
          <div class="relative">
            <Search class="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400" />
            <input
              v-model="searchQuery"
              type="text"
              placeholder="搜索题目..."
              class="input pl-10 w-64"
            />
          </div>
          <button class="btn-secondary flex items-center">
            <Filter class="w-4 h-4 mr-2" />
            筛选
          </button>
        </div>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-4 gap-6">
        <div class="lg:col-span-1">
          <div class="card sticky top-24">
            <h3 class="font-semibold text-gray-900 mb-4 flex items-center">
              <BookOpen class="w-5 h-5 mr-2 text-primary-600" />
              题目分类
            </h3>
            <div class="space-y-2">
              <button
                v-for="cat in categories"
                :key="cat.id"
                @click="selectedCategory = selectedCategory === cat.id ? null : cat.id"
                :class="[
                  'w-full flex items-center justify-between px-4 py-3 rounded-lg transition-colors',
                  selectedCategory === cat.id
                    ? 'bg-primary-50 text-primary-700'
                    : 'bg-gray-50 text-gray-600 hover:bg-gray-100'
                ]"
              >
                <span>{{ cat.name }}</span>
                <span class="text-sm text-gray-400">{{ cat.count }}</span>
              </button>
            </div>

            <div class="mt-6">
              <h4 class="text-sm font-medium text-gray-700 mb-3">难度筛选</h4>
              <select
                v-model="selectedDifficulty"
                class="input w-full"
              >
                <option v-for="diff in difficulties" :key="diff.value" :value="diff.value">
                  {{ diff.label }}
                </option>
              </select>
            </div>
          </div>
        </div>

        <div class="lg:col-span-3">
          <div class="space-y-4">
            <div
              v-for="question in questions"
              :key="question.id"
              @click="selectQuestion(question)"
              :class="[
                'card cursor-pointer',
                currentQuestion?.id === question.id ? 'ring-2 ring-primary-500' : ''
              ]"
            >
              <div class="flex items-start justify-between">
                <div class="flex-1">
                  <h3 class="font-medium text-gray-900 mb-2">{{ question.title }}</h3>
                  <div class="flex items-center space-x-3">
                    <span class="badge bg-primary-100 text-primary-700 text-xs">{{ question.category }}</span>
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
              <div class="flex items-center mt-3 text-sm text-gray-400">
                <span>{{ question.views }} 浏览</span>
                <span class="mx-2">·</span>
                <span>{{ question.likes }} 点赞</span>
              </div>
            </div>
          </div>

          <div v-if="currentQuestion" class="mt-6 card">
            <div class="flex items-center justify-between mb-4">
              <h3 class="text-lg font-semibold text-gray-900">{{ currentQuestion.title }}</h3>
              <button @click="toggleAnswer" class="btn-primary text-sm">
                {{ showAnswer ? '隐藏答案' : '查看答案' }}
              </button>
            </div>
            
            <div class="p-4 bg-gray-50 rounded-lg mb-4">
              <p class="text-gray-700">
                这是一道关于{{ currentQuestion.category }}的面试题，请思考并作答。
              </p>
            </div>

            <div v-if="showAnswer" class="p-4 bg-green-50 rounded-lg border border-green-200">
              <div class="flex items-center text-green-700 mb-2">
                <CheckCircle class="w-5 h-5 mr-2" />
                <span class="font-medium">参考答案</span>
              </div>
              <p class="text-gray-700 text-sm leading-relaxed">
                {{ currentQuestion.difficulty === 'easy' ? 
                  '这是一个简单的问题。equals方法比较的是对象的内容，而==比较的是对象的引用地址。在Object类中，equals方法默认使用==实现，但很多类如String、Integer等都重写了equals方法来比较内容。' :
                  currentQuestion.difficulty === 'medium' ?
                  'HashMap基于哈希表实现，使用数组+链表/红黑树存储。当发生哈希冲突时，会将元素放入链表中，当链表长度超过8且数组长度大于64时，链表会转换为红黑树以提高查询效率。HashMap允许null键和null值。' :
                  'MySQL索引主要有B+树索引、哈希索引、全文索引等。B+树索引是最常用的，适合范围查询；哈希索引适合等值查询但不支持范围查询；全文索引适合全文搜索。合理使用索引可以大幅提升查询性能，但过多的索引会影响写入性能。'
                }}
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>