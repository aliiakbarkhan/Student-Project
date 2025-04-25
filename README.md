
# Student Data Handling in C Language

<img align="middle" alt="GIF" src="https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/12cbe8a4-f55c-4b40-85bb-d8e1405e7b84/dg3u7hq-c1a76bed-89de-4294-9621-bb4ec5928066.gif?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzEyY2JlOGE0LWY1NWMtNGI0MC04NWJiLWQ4ZTE0MDVlN2I4NFwvZGczdTdocS1jMWE3NmJlZC04OWRlLTQyOTQtOTYyMS1iYjRlYzU5MjgwNjYuZ2lmIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.rYkG0BxIeofIeDrbwyDJge1i8l4MDCGA1ZxtoHva4nU" />

A C-based Student Details Management System that efficiently handles student data, including storage, retrieval, and management functionalities.



## Tech Stack

**Language:** C Language



## Support

For support, email aliakbarkhana79@gmail.com.


## Optimizations

There was nothing to optimise about it. It is a pretty basic code on File Handling.


## Authors

https://github.com/user-attachments/assets/af7a9530-cbef-4cb4-8f47-7d2a0691824d


- [Ali Akbar Khan](https://www.github.com/aliiakbarkhan)


## Badges




[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)



import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
class ServerMultiplexed
{
public static void main (String args[])throws IOException
{
Selector selector=Selector.open();
ServerSocketChannel serchannel=ServerSocketChannel.open();
serchannel.bind(new InetSocketAddress(5000));
serchannel.configureBlocking(false);
serchannel.register(selector,SelectionKey.OP_ACCEPT);
System.out.println("Srerver started on port 5000...");
while(true)
{
selector.select();
Iterator<SelectionKey>keys=selector.selectedKeys().iterator();
while(keys.hasNext())
{
SelectionKey Key=keys.next();
keys.remove();
if(Key.isAcceptable())
{
ServerSocketChannel srv=(ServerSocketChannel)Key.channel();
SocketChannel client=srv.accept();
client.configureBlocking(false);
client.register(selector,SelectionKey.OP_READ);
System.out.println("New client connected
:"+client.getRemoteAddress());
}
if(Key.isReadable())
{
SocketChannel client=(SocketChannel)Key.channel();
ByteBuffer buffer=ByteBuffer.allocate(1024);
int bytesread=client.read(buffer);
if(bytesread==-1)
{
System.out.println("Client
disconnected:"+client.getRemoteAddress());
client.close();
Key.cancel();
continue;
}
buffer.flip();
String message=new String(buffer.array(),0,buffer.limit());
System.out.println("Received :"+message);
buffer.rewind();
client.write(buffer);
}
}
}
}
}
>TCP Client RTT
import java.io.*;
import java.net.Socket;
public class TCPClientRTT {
public static void main(String[] args) {
try (Socket socket = new Socket("localhost", 5000);
BufferedReader userInput = new BufferedReader(new
InputStreamReader(System.in));
BufferedReader serverInput = new BufferedReader(new
InputStreamReader(socket.getInputStream()));
OutputStream output = socket.getOutputStream()) {
System.out.println("Connected to server. Type messages:");
String message;
while (!(message = userInput.readLine()).equalsIgnoreCase("exit")) {
long startTime = System.nanoTime();
output.write(message.getBytes());
output.flush();
char[] buffer = new char[1024];
int charsRead = serverInput.read(buffer);
String response = new String(buffer, 0, charsRead);
long endTime = System.nanoTime();
double rtt = (endTime - startTime) / 1_000_000.0;
System.out.println("Server replied: " + response.trim());
System.out.printf("RTT: %.2f ms%n", rtt);
}
} catch (Exception e) {
e.printStackTrace();
}
}