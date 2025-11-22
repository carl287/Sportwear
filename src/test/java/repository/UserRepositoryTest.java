package repository;

import com.example.Sportwear.Repository.UserRepository;
import com.example.Sportwear.Model.User;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserRepositoryTest {

    @Test
    void testRepositoryMock() {
        UserRepository repo = Mockito.mock(UserRepository.class);

        User u = new User();
        u.setUsername("paula");

        repo.save(u);

        Mockito.verify(repo).save(u);
    }
}

