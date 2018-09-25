src-dir = src
bin-dir = bin
obj-dir = obj

units  = $(shell find $(src-dir) -name '*.java')
output = knight-tour.jar

main-class = dcc.gahag.chess.Main


directories:
	@mkdir -p ${bin-dir} ${obj-dir}

build: directories
	@javac -d ${obj-dir} ${units}
	@jar -cvef ${main-class} ${bin-dir}/${output} -C ${obj-dir} . # ${objects}

run:
	@java -jar ${bin-dir}/${output}


clean:
	@rm -rf ${bin-dir} ${obj-dir}
