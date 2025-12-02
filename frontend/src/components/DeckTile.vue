<template>
  <div class="deck-tile">
    <div class="tile-header">
      <div class="tile-title">{{ tile.title?.title || 'Deck' }}</div>
    </div>
    <div class="deck-area">
      Deck Cards
    </div>
    <div class="tile-grid">
      <div v-for="(location, i) in tile.row2.requiredWorkerCount" :key="`row2-${i}`"
           class="worker-slot"
           :class="{ 
             'filled': tile.row2.playerId !== null && tile.row2.playerId !== undefined,
             [`player-${tile.row2.playerId}`]: tile.row2.playerId !== null && tile.row2.playerId !== undefined,
             'clickable': canClickRow(2)
           }"
           :style="{ gridRow: 1, gridColumn: i + 1 }"
           @click="clickRow(2)">
      </div>
      <div v-for="(location, i) in tile.row1.requiredWorkerCount" :key="`row1-${i}`"
           class="worker-slot"
           :class="{ 
             'filled': tile.row1.playerId !== null && tile.row1.playerId !== undefined,
             [`player-${tile.row1.playerId}`]: tile.row1.playerId !== null && tile.row1.playerId !== undefined,
             'clickable': canClickRow(1)
           }"
           :style="{ gridRow: 2, gridColumn: i + 1 }"
           @click="clickRow(1)">
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import type { DeckTile as DeckTileType } from '../types/api'
import { GameStateStore, useGameState } from '../composables/useGameState'
import { computed } from 'vue'

interface Props {
  tile: DeckTileType
  tileRow: number
  tileCol: number
}

const props = defineProps<Props>()
const { sendPatch } = useGameState()

const currentPlayer = computed(() => GameStateStore.currentPlayer)

const canClickRow = (row: number) => {
  // Find the lowest unfilled row (row 2 is bottom for deck tiles)
  const row1IsEmpty = props.tile.row1.playerId === null;
  const row2IsEmpty = props.tile.row2.playerId === null;

  if(row === 1 && row1IsEmpty)
    return true;
  else if (row === 2 && row2IsEmpty && !row1IsEmpty)
    return true;
  else
    return false
}

const clickRow = async (row: number) => {
  if (!canClickRow(row)) return
  
  const rowKey = row === 1 ? 'row1' : 'row2'
  const rowData = props.tile[rowKey];
  
  const patches = {
    op: 'replace',
    path: `/table/sharedBoard/deckTiles/${props.tileRow}/${props.tileCol}/${rowKey}/playerId`,
    value: currentPlayer.value
  };
  
  await sendPatch([patches])
}
</script>

<style scoped>
.deck-tile {
  border: 1px solid #999;
  background: #d0d0d0;
  padding: 4px;
  min-width: 80px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.tile-header {
  text-align: center;
}

.tile-title {
  font-size: 10px;
  font-weight: bold;
}

.deck-area {
  height: 92px;
  background: #f8f8f8;
  border: 1px solid #aaa;
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
</style>