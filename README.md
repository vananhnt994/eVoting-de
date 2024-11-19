# MSE-Gruppe5
<div align="center">
<img src="https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg" alt="Awesome Badge"/>
<img src="https://img.shields.io/static/v1?label=%F0%9F%8C%9F&message=If%20Useful&style=style=flat&color=BC4E99" alt="Star Badge"/>
<a href="https://discord.gg/D98cKTzd"><img src="https://img.shields.io/discord/1032643348026109992.svg?style=flat&label=Join%20Community&color=7289DA" alt="Join Community Badge"/></a>
<br>
<a href="https://github.com/vananhnt994/eVoting-de/stargazers"><img src="https://img.shields.io/github/stars/vananhnt994/eVoting-de" alt="Stars Badge"/></a>
<a href="https://github.com/vananhnt994/eVoting-de/pulls"><img src="https://img.shields.io/github/issues-pr/vananhnt994/eVoting-de" alt="Pull Requests Badge"/></a>
<a href="https://github.com/vananhnt994/eVoting-de/commits"><img src="https://img.shields.io/github/commit-activity/t/vananhnt994/eVoting-de" alt="Total Commits Badge"/></a>

</div>


## Inhalt
- [Installation Projekt](#installation-projekt) 
- [Projekt Build](#projekt-build)
- [Start Backend Server](#start-backend-server)


## Installation Projekt
- `mvn clean install`
- Bei der Nutzung von Maven wird vielfach der Befehl mvn clean install als Erstes gelernt und fortan wie automatisch ausgeführt. Dass dieser in den meisten Fällen zu längeren Build-Zeiten führt und es einen vielfach optimaleren Befehl gibt, ist häufig unbekannt. Betrachten wir das ganze doch mal mit ein wenig mehr Details.

- Beim Aufruf von mvn übergeben wir, in der Regel, eine oder mehrere sogenannte Phasen. Anhand der Phase erkennt Maven, welcher Lebenszyklus ausgeführt werden soll. Im Standard bringt Maven dabei die drei Lebenszyklen clean, default und site mit. Jeder dieser Lebenszyklen ist wiederum in Phasen unterteilt. Diese werden der Reihe nach ausgeführt, bis wir die angegebenen Phasen durchlaufen haben, und stoppen anschließend.

- Im Falle von mvn clean install führen wir also zuerst alle Phasen inklusive clean, die zum Lebenszyklus clean gehören, aus und direkt anschließend alle Phasen inklusive install im Lebenszyklus default. Die Phasen selber verrichten dabei keine Arbeit, sondern diese wird von Maven-Plug-ins erledigt. Dazu binden diese ein oder mehrere Goals an Phasen. Standardmäßig bringt Maven dabei bereits ein Subset von Plug-ins und gebundenen Goals mit.

- Beim Befehl mvn clean install sorgen wir also durch das Goal clean des maven-clean-plugin dafür, dass das gesamte target-Verzeichnis aller Module entfernt wird. Dadurch müssen beim Durchlaufen des default-Lebenszyklus durch die Angabe von install sämtliche Artefakte, wie unsere kompilierten Class- oder JAR-Dateien, neu erzeugt werden. Prinzipiell ist dagegen wenig einzuwenden. Es entsteht ein sauberer Build, bei dem die Gefahr, durch alte Dateien in Probleme zu laufen, nahezu nicht gegeben ist. Allerdings erhöhen sich dadurch, dass alles neu erzeugt werden muss, natürlich auch die benötigte Zeit und der Ressourcenverbrauch. In den meisten Projekten und Fällen ist dies nicht nötig und wir brauchen nicht jedes Mal die clean-Phase zu durchlaufen.

## Projekt Build
- `mvn -B package --file pom.xml` wird in der Maven-Befehlszeile verwendet, um ein Maven-Projekt zu bauen. Hier ist eine kurze Erklärung der einzelnen Teile:
- mvn: Dies ist das Kommandozeilenwerkzeug für Apache Maven, ein Build-Management-Tool für Java-Projekte.
- "-B": Diese Option steht für "Batch Mode". Sie sorgt dafür, dass Maven im Batch-Modus läuft, was bedeutet, dass es keine interaktiven Eingaben erwartet. Dies ist nützlich für automatisierte Builds, z.B. in CI/CD-Pipelines.
- package: Dies ist das Ziel (Goal), das angibt, dass Maven das Projekt kompilieren und die Artefakte (z.B. JAR oder WAR-Dateien) erstellen soll. Es führt alle notwendigen Schritte aus, um das Projekt zu paketieren.
- "--file pom.xml": Diese Option gibt an, dass Maven die pom.xml-Datei verwenden soll, um die Projektkonfiguration und Abhängigkeiten zu lesen. Standardmäßig sucht Maven nach einer pom.xml im aktuellen Verzeichnis, aber diese Option kann verwendet werden, um eine andere Datei anzugeben.

## Start Backend Server 
- `mvn spring-boot:run`
- Der Befehl mvn spring-boot:run wird verwendet, um eine Spring Boot-Anwendung zu starten. Hier ist eine kurze Erklärung der einzelnen Teile:
- mvn: Dies ist das Kommandozeilenwerkzeug für Apache Maven, ein Build-Management-Tool für Java-Projekte.
- spring-boot:run: Dies ist ein spezifisches Maven-Ziel (Goal) des Spring Boot Maven Plugins. Es führt die Anwendung aus, indem es die Hauptklasse (die mit der Annotation @SpringBootApplication gekennzeichnet ist) findet und die Anwendung startet.
