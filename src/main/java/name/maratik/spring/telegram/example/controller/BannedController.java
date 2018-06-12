package name.maratik.spring.telegram.example.controller;

import maratik.name.spring.telegram.annotation.TelegramBot;
import maratik.name.spring.telegram.annotation.TelegramForward;
import maratik.name.spring.telegram.annotation.TelegramMessage;
import org.springframework.beans.factory.annotation.Value;

/**
 * This controller is working for do nothing for specified user ids: 123, 321, 12345.
 * {@link TelegramBot#userId()} accepts the same behaviour as {@link Value}
 * (including {@code ${...} and #{...}}).
 * @author <a href="mailto:maratik@yandex-team.ru">Marat Bukharov</a>
 */
@TelegramBot({"123,321", "12345"})
public class BannedController {
    @TelegramMessage
    public void message() {
    }

    @TelegramForward
    public void forward() {
    }
}
