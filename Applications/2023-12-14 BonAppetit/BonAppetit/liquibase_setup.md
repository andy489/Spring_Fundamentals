1. Download liquibase: https://www.liquibase.com/download
2. Download mysql-connector-j-8.0.32.jar to liquibase/bin folder
2. Attach liquibase library as application dependency:
   - implementation 'org.liquibase:liquibase-core'
3. add to plugins:
   - id 'org.liquibase.gradle' version '2.2.0'
4. Create database with "jpa: hibernate: ddl-auto: create-drop"
5. Use 'liquibase init project' to create liquibase.properties file and fill needed arguments for liquibase commands
6. Update liquibase.properties
7. Run 'liquibase generateChangeLog'
8. Create/copy changelog-master.xml (edit if there are enums or etc.)
9. Create/copy db.changelog with changelog-v1.0.xml
10. Copy content from generated changelog to changelog-v1.0.xml 
11. In application yml: "spring: jpa: hibernate: ddl-auto: none" and "liquibase: enabled: true"