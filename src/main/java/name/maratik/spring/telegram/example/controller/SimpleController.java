package name.maratik.spring.telegram.example.controller;

import name.maratik.spring.telegram.TelegramBotService;
import name.maratik.spring.telegram.annotation.TelegramBot;
import name.maratik.spring.telegram.annotation.TelegramCommand;
import name.maratik.spring.telegram.annotation.TelegramForward;
import name.maratik.spring.telegram.annotation.TelegramHelp;
import name.maratik.spring.telegram.annotation.TelegramMessage;
import name.maratik.spring.telegram.model.TelegramMessageCommand;

import org.springframework.beans.factory.config.EmbeddedValueResolver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.Instant;

/**
 * This controller will work for all user (except those, who filtered out to another {@link TelegramBot} controllers
 * with specified {@link TelegramBot#userId()}).
 * @author <a href="mailto:maratik@yandex-team.ru">Marat Bukharov</a>
 */
@TelegramBot
public class SimpleController {
    /**
     * The message handler. (For argument mapping see
     * {@link TelegramBotService#TelegramBotService(TelegramBotsApi, EmbeddedValueResolver, java.lang.String)}
     * )
     * .
     * @param userId user id
     * @param user User object
     * @param message message
     * @return send message object, that will be sent to user
     */
    @TelegramMessage
    public SendMessage defaultAction(long userId, User user, String message) {
        return new SendMessage()
            .setChatId(userId)
            .setText(String.format("Hello %s, you've send me %s", user.getFirstName(), message));
    }

    @TelegramCommand(commands = "/some_command", description = "Some command")
    public SendMessage someCommand(long userId, TelegramMessageCommand telegramMessageCommand) {
        return new SendMessage()
            .setChatId(userId)
            .setText(String.format("You've send me %s command with arguments: %s",
                telegramMessageCommand.getCommand(), telegramMessageCommand.getArgument()
            ));
    }

    /**
     * Controller for command which is not mentioned at {@code /help}.
     */
    @TelegramCommand(commands = "/some_hidden_command", description = "Some command", hidden = true)
    public SendMessage someHiddenCommand(long userId, TelegramMessageCommand telegramMessageCommand) {
        return new SendMessage()
            .setChatId(userId)
            .setText(String.format("You've send me %s hidden command with arguments: %s",
                telegramMessageCommand.getCommand(), telegramMessageCommand.getArgument()
            ));
    }

    @TelegramForward
    public BotApiMethod<?> defaultForwardHandler(long userId, Message message) {
        return new ForwardMessage()
            .setChatId(userId)
            .setFromChatId(message.getForwardFromChat().getId())
            .setMessageId(message.getForwardFromMessageId());
    }

    /**
     * Accepts forwards only from {@link TelegramForward#from()}.
     */
    @TelegramForward("2321321")
    public SendMessage forwardFromSomeChat(long userId, String text, Instant instant) {
        return new SendMessage()
            .setChatId(userId)
            .setText(String.format("You forwarder to me %s, which time is %s", text, instant));
    }

    @TelegramHelp
    public String helpPrefix() {
        return "This text will be prefixed at /help command\n";
    }
}
