package name.maratik.spring.telegram.example.controller;

import name.maratik.spring.telegram.annotation.TelegramBot;
import name.maratik.spring.telegram.annotation.TelegramCommand;

/**
 * Some controller, which is richer than default controller (it is achieved by inheritance).
 * @author <a href="mailto:maratik@yandex-team.ru">Marat Bukharov</a>
 */
@TelegramBot("[some_admin_user_ids]")
public class AdminController extends SimpleController {
    @TelegramCommand("/ban")
    public void banUser() {
    }
}
