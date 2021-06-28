Aby uruchomić aplikację należy:
1. W katalogu źródłowym zbudować aplikację za pomocą Gradle'a (na Windowsie ./gradlew.bat clean build)
2. Wywołać polecenie docker-compose up --build
3. Uruchomione zostają dwa kontenery - aplikacja sweaters i baza mariadb
4. Wejść na adres http://localhost:8080/index
