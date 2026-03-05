package DesignPatterns.DataTransferObject.Domain;
import java.util.Objects;

public class User {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final String cpf;

    private User(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.cpf = builder.cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String cpf;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder cpf(String cpf){
            this.cpf = cpf;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}