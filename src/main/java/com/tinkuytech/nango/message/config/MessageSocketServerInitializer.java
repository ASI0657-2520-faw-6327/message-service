package com.tinkuytech.nango.message.config;

import com.tinkuytech.nango.message.service.MessageSocketServerService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Component
public class MessageSocketServerInitializer {

    private final ExecutorService executorService;
    private final MessageSocketServerService socketServerService;

    public MessageSocketServerInitializer(ExecutorService executorService, MessageSocketServerService socketServerService) {
        this.executorService = executorService;
        this.socketServerService = socketServerService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startSocketServer() {
        executorService.submit(socketServerService);
        System.out.println("Servidor de sockets iniciado correctamente en el puerto 8090");
    }
}
