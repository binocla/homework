import org.telegram.telegrambots.meta.api.objects.User;

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
    public static String help(String str) {
        StringBuilder s = new StringBuilder();
        for (Method m: BotCommands.class.getMethods()) {
            if (m.isAnnotationPresent(Command.class) && m.getName().equals(str)) {
                s.append(m.getAnnotation(Command.class).additionalInfo()).append(" (/").append(m.getName()).append(")\n");
            }
        }
        if (s.isEmpty()) {
            s.append("Нет описания такой команды");
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
    public static String reg(String username) {
            return UserController.Register(username);
    }
    @Command(description = "Удаление", additionalInfo = "Бот удаляет пользователя. Работает по принципу регистрации (/del *name* inProgess)")
    public static String del(String username) {
        return UserController.Delete(username);
    }
    @Command(description = "Показать всех пользователей", additionalInfo = "Set из всех участников")
    public static Set<String> show() {
        return UserController.set;
    }

    public static String proceedCommand(User user, String s) {
        String v = "Нет такой команды";
        String[] ar = s.split(" ");
        switch (ar.length) {
            case 1:
                switch (ar[0]) {
                    case "/help", "/start" -> v = help();
                    case "/reg" -> v = reg(user.getUserName());
                    case "/del" -> v = del(user.getUserName());
                }
                break;
            case 2:
                switch (ar[0]) {
                    case "/help", "/start" -> v = help(ar[1]);
                }
                switch (ar[0]) {
                    case "/reg" -> v = reg(ar[1]);
                    case "/del" -> v = del(ar[1]);
                }
                break;
        }
        return v;
    }

}
