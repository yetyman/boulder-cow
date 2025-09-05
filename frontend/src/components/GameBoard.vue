<template>
  <div class="game-board">
    <TitleText :title="'Main Board'" />
    <button @click="clearTopRows" class="clear-button">Clear Top Rows</button>
    <div class="board-content">
      <LoadingPlaceholder :data="deckTiles" message="Loading left decks...">
        <div class="deck-col">
          <DeckTile v-for="(tile, i) in deckTiles[0] || []" :key="i" :tile="tile" :tile-row="0" :tile-col="i" />
        </div>
      </LoadingPlaceholder>
      <LoadingPlaceholder :data="resourceTiles" message="Loading resource tiles...">
        <div class="resource-tiles">
          <div v-for="(row, i) in resourceTiles" :key="i" class="resource-row">
            <ResourceTile v-for="(tile, j) in row" :key="`${i}-${j}`" :tile="tile" :tile-row="i" :tile-col="j" />
          </div>
        </div>
      </LoadingPlaceholder>
      <LoadingPlaceholder :data="deckTiles" message="Loading right decks...">
        <div class="deck-col">
          <DeckTile v-for="(tile, i) in deckTiles[1] || []" :key="i" :tile="tile" :tile-row="1" :tile-col="i" />
        </div>
      </LoadingPlaceholder>
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import LoadingPlaceholder from './LoadingPlaceholder.vue'
import ResourceTile from './ResourceTile.vue'
import DeckTile from './DeckTile.vue'
import { GameStateStore, useGameState } from '../composables/useGameState'
import { computed } from 'vue'
import type { SharedBoard, ResourceTile as ResourceTileType, DeckTile as DeckTileType } from '../types/api'

const { sendPatch } = useGameState()

const boardData = computed((): SharedBoard => {
  return GameStateStore.table.sharedBoard || {} as SharedBoard
})

const resourceTiles = computed((): ResourceTileType[][] => {
  return boardData.value.resourceTiles || []
})

const deckTiles = computed((): DeckTileType[][] => {
  return boardData.value.deckTiles || []
})

const clearTopRows = async () => {
  const patches: any[] = []
  
  // Clear resource tiles
  resourceTiles.value.forEach((row, i) => {
    row.forEach((tile, j) => {
      // Find topmost filled row (row3 is top)
      if (tile.row3?.some(loc => loc.playerId !== null && loc.playerId !== undefined)) {
        tile.row3.forEach((_, k) => {
          patches.push({ op: 'replace', path: `/table/sharedBoard/resourceTiles/${i}/${j}/row3/${k}/playerId`, value: null })
        })
      } else if (tile.row2?.some(loc => loc.playerId !== null && loc.playerId !== undefined)) {
        tile.row2.forEach((_, k) => {
          patches.push({ op: 'replace', path: `/table/sharedBoard/resourceTiles/${i}/${j}/row2/${k}/playerId`, value: null })
        })
      } else if (tile.row1?.some(loc => loc.playerId !== null && loc.playerId !== undefined)) {
        tile.row1.forEach((_, k) => {
          patches.push({ op: 'replace', path: `/table/sharedBoard/resourceTiles/${i}/${j}/row1/${k}/playerId`, value: null })
        })
      }
    })
  })
  
  // Clear deck tiles
  deckTiles.value.forEach((row, i) => {
    row.forEach((tile, j) => {
      // Find topmost filled row (row2 is top for deck tiles)
      if (tile.row2?.some(loc => loc.playerId !== null && loc.playerId !== undefined)) {
        tile.row2.forEach((_, k) => {
          patches.push({ op: 'replace', path: `/table/sharedBoard/deckTiles/${i}/${j}/row2/${k}/playerId`, value: null })
        })
      } else if (tile.row1?.some(loc => loc.playerId !== null && loc.playerId !== undefined)) {
        tile.row1.forEach((_, k) => {
          patches.push({ op: 'replace', path: `/table/sharedBoard/deckTiles/${i}/${j}/row1/${k}/playerId`, value: null })
        })
      }
    })
  })
  
  if (patches.length > 0) {
    console.log(`Clearing ${patches.length} total patches:`, patches)
    await sendPatch(patches)
  }
}
</script>

<style scoped>
.game-board {
  border: 2px solid #333;
  padding: 20px;
  background: #f5f5f5;
  width: fit-content;
}

.board-content {
  display: flex;
  flex-direction: row;
  gap: 20px;
}

.resource-tiles {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.resource-row {
  display: flex;
  gap: 5px;
}



.deck-col {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 5px;
}

.clear-button {
  padding: 10px 20px;
  background: #ff4444;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-bottom: 10px;
}

.clear-button:hover {
  background: #cc3333;
}


</style>