package com.akashdeep.medicinereminder.bot;

import com.akashdeep.medicinereminder.config.TelegramBotProperties;
import com.akashdeep.medicinereminder.service.CommandRouterService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.interfaces.LongPollingUpdateConsumer;
import org.telegram.telegrambots.longpolling.starter.SpringLongPollingBot;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.generics.TelegramClient;

@Component
public class MedicineReminderTelegramBot
        implements SpringLongPollingBot, LongPollingSingleThreadUpdateConsumer {

    private final TelegramBotProperties properties;
    private final CommandRouterService commandRouterService;
    private final TelegramClient telegramClient;

    public MedicineReminderTelegramBot(
            TelegramBotProperties properties,
            CommandRouterService commandRouterService) {

        this.properties = properties;
        this.commandRouterService = commandRouterService;

        this.telegramClient =
                new OkHttpTelegramClient(properties.token());
    }

    @Override
    public String getBotToken() {
        return properties.token();
    }

    @Override
    public LongPollingUpdateConsumer getUpdatesConsumer() {
        return this;
    }

    @Override
    public void consume(Update update) {

        if (!update.hasMessage()) {
            return;
        }

        if (!update.getMessage().hasText()) {
            return;
        }

        String command = update.getMessage().getText();

        String response =
                commandRouterService.handleCommand(command);

        SendMessage sendMessage =
                SendMessage.builder()
                        .chatId(update.getMessage().getChatId().toString())
                        .text(response)
                        .build();

        try {
            telegramClient.execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}