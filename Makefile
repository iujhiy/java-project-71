setup:
	make -cd ./app setup

clean:
	make -cd ./app clean

build:
	make -cd ./app build

install:
	make -cd ./app install

run-dist:
	make -cd ./app run-dist

run:
	make -cd ./app run

test:
	make -cd ./app test

report:
	make -cd ./app report

lint:
	make -cd ./app lint

check-deps:
	make -cd ./app check-deps
