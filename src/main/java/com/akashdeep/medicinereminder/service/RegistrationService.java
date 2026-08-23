package com.akashdeep.medicinereminder.service;

import com.akashdeep.medicinereminder.dto.RegistrationSession;
import com.akashdeep.medicinereminder.dto.RegistrationStep;
import com.akashdeep.medicinereminder.entity.User;
import com.akashdeep.medicinereminder.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RegistrationService {

    private final Map<Long, RegistrationSession> sessions =
            new ConcurrentHashMap<>();
    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void startRegistration(Long chatId) {
        if (chatId == null) {
            throw new IllegalArgumentException("A valid Telegram chat is required.");
        }

        sessions.put(
                chatId,
                new RegistrationSession(
                        chatId,
                        RegistrationStep.WAITING_FOR_NAME));
    }

    public RegistrationSession getSession(Long chatId) {
        return sessions.get(chatId);
    }

    public boolean isRegistrationInProgress(Long chatId) {
        return sessions.containsKey(chatId);
    }

    public void storeName(Long chatId, String fullName) {
        RegistrationSession session = requireSession(chatId);
        String validatedName = validateText(fullName, "Please enter your full name.", 255);

        session.setFullName(validatedName);
        session.setStep(RegistrationStep.WAITING_FOR_RELATIONSHIP);
    }

    public void completeRegistration(Long chatId, String username, String relationship) {
        RegistrationSession session = requireSession(chatId);
        String validatedRelationship = validateText(
                relationship,
            "Please enter your relationship.",
            100);

        if (session.getFullName() == null) {
            throw new IllegalStateException("A full name is required before relationship.");
        }

        session.setRelationship(validatedRelationship);

        User user = new User();
        user.setChatId(chatId);
        user.setTelegramUsername(username);
        user.setFullName(session.getFullName());
        user.setRelationship(validatedRelationship);
        user.setActive(true);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);
        session.setStep(RegistrationStep.COMPLETED);
        removeSession(chatId);
    }

    private RegistrationSession requireSession(Long chatId) {
        if (chatId == null) {
            throw new IllegalArgumentException("A valid Telegram chat is required.");
        }

        RegistrationSession session = sessions.get(chatId);
        if (session == null) {
            throw new IllegalStateException("Registration is not in progress.");
        }
        return session;
    }

    private String validateText(String value, String errorMessage, int maxLength) {
        String trimmedValue = value == null ? null : value.trim();
        if (trimmedValue == null
                || trimmedValue.isEmpty()
                || trimmedValue.startsWith("/")
                || trimmedValue.length() > maxLength) {
            throw new IllegalArgumentException(errorMessage);
        }

        return trimmedValue;
    }

    public void removeSession(Long chatId) {
        sessions.remove(chatId);
    }
}