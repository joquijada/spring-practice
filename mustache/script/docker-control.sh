#!/bin/bash

# Docker Control Script for Alaska DOH Person Registration Demo
# Manages Docker container lifecycle: start, stop, restart

set -e

# Configuration
IMAGE_NAME="alaska-doh-demo"
CONTAINER_NAME="alaska-doh-demo-container"
PORT="8080"
DOCKERFILE_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

# Colors for output (only if terminal supports it)
if [ -t 1 ]; then
    RED=$(printf '\033[0;31m')
    GREEN=$(printf '\033[0;32m')
    YELLOW=$(printf '\033[1;33m')
    BLUE=$(printf '\033[0;34m')
    NC=$(printf '\033[0m')
else
    RED=""
    GREEN=""
    YELLOW=""
    BLUE=""
    NC=""
fi

# Function to print colored messages
log_info() {
    printf "%b[INFO]%b %s\n" "${BLUE}" "${NC}" "$1"
}

log_success() {
    printf "%b[SUCCESS]%b %s\n" "${GREEN}" "${NC}" "$1"
}

log_warning() {
    printf "%b[WARNING]%b %s\n" "${YELLOW}" "${NC}" "$1"
}

log_error() {
    printf "%b[ERROR]%b %s\n" "${RED}" "${NC}" "$1"
}

# Function to check if container exists
container_exists() {
    docker ps -a --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"
}

# Function to check if container is running
container_running() {
    docker ps --format '{{.Names}}' | grep -q "^${CONTAINER_NAME}$"
}

# Function to compile SASS
compile_sass() {
    log_info "Compiling SASS to CSS..."
    cd "$DOCKERFILE_DIR"
    if [ -f "package.json" ]; then
        npm run build:css
        log_success "SASS compiled successfully"
    else
        log_warning "package.json not found, skipping SASS compilation"
    fi
}

# Function to stop the container
stop_container() {
    if container_running; then
        log_info "Stopping container: $CONTAINER_NAME"
        docker stop "$CONTAINER_NAME"
        log_success "Container stopped"
    else
        log_warning "Container is not running"
    fi
}

# Function to remove the container
remove_container() {
    if container_exists; then
        log_info "Removing container: $CONTAINER_NAME"
        docker rm "$CONTAINER_NAME"
        log_success "Container removed"
    fi
}

# Function to build the Docker image
build_image() {
    log_info "Building Docker image from scratch: $IMAGE_NAME"
    log_info "Build context: $DOCKERFILE_DIR"

    # Compile SASS first
    compile_sass

    # Build with no cache to ensure fresh build
    cd "$DOCKERFILE_DIR"
    docker build --no-cache -t "$IMAGE_NAME" .

    log_success "Docker image built successfully"
}

# Function to start the container
start_container() {
    if container_running; then
        log_warning "Container is already running"
        show_access_info
        return 0
    fi

    # Remove existing container if it exists
    remove_container

    # Build fresh image
    build_image

    log_info "Starting container: $CONTAINER_NAME"
    log_info "Mapping port $PORT:8080"

    docker run -d \
        --name "$CONTAINER_NAME" \
        -p "$PORT:8080" \
        "$IMAGE_NAME"

    log_success "Container started successfully"
    show_access_info
}

# Function to show access information
show_access_info() {
    printf "\n"
    printf "%b━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━%b\n" "${GREEN}" "${NC}"
    printf "%b  Application Access Information%b\n" "${GREEN}" "${NC}"
    printf "%b━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━%b\n" "${GREEN}" "${NC}"
    printf "  %bServer-Side Rendered:%b http://localhost:%s/persons\n" "${BLUE}" "${NC}" "$PORT"
    printf "  %bDynamic AJAX Version:%b http://localhost:%s/persons-dynamic\n" "${BLUE}" "${NC}" "$PORT"
    printf "  %bH2 Console:%b          http://localhost:%s/h2-console\n" "${BLUE}" "${NC}" "$PORT"
    printf "%b━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━%b\n" "${GREEN}" "${NC}"
    printf "\n"
}

# Function to show container status
show_status() {
    printf "\n"
    log_info "Container Status:"
    if container_running; then
        printf "%b  Status: RUNNING%b\n" "${GREEN}" "${NC}"
        printf "  Container ID: %s\n" "$(docker ps --filter name=$CONTAINER_NAME --format '{{.ID}}')"
        printf "  Image: %s\n" "$(docker ps --filter name=$CONTAINER_NAME --format '{{.Image}}')"
        printf "  Ports: %s\n" "$(docker ps --filter name=$CONTAINER_NAME --format '{{.Ports}}')"
        show_access_info
    elif container_exists; then
        printf "%b  Status: STOPPED%b\n" "${YELLOW}" "${NC}"
        printf "  Container exists but is not running\n"
    else
        printf "%b  Status: NOT FOUND%b\n" "${RED}" "${NC}"
        printf "  Container does not exist\n"
    fi
    printf "\n"
}

# Function to show logs
show_logs() {
    if container_exists; then
        log_info "Showing container logs (press Ctrl+C to exit):"
        docker logs -f "$CONTAINER_NAME"
    else
        log_error "Container does not exist"
        exit 1
    fi
}

# Function to restart the container
restart_container() {
    log_info "Restarting container..."
    stop_container
    start_container
}

# Function to show usage
show_usage() {
    printf "%bDocker Control Script for Alaska DOH Person Registration Demo%b\n\n" "${BLUE}" "${NC}"
    printf "Usage: %s {start|stop|restart|status|logs|help}\n\n" "$0"
    printf "Commands:\n"
    printf "  %bstart%b    - Build image from scratch and start container\n" "${GREEN}" "${NC}"
    printf "  %bstop%b     - Stop the running container\n" "${GREEN}" "${NC}"
    printf "  %brestart%b  - Stop and start the container (rebuilds image)\n" "${GREEN}" "${NC}"
    printf "  %bstatus%b   - Show container status\n" "${GREEN}" "${NC}"
    printf "  %blogs%b     - Show and follow container logs\n" "${GREEN}" "${NC}"
    printf "  %bhelp%b     - Show this help message\n\n" "${GREEN}" "${NC}"
    printf "Configuration:\n"
    printf "  Image Name:      %s\n" "$IMAGE_NAME"
    printf "  Container Name:  %s\n" "$CONTAINER_NAME"
    printf "  Port Mapping:    %s:8080\n" "$PORT"
    printf "  Build Context:   %s\n\n" "$DOCKERFILE_DIR"
    printf "Examples:\n"
    printf "  %s start        # Build and start the container\n" "$0"
    printf "  %s stop         # Stop the container\n" "$0"
    printf "  %s restart      # Rebuild and restart the container\n" "$0"
    printf "  %s status       # Check container status\n" "$0"
    printf "  %s logs         # View container logs\n\n" "$0"
}

# Main script logic
case "${1:-}" in
    start)
        start_container
        ;;
    stop)
        stop_container
        ;;
    restart)
        restart_container
        ;;
    status)
        show_status
        ;;
    logs)
        show_logs
        ;;
    help|--help|-h)
        show_usage
        ;;
    *)
        log_error "Invalid command: ${1:-}"
        printf "\n"
        show_usage
        exit 1
        ;;
esac

exit 0
