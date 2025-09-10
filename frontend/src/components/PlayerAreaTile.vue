<template>
  <div class="player-area">
    <TitleText :title="player.name" />
    <div class="player-content">
      <ResourceTracker :playerIndex="playerIndex" />
      <HomeBoardTile :playerIndex="playerIndex" />
      <HandOfCards :cards="playerHand" :is-current-player="playerIndex === currentPlayer" :player-index="playerIndex" />
      <TurnTracker :playerIndex="playerIndex" :cards="data.turnTracker.cards" :sheeps="data.turnTracker.sheeps" />
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
import type {Player, Card, PlayerArea} from '../types/api'
import TurnTracker from "./TurnTracker.vue";

interface Props {
  player: Player
  playerIndex: number
}

const props = defineProps<Props>()

const data = computed(() : PlayerArea =>{
  return GameStateStore.table.playerAreas?.[props.playerIndex];
})
const playerHand = computed((): Card[] => {
  return data.value?.hand || []
})

const currentPlayer = computed(() => GameStateStore.currentPlayer)
</script>

<style scoped>
.player-area {
  border: 1px solid #666;
  padding: 8px;
  background: #fafafa;
}

.player-content {
  display: flex;
  flex-direction: column;
  gap: 5px;
}

</style>