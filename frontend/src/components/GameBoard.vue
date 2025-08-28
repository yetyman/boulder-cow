<template>
  <div class="game-board">
    <TitleText :title="'Main Board'" />
    <div class="board-content">
      <LoadingPlaceholder :data="deckTiles" message="Loading left decks...">
        <div class="deck-col">
          <DeckTile v-for="(tile, i) in deckTiles[0] || []" :key="i" :tile="tile" />
        </div>
      </LoadingPlaceholder>
      <LoadingPlaceholder :data="resourceTiles" message="Loading resource tiles...">
        <div class="resource-tiles">
          <div v-for="(row, i) in resourceTiles" :key="i" class="resource-row">
            <ResourceTile v-for="(tile, j) in row" :key="`${i}-${j}`" :tile="tile" />
          </div>
        </div>
      </LoadingPlaceholder>
      <LoadingPlaceholder :data="deckTiles" message="Loading right decks...">
        <div class="deck-col">
          <DeckTile v-for="(tile, i) in deckTiles[1] || []" :key="i" :tile="tile" />
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
import { GameStateStore } from '../composables/useGameState'
import { computed } from 'vue'
import type { SharedBoard, ResourceTile as ResourceTileType, DeckTile as DeckTileType } from '../types/api'

const boardData = computed((): SharedBoard => {
  return GameStateStore.table.sharedBoard || {} as SharedBoard
})

const resourceTiles = computed((): ResourceTileType[][] => {
  return boardData.value.resourceTiles || []
})

const deckTiles = computed((): DeckTileType[][] => {
  return boardData.value.deckTiles || []
})
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


</style>