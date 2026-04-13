# Library

Desktopowa aplikacja biblioteczna napisana w JavaFX + SQLite.

Projekt umozliwia zarzadzanie ksiazkozbiorami oraz obsluge wypozyczen w prostym interfejsie GUI.

## O projekcie

Aplikacja posiada dwa tryby pracy:
- administrator: dodawanie, edycja i usuwanie ksiazek,
- czytelnik: przegladanie katalogu i obsluga wypozyczen.

Dane przechowywane sa lokalnie w bazie SQLite (`my.db`).

## Zrzuty ekranu

![Logo projektu](Library/src/main/resources/com/example/library/logo.png)

![Ekran aplikacji 1](Library/src/main/resources/com/example/library/Zrzut%20ekranu%202025-02-01%20175314.png)

![Ekran aplikacji 2](Library/src/main/resources/com/example/library/Zrzut%20ekranu%202025-02-02%20141549.png)

## Funkcjonalnosci

- logowanie z rozroznieniem roli (admin/czytelnik),
- wyszukiwanie ksiazek po tytule, autorze i gatunku,
- filtrowanie listy do aktualnie wypozyczonych pozycji,
- CRUD ksiazek (dodawanie, edycja, usuwanie),
- wypozyczenie, zwrot i przedluzenie terminu oddania,
- czytelna wizualizacja statusu dostepnosci ksiazki.

## Technologie

- Java 21
- JavaFX 21 (`javafx-controls`, `javafx-fxml`)
- SQLite (`jdbc:sqlite:my.db`)
- Maven
- JUnit 5 (zaleznosci testowe)

## Struktura projektu

Najwazniejsze elementy kodu:
- `HelloApplication` - start aplikacji i inicjalizacja tabeli `Books`,
- `LogInController`, `LibraryController`, `AdminController`, `BookController` - kontrolery widokow,
- `models/Book` - model domenowy,
- `models/BookList` - logika listy i synchronizacja danych,
- `repository/BookRepository` - warstwa dostepu do danych (CRUD),
- `resources/*.fxml` - widoki JavaFX.

## Uruchomienie lokalne

Wymagania:
- JDK 21+
- Maven 3.9+

Kroki:
1. Przejdz do katalogu aplikacji:
	```bash
	cd Library
	```
2. Uruchom aplikacje:
	```bash
	mvn clean javafx:run
	```

## Dane logowania (demo)

- administrator: `admin` / `admin`
- czytelnik: dowolny login + haslo `student`

## Baza danych

- baza: SQLite (`my.db`),
- tabela `Books` tworzona jest przy starcie aplikacji,
- pola: `id`, `title`, `author`, `genre`, `publisher`, `isRented`, `expiration`.

## Znane ograniczenia

- `mvnw` jest obecny, ale repozytorium nie zawiera kompletnego katalogu `.mvn/wrapper`,
- brak testow automatycznych mimo przygotowanych zaleznosci JUnit,
- czesc sciezek i danych jest osadzona na stale w kodzie.
