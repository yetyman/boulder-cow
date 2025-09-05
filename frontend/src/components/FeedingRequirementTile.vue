<template>
  <div class="feeding-req" @click="$emit('click')">
    Feed {{ index + 1 }}
  </div>
</template>

<script setup lang="ts">
import type { FeedingRequirement } from '../types/api'

interface Props {
  requirement: FeedingRequirement
  index: number
}

defineProps<Props>()
defineEmits<{
  click: []
}>()
</script>

<style scoped>
.feeding-req {
  background: rgba(224, 224, 224, 0.9);
  border: 1px solid #999;
  font-size: 10px;
  text-align: center;
  line-height: 20px;
  cursor: pointer;
  z-index: 10;
  box-sizing: border-box;
}
</style>