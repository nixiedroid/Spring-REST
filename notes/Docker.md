
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
