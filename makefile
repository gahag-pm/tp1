src-dir = src
bin-dir = bin
obj-dir = obj

units  = $(shell find $(src-dir) -name '*.java')
output = knight-tour.jar

main-class = dcc.gahag.chess.Main


bin-dir:
	@mkdir -p ${bin-dir}

build: bin-dir
	@javac -d ${obj-dir} ${units}
	@jar -cvef ${main-class} ${bin-dir}/${output} -C ${obj-dir} . # ${objects}

run:
	@java -jar ${bin-dir}/${output}


clean:
	@rm -rf ${bin-dir} ${obj-dir}
