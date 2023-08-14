package ch.cern.todo.persistence.model;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="CURRENTUSERS")
public class CurrentUser implements UserDetails {

    @NotNull
    @Id
    @Column(columnDefinition = "NUMBER")
    private int id;

    @NotNull
    @Column(unique=true, columnDefinition = "VARCHAR2(100)")
    private String userName;

    @NotNull
    @Column(columnDefinition = "VARCHAR2(100)")
    private String password;

    public CurrentUser() {
    }

    public CurrentUser(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
