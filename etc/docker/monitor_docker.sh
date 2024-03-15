#!/bin/bash

# Check if Docker is installed and in PATH
if ! command -v docker &> /dev/null; then
    echo "Docker could not be found. Please install Docker."
    exit 1
fi

# Check for container ID/name argument
if [ $# -lt 1 ]; then
    echo "Usage: $0 <container_name_or_id> <output_directory>"
    exit 1
fi

CONTAINER=$1
OUTPUT_DIR=$2

# Validate output directory
if [ -z "$OUTPUT_DIR" ]; then
    echo "Output directory not specified. Using current directory."
    OUTPUT_DIR="."
elif [ ! -d "$OUTPUT_DIR" ]; then
    echo "Output directory does not exist. Please create it first."
    exit 1
fi

# Generate output file name with current date and time
DATE=$(date +"%Y-%m-%d_%H-%M-%S")
OUTPUT_FILE="$OUTPUT_DIR/${CONTAINER}_stats_$DATE.csv"

# Check if the specified container is running
if ! docker ps --format '{{.Names}}' | grep -wq $CONTAINER; then
    echo "The specified container is not running."
    exit 1
fi

# Initialize CSV file with headers
echo "Timestamp,CPU Usage (%),Memory Usage,Memory Limit,Memory Usage (%)" > "$OUTPUT_FILE"

# Function to fetch and parse container stats
fetch_stats() {
    # Fetch stats for the specified container
    STATS=$(docker stats --no-stream --format "{{.CPUPerc}},{{.MemUsage}}" $CONTAINER)

    # Parse CPU and memory usage
    CPU_USAGE=$(echo $STATS | cut -d ',' -f1 | tr -d '%')
    MEM_USAGE_RAW=$(echo $STATS | cut -d ',' -f2)
    MEM_USAGE=$(echo $MEM_USAGE_RAW | awk '{print $1}')
    MEM_LIMIT=$(echo $MEM_USAGE_RAW | awk '{print $3}')

    # Convert memory usage and limit from MiB/GiB to bytes for more accurate calculation
    MEM_USAGE_BYTES=$(echo $MEM_USAGE | awk '{gsub(/MiB|GiB/, ""); print}')
    MEM_LIMIT_BYTES=$(echo $MEM_LIMIT | awk '{gsub(/MiB|GiB/, ""); print}')
    if [[ $MEM_USAGE =~ GiB$ ]]; then MEM_USAGE_BYTES=$(echo "$MEM_USAGE_BYTES * 1024" | bc); fi
    if [[ $MEM_LIMIT =~ GiB$ ]]; then MEM_LIMIT_BYTES=$(echo "$MEM_LIMIT_BYTES * 1024" | bc); fi

    # Calculate memory usage percentage
    MEM_PERCENT=$(echo "scale=2; ($MEM_USAGE_BYTES / $MEM_LIMIT_BYTES) * 100" | bc)

    # Get current timestamp
    TIMESTAMP=$(date +"%Y-%m-%d %H:%M:%S")

    # Output to CSV
    echo "$TIMESTAMP,$CPU_USAGE,$MEM_USAGE,$MEM_LIMIT,$MEM_PERCENT" >> "$OUTPUT_FILE"
}

# Main loop to fetch stats every second
while true; do
    fetch_stats
    sleep 1
done
