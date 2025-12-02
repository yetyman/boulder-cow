import { ref } from 'vue'
import { ActionRequest, ActionResponse } from '../types/actions'

const pendingChoices = ref<ActionResponse | null>(null)
const loading = ref(false)

export function useActions() {
  const sendAction = async (request: ActionRequest): Promise<ActionResponse> => {
    loading.value = true
    try {
      const response = await fetch('http://localhost:8080/api/game/action', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(request)
      })
      const result = await response.json()
      
      if (result.choices) {
        pendingChoices.value = result
      } else if (result.completed) {
        pendingChoices.value = null
      }
      
      return result
    } finally {
      loading.value = false
    }
  }

  const selectSpace = (locationId: string, playerId?: string) => {
    return sendAction({
      actionType: 'select',
      locationId,
      playerId
    })
  }

  const makeChoice = (choiceId: string, locationId: string) => {
    if (!pendingChoices.value) return Promise.resolve({ valid: false, message: 'No pending choices' })
    
    return sendAction({
      actionType: 'choose',
      locationId,
      actionData: choiceId
    })
  }

  const clearChoices = () => {
    pendingChoices.value = null
  }

  const undo = async () => {
    try {
      const response = await fetch('http://localhost:8080/api/game/undo', {
        method: 'POST'
      })
      return await response.json()
    } catch (err: any) {
      return { success: false, error: err.message }
    }
  }

  return {
    pendingChoices,
    loading,
    sendAction,
    selectSpace,
    makeChoice,
    clearChoices,
    undo
  }
}