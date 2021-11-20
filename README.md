# Chatting-Application

Chatting Application is a desktop based application. 

This client server chat application is based on java swing and used socket package. its simple and easy and require only core java knowledge. 
I have taken this program from internet and modified a little bit to make it simpler and more elegant.


This application/program is a good example of using java.io, java.net package to create a chat application. 
A beginner of java language, who is familiar with this packages can able, be beneficiate.

Chatting is a method of using technology to bring people and ideas   “together” despite of the geographical barriers. The technology has been available for years but the acceptance it was quit recent. Our project is an example of a multiple client chat server.

It is made up of 2 applications the client application, which runs on the user’s Pc and server application ,which runs on any Pc on  the network. To start chatting client should get connected to server. We will focus on TCP and UDP socket connections which are a fundamental part of socket programming.


MAIN OBJECTIVE

The aim of this project is to express how we can implement a simple chat application between a server and a client? The application is a desktop based application and is implemented using Swing and awt. The project is developed in Java SE language executed on a single stand-alone java across a network using loop back address concept.
Application consists of two programs:

1) Server
The server module of the application waits for the client to connect to it. Then if connection is granted a client interacts communicates and connects to the server, it can mutually communicate with the server. The duty of the server is to let clients exchange the messages.
2) Client
The client module is the one that utilizer sends requests to the server. Utilizer utilizes the client as the means to connect to the server. Once he establishes the connection, he can communicate to the connected server.


User Interface 

The user interface required to be developed for the system should be user friendly and attractive.
There are two sets of Java APIs for graphics programming: 
AWT (Abstract Windowing Toolkit) and Swing.

Software Interfaces 

Programming Language : Java & SOCKET PROGRAMMING

Operational Concepts and Scenarios
 
Operation of the application based on the inputs given by the user:  
1) When the run button is clicked then the chat form is initialized with a connection between the host and the client machine. 
   Note: Server must be started at first before a client start.
2) Contains a rich textbox which send messages from one user to another
3) Contains a textbox for messages to be written that is sent across the network.
4) Contains a Send button
5) When the sent button is clicked, in the background, the text in the textbox is encoded and sent as a packet over the network to the client machine. Here this message is decoded and is shown in the rich textbox.
