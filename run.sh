#!/bin/sh  
  

mvn clean

rm -rf target

mvn package 
echo "\"打包完成\""
mv -f  /home/repository/ssm/target/spring_mvc_mybatis.war   /opt/apache-tomcat-9.0.0.M26/webapps/
echo "\"移动到tomcat完成\""
sh /opt/apache-tomcat-9.0.0.M26/bin/shutdown.sh
sh /opt/apache-tomcat-9.0.0.M26/bin/startup.sh
echo "\"tomcat重启成功\""
tail -f  /opt/apache-tomcat-9.0.0.M26/logs/catalina.out