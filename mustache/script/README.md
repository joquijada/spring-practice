# Docker Control Script

Convenience script for managing the Alaska DOH Person Registration Demo Docker container.

## Quick Start

```bash
# Start the application (rebuilds image from scratch)
./docker-control.sh start

# Access the application
# - Dynamic Version: http://localhost:8080/persons-dynamic
# - SSR Version: http://localhost:8080/persons
```

## Commands

### Start Container
```bash
./docker-control.sh start
```
- Compiles SASS to CSS
- Rebuilds Docker image from scratch (with `--no-cache`)
- Starts the container
- Shows access URLs

### Stop Container
```bash
./docker-control.sh stop
```
- Stops the running container
- Container can be started again without rebuilding

### Restart Container
```bash
./docker-control.sh restart
```
- Stops the container
- Rebuilds the image from scratch
- Starts the container
- Useful after making code changes

### Check Status
```bash
./docker-control.sh status
```
- Shows container status (RUNNING, STOPPED, or NOT FOUND)
- Displays container details if running
- Shows access URLs if running

### View Logs
```bash
./docker-control.sh logs
```
- Shows real-time container logs
- Press Ctrl+C to exit
- Useful for debugging

### Show Help
```bash
./docker-control.sh help
```
- Displays usage information
- Shows all available commands
- Provides examples

## Configuration

The script uses these defaults:
- **Image Name**: `alaska-doh-demo`
- **Container Name**: `alaska-doh-demo-container`
- **Port**: `8080`

## Features

- **Colored Output**: Easy-to-read colored messages
- **Error Handling**: Proper error messages and exit codes
- **Auto-rebuild**: Always rebuilds from scratch to pick up latest changes
- **SASS Compilation**: Automatically compiles SASS before building
- **Status Checking**: Shows detailed container status
- **Log Viewing**: Real-time log streaming

## Typical Workflow

```bash
# 1. Make code changes to the application
# 2. Run the restart command to rebuild and restart
./docker-control.sh restart

# 3. View logs to verify startup
./docker-control.sh logs

# 4. Access the application
# Dynamic: http://localhost:8080/persons-dynamic
# SSR: http://localhost:8080/persons

# 5. When done, stop the container
./docker-control.sh stop
```

## Troubleshooting

### Port Already in Use
If you get a port conflict error, stop any other services running on port 8080:
```bash
# Find process using port 8080
lsof -ti:8080

# Kill the process
lsof -ti:8080 | xargs kill -9
```

### Container Won't Start
Check the logs for errors:
```bash
./docker-control.sh logs
```

### Build Fails
Ensure you have:
- Docker installed and running
- Node.js and npm installed (for SASS compilation)
- Sufficient disk space

### Fresh Start
To completely remove everything and start fresh:
```bash
# Stop and remove container
./docker-control.sh stop
docker rm alaska-doh-demo-container

# Remove image
docker rmi alaska-doh-demo

# Rebuild and start
./docker-control.sh start
```

## Notes

- The script always uses `--no-cache` when building to ensure the latest changes are included
- SASS is compiled automatically before each Docker build
- The container runs in detached mode (`-d` flag)
- Logs are preserved even after stopping the container (until it's removed)
