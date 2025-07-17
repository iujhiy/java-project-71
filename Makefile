setup:
	make -C app setup

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C app run-dist

run:
	make -C app run

test:
	make -C app test

report:
	make -C app report

lint:
	make -C app lint

check-deps:
	make -C app check-deps

dependency-submission:
	make -C app dependency-submission

sonar-run:
	make -C app sonar-run
