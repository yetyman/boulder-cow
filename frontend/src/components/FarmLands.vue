<template>
  <div class="farm-lands">
    <TitleText :title="'Farm Lands'" />
    <div class="lands-grid">
      <div v-for="(land, index) in farmLands" :key="index" class="farm-land">
        <div class="land-content">
          Land {{ index + 1 }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import type { FarmLand } from '../types/api'

interface Props {
  farmLands?: FarmLand[]
}

withDefaults(defineProps<Props>(), {
  farmLands: () => []
})
</script>

<style scoped>
.farm-lands {
  border: 1px solid #8b4513;
  padding: 8px;
  background: #f4e4bc;
}

.lands-grid {
  display: flex;
  gap: 5px;
}

.farm-land {
  flex: 1;
  min-height: 60px;
  border: 1px solid #8b4513;
  background: #deb887;
}

.land-content {
  padding: 10px;
  text-align: center;
  font-size: 12px;
}
</style>