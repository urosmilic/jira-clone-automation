# Jira Clone Automation
Automatizovani testovi napisani u Java + Playwright frameworku sa TestNG i Allure reporting integracijom.

## Funkcionalnosti
*  Lokalno pokretanje testova individualno (test klasa ili test), preko testng.xml fajla kao i uz pomoc command line-a
*  Moguc izbor browsera kao i headless moda prilikom lokalnog pokretanja editovanjem config.properties fajla (ukoliko se pokrece lokalno, za pracenje desavanja u browseru headless treba biti false)
*  Generisanje Allure izveštaja sa screenshotovima prilikom pada testa
*  Pokretanje testova preko Github akcija: [1] testovi se automatski pokrecu svakog dana u 12:00 CET (verovatno ce biti modifikovano na 1 nedeljno), [2] nakon svakom merge-a kao i [3] manualno uz pomoc triggera "Test trigger with allure reporting"
*  Manualno pokretanje preko Github akcija omogucava i prosledjivanje taga, kako bi se izvrsio samo odredjeni skup testova (regression, smoke)

## Preduslovi
*  JDK 17+
*  Maven 3.8+
*  Node.js (za Playwright)

## Pokretanje testova iz command line-a i generisanje reporta
*  mvn clean test (pokretanje testova)
*  mvn allure:report (kreiranje reporta na adresi target/site/allure-maven-plugin/index.html)
*  Bitno je uvek uraditi "mvn clean" zbog generisanja novog reporta ukoliko se test pokrece na bilo koji nacin osim preko command line-a
