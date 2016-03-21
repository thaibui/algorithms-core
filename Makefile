all:
	@echo "Do nothing. Please take a look at Makefile and call specific commands."

install:
	@echo "Install all dependencies"
	mvn -e clean install

benchmark: install
	@echo "Running all benchmarks with preconfigured options"
	java -jar benchmark/target/benchmarks.jar -i 5 -wi 5 -wf 1 -wbs 2 -t 2 -f 2
