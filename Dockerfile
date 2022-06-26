FROM tomcat:8-jre11-openjdk
COPY /taxi-impl/target/taxi-impl-1.0.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh","run"]