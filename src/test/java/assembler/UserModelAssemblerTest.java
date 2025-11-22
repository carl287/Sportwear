package assembler;


import com.example.Sportwear.assemblers.UserModelAssembler;
import com.example.Sportwear.Controller.UserController;
import com.example.Sportwear.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelAssemblerTest {

    @Test
    void testToModel() {
        UserModelAssembler assembler = new UserModelAssembler();

        User user = new User();
        user.setId(5);

        EntityModel<User> model = assembler.toModel(user);

        assertEquals(5, model.getContent().getId());

        assertTrue(model.getLinks().hasLink("self"));
        assertTrue(model.getLinks().hasLink("users"));

        assertNotNull(model.getLink("self"));
    }
}
