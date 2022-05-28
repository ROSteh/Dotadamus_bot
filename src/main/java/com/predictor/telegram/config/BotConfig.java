package com.predictor.telegram.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация основных настроек бота
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "bot")
public class BotConfig {
    /**
     * Имя бота
     */
    private String username;
    /**
     * Токен бота полученный у @BotFather
     */
    private String token;
    /**
     * идентификатор чата для сообщений
     */
    private String chatId;
}
