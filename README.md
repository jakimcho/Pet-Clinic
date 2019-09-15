# Pet-Clinic

## Mvn configs
- Sonar
mvn clean test jacoco:prepare-agent sonar:sonar -Dsonar.projectKey=jakimcho_Pet-Clinic -Dsonar.organization=jakimcho-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login={key} -Dsonar.junit.reportPaths=./target/surefire-reports
- Jacoco
mvn clean test jacoco:report