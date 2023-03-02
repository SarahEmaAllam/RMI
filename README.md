# 3. Java Sockets and RMI

Submission by Group 11: <br/>
Krishan Jokhan, s3790746 <br/>
Sarah-Emanuela Allam, s3747328 <br/>
Shrushti Kaul, s5288843 <br/>

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
This is the client class. The client tries to connect to the server through port 5000, where is server is waiting for connections, once an input has been taken via the terminal (using the Scanner object). The PrintWriter object helps streaming the client input to the server, once connected. The BufferedReader object with the InputStreamReader are responsible for capturing the response given back from the server. This response is print in the terminal for the user to see.

### aServer.java

This is the server class. The Server socket is created on localhost with port 5000 and it listens from the port for any forthcoming requests from any client. The server has the same input and output stream setup as the Client (BufferedReader, PrintWriter). In addition, the server has the printAndCountLetters() function which accepts the Client's incoming user input on the same socket and counts the number of alphabetic characters in the String and returns that value back to the client. 

## b
### Compute
Compute is an interface extended by the server bServer. Compute also extends the Remote interface, which makes its methods available to be invoked by the client, and the objects passed to the method are Serializeable in order to be passed from the server to the client. 

### Task
Task is another interface and it specifies the parameter type (Task) which the Compute interface takes in its method.
### bServer
The server creates the initial remote object and exports the remote object when the client makes a remote method invocation for the server to execute a task on the object. The class has a constructor which initializes the server as a Java Object, by calling the super() method. The bServer class has a main() method where it exports the server instance as a remote object which can receive incvations of the implemented interface methods from the client. The main() functions also creates a registry, a remote object naming service, which informs the client of the reference to the remote object by name.
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
