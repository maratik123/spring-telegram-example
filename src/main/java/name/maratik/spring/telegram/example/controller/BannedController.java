//    cwshopbot
//    Copyright (C) 2018  Marat Bukharov.
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU Affero General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU Affero General Public License for more details.
//
//    You should have received a copy of the GNU Affero General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
