package com.akashdeep.medicinereminder.service;

import com.akashdeep.medicinereminder.command.StartCommandHandler;
import com.akashdeep.medicinereminder.command.RegisterCommandHandler;
import org.springframework.stereotype.Service;

@Service
public class CommandRouterService {

    private final StartCommandHandler startCommandHandler;
    private final RegisterCommandHandler registerCommandHandler;

    public CommandRouterService(
            StartCommandHandler startCommandHandler,
            RegisterCommandHandler registerCommandHandler) {
        this.startCommandHandler = startCommandHandler;
        this.registerCommandHandler = registerCommandHandler;
    }

    public String handleCommand(Long chatId, String username, String command) {

        if ("/register".equals(command)) {
            return registerCommandHandler.startRegistration(chatId);
        }

        if (registerCommandHandler.isRegistrationInProgress(chatId)) {
            return registerCommandHandler.handleRegistrationMessage(chatId, username, command);
        }

        return switch (command) {
            case "/start" -> startCommandHandler.getWelcomeMessage();

            default -> """
                    Unknown command.

                    Use /start to begin.
                    """;
        };
    }
}