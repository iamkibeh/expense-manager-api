package tech.kibetimmanuel.expensemanagerapi.dto;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import tech.kibetimmanuel.expensemanagerapi.model.User;

@Data
public class UserDto {
    private String name;
    private String email;
    private String password;

    public User buildUser(){
        User user = new User();
        BeanUtils.copyProperties(this, user);

        return user;
    }

}
