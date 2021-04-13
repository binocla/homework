import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;
import java.util.Set;

public class BotCommands {
    @Command(description = "Помощь")
    public static String help() {
        StringBuilder s = new StringBuilder();
        for (Method m: BotCommands.class.getMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                s.append(m.getAnnotation(Command.class).description()).append("\n").append(m.getAnnotation(Command.class).additionalInfo()).append(" (/").append(m.getName()).append(")\n");
            }
        }
        if (s.isEmpty()) {
            s.append("Ничего не умею =(");
        }
        return s.toString();
    }

    @Command(description = "Пока", additionalInfo = "Бот выводит Пока")
    public static String bye() {
        return "Пока!";
    }
    @Command(description = "Привет", additionalInfo = "Бот выводит Привет")
    public static String hello() { return "Привет!"; }
    @Command(description = "Регистрация", additionalInfo = "Бот регистрирует пользователя. По /reg - по юзернейму, по /reg *name* по кастомному имени (inProgress)")
    public static String reg(Update update) {
            return UserController.Register(update.getMessage().getFrom().getUserName());
    }
    @Command(description = "Удаление", additionalInfo = "Бот удаляет пользователя. Работает по принципу регистрации (/del *name* inProgess)")
    public static String del(Update update) {
        return UserController.Delete(update.getMessage().getFrom().getUserName());
    }
    @Command(description = "Показать всех пользователей", additionalInfo = "Set из всех участников")
    public static Set<String> show() {
        return UserController.set;
    }

}
