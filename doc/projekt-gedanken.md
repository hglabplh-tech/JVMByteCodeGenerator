# Erfahrungsbericht über meine Free Ware Projekte welche kleinere  Computersprachen implementieren


## Die Absicht hinter diesem 'FUN' Projekt

Durch verschiedene Ereignisse, welche hier nicht näher erläutert sein wollen, kam ich auf die Idee
einen 370 Assembler Code so zu übersetzen, dass er in der JVM lauffähig ist.
Mit dem Schreiben des Projekts, kam ich auf die Idee, ich könnte meine zwei ersten Liblingssprachen
(PL/1 und Pascal) zusammenführen von dem, was Ihre Stärken waren.
Dazu will ich noch eínige Sprachfeatures entwickeln,
Hier zu benutze ich Clojure / Java als Sprachen für die Implementierung.
Als die wichtgsten Bibliotheken wird Javassist (Zusammensetzen und Schreiben von Byte-Code) und
ANTLR-4 zur Parser Lexer Generierung ... und zum Bauen eins Compilers /Intrpreters,,,,
Hier meine leidigen und guten Erfahrungen damit.

## Allgemeine Beobachtungen und Erfahrungen beim Projektdesign / Informationsbeschaffung







## Der Zweck dieser Bibliothek 

Die hier vorliegende Bibliothek hat den Zweck entweder die Logik eines beliebigen Assembler 
oder einen AST (Compiler Hochsprache) in Java Bytecode zu übersetzen, so dass das dadurch 
resultierende Programm auf der Java Virtual Machine(JVM) ab Java 8 lauffähig ist.

Um das generieren zu vereinfachen verwende ich die Javassist Library, 
welche auch einen Java Compiler enthält.Natürlich könnte man auch bei jeder Sprache, welche
man auf der JVM entwickelt die Befehle jedesmal einzeln anführen (ohne vorgefertigte Utilities für 
Standardlogik). Ich will hier nur den Versuch starten ob man mit einigen vorgefertigten Logikbausteinen,
welche aus mehereren byte Code Statements bestehen, nicht doch etwas schneller und weniger redundant 
entwickeln kann.


