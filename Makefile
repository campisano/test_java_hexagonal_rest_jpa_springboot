DEBUG_5005=-Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

all: test

clean:
	mvn clean

test:
	mvn test

run:
	mvn spring-boot:run

debug:
	mvn spring-boot:run $(DEBUG_5005)

package: clean
	mvn package spring-boot:repackage
