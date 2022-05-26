package com.predictor.telegram;

import com.predictor.telegram.config.BotConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс бота для отправки сообщений с предсказаниями матча
 */
@Slf4j
@Component
@AllArgsConstructor
public class SayPredictionBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    /**
     * Handler входящих сообщений
     * @param update новое сообщение
     */
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
        log.info(String.valueOf(update));
    }

    /**
     * Установка имени бота
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return botConfig.getUsername();
    }

    /**
     * Установка токена бота
     * @return токен бота
     */
    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }
}