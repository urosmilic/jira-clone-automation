# Jira Clone Automation
Automatizovani testovi napisani u Java + Playwright frameworku sa TestNG i Allure reporting integracijom.

## Funkcionalnosti
*  Lokalno pokretanje testova individualno, preko testng.xml fajla kao i uz pomoc command line-a
*  Moguc izbor browsera kao i headless moda prilikom lokalnog pokretanja editovanjem config.properties fajla
*  Generisanje Allure izveštaja sa screenshotovima prilikom pada testa
*  Pokretanje testova preko Github Akcija, gde se isti mogu izvrsiti manualno ili se osloniti na scheduled job (CICD)
*  Manualno pokretanje preko github akcija omogucava i slanje taga, kako bi se izvrsio samo odredjeni skup testova (regression, smoke)

## Preduslovi
*  JDK 17+
*  Maven 3.8+
*  Node.js (za Playwright)

## Pokretanje testova iz command line-a i generisanje reporta
*  mvn clean test (pokretanje testova)
*  mvn allure:report (kreiranje reporta na adresi target/site/allure-maven-plugin/index.html)
