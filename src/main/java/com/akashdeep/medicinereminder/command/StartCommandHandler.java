package com.akashdeep.medicinereminder.command;

import org.springframework.stereotype.Component;;

@Component
public class StartCommandHandler {
    
    public String getWelcomeMessage() {
        return """
                Welcome to Medicine Reminder Bot.

                Available Commands:

                /register
                /addmedicine
                /list
                /help
                """;
    }
}
