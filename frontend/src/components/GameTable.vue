<template>
  <div class="game-table">
    <div class="top-row">
      <GameBoardTile />
      <LoadingPlaceholder :data="gameState.table.playerAreas" message="Loading players...">
        <div class="other-players">
          <PlayerAreaTile v-for="({ area, index }) in otherPlayerAreas" :key="index" :player="area.player" :playerIndex="index" />
        </div>
      </LoadingPlaceholder>
    </div>
    <div class="bottom-row">
      <PlayerAreaTile v-if="currentPlayerArea" :player="currentPlayerArea.player" :playerIndex="currentPlayerIndex" />
      <ActiveCards v-if="currentPlayerActiveCards.length > 0" :active-cards="currentPlayerActiveCards" />
    </div>
  </div>
</template>

<script setup lang="ts">
import GameBoardTile from './GameBoardTile.vue'
import PlayerAreaTile from './PlayerAreaTile.vue'
import LoadingPlaceholder from './LoadingPlaceholder.vue'
import ActiveCards from './ActiveCards.vue'
import { useGameState, GameStateStore } from '../composables/useGameState'
import { onMounted, computed } from 'vue'
import type { Card } from '../types/api'

const { gameState, loading, error, fetchGameState } = useGameState()

const currentPlayerIndex = computed(() => GameStateStore.currentPlayer)

const currentPlayerActiveCards = computed((): Card[] => {
  return GameStateStore.table.playerAreas?.[currentPlayerIndex.value]?.activeCards || []
})

const currentPlayerArea = computed(() => {
  return GameStateStore.table.playerAreas?.[currentPlayerIndex.value]
})

const otherPlayerAreas = computed(() => {
  return GameStateStore.table.playerAreas?.map((area, index) => ({ area, index })).filter(({ index }) => index !== currentPlayerIndex.value) || []
})

onMounted(() => {
  fetchGameState()
})
</script>

<style scoped>
.game-table {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.top-row {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.bottom-row {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.other-players {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}
</style>