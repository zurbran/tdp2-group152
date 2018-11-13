#!/bin/bash
stty -echo
echo Stopping docker-mysql-server
sudo docker stop docker_mysql_latest
sudo docker rm docker_mysql_latest
echo Starting docker_mysql_server
sudo docker stop mysql-server-instance
DOCKER_ID=`sudo docker run -d -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:latest --innodb_buffer_pool_size=3072M --innodb_log_file_size=1024M --max_connections=200`
sudo docker rename $DOCKER_ID docker_mysql_latest
sudo docker ps
stty echo
