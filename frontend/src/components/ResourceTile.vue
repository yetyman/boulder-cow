<template>
  <div class="resource-tile">
    <div class="tile-header">
      <div class="tile-title">{{ tile.title?.title || 'Resource' }}</div>
    </div>
    <div class="tile-grid">
      <div v-for="(location, i) in tile.row3" :key="`row3-${i}`" 
           class="worker-slot"
           :style="{ gridRow: 1, gridColumn: i + 1 }">
      </div>
      <div v-for="(location, i) in tile.row2" :key="`row2-${i}`" 
           class="worker-slot"
           :style="{ gridRow: 2, gridColumn: i + 1 }">
      </div>
      <div v-for="(location, i) in tile.row1" :key="`row1-${i}`" 
           class="worker-slot"
           :style="{ gridRow: 3, gridColumn: i + 1 }">
      </div>
      <div v-if="tile.symbolicDisplay" 
           class="symbolic-display"
           :style="{ gridRow: '2 / 4', gridColumn: 3 }">
        Symbol
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { ResourceTile as ResourceTileType } from '../types/api'

interface Props {
  tile: ResourceTileType
}

defineProps<Props>()
</script>

<style scoped>
.resource-tile {
  border: 1px solid #999;
  background: #e0e0e0;
  padding: 5px;
  min-width: 80px;
}

.tile-header {
  text-align: center;
  margin-bottom: 5px;
}

.tile-title {
  font-size: 10px;
  font-weight: bold;
}

.tile-grid {
  display: grid;
  grid-template-columns: repeat(3, 20px);
  grid-template-rows: repeat(3, 20px);
  gap: 2px;
}

.worker-slot {
  border: 1px solid #666;
  background: #f0f0f0;
  border-radius: 50%;
}

.symbolic-display {
  background: rgba(200, 200, 200, 0.8);
  border: 1px solid #999;
  font-size: 8px;
  text-align: center;
  line-height: 20px;
}
</style>