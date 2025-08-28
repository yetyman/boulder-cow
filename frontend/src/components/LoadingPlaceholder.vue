<template>
  <div v-if="isEmpty" class="loading-placeholder">
    <div class="placeholder-content">
      {{ message || 'Loading...' }}
    </div>
  </div>
  <slot v-else></slot>
</template>

<script setup lang="ts">
import { computed } from 'vue'

interface Props {
  data: any
  message?: string
}

const props = withDefaults(defineProps<Props>(), {
  message: 'Loading...'
})

const isEmpty = computed((): boolean => {
  if (props.data === null || props.data === undefined) return true
  if (Array.isArray(props.data)) return props.data.length === 0
  if (typeof props.data === 'object') return Object.keys(props.data).length === 0
  return false
})
</script>

<style scoped>
.loading-placeholder {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100px;
  border: 1px dashed #ccc;
  background: #f9f9f9;
}

.placeholder-content {
  color: #666;
  font-style: italic;
}
</style>