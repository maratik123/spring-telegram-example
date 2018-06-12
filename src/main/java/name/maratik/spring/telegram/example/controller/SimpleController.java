package name.maratik.spring.telegram.example.controller;

import maratik.name.spring.telegram.TelegramBotService;
import maratik.name.spring.telegram.annotation.TelegramBot;
import maratik.name.spring.telegram.annotation.TelegramCommand;
import maratik.name.spring.telegram.annotation.TelegramForward;
import maratik.name.spring.telegram.annotation.TelegramHelp;
import maratik.name.spring.telegram.annotation.TelegramMessage;
import maratik.name.spring.telegram.model.TelegramMessageCommand;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.ForwardMessage;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

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
     * {@link TelegramBotService#TelegramBotService(org.telegram.telegrambots.TelegramBotsApi, org.springframework.beans.factory.config.EmbeddedValueResolver, java.lang.String)}
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
