# 3. Java Sockets and RMI

## How to compile
Compilation is done with Apache Ant.

For server:
- Change directory to `server`
- `ant jar`
- run with `java -jar jar/server.jar`

For client:
- Change directory to `client`
- `ant jar`
- run with `java -jar jar/client.jar`

The server needs to be run first in order to register the remote method.

When the client is run, input is asked for. This will be run to count
the amount of (Latin) letters. The client exists after a response has been
given by the server.

## a
### aClient.java
This is the client class. The client opens a socket on localhost, port 5000. It consists of a Scanner object which reads the input from the terminal, a BufferedReader which accepts an InputStreamReader from the socket stream, and a PrintWriter which outputs the socket's stream. The PrintWriter output stream print the incoming message. These functionalities are wrapped in a loop which continues to expect user input.

### aServer.java

This is the server class. The server opens a socket on localhost, port 5000. The server has the same input and output stream setup as the Client (BufferedReader, PrintWriter). In addition, the server has the printAndCountLetters() function which accepts the Client's incoming user input on the same socket and counts the number of characters in the String. 

## b
### Compute
Compute is an interface extended by the server bServer. Compute also extends the Remote interface, which makes its methods available to be invoked by the client, and the objects passed to the method are Serializeable in order to be passed from the server to the client. 

### Task
Task is another interface and it specifies the parameter type (Task) which the Compute interface takes in its method.
### bServer
The server creates the initial remote object and exports the remote object when the client makes a remote method invocation for the server to execute a task on the object. The class has a constructor which initializes the server as a Java Object, by calling the super() method. The bServer class has a main() method where it implements the remote interfaces 
### Task
Task is a non-remote interface implemented by the client bClient (and also by the s, see aboveerver). It specifies what generic task T should the server execute with the execute() method. 
### bClient
bClient is the client class: it sends a request to the server, which in turns sends a request to the registry to retrieve the name in the registry which references the Task object to be passed from the client to the server.

## c
### cClient
The main entrypoint of the client application. Here, the remote method
is looked up and forwarded to `ClientImpl`

### ClientImpl
The rest of the client logic is in this class. The `run` command asks for a string to give to the remote
procedure. The RPC is called in `countAtLeastOnce`, where periodically, an RPC is made,
until one response has been received. Then, all the tasks will be cancelled. The in-code documentation
goes into more detail as to how the code is organised.

### cServer
The main logic for the server. The remote procedure is registered in `main`. The actual procedure is
implemented as `printAndCountLetters`.

### Compute
The interface exposing `printAndCountLetters` as remote procedure.


## d
A complete "Exactly-Once" is not possible to achieve, because there is time between "registering an invocation
was done" and the actual invocation. The program can crash between these events, causing the program to think
that an invocation was done, while in reality, nothing happened.

The other way around does not work either: if invocation happens first, and the server crashes after
before registering the invocation happened, the program would think nothing happened, while in reality,
the invocation did happen.
