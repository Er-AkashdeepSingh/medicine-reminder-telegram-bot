package com.akashdeep.medicinereminder.service;

import com.akashdeep.medicinereminder.command.StartCommandHandler;
import org.springframework.stereotype.Service;

@Service
public class CommandRouterService {

    private final StartCommandHandler startCommandHandler;

    public CommandRouterService(StartCommandHandler startCommandHandler) {
        this.startCommandHandler = startCommandHandler;
    }

    public String handleCommand(String command) {

        return switch (command) {

            case "/start" -> startCommandHandler.getWelcomeMessage();

            default -> """
                    Unknown command.

                    Use /start to begin.
                    """;
        };
    }
}