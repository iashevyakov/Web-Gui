import org.junit.Before;
import org.junit.Test;
import ru.itis.inform.dao.UserDao;
import ru.itis.inform.models.User;
import ru.itis.inform.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {

         userService = mock(UserServiceImpl.class);

        User simpleUser = new User("1","ivan","ivan","ivan",true,false);

        when(userService.find(anyString())).thenReturn(simpleUser);

        when(userService.findId("1")).thenReturn(simpleUser);

        List<User> list = new LinkedList<User>();

        list.add(simpleUser);

        when(userService.findAll()).thenReturn(list);

    }

    @Test
    public void find() throws Exception {

        User actual = userService.find("ivan");

        assertEquals(actual.getName(), "ivan");

    }
    @Test
    public void findId() throws Exception {

        User actual = userService.findId("1");

        assertEquals(actual.getIs_admin(), true);
    }
    @Test
    public void findAll() throws Exception {

        User actual = userService.find("ivan");

        List<User> list = userService.findAll();

        assertEquals(list.get(0),actual);

    }

}
