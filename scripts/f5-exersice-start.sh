#Stop tomcat
sudo service tomcat7 stop
#Start Zookeeper
sudo service zookeeper start
echo "zookeeper is running"
#Start kafka broker
cd /home/ubuntu/kafka_2.11-0.10.1.0
export KAFKA_HEAP_OPTS="-Xmx256M -Xms128M"
sudo bin/kafka-server-start.sh config/server.properties
echo "kafka server started"
#Start applications
cd /var/f5
sudo java -jar f5-consumer.jar &
echo "f5 consumer started"
sudo java -jar f5-webserver.jar &
echo "f5 webserver started"
echo -n "Please Wait for 5 seconds and Enter to start the test"
read
sudo java -jar f5-test.jar
echo "f5 test started"