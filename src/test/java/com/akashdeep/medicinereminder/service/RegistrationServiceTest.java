package com.akashdeep.medicinereminder.service;

import com.akashdeep.medicinereminder.dto.RegistrationStep;
import com.akashdeep.medicinereminder.entity.User;
import com.akashdeep.medicinereminder.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    private static final Long CHAT_ID = 123L;

    @Mock
    private UserRepository userRepository;

    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        registrationService = new RegistrationService(userRepository);
        registrationService.startRegistration(CHAT_ID);
    }

    @Test
    void storesNameAndMovesToRelationshipStep() {
        registrationService.storeName(CHAT_ID, "  Alex Doe  ");

        assertEquals("Alex Doe", registrationService.getSession(CHAT_ID).getFullName());
        assertEquals(
                RegistrationStep.WAITING_FOR_RELATIONSHIP,
                registrationService.getSession(CHAT_ID).getStep());
    }

    @Test
    void rejectsBlankNameAndKeepsNameStep() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.storeName(CHAT_ID, "  "));

        assertEquals("Please enter your full name.", exception.getMessage());
        assertEquals(
                RegistrationStep.WAITING_FOR_NAME,
                registrationService.getSession(CHAT_ID).getStep());
    }

            @Test
            void rejectsCommandAsNameAndKeepsNameStep() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.storeName(CHAT_ID, "/start"));

            assertEquals("Please enter your full name.", exception.getMessage());
            assertEquals(
                RegistrationStep.WAITING_FOR_NAME,
                registrationService.getSession(CHAT_ID).getStep());
            }

    @Test
    void persistsUserAndRemovesSessionAfterRelationship() {
        registrationService.storeName(CHAT_ID, "Alex Doe");

        registrationService.completeRegistration(CHAT_ID, "alex", "self");

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals(CHAT_ID, savedUser.getChatId());
        assertEquals("alex", savedUser.getTelegramUsername());
        assertEquals("Alex Doe", savedUser.getFullName());
        assertEquals("self", savedUser.getRelationship());
        assertTrue(savedUser.isActive());
        assertNotNull(savedUser.getCreatedAt());
        assertFalse(registrationService.isRegistrationInProgress(CHAT_ID));
    }

    @Test
    void rejectsBlankRelationshipWithoutPersisting() {
        registrationService.storeName(CHAT_ID, "Alex Doe");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> registrationService.completeRegistration(CHAT_ID, null, ""));

        assertEquals("Please enter your relationship.", exception.getMessage());
        assertTrue(registrationService.isRegistrationInProgress(CHAT_ID));
    }
}