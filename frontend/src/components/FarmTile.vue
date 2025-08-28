<template>
  <div class="farm-tile">
    <div class="tile-content"></div>
  </div>
</template>

<script setup lang="ts">
import type { FarmPlacementLocation } from '../types/api'

interface Props {
  tile?: FarmPlacementLocation
}

withDefaults(defineProps<Props>(), {
  tile: () => ({} as FarmPlacementLocation)
})
</script>

<style scoped>
.farm-tile {
  width: 25px;
  height: 25px;
  border: 1px solid #8b4513;
  background: #deb887;
}

.tile-content {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>