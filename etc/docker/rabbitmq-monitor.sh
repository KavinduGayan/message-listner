#!/bin/sh

./monitor_docker.sh rabbitmq-producer /hms/iit/logs/rabbitmq-producer/
./monitor_docker.sh rabbitmq-listener /hms/iit/logs/rabbitmq-listener/
./monitor_docker.sh rabbitmq /hms/iit/logs/rabbitmq/
