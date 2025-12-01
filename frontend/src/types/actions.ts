export interface ActionRequest {
  actionType: string
  locationId: string
  actionData?: any
  playerId?: string
}

export interface ActionChoice {
  choiceId: string
  description: string
  requirement?: any
  choiceData?: any
}

export interface ActionResponse {
  valid: boolean
  message?: string
  choices?: ActionChoice[]
  completed?: boolean
}