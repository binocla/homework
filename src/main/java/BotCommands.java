import org.telegram.telegrambots.meta.api.objects.Update;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;

public class BotCommands {
    @Command(description = "Помощь")
    public static String help() {
        StringBuilder s = new StringBuilder();
        for (Method m: BotCommands.class.getMethods()) {
            if (m.isAnnotationPresent(Command.class)) {
                s.append(m.getAnnotation(Command.class).description()).append(" (/").append(m.getName()).append(")\n");
            }
        }
        if (s.isEmpty()) {
            s.append("Ничего не умею =(");
        }
        return s.toString();
    }
    @Command(description = "Пока")
    public static String bye() {
        return "Пока!";
    }
    @Command(description = "Привет")
    public static String hello() { return "Привет!"; }
    @Command(description = "Регистрация")
    public static String reg(Update update) {
            return UserController.Register(update);
    }
    @Command(description = "Удаление")
    public static String del(Update update) {
        return UserController.Delete(update);
    }
    @Command(description = "Показать всех пользователей")
    public static Set<String> show() {
        return UserController.set;
    }

}
