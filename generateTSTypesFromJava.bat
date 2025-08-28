@echo off
echo Starting backend...
start "Backend" cmd /k "cd backend && mvn spring-boot:run"

echo Waiting for backend to start...
timeout /t 45 /nobreak

echo Testing backend connection...
curl -f http://localhost:8080/api/test/hello >nul 2>&1
if %errorlevel% neq 0 (
    echo Backend not ready, waiting more...
    timeout /t 15 /nobreak
)

echo Generating TypeScript types...
cd frontend
npm run generate-types

echo Done! Types generated in frontend/src/types/