.PHONY: build run-dist

build:
	./gradlew build

run-dist: build
	./build/install/app/bin/app
	
report:
	./gradlew jacocoTestReport


