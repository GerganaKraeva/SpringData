package bg.softuni.json_exercise.service.dtos.export;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class UserAndProductsDTO implements Serializable {
    @Expose
     private int usersCount;
    @Expose
    private List<UserSoldDTO> users;

    public List<UserSoldDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserSoldDTO> users) {
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }


}
