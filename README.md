# 3. Java Sockets and RMI
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
The server creates the initial remote object and exports the remote object when the client makes a remote method invocation for the server to execute a task on the object. The class has a constructor which initializes the server as a Java Object, by calling the super() method. The bServer class has a main() method where it exports the server instance as a remote object which can receive incvations of the implemented interface methods from the client. The main() functions also creates a registry, a remote object naming service, which informs the client of the reference to the remote object by name.
### Task
Task is a non-remote interface implemented by the client bClient (and also by the s, see aboveerver). It specifies what generic task T should the server execute with the execute() method. 
### bClient
bClient is the client class: it sends a request to the server, which in turns sends a request to the registry to retrieve the name in the registry which references the Task object to be passed from the client to the server.