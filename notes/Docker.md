
`git clone https://github.com/nixiedroid/Spring-REST.git`

or

`git pull https://github.com/nixiedroid/Spring-REST.git`

```shell
cd Spring-REST
chmod +x gradlew
./gradlew
./gradlew bootJar
docker image build -t nixedroid/spring:1 .
 docker images
 docker run -P
```