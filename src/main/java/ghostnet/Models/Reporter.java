package ghostnet.Models;

import org.checkerframework.checker.nullness.qual.Nullable;

import javax.persistence.Entity;

@Entity
public class Reporter extends Person {
    @Nullable
    private String phone;

    public @Nullable String getPhone() {
        return phone;
    }

    public void setPhone(@Nullable String phone) {
        this.phone = phone;
    }
}
