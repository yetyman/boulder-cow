<template>
  <div class="player-area">
    <TitleText :title="player.name" />
    <div class="player-content">
      <ResourceTracker :playerIndex="playerIndex" />
      <HomeBoardTile :playerIndex="playerIndex" />
      <HandOfCards :cards="playerHand" :is-current-player="playerIndex === currentPlayer" :player-index="playerIndex" />
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import ResourceTracker from './ResourceTracker.vue'
import HomeBoardTile from './HomeBoardTile.vue'
import HandOfCards from './HandOfCards.vue'
import { GameStateStore } from '../composables/useGameState'
import { computed } from 'vue'
import type { Player, Card } from '../types/api'

interface Props {
  player: Player
  playerIndex: number
}

const props = defineProps<Props>()

const playerHand = computed((): Card[] => {
  return GameStateStore.table.playerAreas?.[props.playerIndex]?.hand || []
})

const currentPlayer = computed(() => GameStateStore.currentPlayer)
</script>

<style scoped>
.player-area {
  border: 1px solid #666;
  padding: 8px;
  background: #fafafa;
  min-width: 300px;
}

.player-content {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.resource-tracker {
  padding: 10px;
  background: #e8e8e8;
  border: 1px solid #ccc;
}


</style>