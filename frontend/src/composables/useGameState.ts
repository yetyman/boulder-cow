import { ref, reactive } from 'vue'
import { Api, GameState, Player, Table } from '../types/api'

const api = new Api()

// Global reactive game state
const globalGameState = reactive<GameState>({
  table: {
    currentPlayerIndex: 0,
    sharedBoard: {
      resourceTiles: [],
      deckTiles: []
    },
    playerAreas: []
  },
  version: 0
});

let clientVersion = 0
let lastServerVersion = 0
let websocket: WebSocket | null = null

const loading = ref(false)
const error = ref<string | null>(null)

function applyJsonPatch(target: any, patches: any[]) {
  patches.forEach(patch => {
    const path = patch.path.split('/').filter(Boolean)
    let obj = target
    
    if (patch.op === 'replace' || patch.op === 'add') {
      for (let i = 0; i < path.length - 1; i++) {
        obj = obj[path[i]]
      }
      obj[path[path.length - 1]] = patch.value
    }
  })
}

// Static access to game state
export const GameStateStore = {
  get state(): GameState {
    return globalGameState
  },
  
  get players(): Player[] {
    return globalGameState.table.playerAreas.map(p=>p.player) || []
  },
  
  get table(): Table {
    return globalGameState.table || {};
  },
  
  get currentPlayer(): number {
    return globalGameState.table.currentPlayerIndex || 0
  },
  
  get loading(): boolean {
    return loading.value
  },
  
  get error(): string | null {
    return error.value
  }
}

function connectWebSocket() {
  if (websocket) return
  
  websocket = new WebSocket('ws://localhost:8080/ws/game')
  
  websocket.onmessage = (event) => {
    try {
      const data = JSON.parse(event.data)
      console.log('WebSocket received:', data)
      if (data.diff && Array.isArray(data.diff)) {
        if (data.diff.length > 0) {
          console.log('Applying diff:', data.diff)
          applyJsonPatch(globalGameState, data.diff)
        } else {
          console.log('Empty diff - client already in sync')
        }
        globalGameState.version = data.serverVersion
        lastServerVersion = data.serverVersion
      } else {
        console.log('Skipping WebSocket message - no valid diff')
      }
    } catch (err) {
      console.error('WebSocket message error:', err, event.data)
    }
  }
  
  websocket.onclose = () => {
    websocket = null
    setTimeout(connectWebSocket, 1000) // Reconnect after 1 second
  }
}

export function useGameState() {
  const fetchGameState = async (useDelta = true) => {
    console.log('Fetching game state...')
    loading.value = true
    error.value = null
    try {
      const endpoint = useDelta && globalGameState.version 
        ? `/api/game/delta?since=${globalGameState.version}`
        : '/api/game/state'
      
      const response = await fetch(`http://localhost:8080${endpoint}`)
      const data = await response.json()
      
      if (data.diff && Array.isArray(data.diff) && data.diff.length > 0) {
        // Apply JSON patch
        applyJsonPatch(globalGameState, data.diff)
        globalGameState.version = data.version
        lastServerVersion = data.serverVersion || data.version
        console.log(`Server last knew client version: ${data.lastClientVersion}`)
      } else if (!data.diff) {
        // Full state or table property
        if (data.table) {
          globalGameState.table = data.table
          globalGameState.version = data.version
        } else {
          Object.assign(globalGameState, data)
        }
        lastServerVersion = data.serverVersion || data.version || 0
        console.log(`Server last knew client version: ${data.lastClientVersion}`)
      }
    } catch (err: any) {
      console.error('Fetch error:', err)
      error.value = err.message || 'Failed to fetch game state'
    } finally {
      loading.value = false
    }
  }

  const performAction = async (action: string) => {
    try {
      const response = await fetch('http://localhost:8080/api/game/action', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(action)
      })
      const data = await response.json()
      Object.assign(globalGameState, data)
    } catch (err: any) {
      error.value = err.message || 'Failed to perform action'
    }
  }

  const sendPatch = async (patches: any[]) => {
    try {
      clientVersion++
      const payload = {
        patches,
        clientVersion,
        lastServerVersion
      }
      
      console.log('Sending patch:', patches)
      const response = await fetch('http://localhost:8080/api/game/patch', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      })
      const result = await response.json()
      console.log('Patch result:', result)
      
      if (result.success) {
        // Apply our own patch since server accepted it
        console.log('Applying own patch to client state')
        applyJsonPatch(globalGameState, patches)
        globalGameState.version = result.serverVersion
        lastServerVersion = result.serverVersion
        console.log(`Server last knew client version: ${result.lastClientVersion}`)
      } else if (result.alignmentPatch) {
        // Handle conflict by applying alignment patch
        console.log('Conflict detected, applying alignment patch')
        applyJsonPatch(globalGameState, result.alignmentPatch)
        globalGameState.version = result.serverVersion
        lastServerVersion = result.serverVersion
      }
      return result
    } catch (err: any) {
      error.value = err.message || 'Failed to send patch'
      return { success: false, error: err.message }
    }
  }

  const updateField = async (path: string, value: any) => {
    return sendPatch([{ op: 'replace', path, value }])
  }

  // Connect WebSocket on first use
  if (!websocket) {
    connectWebSocket()
  }

  return {
    gameState: globalGameState,
    loading,
    error,
    fetchGameState,
    performAction,
    sendPatch,
    updateField
  }
}