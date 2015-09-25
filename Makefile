CC=javac
SRV=Lab1_practical3.java
CLI=Lab1_original_client.java

all: srv cli

srv:
	$(CC) $(SRV)
cli:
	$(CC) $(CLI)

clean:
	rm -f ./*.class
