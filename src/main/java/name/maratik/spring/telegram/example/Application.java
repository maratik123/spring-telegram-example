package name.maratik.spring.telegram.example;

import name.maratik.spring.telegram.annotation.EnableTelegramBot;
import name.maratik.spring.telegram.annotation.TelegramCommand;
import name.maratik.spring.telegram.config.TelegramBotBuilder;
import name.maratik.spring.telegram.config.TelegramBotType;
import name.maratik.spring.telegram.util.Localizable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableTelegramBot
@Configuration
public class Application {
    @Bean
    public TelegramBotType telegramBotType() {
        return TelegramBotType.LONG_POLLING;
    }

    @Bean
    public TelegramBotBuilder telegramBotBuilder() {
        return new TelegramBotBuilder()
            .username("[username]")
            .token("[token]");
    }

    /**
     * Optional bean to support localization.
     * For example, it can be used at {@link TelegramCommand#description()}:
     * <br/>
     * <code>
     *     {@literal @}TelegramCommand(description = "#{{@literal @}loc.t('some translation tag')}")
     * </code>
     * <br/>
     * Looks ugly, but anyway.
     */
    @Bean
    public Localizable loc() {
        return new Localizable();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
