<template>
  <div class="home-board">
    <TitleText :title="'Home Board'" />
    <div class="board-content">
      <div class="grid-container">
        <template v-for="(row, i) in boulderGrid" :key="i">
          <div v-for="(slot, j) in row" 
               :key="`${i}-${j}`" 
               class="boulder-slot"
               :style="{ gridRow: i + 1, gridColumn: j + 1 }">
          </div>
        </template>
        
        <template v-for="(row, i) in boulders" :key="i">
          <div v-for="boulder in row" 
               :key="`boulder-${i}-${boulder.col}`" 
               class="boulder"
               :style="{ gridRow: boulder.row + 1, gridColumn: boulder.col + 1 }">
          </div>
        </template>
        
        <div v-for="(req, i) in feedingRequirements" 
             :key="i" 
             class="feeding-req" 
             :style="{ 
               gridRow: i + 1, 
               gridColumn: '1 / 3'
             }"
             @click="moveRequirement(i)">
          Feed {{ i + 1 }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import { GameStateStore } from '../composables/useGameState'
import { computed } from 'vue'
import type { HomeBoard, Boulder, BoulderPlacementLocation, FeedingRequirement } from '../types/api'

interface Props {
  playerIndex: number
}

const props = defineProps<Props>()

const homeBoardData = computed((): HomeBoard => {
  return GameStateStore.table.playerAreas?.[props.playerIndex]?.homeBoard || {} as HomeBoard
})

const boulderGrid = computed((): BoulderPlacementLocation[][] => {
  return homeBoardData.value.boulderGrid || Array(5).fill(null).map(() => Array(12).fill({}))
})

const boulders = computed((): Boulder[][] => {
  return homeBoardData.value.boulders || Array(5).fill(null).map(() => Array(2).fill({}))
})

const feedingRequirements = computed((): FeedingRequirement[] => {
  return homeBoardData.value.feedingRequirements || Array(5).fill({})
})

const moveRequirement = (index: number) => {
  // TODO: Handle feeding requirement movement
  console.log(`Moving requirement ${index + 1}`)
}
</script>

<style scoped>
.home-board {
  border: 1px solid #666;
  padding: 10px;
  background: #f0f0f0;
}

.board-content {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(12, 22px);
  grid-template-rows: repeat(5, 22px);
  gap: 1px;
}

.boulder-slot {
  border: 1px solid #ccc;
  background: #fff;
}

.boulder {
  width: 20px;
  height: 20px;
  background: #666;
  border-radius: 50%;
  border: 1px solid #333;
  z-index: 5;
}

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