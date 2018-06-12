package name.maratik.spring.telegram.example.controller;

import name.maratik.spring.telegram.example.util.SomeEnum;

import maratik.name.spring.telegram.annotation.TelegramBot;
import maratik.name.spring.telegram.annotation.TelegramCommand;
import maratik.name.spring.telegram.util.Localizable;
import org.telegram.telegrambots.api.methods.send.SendMessage;

/**
 * @author <a href="mailto:maratik@yandex-team.ru">Marat Bukharov</a>
 */
@TelegramBot
public class LocalizationExampleController extends Localizable {
    @TelegramCommand(commands = "/hello", description = "#{@loc.t('LocalizationExampleController.COMMAND.HELLO.DESC')}")
    public SendMessage hello(long userId) {
        return new SendMessage()
            .setChatId(userId)
            .setText(t("LocalizationExampleController.COMMAND.HELLO.REPLY",
                t(SomeEnum.SOME_ENUM_VALUE)
            ));
    }
}
