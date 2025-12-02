# Boulder Cow
A fun web-based board game inspired by but wholly unaffiliated with Hallertau

## Architecture Overview

### Frontend-Backend Communication
- **Frontend**: Vue 3 + TypeScript SPA running on port 5173
- **Backend**: Spring Boot REST API + WebSocket server on port 8080
- **Real-time Updates**: WebSocket connection for live game state synchronization
- **API Proxy**: Vite dev server proxies `/api` requests to backend

### Backend Architecture (Spring Boot)

#### Core Components
- **BouldercowApp**: Main Spring Boot application
- **GameController**: REST endpoints for game state management and actions
- **GameWebSocketHandler**: WebSocket handler for real-time state broadcasting
- **ActionManager**: Processes game actions with pluggable processors

#### Game State Management
- **Differential Updates**: JSON Patch-based state synchronization
- **Conflict Resolution**: Client-server state alignment with conflict detection
- **Version Control**: Incremental versioning for state consistency
- **Session Management**: Per-client state caching and version tracking

#### Domain Model
- **Game Areas**: SharedBoard and PlayerArea components
- **Card System**: Card definitions with fluent requirement/effect builders
- **Resource Management**: Resource tracking and worker placement mechanics
- **Action Flow**: Phase-based game progression with timing requirements

### Frontend Architecture (Vue 3)

#### State Management
- **useGameState**: Composable for reactive game state management
- **Global Store**: Reactive game state with automatic UI updates
- **JSON Patch Integration**: Client-side patch application and conflict handling
- **WebSocket Client**: Automatic reconnection and real-time updates

#### Component Structure
- **GameTable**: Main game board container
- **Tile Components**: Modular UI tiles for different game areas
- **Action System**: Dialog-based action selection and execution
- **Drag & Drop**: Vue-draggable-plus for interactive game pieces

### Technology Stack

#### Backend
- **Spring Boot 3.5.5**: Web framework with WebSocket support
- **Jackson**: JSON serialization/deserialization
- **ZJsonPatch**: JSON Patch operations for differential updates
- **SpringDoc OpenAPI**: API documentation and type generation

#### Frontend
- **Vue 3**: Reactive UI framework with Composition API
- **Vite**: Build tool and development server
- **Axios**: HTTP client for API communication
- **Fast-JSON-Patch**: Client-side JSON Patch operations
- **TypeScript**: Type-safe API integration via generated types

### Development Workflow
- **Type Generation**: Swagger-to-TypeScript API client generation
- **Docker Compose**: Containerized development environment
- **Hot Reload**: Vite dev server with proxy configuration
- **CORS Configuration**: Cross-origin support for local development
