@echo off
start "Backend" cmd /k "cd backend && mvn spring-boot:run"
start "Frontend" cmd /k "cd frontend && npm run dev"