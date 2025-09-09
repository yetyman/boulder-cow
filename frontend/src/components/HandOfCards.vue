<template>
  <div class="hand-of-cards">
    <TitleText :title="'Hand'" />
    <VueDraggable
        class="cards-container"
        v-model="localCards"
        animation="150"
        ghostClass="ghost"
    >
      <div
        v-for="(card, index) in localCards"
        :key="`card-${index}`"
        class="card"
        :class="{ 'hovered': hoveredIndex === index }"
        @mouseenter="props.isCurrentPlayer ? hoveredIndex = index : null"
        @mouseleave="props.isCurrentPlayer ? hoveredIndex = -1 : null">
        <div class="card-content">
          <div class="card-title">{{ card.title || `Card ${index + 1}` }}</div>
          <div class="card-description">{{ card.description || 'Card description' }}</div>
        </div>
      </div>
    </VueDraggable>
  </div>
</template>

<script setup lang="ts">
import TitleText from './TitleText.vue'
import { ref, watch, computed } from 'vue'
import { useGameState } from '../composables/useGameState'
import type { Card } from '../types/api'
import {VueDraggable} from "vue-draggable-plus";

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

watch(() => props.cards, (newCards) => {
  localCards.value = [...newCards]
}, { deep: true })

const getCardStyle = (index: number) => {
  const cardCount = props.cards.length
  const maxSpacing = Math.min(40, 170 / Math.max(1, cardCount - 1))
  const baseLeft = index * maxSpacing
  
  let left = baseLeft
  let scale = 1
  let translateY = 0
  let zIndex = index

  if (props.isCurrentPlayer && hoveredIndex.value !== -1) {
    if (index === hoveredIndex.value) {
      scale = 1.2
      translateY = -20
      zIndex = 100
    } else if (index < hoveredIndex.value) {
      left = baseLeft - 20
    } else if (index > hoveredIndex.value) {
      left = baseLeft + 20
    }
  }

  return {
    left: `${left}px`,
    zIndex,
    transform: `scale(${scale}) translateY(${translateY}px)`,
    transition: 'all 0.3s cubic-bezier(0.4, 0, 0.2, 1)'
  }
}
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
  grid-template-columns: repeat(auto-fit, 20px);
  height: 120px;
  margin-top: 10px;
  width: 250px;
}

.card {
  margin-right: -50px;
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

.card.hovered {
  box-shadow: 0 8px 16px rgba(0,0,0,0.3);
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






</style>