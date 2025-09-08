<template>
  <div class="resource-tracker">
    <TitleText :title="'Resources'" />
    <LoadingPlaceholder :data="resourceData" message="Loading resource tracker...">
      <div class="tracker-content">
        <div class="grid-container">
          <div v-for="(counter, i) in resourceData.maxValue-1"
               :key="counter" 
               class="counter"
               :style="{ gridRow: i + 1, gridColumn: 1 }">
            {{ 5-i }};
          </div>
          
          <template v-for="(col, i) in farmLocations" :key="i">
            <FarmTile v-for="(row, j) in resourceData.maxValue-1"
                      :key="`${i}-${j}`" 
                      :tile="row"
                      :style="{ gridRow: j + 1, gridColumn: i + 2 }"
                       />
          </template>

          <template v-for="(farm, landIndex) in farmLocations"
                    :key="landIndex">
            <div v-if="farm !== null"
                 class="farm-land-overlay"
                 :style="{
                   gridRow: (6 - farm.value),
                   gridColumn: landIndex + 2
                 }"
                 @click="moveTile(farm, landIndex)">
              Land {{ farm.value }}
            </div>
          </template>
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
import { GameStateStore, useGameState } from '../composables/useGameState'
import { computed } from 'vue'
import type { ResourceTracker, FarmLand } from '../types/api'

interface Props {
  playerIndex: number
}

const props = defineProps<Props>()
const { sendPatch } = useGameState()

const resourceData = computed((): ResourceTracker => {
  return GameStateStore.table.playerAreas?.[props.playerIndex]?.resourceTracker || {} as ResourceTracker
})

const farmLocations = computed((): FarmLand[] => {
  return resourceData.value.farms || Array(7).fill({})
})

const moveTile = async (farm: FarmLand, col: number) => {
  const landIndex = col
  const currentValue = farm.value || 1
  const newValue = currentValue === 2 ? 5 : currentValue - 1
  
  await sendPatch([{ op: 'replace', path: `/table/playerAreas/${props.playerIndex}/resourceTracker/farms/${landIndex}/value`, value: newValue }])
}
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