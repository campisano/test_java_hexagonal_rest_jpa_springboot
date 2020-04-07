RUN_ARGS := -noverify -XX:TieredStopAtLevel=1 -Xnoagent -Djava.compiler=NONE
DEBUG_ARGS := $(RUN_ARGS) -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000

.PHONY: test
test: clean
	mvn test

.PHONY: run
run: clean
	mvn spring-boot:run -Dspring-boot.run.jvmArguments="$(RUN_ARGS)"

.PHONY: debug
debug:
	mvn spring-boot:run -Dspring-boot.run.jvmArguments="$(DEBUG_ARGS)"

.PHONY: clean
clean:
	mvn -q clean
