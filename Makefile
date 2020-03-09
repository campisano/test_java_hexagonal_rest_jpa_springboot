RUN_ARGS := -noverify -XX:TieredStopAtLevel=1 -Xnoagent -Djava.compiler=NONE
DEBUG_ARGS := $(RUN_ARGS) -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8000

.PHONY: test
test: clean
	mvn test -Drun.jvmArguments="$(RUN_ARGS)"

.PHONY: run
run: clean
	mvn spring-boot:run -Drun.jvmArguments="$(RUN_ARGS)"

.PHONY: package
package: clean
	mvn package spring-boot:repackage

.PHONY: debug
debug:
	mvn spring-boot:run -Drun.jvmArguments="$(DEBUG_ARGS)"

.PHONY: clean
clean:
	mvn clean
