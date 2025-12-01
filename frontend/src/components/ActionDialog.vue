<template>
  <div v-if="pendingChoices" class="action-dialog">
    <h3>Choose an Action</h3>
    <div v-for="choice in pendingChoices.choices" :key="choice.choiceId" class="choice">
      <button @click="makeChoice(choice.choiceId, currentLocationId)">
        {{ choice.description }}
      </button>
      <div v-if="choice.requirement" class="requirement">
        Requirements: {{ formatRequirement(choice.requirement) }}
      </div>
    </div>
    <button @click="clearChoices">Cancel</button>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useActions } from '../composables/useActions'

const { pendingChoices, makeChoice, clearChoices } = useActions()
const currentLocationId = ref('sampleSpace') // Store current location

const formatRequirement = (req: any) => {
  return req ? JSON.stringify(req) : 'None'
}
</script>

<style scoped>
.action-dialog {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  border: 2px solid #333;
  padding: 20px;
  z-index: 1000;
}
.choice {
  margin: 10px 0;
}
.requirement {
  font-size: 0.8em;
  color: #666;
}
</style>