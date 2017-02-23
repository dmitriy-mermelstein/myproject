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
cd /var/myproject
sudo java -jar myproject-consumer.jar &
echo "myproject consumer started"
sudo java -jar myproject-webserver.jar &
echo "myproject webserver started"
echo -n "Please Wait for 5 seconds and Enter to start the test"
read
sudo java -jar myproject-test.jar
echo "myproject test started"