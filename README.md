# PokeBattler, Eindopdracht Appstudio
Voor het allerlaatste project, heb ik mezelf uitgedaagd om allebei de opdrachten te implementeren. PokeBattler is een app gemaakt voor de Pokemon fanaat. Je kan:

* pokemon battles voorspellen
* de Pokedex bekijken
* je favoriete pokemon opslaan
* highscores bekijken
* andere users bekijken
* een account maken
* in en uitloggen

## Functionaliteiten
### Registratie
Je kan in de app je aanmelden via het registreer menu, Hierin wordt om een email adres gevraagd en een wachtwoord. Deze moet ook bevestigd worden. Vervolgens moet je wat persoonlijke gegevens invullen en kan je beginnen
### Pokedex
De Pokedex heeft 700 Pokemon die je kan bekijken. Als je op een pokemon klikt, krijg je informatie over de gekozen pokemon te zien. Als je een _longclick_ op een Pokemon doet, voeg je hem toe aan je favorieten en krijgt hij een gele achtergrond, Je standaard favoriete Pokemon is natuurlijk Pikachu, die heb ik alvast voor je ingesteld. Wil je nou dat nou niet? houd dan pikachu lang ingedrukt en hij is weg uit je favorieten. Makkelijk toch?
### Het spel
Het spel draait om het voorspellen van de winnaar van pokemon gevecht tussen twee pokemon. Je begint door op start text te klikken en vervolgens komen er 2 pokemon in beeld, met de belangrijkste informatie, de types. Vervolgens kun je de winnaar selecteren door op het plaatje te klikken. Ook is het mogelijk om tijdens het spel te de gegevens van de pokemon te bekijken, dit kan door op de informatie button te klikken. Let op, de tijd stopt niet! Je hebt slecht 2 minuten om te spelen dus probeer snel te kiezen. De puntentelling is 2<sup>highest total stats/lowest total stats</sup>, dit betekent dat elk gevecht een uniek aantal punten oplevert. Hierdoor wordt er onderscheid gemaakt tussen makkelijke en moeilijke gevechten. Als je 3 vragen achter elkaar goed  beantwoordt, dan activeer je de verdubbelaar! Hiermee verdien je dubbel punten voor elk goed volgend antwoord. Deze boost verdwijnt als je een vraag fout beantwoord. 
Na de twee minuten krijg je te horen of je een highscore hebt gehaald.
### De Highscore
Je kan natuurlijk ook elkaars highscore bekijken. Hierin zie je alle scores gerankt. Ook zie je de gebruiker haar allereerste favoriete pokemon. Je kan jezelf makkelijk terugvinden, omdat je een gele achtergrond heb, wel zo handig. Bij de highscore kan je ook andere users bekijken. Klik op een user en krijg zijn/haar highscore, favoriete generatie, leeftijd, naam en zijn/haar favoriete pokemons. 

## De code
### Firebase
PokeBattler is gemaakt met een database die op FireBase staat, dit was overlegd met Martijn. Op Firebase staan 3 sets: Pokemons, Userinfo en de Battles. Hierin staan alle gegevens die nodig zijn voor de app. Ook staan op Firebase alle plaatjes van alle 700+ pokemon. 
### Fragments
De app is volledig gebouwd met fragments en kan daarom ook gebruik maken van een Bottom Navigation bar. Deze is volledig in Pokemon style en werkt vloeiend. 


## Screenshots
![Inlogscherm](/Doc/Inlog.png?raw=true)
![Functionaliteiten](/Doc/HighPokeGame.png?raw=true)
![Nieuwe Highscore](/Doc/endGame.png?raw=true)

![BCH compliance](https://bettercodehub.com/edge/badge/vincentdamen/TestingBCH?branch=master)
