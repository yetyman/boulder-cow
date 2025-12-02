<template>
  <div class="home-board">
    <TitleText :title="'Home Board'" />
    <div class="board-content">
      <div class="grid-container">
        <template v-for="(row, i) in homeBoardData.buildingRows" :key="i">
        <template v-for="(col, j) in row.maxValue" :key="j">
          <div class="boulder-slot"
               :style="{ gridRow: i + 1, gridColumn: j + 1 }">
          </div>
        </template>
        </template>

        <div v-for="(row, i) in homeBoardData.buildingRows" :key="i"
             class="boulder"
             :style="{ gridRow: i + 1, gridColumn: row.boulder1.value + 1 }"
             @click="moveBoulder(i, row.boulder1, 'boulder1')">
        </div>
        <div v-for="(row, i) in homeBoardData.buildingRows" :key="i"
             class="boulder"
             :style="{ gridRow: i + 1, gridColumn: row.boulder2.value + 1 }"
             @click="moveBoulder(i, row.boulder2, 'boulder2')">
        </div>
        <BuildingTile v-for="(row, i) in homeBoardData.buildingRows" :key="i"
             :requirement="row.building"
             :index="i"
             :style="{
                gridRow: i + 1,
                gridColumn: `${(row.building.value || 0) + 1} / ${(row.building.value || 0) + 4}`
              }"
             @click="moveRequirement(i)" />
        </div>
      </div>
    </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import BuildingTile from './BuildingTile.vue'
import { GameStateStore, useGameState } from '../composables/useGameState'
import { computed } from 'vue'
import {HomeBoard, Boulder, Building, BuildingTracker} from '../types/api'

interface Props {
  playerIndex: number
}

const props = defineProps<Props>()
const { sendPatch } = useGameState()

const homeBoardData = computed((): HomeBoard => {
  return GameStateStore.table.playerAreas?.[props.playerIndex]?.homeBoard || {} as HomeBoard
})

const buildingRows = computed((): BuildingTracker[] => {
  return homeBoardData.value.buildingRows || Array(5).fill({})
})

const moveRequirement = async (index: number) => {
  const currentPos = buildingRows.value[index]?.building?.value
  const newPos = currentPos + 1
  
  // Check if boulder is blocking the movement
  const requirementRow = index
  const blockingBoulder = homeBoardData?.value?.buildingRows[requirementRow]?.boulder1.value === newPos + 2;
  if (blockingBoulder) {
    alert('Boulder is blocking this movement! Move the boulder first.')
    return
  }
  
  await sendPatch([{ op: 'replace', path: `/table/playerAreas/${props.playerIndex}/homeBoard/buildingRows/${index}/building/value`, value: newPos }])
}

const moveBoulder = async (rowIndex: number, boulder : Boulder, boulderKey: string) => {
  const newCol = boulder.value + 1
  
  // Check if another boulder is blocking the movement
  const blockingBoulder = homeBoardData.value.buildingRows[rowIndex].boulder2.value === newCol;
  if (blockingBoulder) {
    alert('Another boulder is blocking this movement!')
    return
  }
  
  await sendPatch([{ op: 'replace', path: `/table/playerAreas/${props.playerIndex}/homeBoard/buildingRows/${rowIndex}/${boulderKey}/value`, value: newCol }])
}
</script>

<style scoped>
.home-board {
  border: 1px solid #666;
  padding: 4px;
  background: #f0f0f0;
}

.board-content {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.grid-container {
  display: grid;
  grid-template-columns: repeat(13, 22px);
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