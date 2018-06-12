package name.maratik.spring.telegram.example.util;

import name.maratik.spring.telegram.util.LocalizableValue;

/**
 * @author <a href="mailto:maratik@yandex-team.ru">Marat Bukharov</a>
 */
public enum SomeEnum implements LocalizableValue {
    SOME_ENUM_VALUE("SomeEnum.SOME_ENUM_VALUE");

    private final String translationTag;

    SomeEnum(String translationTag) {
        this.translationTag = translationTag;
    }

    @Override
    public String getTranslationTag() {
        return translationTag;
    }
}
