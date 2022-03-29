package chess;

public class Role {
    private final String role;

    public Role(String role) {
        this.role = role;
    }

    public String get() {
        return role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "role='" + role + '\'' +
                '}';
    }
}
