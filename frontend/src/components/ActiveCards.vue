<template>
  <div class="active-cards" @click="showPopup = true">
    <TitleText :title="'Active Cards'" />
    <div class="cards-grid">
      <div 
        v-for="(card, index) in activeCards" 
        :key="index"
        class="active-card">
        <div class="card-content">
          <div class="card-title">{{ card.title || `Active ${index + 1}` }}</div>
          <div class="card-description">{{ card.description || 'Active card' }}</div>
        </div>
      </div>
    </div>
  </div>
  
  <div v-if="showPopup" class="popup-overlay" @click="showPopup = false">
    <div class="popup-content" @click.stop>
      <h2>Active Cards</h2>
      <div class="popup-cards-grid">
        <div 
          v-for="(card, index) in activeCards" 
          :key="index"
          class="popup-card">
          <div class="popup-card-content">
            <div class="popup-card-title">{{ card.title || `Active ${index + 1}` }}</div>
            <div class="popup-card-description">{{ card.description || 'Active card' }}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import TitleText from './TitleText.vue'
import type { Card } from '../types/api'

interface Props {
  activeCards: Card[]
}

defineProps<Props>()

const showPopup = ref(false)
</script>

<style scoped>
.active-cards {
  border: 1px solid #666;
  padding: 10px;
  background: #f0f0f0;
  min-width: 200px;
  cursor: pointer;
  height: fit-content;
  max-height: 500px;
  display: flex;
  flex-direction: column;
}

.cards-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 10px;
  max-height: 600px;
  overflow-y: auto;
}

.active-card {
  width: 80px;
  height: 100px;
  background: #fff;
  border: 2px solid #333;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.2);
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

.popup-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.8);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.popup-content {
  background: white;
  padding: 10px;
  border-radius: 10px;
  width: 95vw;
  height: calc(98vh - 60px);
  overflow-y: auto;
  display: flex;
  flex-direction: column;
}

.popup-cards-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));
  gap: 15px;
  margin-top: 5px;
  flex: 1;
  min-height: 0;
}

.popup-card {
  width: 150px;
  height: 200px;
  background: #fff;
  border: 2px solid #333;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
}

.popup-card-content {
  padding: 15px;
  height: 100%;
  display: flex;
  flex-direction: column;
}

.popup-card-title {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 8px;
}

.popup-card-description {
  font-size: 12px;
  color: #666;
  flex: 1;
}
</style>