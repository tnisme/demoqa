package demoqa.entities;

/**
 * Represents a user account with authentication credentials.
 * This class follows the builder pattern for object creation.
 */
public class Account {
    private String username;
    private String password;

    private Account(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + " password: " + getPassword();
    }

    public static class Builder {
        private String username;
        private String password;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}