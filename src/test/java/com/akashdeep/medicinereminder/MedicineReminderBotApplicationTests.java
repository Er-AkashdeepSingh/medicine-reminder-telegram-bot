package com.akashdeep.medicinereminder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.longpolling.starter.TelegramBotStarterConfiguration;

@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = TelegramBotStarterConfiguration.class)
class MedicineReminderBotApplicationTests {

	@Test
	void contextLoads() {
	}

}
