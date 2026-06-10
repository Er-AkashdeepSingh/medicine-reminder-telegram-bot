package com.akashdeep.medicinereminder.config;

import com.akashdeep.medicinereminder.bot.MedicineReminderTelegramBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final MedicineReminderTelegramBot bot;

    public StartupRunner(MedicineReminderTelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void run(String... args) {
        bot.printBotInfo();
    }
}