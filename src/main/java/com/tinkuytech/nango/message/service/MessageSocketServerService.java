package com.tinkuytech.nango.message.service;

import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class MessageSocketServerService implements Runnable {

    private static final int PORT = 8090;

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor de sockets escuchando en el puerto " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor de sockets: " + e.getMessage());
        }
    }

    private void handleClient(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            String message = reader.readLine();
            System.out.println("Mensaje recibido: " + message);
            writer.println("Mensaje recibido correctamente por el servidor");
        } catch (IOException e) {
            System.err.println("Error manejando cliente: " + e.getMessage());
        }
    }
}
