.PHONY: build run-dist report

build:
	make -C app build

run-dist:
	make -C app run-dist

report:
	make -C app report