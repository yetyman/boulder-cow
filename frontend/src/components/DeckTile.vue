<template>
  <div class="deck-tile">
    <div class="tile-header">
      <div class="tile-title">{{ tile.title?.title || 'Deck' }}</div>
    </div>
    <div class="deck-area">
      Deck Cards
    </div>
    <div class="tile-grid">
      <div v-for="(location, i) in tile.row2" :key="`row2-${i}`" 
           class="worker-slot"
           :style="{ gridRow: 1, gridColumn: i + 1 }">
      </div>
      <div v-for="(location, i) in tile.row1" :key="`row1-${i}`" 
           class="worker-slot"
           :style="{ gridRow: 2, gridColumn: i + 1 }">
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { DeckTile as DeckTileType } from '../types/api'

interface Props {
  tile: DeckTileType
}

defineProps<Props>()
</script>

<style scoped>
.deck-tile {
  border: 1px solid #999;
  background: #d0d0d0;
  padding: 10px;
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

.deck-area {
  height: 90px;
  background: #f8f8f8;
  border: 1px solid #aaa;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 12px;
  color: #666;
}

.tile-grid {
  display: grid;
  grid-template-columns: repeat(2, 20px);
  grid-template-rows: repeat(2, 20px);
  gap: 2px;
}

.worker-slot {
  border: 1px solid #666;
  background: #f0f0f0;
  border-radius: 50%;
}
</style>