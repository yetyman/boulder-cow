<template>
  <div class="game-table">
    <GameBoardTile />
    <LoadingPlaceholder :data="gameState.table.playerAreas" message="Loading players...">
      <div class="player-areas">
        <PlayerAreaTile v-for="(playerArea, index) in gameState.table.playerAreas" :key="index" :player="playerArea.player" :playerIndex="index" />
      </div>
    </LoadingPlaceholder>
  </div>
</template>

<script setup lang="ts">
import GameBoardTile from './GameBoardTile.vue'
import PlayerAreaTile from './PlayerAreaTile.vue'
import LoadingPlaceholder from './LoadingPlaceholder.vue'
import { useGameState } from '../composables/useGameState'
import { onMounted } from 'vue'

const { gameState, loading, error, fetchGameState } = useGameState()

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

.player-areas {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}
</style>