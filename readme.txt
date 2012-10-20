Simple quickbase view into liferay portlet. Showing the table data from quickbase using quickbase Java SDK API. 

- run below maven command to install the setup
mvn clean install

- You need to manually install quickbase jar file into your maven repository using this command

mvn install:install-file -Dfile=%PROJECT_HOME%\src\main\webapp\WEB-INF\lib\quickbase.jar -DgroupId=com.quickbase -DartifactId=quickbase -Dversion=1.0 -Dpackaging=jar