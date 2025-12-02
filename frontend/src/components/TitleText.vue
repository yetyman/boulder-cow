<template>
  <div class="title-text">
    <span>@</span>
    <span class="title">{{ title }}</span>
    <span>@</span>
  </div>
</template>

<script setup lang="ts">
interface Props {
  title?: string
}

withDefaults(defineProps<Props>(), {
  title: ''
})
</script>

<style scoped>
.title-text {
  display: flex;
  align-items: center;
  margin-bottom: 4px;
}

.title {
  flex: 1;
  font-weight: bold;
  text-align: center;
}
</style>