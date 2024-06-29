
`git clone https://github.com/nixiedroid/Spring-REST.git`
cd Spring-REST

```shell
git pull https://github.com/nixiedroid/Spring-REST.git
chmod +x gradlew
./gradlew
./gradlew bootJar
docker image build -t nixedroid/spring:latest Application/
docker images
docker run -p 8086:8086 nixedroid/spring
sudo lsof -i -P -n | grep LISTEN
```


Or, get from actions

```shell
docker pull ghcr.io/nixiedroid/spring-rest:master
docker images
docker run --name spring-rest -d -p 8086:8080 ghcr.io/nixiedroid/spring-rest:master
#docker start spring-rest
docker ps
docker attach spring-rest
```