<template>
  <div class="resource-tracker">
    <TitleText :title="'Resources'" />
    <LoadingPlaceholder :data="resourceData" message="Loading resource tracker...">
      <div class="tracker-content">
        <div class="grid-container">
          <div v-for="(counter, i) in counters.slice(0, farmGrid.length)" 
               :key="counter" 
               class="counter"
               :style="{ gridRow: i + 1, gridColumn: 1 }">
            {{ 5-i }}
          </div>
          
          <template v-for="(row, i) in farmGrid" :key="i">
            <FarmTile v-for="(tile, j) in row" 
                      :key="`${i}-${j}`" 
                      :tile="tile"
                      :style="{ gridRow: i + 1, gridColumn: j + 2 }" />
          </template>
          
          <div v-for="(land, landIndex) in farmLands" 
               :key="land" 
               class="farm-land-overlay"
               :style="{ 
                 gridRow: (6 - land.value), 
                 gridColumn: landIndex + 2 
               }">
            Land {{ land.value }}
          </div>
        </div>
        <div class="one-row">
          <div class="one-counter">1</div>
          <div class="one-farm"></div>
        </div>
      </div>
    </LoadingPlaceholder>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import FarmTile from './FarmTile.vue'
import LoadingPlaceholder from './LoadingPlaceholder.vue'
import { GameStateStore } from '../composables/useGameState'
import { computed } from 'vue'
import type { ResourceTracker, Counter, FarmPlacementLocation, FarmLand } from '../types/api'

interface Props {
  playerIndex: number
}

const props = defineProps<Props>()

const resourceData = computed((): ResourceTracker => {
  return GameStateStore.table.playerAreas?.[props.playerIndex]?.resourceTracker || {} as ResourceTracker
})

const counters = computed((): Counter[] => {
  return resourceData.value.counters || [1, 2, 3, 4, 5] as Counter[]
})

const farmGrid = computed((): FarmPlacementLocation[][] => {
  return resourceData.value.farm || Array(4).fill(null).map(() => Array(7).fill({}))
})

const farmLands = computed((): FarmLand[] => {
  return resourceData.value.farmLand || [{}, {}, {}] as FarmLand[]
})
</script>

<style scoped>
.resource-tracker {
  border: 1px solid #999;
  padding: 10px;
  background: #f9f9f9;
}

.tracker-content {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.grid-container {
  display: grid;
  grid-template-columns: 40px repeat(7, 27px);
  grid-template-rows: repeat(4, 27px);
  gap: 2px;
}

.counter {
  background: #ddd;
  border: 1px solid #999;
  text-align: center;
  line-height: 25px;
  font-size: 12px;
}

.farm-land-overlay {
  background: rgba(139, 69, 19, 0.8);
  color: white;
  font-size: 8px;
  text-align: center;
  line-height: 25px;
  border: 1px solid #8b4513;
  z-index: 10;
}

.one-row {
  display: flex;
  gap: 2px;
  margin-top: 2px;
}

.one-counter {
  width: 40px;
  height: 25px;
  background: #ddd;
  border: 1px solid #999;
  text-align: center;
  line-height: 23px;
}

.one-farm {
  flex: 1;
  height: 25px;
  background: #deb887;
  border: 1px solid #8b4513;
}
</style>