1. Download `mysql-connector-j-8.0.32.jar` or higher version from https://dev.mysql.com/downloads/connector/j and add it to lib folder at the level of src.

2. Add `liquibase` dependency in `pom.xml` file if with `maven`:
   ```xml
   <dependency>
     <groupId>org.liquibase</groupId>
     <artifactId>liquibase-core</artifactId>
   </dependency>
   ```
   or in `build.gradle` file if with `gradle`
   ```groovy
   implementation 'org.liquibase:liquibase-core:4.23.0'
   ```

3. Configure in `application.yaml` file:

   ```yaml
   spring:
     liquibase:
       enabled: true
       change-log: classpath:changelog-master.xml
   ```

4. Create `changelog-master.xml` in `resources`
5. Create `db.changelog` hierarchy with `changelog-v1.0.xml`
6. Create `liquibase.properties` file (use `liquibase init project`)
7. Run command:
   ```bash
   liquibase --url=jdbc:mysql://localhost:3306/dictionary
   --username=root --password=root
   --classpath=lib/mysql-connector-j-8.0.32.jar
   --changeLogFile=auto-gen-changelog.mysql.xml generateChangeLog
   ```

