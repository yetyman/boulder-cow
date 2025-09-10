<template>
  <div class="turn-tracker">
    <TitleText :title="'Turn Tracker'" />
    <div class="tracker-content">
      <div class="card-grid">
        <div 
          v-for="(card, index) in cards" 
          :key="index"
          class="card-spot"
        >
          <div v-if="card" class="card">{{ card.name }}</div>
          <div v-if="sheeps[index] > 0" class="sheep-overlay">
            🐑 {{ sheeps[index] }}
          </div>
        </div>
      </div>
      <div class="sheep-bar">
        <div class="sheep-display">🐑 Safe Sheep</div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import type { Card } from '../types/api'

interface Props {
  playerIndex: number
  cards: Card[]
  sheeps: number[]
}

withDefaults(defineProps<Props>(), {
  cards: () => new Array(6).fill(null),
  sheeps: () => new Array(6).fill(0)
})
</script>

<style scoped>
.turn-tracker {
  border: 1px solid #666;
  padding: 10px;
  background: #f0f0f0;
}

.tracker-content {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.card-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 2px;
  max-width: 250px;
}

.card-spot {
  position: relative;
  width: 80px;
  height: 100px;
  border: 1px solid #ccc;
  background: #fff;
}

.card {
  width: 100%;
  height: 100%;
  background: #f0f0f0;
  border: 1px solid #333;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 10px;
}

.sheep-overlay {
  position: absolute;
  top: -8px;
  right: -8px;
  background: white;
  border-radius: 50%;
  padding: 2px 4px;
  font-size: 10px;
  border: 1px solid #ddd;
}

.sheep-bar {
  width: 100%;
  height: 25px;
  background: #e8f5e8;
  border: 1px solid #999;
  display: flex;
  align-items: center;
  padding: 0 8px;
}

.sheep-display {
  font-size: 12px;
}
</style>