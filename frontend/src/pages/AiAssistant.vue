<script setup lang="ts">
import { ref } from 'vue'
import { Bot, FileText, Video, Code, MessageCircle, Sparkles } from 'lucide-vue-next'

const activeTab = ref('chat')

const tabs = [
  { id: 'chat', name: '智能对话', icon: MessageCircle },
  { id: 'resume', name: '简历分析', icon: FileText },
  { id: 'interview', name: '模拟面试', icon: Video },
  { id: 'code', name: '代码评审', icon: Code },
]

const messages = ref([
  { id: 1, role: 'assistant', content: '你好！我是你的AI求职助手，我可以帮你分析简历、模拟面试、评审代码，或者解答任何技术问题。请问有什么我可以帮助你的吗？' },
])

const inputMessage = ref('')

const sendMessage = () => {
  if (!inputMessage.value.trim()) return
  
  messages.value.push({
    id: Date.now(),
    role: 'user',
    content: inputMessage.value
  })
  
  setTimeout(() => {
    messages.value.push({
      id: Date.now() + 1,
      role: 'assistant',
      content: '这是一个很好的问题！根据您的需求，我为您提供以下建议：\n\n1. 分析问题核心要点\n2. 提供可行的解决方案\n3. 给出具体的实施步骤\n\n如果您需要更详细的解答，请随时告诉我！'
    })
  }, 1000)
  
  inputMessage.value = ''
}
</script>

<template>
  <div class="min-h-screen pt-16 bg-gray-50">
    <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
      <div class="flex items-center mb-6">
        <div class="w-12 h-12 rounded-xl bg-gradient-primary flex items-center justify-center mr-4">
          <Bot class="w-6 h-6 text-white" />
        </div>
        <div>
          <h1 class="text-2xl font-bold text-gray-900">AI智能助手</h1>
          <p class="text-gray-600 text-sm">全方位求职辅导，助你斩获offer</p>
        </div>
      </div>

      <div class="card">
        <div class="flex border-b border-gray-100">
          <button
            v-for="tab in tabs"
            :key="tab.id"
            @click="activeTab = tab.id"
            :class="[
              'flex-1 flex items-center justify-center space-x-2 py-4 font-medium transition-colors',
              activeTab === tab.id
                ? 'text-primary-600 border-b-2 border-primary-600'
                : 'text-gray-500 hover:text-gray-700'
            ]"
          >
            <component :is="tab.icon" class="w-5 h-5" />
            <span>{{ tab.name }}</span>
          </button>
        </div>

        <div v-if="activeTab === 'chat'" class="h-[500px] flex flex-col">
          <div class="flex-1 overflow-y-auto p-6 space-y-4">
            <div
              v-for="message in messages"
              :key="message.id"
              :class="[
                'flex space-x-3',
                message.role === 'user' ? 'flex-row-reverse' : ''
              ]"
            >
              <div :class="[
                'w-10 h-10 rounded-full flex items-center justify-center flex-shrink-0',
                message.role === 'user' ? 'bg-primary-600 text-white' : 'bg-gray-100 text-gray-600'
              ]">
                <Bot v-if="message.role === 'assistant'" class="w-5 h-5" />
                <span v-else>U</span>
              </div>
              <div :class="[
                'max-w-[70%]',
                message.role === 'user' ? 'text-right' : ''
              ]">
                <p :class="[
                  'px-4 py-3 rounded-2xl',
                  message.role === 'user'
                    ? 'bg-primary-600 text-white rounded-tr-md'
                    : 'bg-gray-100 text-gray-700 rounded-tl-md'
                ]">
                  {{ message.content }}
                </p>
              </div>
            </div>
          </div>

          <div class="p-4 border-t border-gray-100">
            <div class="flex items-center space-x-3">
              <input
                v-model="inputMessage"
                @keyup.enter="sendMessage"
                type="text"
                placeholder="输入你的问题..."
                class="flex-1 input"
              />
              <button @click="sendMessage" class="btn-primary">
                <Sparkles class="w-5 h-5" />
              </button>
            </div>
          </div>
        </div>

        <div v-else-if="activeTab === 'resume'" class="p-6">
          <div class="text-center py-12">
            <div class="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mx-auto mb-4">
              <FileText class="w-10 h-10 text-gray-400" />
            </div>
            <h3 class="text-lg font-semibold text-gray-900 mb-2">简历分析</h3>
            <p class="text-gray-600 mb-6">上传您的简历，AI将为您提供专业的分析和优化建议</p>
            <button class="btn-primary">上传简历</button>
          </div>
        </div>

        <div v-else-if="activeTab === 'interview'" class="p-6">
          <div class="text-center py-12">
            <div class="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mx-auto mb-4">
              <Video class="w-10 h-10 text-gray-400" />
            </div>
            <h3 class="text-lg font-semibold text-gray-900 mb-2">模拟面试</h3>
            <p class="text-gray-600 mb-6">设定目标公司和职位，开始AI模拟面试</p>
            <button class="btn-primary">开始面试</button>
          </div>
        </div>

        <div v-else-if="activeTab === 'code'" class="p-6">
          <div class="text-center py-12">
            <div class="w-20 h-20 rounded-full bg-gray-100 flex items-center justify-center mx-auto mb-4">
              <Code class="w-10 h-10 text-gray-400" />
            </div>
            <h3 class="text-lg font-semibold text-gray-900 mb-2">代码评审</h3>
            <p class="text-gray-600 mb-6">粘贴您的代码，AI将为您提供专业的代码评审</p>
            <button class="btn-primary">开始评审</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>