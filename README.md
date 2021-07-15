# SocketClientServer
# v95
*Target is to prepare service which can transform response from FIS server*  

#### Actions
- [x] Client Server class
- [x] Transform Method  
- [x] Mock test
- [x] Log4j
- [x] Dockerfile
- [x] Loop
- [x] Refactoring
- [x] Real tests
- [ ] Performace test

docker build -f Dockerfile -t milling:v79 .
   docker run -p 4444:4444 --name serverformilling ff7
   docker logout
docker login 
docker tag 49 loran07/serverformilling:v79
docker push loran07/serverformilling:v79
docker pull loran07/serverformilling:v79

docker stop 47u
docker start 47u
ping 10.235.244.57 -t
docker image prune -a
docker system prune -a --volumes
git status
git add. 
git commit -m "initial commit"
git push -a

//https://www.samouczekprogramisty.pl/java-z-linii-polecen/

!!!! zbudowac artifact od nowa aby zrobil extract biblotek jar

java -cp .  -jar out\artifacts\SocketClientServer_jar\SocketClientServer.jar out\artifacts\SocketClientServer_jar\ipconfig.csv out\artifacts\SocketClientServer_jar\prefixes.csv

; separator  dla Windows
: separator dla reszty
. bierzacy katalog
java -cp .:commons-lang3-3.5.jar pl.samouczekprogramisty.commandline.CheckName

budowa jar bez wpisu manifestu
jar cf <nazwa pliku wyjsciowego> <lista katalogów, klas do umieszczenia w pliku JAR>

budowa jar z wpisem manifestu
jar cfe output.jar pl.samouczekprogramisty.commandline.MainClass .

zawartosc jar
jar tf <sciezka pliku JAR>