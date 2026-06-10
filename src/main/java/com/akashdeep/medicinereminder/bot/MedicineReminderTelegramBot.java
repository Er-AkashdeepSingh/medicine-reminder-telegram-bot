package com.akashdeep.medicinereminder.bot;

import com.akashdeep.medicinereminder.config.TelegramBotProperties;
import org.springframework.stereotype.Component;

@Component
public class MedicineReminderTelegramBot {

    private final TelegramBotProperties properties;

    public MedicineReminderTelegramBot(TelegramBotProperties properties) {
        this.properties = properties;
    }

    public void printBotInfo() {
        System.out.println("Bot Username: " + properties.username());
    }
}