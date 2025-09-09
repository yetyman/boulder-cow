<template>
  <div class="hand-of-cards">
    <TitleText :title="'Hand'" />
    <div ref="el"
        class="cards-container"
    >
      <div ref="items"
        v-for="(card, index) in localCards"
        :key="`card-${index}`"
        class="card"
        :style="{ zIndex: index }">
        <div class="card-content">
          <div class="card-title">{{ card.title || `Card ${index + 1}` }}</div>
          <div class="card-description">{{ card.description || 'Card description' }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import { ref, watch, computed } from 'vue'
import { useGameState } from '../composables/useGameState'
import type { Card } from '../types/api'
import {useDraggable, VueDraggable} from "vue-draggable-plus";

interface Props {
  cards: Card[]
  isCurrentPlayer?: boolean
  playerIndex?: number
}

const props = withDefaults(defineProps<Props>(), {
  isCurrentPlayer: false
})
const hoveredIndex = ref(-1)
const { sendPatch } = useGameState()

const localCards = ref([...props.cards])

const el = ref();
const draggable = useDraggable(el, localCards, {
  animation: 150,
  ghostClass: 'ghost',
  dragClass: 'drag',
  disabled: !props.isCurrentPlayer,
  onEnd: () => {
    if (props.isCurrentPlayer && props.playerIndex !== undefined) {
      const patches = localCards.value.map((card, index) => ({
        op: 'replace',
        path: `/table/playerAreas/${props.playerIndex}/hand/${index}`,
        value: card
      }))
      sendPatch(patches)
    }
  }
});

watch(() => props.cards, (newCards) => {
  localCards.value = [...newCards]
}, { deep: true })

</script>

<style scoped>
.hand-of-cards {
  border: 1px solid #666;
  padding: 10px;
  background: #f0f0f0;
  margin-top: 10px;
}

.cards-container {
  display: grid;
  grid-template-columns: repeat(auto-fit, 30px);
  height: 120px;
  margin-top: 10px;
  width: 250px;
}

.card {
  margin-right: -20px;
  width: 80px;
  height: 100px;
  background: #fff;
  border: 2px solid #333;
  border-radius: 8px;
  cursor: pointer;
  box-shadow: 0 2px 4px rgba(0,0,0,0.2);
  user-select: none;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}

.card-content {
  padding: 8px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.card-title {
  font-size: 10px;
  font-weight: bold;
  margin-bottom: 4px;
}

.card-description {
  font-size: 8px;
  color: #666;
  flex: 1;
}

[draggable="true"] {
  pointer-events: none;
  opacity: 0.85;
  background: transparent !important;
  border: 2px dashed #333 !important;
  z-index: 999 !important;
  box-shadow: 0 8px 16px rgba(0,0,0,0.3);
  transform: translateY(-15px) !important;
}

.cards-container:not(:has([draggable="true"])) .card:hover {
  /* CSS for when no drag exists */
  z-index: 999 !important;
  transform: translateY(-15px);
}

.drag {
  opacity: 0 !important;
  position: fixed;
  left: -100%;
}

.cards-container:has([draggable="true"]) .card:not([draggable="true"]) {
}

[draggable="true"] ~ .card {
  transform: rotate(8deg);
}

.card:has(~ [draggable="true"]) {
  transform: rotate(-8deg);
}

[draggable="true"] + .card {
  transform: rotate(4deg);
}

.card:has(+ [draggable="true"]) {
  transform: rotate(-4deg);
}





</style>