# OOP2 Programmierprojekt

## Hinweise zu Git

Stellen Sie bitte zuerst sicher, dass Sie einen SSH-Schlüssel in Ihrem GitHub-Konto hinterlegt haben, siehe:
https://help.github.com/articles/connecting-to-github-with-ssh/

Dieses Git-Repository enthält einige Ressourcen als [Submodul](https://git-scm.com/book/de/v1/Git-Tools-Submodule), damit diese bei der Auswertung nicht wieder dutzende Male heruntergeladen werden müssen.
Aus diesem Grund muss das Repository mit `git clone --recursive <Pfad zum Repository>` heruntergeladen werden. Falls Sie das beim ersten `clone` vergessen haben, können Sie die Ressourcen mit den folgenden beiden Befehlen nachladen:
1. `git submodule init`
2. `git submodule update`

## SwissMountainsFX
Implementieren Sie eine Applikation auf Basis JavaFX gemäss der Aufgabenbeschreibung aus der Lektion (siehe Moodle). 
 - Benutzen Sie als Ausgangslage zur Umsetzung die vorgegebene Struktur des "Application-Template". Anpassungen und Ergänzungen am Template (z.B. das Basislayout der UI, Klassen- und Variablennamen, JavaDoc, Unit-Tests, ...) sind selbstverständlich erlaubt und sogar ausdrücklich erwünscht.
 - Die Verwendung von SceneBuilder und FXML ist nicht erlaubt.


## Abgabe
-  Dienstag, 12.6.2018, 12:00 Uhr

## Bitte beachten Sie:
 - Wie in der Aufgabenbeschreibung bereits festgelegt, müssen für eine 4.0 folgende Basis-Features implementiert sein:
   - Einlesen der Daten aus Datei
   - Abspeichern der Änderungen
   - Darstellen aller Berg in Liste
   - Editor-Bereich:
     - arbeitet stets auf dem in der Liste ausgewählten Berggipfel
     - Änderungen führen zu unmittelbarer Aktualisierung der Liste und des Header-Bereichs
   - Header-Bereich mit Name, Höhe, Gebiet und Bild
   - Layout mit SplitPane
   - sinnvolles Resizing-Verhalten
   - Anlegen eines neuen Berggipfels
   - Löschen bestehender Einträge  
   
 - Nicht compilierbarer Code wird mit einer 1.0 gewertet.

 - Das Programmierprojekt kann auch in Zweierteams bearbeitet werden. 
   - Es wird erwartet, dass die Lösung gemeinsam erarbeitet und implementiert wird (Stichwort: Pair-Programming).
   - Teams wird bei der Endnote eine Notenstufe (-1) abgezogen.
 
 - Die Teams müssen sich spätestens bis Dienstag, den 15.5. gebildet haben.
 
 - Das Team arbeitet dann auf dem Git-Repository eines der Team-Mitglieder.
 
 - Das jeweils andere Team-Mitglied muss als Collaborator in diesem Repository eingetragen sein.
 
 - Einen Collaborator fügt man über `Settings -> Collaborators & teams` hinzu.
 
 - Das Programmierzentrum ist geöffnet und Nachfragen werden zum Beispiel auch per Mail gerne beantwortet (auch wenn die Reaktionszeit per E-Mail etwas länger sein kann).
