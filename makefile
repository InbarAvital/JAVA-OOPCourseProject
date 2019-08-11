# id: 212373443
# username: avitali
compile: bin
	chmod -R 777 bin
	javac -cp biuoop-1.4.jar:src -d bin src/*/*.java

run:
	java -cp biuoop-1.4.jar:resources:bin runners/Ass7Game

jar:
	jar cfm ass7game.jar Manifest.mf -C bin/ . -C resources/ .

check:
	java -jar checkstyle-5.7-all.jar -c biuoop.xml src/*/*.java

bin:
	mkdir bin