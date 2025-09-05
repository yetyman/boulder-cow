<template>
  <div class="resource-tile">
    <div class="tile-header">
      <div class="tile-title">{{ tile.title?.title || 'Resource' }}</div>
    </div>
    <div class="tile-grid">
      <div v-for="(location, i) in tile.row3" :key="`row3-${i}`" 
           class="worker-slot"
           :class="{ 
             'filled': location.playerId !== null && location.playerId !== undefined,
             [`player-${location.playerId}`]: location.playerId !== null && location.playerId !== undefined,
             'clickable': canClickRow(1)
           }"
           :style="{ gridRow: 1, gridColumn: i + 1 }"
           @click="clickRow(1)">
      </div>
      <div v-for="(location, i) in tile.row2" :key="`row2-${i}`" 
           class="worker-slot"
           :class="{ 
             'filled': location.playerId !== null && location.playerId !== undefined,
             [`player-${location.playerId}`]: location.playerId !== undefined,
             'clickable': canClickRow(2)
           }"
           :style="{ gridRow: 2, gridColumn: i + 1 }"
           @click="clickRow(2)">
      </div>
      <div v-for="(location, i) in tile.row1" :key="`row1-${i}`" 
           class="worker-slot"
           :class="{ 
             'filled': location.playerId !== null && location.playerId !== undefined,
             [`player-${location.playerId}`]: location.playerId !== undefined,
             'clickable': canClickRow(3)
           }"
           :style="{ gridRow: 3, gridColumn: i + 1 }"
           @click="clickRow(3)">
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
import { GameStateStore, useGameState } from '../composables/useGameState'
import { computed } from 'vue'

interface Props {
  tile: ResourceTileType
  tileRow: number
  tileCol: number
}

const props = defineProps<Props>()
const { sendPatch } = useGameState()

const currentPlayer = computed(() => GameStateStore.currentPlayer)

const canClickRow = (row: number) => {
  // Find the lowest unfilled row (highest number since row 3 is bottom)
  const row1HasEmpty = props.tile.row1?.some(loc => loc.playerId === null || loc.playerId === undefined)
  const row2HasEmpty = props.tile.row2?.some(loc => loc.playerId === null || loc.playerId === undefined)
  const row3HasEmpty = props.tile.row3?.some(loc => loc.playerId === null || loc.playerId === undefined)
  
  if (row1HasEmpty) return row === 3
  if (row2HasEmpty) return row === 2
  if (row3HasEmpty) return row === 1
  return false
}

const clickRow = async (row: number) => {
  console.log('Row clicked:', row, 'Can click:', canClickRow(row))
  if (!canClickRow(row)) return
  
  const rowKey = row === 1 ? 'row3' : row === 2 ? 'row2' : 'row1'
  const rowData = props.tile[rowKey] || []
  console.log('Row data:', rowData, 'Current player:', currentPlayer.value)
  
  const patches = rowData.map((_, i) => ({
    op: 'replace',
    path: `/table/sharedBoard/resourceTiles/${props.tileRow}/${props.tileCol}/${rowKey}/${i}/playerId`,
    value: currentPlayer.value
  }))
  
  console.log('Sending patches:', patches)
  await sendPatch(patches)
}
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

.worker-slot.clickable {
  cursor: pointer;
}

.worker-slot.clickable:hover {
  background: #e0e0e0;
}

.worker-slot.filled {
  border: 2px solid #333 !important;
}

.worker-slot.player-0 { background: #ff6b6b !important; }
.worker-slot.player-1 { background: #4ecdc4 !important; }
.worker-slot.player-2 { background: #45b7d1 !important; }
.worker-slot.player-3 { background: #96ceb4 !important; }

.symbolic-display {
  background: rgba(200, 200, 200, 0.8);
  border: 1px solid #999;
  font-size: 8px;
  text-align: center;
  line-height: 20px;
}
</style>