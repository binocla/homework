import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class UserController {
    static Set<String> set = new LinkedHashSet<>();
    public static String Register(Update update) {
        int size = set.size();
        set.add(update.getMessage().getFrom().getUserName());
        if (set.size() == size) {
            return "Вы уже были зареганы!";
        } else {
            return "Успешно!";
        }
    }
    public static String Delete(Update update) {
        try {
            if (set.isEmpty() || !set.contains(update.getMessage().getFrom().getUserName())) {
                throw new Exception();
            }
            set.remove(update.getMessage().getFrom().getUserName());
            return "Успешно удалились =(";
        } catch (Exception e) {
            return "Что-то пошло не так! Вы вообще регистрировались?";
        }
    }
}
