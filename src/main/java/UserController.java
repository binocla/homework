import java.util.LinkedHashSet;
import java.util.Set;

public class UserController {
    static Set<String> set = new LinkedHashSet<>();
    public static String Register(String name) {
        int size = set.size();
        set.add(name);
        if (set.size() == size) {
            return "Вы уже были зареганы!";
        } else {
            return "Успешно!";
        }
    }
    public static String Delete(String name) {
        try {
            if (set.isEmpty() || !set.contains(name)) {
                throw new Exception();
            }
            set.remove(name);
            return "Успешно удалились =(";
        } catch (Exception e) {
            return "Что-то пошло не так! Вы вообще регистрировались?";
        }
    }
}
