.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.7

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

run-dist:
	./build/install/app/bin/app -f json /home/nikita/java-project-71/app/src/test/resources/file1.yaml /home/nikita/java-project-71/app/src/test/resources/file2.yaml

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

check-deps:
	./gradlew dependencyUpdates -Drevision=release

sonar-run:
	./gradlew build sonar --info

build-run: build run


.PHONY: build
