package software.jevera.dao.inmemory;

import org.junit.Test;
import software.jevera.domain.User;

import static org.junit.Assert.*;

public class UserInMemoryRepositoryTest {

    UserInMemoryRepository userInMemoryRepository = new UserInMemoryRepository();

    @Test
    public void isUserWithLoginExists() {
        User user = new User();
        user.setLogin("user name");
        User user1 = new User();
        user1.setLogin("user name");

        assertEquals(true, userInMemoryRepository.isUserWithLoginExists(user.getLogin()));
    }

    @Test
    public void save() {
        

    }

    @Test
    public void findUserByLogin() {
    }
}