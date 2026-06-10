package com.akashdeep.medicinereminder;

import com.akashdeep.medicinereminder.config.TelegramBotProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TelegramBotProperties.class)
public class MedicineReminderBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineReminderBotApplication.class, args);
	}
	
}
