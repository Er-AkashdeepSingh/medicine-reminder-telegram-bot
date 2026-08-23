package com.akashdeep.medicinereminder.command;

import com.akashdeep.medicinereminder.dto.RegistrationStep;
import com.akashdeep.medicinereminder.service.RegistrationService;
import org.springframework.stereotype.Component;

@Component
public class RegisterCommandHandler {

    private final RegistrationService registrationService;

    public RegisterCommandHandler(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public String startRegistration(Long chatId) {
        registrationService.startRegistration(chatId);

        return """
                Registration Started.

                Please enter your full name:
                """;
    }

    public boolean isRegistrationInProgress(Long chatId) {
        return registrationService.isRegistrationInProgress(chatId);
    }

    public String handleRegistrationMessage(Long chatId, String username, String message) {
        var session = registrationService.getSession(chatId);

        if (session == null) {
            return "Registration is not in progress. Use /register to begin.";
        }

        try {
            if (session.getStep() == RegistrationStep.WAITING_FOR_NAME) {
                registrationService.storeName(chatId, message);
                return "Please enter your relationship (for example: self, mother, or brother):";
            }

            if (session.getStep() == RegistrationStep.WAITING_FOR_RELATIONSHIP) {
                registrationService.completeRegistration(chatId, username, message);
                return "Registration completed successfully.";
            }
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        } catch (RuntimeException exception) {
            return "Registration could not be completed. Please try again.";
        }

        return "Unexpected registration state. Please use /register to start again.";
    }
}