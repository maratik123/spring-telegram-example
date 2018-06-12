package name.maratik.spring.telegram.example;

import maratik.name.spring.telegram.annotation.EnableTelegramBot;
import maratik.name.spring.telegram.config.TelegramBotBuilder;
import maratik.name.spring.telegram.config.TelegramBotType;
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

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
