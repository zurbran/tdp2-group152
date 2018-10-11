#!/bin/bash
stty -echo
echo Stopping docker-mysql-server
sudo docker rm -v mysql-server-instance
echo Starting docker_mysql_server
sudo docker stop mysql-server-instance
DBID= sudo docker run -d --name mysql-server-instance --net=host -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes mysql:latest --innodb_buffer_pool_size=3072M --innodb_log_file_size=1024M --max_connections=200
stty echo
