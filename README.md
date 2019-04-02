# Programming cookbook
1. Fasady (facade) - powinny zawierać tylko te metody, które są wykorzystywane w Controllerze
2. Cztery metody Http:
  - @PostMapping - gdy tworzony jest obiekt w bazie,
  - @PutMapping - gdy updatujemy obiekt w bazie,
  - @DeleteMapping - usuwanie obiektu,
  - @GetMapping - zwracanie obiektu do wyświetlenia
  
3. Tworzenie modeli DTO (biznesowych, czyli takich jakie mają się dokładnie wyświetlać/przychodzić z aplikacji frontendowej)
4. Konwersja DTO -> Entity -> DTO przy użyciu stworzonego assemblera, aby uniknąć StackOverflow (spowodowanych relacjami bazo danowych)
5. Aby wprowadzić sprawdzanie czy dany użytkownik ma dostęp do zasobów firmy o podanym ID wystarczy nad metodą dodać adnotację @SecureCompanyScope(role = ROLE) gdzie jako role podajemy kto może mieć dostęp do danego zasobu. Każda metoda przed którą jest adnotacja musi jako argument przyjmować obiekt CompanyIdentity inaczej walidator zawsze będzie zwracał HttpStatus code 403 - Forbidden.
6. Role w firmie: Admin - zarządza całą firmą, Expert - możliwość wprowadzania propozycji zmian w liniach, Worker - używanie kalkulatora oraz przeglądanie linii, Member - ma co najmniej jedną rolę w firimę (jakąkolwiek)
7. W całej aplikacji wyróżniamy dwie role: USER oraz SUPER_ADMIN
8. SUPER_ADMIN ma za zadanie akceptować zgłoszenia rejestracji usera/firmy
9. W pliku data.sql można pisać Inserty, które wypełnią bazę danych podczas inicjalizacji aplikacji
10. Aby uruchomić aplikację trzeba mieć uruchomioną bazę danych MySQL na porcie 3306, a w niej stworzoną bazę danych: calc_o2
11. Serwer startuje pod adresem: localhost:8090 -> port można zmienić w application.properties
12. Aby móc wykonywać autoryzowane zapytania trzeba najpierw się zalogować aby uzyskać token autoryzujący JWT -> każde zapytanie powinno zawierać header: Authorization: Bearer <token z logowanie>
13. Jeśli user nie jest aktywny nie ma możliwości zalogowania się
14. Chronienie end-pointów controllerów odbywa się w konfiguracji: SecurityConfig.java
15. Do wysyłania zapytań do end-pointów warto skorzystać z programu Postman
16. Nowe tabele tworzą się automatycznie na podstawie zdefiniowanych encji w Javie (te klasy z adnotacją @Entity) - tam również definiuje się relacje SQL (np. @OneToMany etc.)
17. Warto korzystać z wrappera na nulle -> Optional: https://klolo.github.io/blog/2017/08/05/jak-uzywac-optional/  - Repozytoria Springowe mogą również zwracać Optionala zamiast nulla, gdy np. nie znajdzie firmy o danym ID. 
  Przykład użycia Optionala + Repository:
  ```
      public User findUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
  ```
  Czyli: znajdź usera o podanym id - jeśli nie istnieje to rzuć wyjątek UserNotFoundException
18. Najlepiej dla tasków stwórzcie brancha na githubie od mastera i po zaimplementowaniu zadania zróbcie pusha + pull requesta z waszego brancha do mastera na githubie - będzie można przejrzeć wszystkie zmiany jakie ktoś wprowadził na danym branchu
