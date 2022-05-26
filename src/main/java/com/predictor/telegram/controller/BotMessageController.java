package com.predictor.telegram.controller;

import com.predictor.telegram.SayPredictionBot;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BotMessageController {
    private final SayPredictionBot bot;

    /**
     * Отправка сообщения ботом
     *
     * @param message Текст сообщения
     * @return ОК - сообщение отправлено
     */
    @PostMapping(value = "/message")
    public ResponseEntity<?> sendMessage(@RequestParam String message) {
        SendMessage botMessage = SendMessage
                .builder()
                .chatId("-661901852")
                .text(message)
                .parseMode(ParseMode.HTML)
                .build();

        try {
            bot.execute(botMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Отправка сообщения ботом
     *
     * @return ОК - сообщение отправлено
     */
    @PostMapping(value = "/msg")
    public ResponseEntity<?> sendMessage2() {

        String msgString = """
                Wolf Team (radiant) 🆚 Ravens (dire)
                Карта: 1
                Выбранные герои Radiant: Hoodwink, Dragon Knight, Phoenix, Lifestealer, Timbersaw
                Выбранные герои Dire: Tiny, Abaddon, Sand King, Keeper of the Light, Ember Spirit

                Преимущество radiant по героям: 62%

                DPC WEU Division II Tour 2 - 2021/2022 -by 4D Esports

                Предполагаемый победитель:
                18:07 - Wolf Team 74% 🎯

                Мин. коэф. по мат. ожиданию - 1.35

                Ссылка на игру: 6520430616
                                """;

        SendMessage botMessage = SendMessage
                .builder()
                .chatId("-661901852")
                .text(msgString)
                .disableWebPagePreview(true)
                .entities(List.of(MessageEntity.builder()
                                .type("bold").offset(37).length(7).text("Карта:")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(46).length(23).text("Выбранные герои Radiant")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(128).length(20).text("Выбранные герои Dire")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(211).length(31).text("Преимущество radiant по героям:")
                                .build(),
                        MessageEntity.builder()
                                .type("text_link").offset(248).length(55).text("DPC WEU Division II Tour 2 - 2021/2022 -by 4D Esports").url("https://ru.dotabuff.com/esports/leagues/14072-dpc-sa-division-ii-tour-2-2021-2022-by-4d-esports")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(330).length(12).text("18:07 - Wolf")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(386).length(6).text("1.35\\n\\n")
                                .build(),
                        MessageEntity.builder()
                                .type("text_link").offset(408).length(10).url("https://ru.dotabuff.com/matches/6520430616").text("6520430616")
                                .build()))
                .build();

        try {
            bot.execute(botMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Отправка сообщения ботом
     *
     * @return ОК - сообщение отправлено
     */
    @PostMapping(value = "/msg3")
    public ResponseEntity<?> sendMessage3(@RequestParam String radiantHeroes,
                                          @RequestParam String direHeroes,
                                          @RequestParam String prediction) {
        log.info(String.valueOf(radiantHeroes));
        log.info(String.valueOf(direHeroes));

        String msgString = """
                Radiant 🆚 Dire
                Выбранные герои Radiant: %s
                Выбранные герои Dire: %s

                Предполагаемый победитель:
                %s 🎯
                """;

        SendMessage botMessage = SendMessage
                .builder()
                .chatId("-661901852")
                .text(String.format(msgString, radiantHeroes, direHeroes, prediction))
                .disableWebPagePreview(true)
                .entities(List.of(MessageEntity.builder()
                                .type("bold").offset(16).length(23).text("Выбранные герои Radiant")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(44+direHeroes.length()).length(20).text("Выбранные герои Dire")
                                .build()))
                .build();

        try {
            bot.execute(botMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Отправка сообщения ботом
     *
     * @return ОК - сообщение отправлено
     */
    @PostMapping(value = "/msg4")
    public ResponseEntity<?> sendMessage4(@RequestParam String radiantHeroes,
                                          @RequestParam String radiantTeam,
                                          @RequestParam String direHeroes,
                                          @RequestParam String direTeam,
                                          @RequestParam String prediction) {
        log.info(String.valueOf(radiantHeroes));
        log.info(String.valueOf(direHeroes));

        String msgString = """
                %s (radiant) 🆚 %s (dire)
                Выбранные герои Radiant: %s
                Выбранные герои Dire: %s

                Предполагаемый победитель:
                %s 🎯
                """;

        SendMessage botMessage = SendMessage
                .builder()
                .chatId("-661901852")
                .text(String.format(msgString, radiantTeam, direTeam, radiantHeroes, direHeroes, prediction))
                .disableWebPagePreview(true)
                .entities(List.of(MessageEntity.builder()
                                .type("bold").offset(22+radiantTeam.length()+direTeam.length()).length(23).text("Выбранные герои Radiant")
                                .build(),
                        MessageEntity.builder()
                                .type("bold").offset(49-9+radiantTeam.length()+direTeam.length()+direHeroes.length()).length(20).text("Выбранные герои Dire")
                                .build()))
                .build();

        try {
            bot.execute(botMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

/*

[MessageEntity(type=bold, offset=37, length=23, url=null, user=null, language=null, text=Выбранные герои Radiant),
MessageEntity(type=bold, offset=119, length=20, url=null, user=null, language=null, text=Выбранные герои Dire),
MessageEntity(type=text_link, offset=202, length=55, url=https://ru.dotabuff.com/esports/leagues/14072-dpc-sa-division-ii-tour-2-2021-2022-by-4d-esports, user=null, language=null, text=DPC WEU Division II Tour 2 - 2021/2022 -by 4D Esports

), MessageEntity(type=bold, offset=284, length=12, url=null, user=null, language=null, text=18:07 - Wolf),
MessageEntity(type=bold, offset=340, length=6, url=null, user=null, language=null, text=1.35

), MessageEntity(type=text_link, offset=362, length=10, url=https://ru.dotabuff.com/matches/6520430616, user=null, language=null, text=6520430616)]







0={MessageEntity@8677}"MessageEntity(type=bold, offset=37, length=7, url=null, user=null, language=null, text=Карта: )"
        1={MessageEntity@8678}"MessageEntity(type=bold, offset=46, length=23, url=null, user=null, language=null, text=Выбранные герои Radiant)"
        2={MessageEntity@8679}"MessageEntity(type=bold, offset=128, length=20, url=null, user=null, language=null, text=Выбранные герои Dire)"
        3={MessageEntity@8680}"MessageEntity(type=bold, offset=211, length=31, url=null, user=null, language=null, text=Преимущество radiant по героям:)"
        4={MessageEntity@8681}"MessageEntity(type=text_link, offset=248, length=55, url=https://ru.dotabuff.com/esports/leagues/14072-dpc-sa-division-ii-tour-2-2021-2022-by-4d-esports, user=null, language=null, text=DPC WEU Division II Tour 2 - 2021/2022 -by 4D Esports\n\n)"
        5={MessageEntity@8682}"MessageEntity(type=bold, offset=330, length=12, url=null, user=null, language=null, text=18:07 - Wolf)"
        6={MessageEntity@8683}"MessageEntity(type=bold, offset=386, length=6, url=null, user=null, language=null, text=1.35\n\n)"
        7={MessageEntity@8684}"MessageEntity(type=text_link, offset=408, length=10, url=https://ru.dotabuff.com/matches/6520430616, user=null, language=null, text=6520430616)"


 */