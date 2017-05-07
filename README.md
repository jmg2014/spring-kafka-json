# spring-kafka-json

This is a example of a Spring boot application with Kafka.

Check the endpoint in kafkaProducerController to add new records into Kafka 


### Apache Kafka & Apache Zookeper  (docker)
docker run --rm -it \
           -p 2181:2181 -p 3030:3030 -p 8081:8081 \
           -p 8082:8082 -p 8083:8083 -p 9092:9092 \
           -e ADV_HOST=192.168.0.82 \
           landoop/fast-data-dev
           
           
### Kafka command lines tools
docker run --rm -it --net=host landoop/fast-data-dev bash           

### How to create a topic
kafka-topics --zookeeper 192.168.0.82:2181 --create --topic car.topic --partition 3 --replication-factor 1

### IP forwarding enabled
sysctl -w net.ipv4.ip_forward=1