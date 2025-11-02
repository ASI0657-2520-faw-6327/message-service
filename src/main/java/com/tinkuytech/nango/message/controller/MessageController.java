package com.tinkuytech.nango.message.controller;

import com.tinkuytech.nango.message.dto.MessageRequestDTO;
import com.tinkuytech.nango.message.dto.MessageResponseDTO;
import com.tinkuytech.nango.message.service.MessageSocketClientService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageSocketClientService clientService;

    public MessageController(MessageSocketClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/send")
    public MessageResponseDTO sendMessage(@RequestBody MessageRequestDTO request) {
        return clientService.sendMessage("localhost", 8090, request);
    }
}
