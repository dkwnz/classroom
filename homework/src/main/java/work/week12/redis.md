启动多个docker容器  
docker run --name redis-6379 -p 6379:6379  -d redis redis-server  
docker run --name redis-6380 -p 6380:6379  -d redis redis-server  
docker run --name redis-6381 -p 6381:6379  -d redis redis-server  
配置主从  
SLAVEOF 172.17.0.2 6379  
sentinel模式  
docker run -it --name redis-sentinel1 -v /root/redis/sentinel1.conf:/usr/local/etc/redis/sentinel.conf -d redis /bin/bash