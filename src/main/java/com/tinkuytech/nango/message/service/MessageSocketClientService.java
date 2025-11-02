package com.tinkuytech.nango.message.service;

import com.tinkuytech.nango.message.dto.MessageRequestDTO;
import com.tinkuytech.nango.message.dto.MessageResponseDTO;
import com.tinkuytech.nango.message.model.Message;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

@Service
public class MessageSocketClientService {

    public MessageResponseDTO sendMessage(String host, int port, MessageRequestDTO request) {
        try (Socket socket = new Socket(host, port)) {

            socket.setSoTimeout(3000);

            // Enviar mensaje
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(request.getSenderId() + "|" + request.getReceiverId() + "|" + request.getContent());
            out.flush();

            // Leer respuesta del servidor
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse;
            try {
                serverResponse = in.readLine();
            } catch (SocketTimeoutException e) {
                serverResponse = "No hubo respuesta del servidor (timeout)";
            }

            Message message = new Message();
            message.setSenderId(request.getSenderId());
            message.setReceiverId(request.getReceiverId());
            message.setContent(request.getContent());
            message.setMessageTime(LocalDateTime.now());
            message.setStatus(Message.Status.SENT);

            System.out.println("Mensaje enviado al servidor: " + serverResponse);

            return new MessageResponseDTO(message);

        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje: " + e.getMessage());

            Message failedMessage = new Message();
            failedMessage.setSenderId(request.getSenderId());
            failedMessage.setReceiverId(request.getReceiverId());
            failedMessage.setContent("Error: " + e.getMessage());
            failedMessage.setMessageTime(LocalDateTime.now());
            failedMessage.setStatus(Message.Status.FAILED);

            return new MessageResponseDTO(failedMessage);
        }
    }
}
