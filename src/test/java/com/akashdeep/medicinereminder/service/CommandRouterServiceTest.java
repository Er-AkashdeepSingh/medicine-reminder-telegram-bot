package com.akashdeep.medicinereminder.service;

import com.akashdeep.medicinereminder.command.RegisterCommandHandler;
import com.akashdeep.medicinereminder.command.StartCommandHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommandRouterServiceTest {

    @Test
    void routesRegisterCommandAndStartsRegistration() {
        StartCommandHandler startHandler = mock(StartCommandHandler.class);
        RegisterCommandHandler registerHandler = mock(RegisterCommandHandler.class);
        CommandRouterService router = new CommandRouterService(startHandler, registerHandler);
        when(registerHandler.startRegistration(123L)).thenReturn("name prompt");

        String response = router.handleCommand(123L, "alex", "/register");

        assertEquals("name prompt", response);
        verify(registerHandler).startRegistration(123L);
        verifyNoInteractions(startHandler);
    }

    @Test
    void forwardsRegistrationResponseWhenSessionIsActive() {
        StartCommandHandler startHandler = mock(StartCommandHandler.class);
        RegisterCommandHandler registerHandler = mock(RegisterCommandHandler.class);
        CommandRouterService router = new CommandRouterService(startHandler, registerHandler);
        when(registerHandler.isRegistrationInProgress(123L)).thenReturn(true);
        when(registerHandler.handleRegistrationMessage(123L, "alex", "Alex Doe"))
                .thenReturn("relationship prompt");

        String response = router.handleCommand(123L, "alex", "Alex Doe");

        assertEquals("relationship prompt", response);
        verify(registerHandler).handleRegistrationMessage(123L, "alex", "Alex Doe");
    }
}