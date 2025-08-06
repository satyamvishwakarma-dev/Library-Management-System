import java.util.Objects;

public class Commands {
    public boolean login(String userName, String password) {
        return Objects.equals(userName, "admin") && Objects.equals(password, "1234");
    }
}
