<template>
  <div class="player-area">
    <TitleText :title="player.name" />
    <div class="player-content">
      <ResourceTracker :playerIndex="playerIndex" />
      <HomeBoard :playerIndex="playerIndex" />
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import ResourceTracker from './ResourceTracker.vue'
import HomeBoard from './HomeBoard.vue'
import type { Player } from '../types/api'

interface Props {
  player: Player
  playerIndex: number
}

defineProps<Props>()
</script>

<style scoped>
.player-area {
  border: 1px solid #666;
  padding: 15px;
  background: #fafafa;
  min-width: 300px;
}

.player-content {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.resource-tracker {
  padding: 10px;
  background: #e8e8e8;
  border: 1px solid #ccc;
}


</style>