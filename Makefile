all:
    @echo "Do nothing. Please take a look at Makefile and call specific commands."

install:
    @echo "Install all dependencies"
    mvn -e clean install

generate-benchmark: install
    @echo "Running all benchmarks with the preconfigured options"
    java -jar benchmark/target/benchmarks.jar \
        -i 5 -wi 5 -wf 1 -wbs 2 -t 2 -f 3 \
        -rf csv -rff benchmark/result/all.csv
    @echo "Converting CSV result to Markdown file"
    @echo "Required 'customd' to be installed -- https://github.com/mplewis/csvtomd"
    csvtomd benchmark/result/all.csv > benchmark/result/all.md