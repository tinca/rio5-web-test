# rio5-web-test
This is a simple example of creating a servlet that uses Rio to resolve a service's codebase using the artifact: URL. The steps to recreate this are as follows:

1. Download Tomcat version 7
2. Download and start [Rio](http://www.rioproject.org)
3. Build the project
4. Deploy [resources/conf/ops.groovy](resources/conf/ops.groovy) using the Rio UI
5. Copy resources/setenv.sh to apache-tomcat-7.xx/bin/ directory
6. Make sure that you either set RIO_HOME in your environment or set it in the setenv.sh script
7. Copy the [resources/catalina.policy](resources/catalina.policy) to apache-tomcat-7.xx/conf/ directory
8. Start tomcat with security (bin/catalina.sh start -security)
9. Run the web-test servlet
