FROM openjdk:11 as builder

WORKDIR /aws_exporter
ADD . /aws_exporter
RUN apt-get update -qq && apt-get install -qq maven && mvn package && \
    mv target/aws_exporter-*-with-dependencies.jar /aws_exporter.jar

FROM openjdk:11-jre-slim as runner
MAINTAINER Prometheus Team <prometheus-developers@googlegroups.com>
EXPOSE 9106

WORKDIR /
RUN mkdir /config
COPY --from=builder /aws_exporter.jar /aws_exporter.jar
ENTRYPOINT [ "java", "-jar", "/aws_exporter.jar", "9106"]
CMD ["/config/config.yml"]
