package src;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import src.Exceptions.*;
import src.Users.Hotel;
import src.Users.HotelManager;
import src.Users.User;
import src.database.Database;
import src.database.PassHashing;

import static org.junit.jupiter.api.Assertions.*;


public class ClassTest {
    @Test
    public void testUserMethod() throws UserNotFoundException, PasswordIncorrectException, UserAlreadyExistsException {
        Database.setUp();
        User result = User.createUser("Ghita","ghita","client","Faget","Ghita@gmail.com");
        User expected = User.getUser("Ghita", "ghita");
        Assert.assertEquals(expected.getName(),result.getName());
    }
    @Test
    public void testUserNotFoundExceptionisThrown() throws UserNotFoundException, PasswordIncorrectException {
        Database.setUp();
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            User user = User.getUser("neneaIAncu", "bere");
        });
    }
    @Test
    public void testUserAlreadyExistsExceptionisThrown() throws UserAlreadyExistsException {
        Database.setUp();
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            User user = User.createUser("victor","victor","owner","toc","victor@gmail.com");
        });

    }
    @Test
    public void testPasswordIncorrectExceptionisThrown() throws UserNotFoundException, PasswordIncorrectException {
        Database.setUp();
        Assertions.assertThrows(PasswordIncorrectException.class, () -> {
            User user = User.getUser("victor", "afostodata");
        });

    }
    @Test
    public void testNoHotelAddedExceptionisThrown() throws NoHotelsAddedException, UserAlreadyExistsException {
        Database.setUp();
        User user = User.createUser("Eliada", "123456","Owner","Arad","eliada@gmail.com");
        Assertions.assertThrows(NoHotelsAddedException.class, () -> {
            user.checkList();
        });


    }
    @Test
    public void HotelAlreadyExistsEceptionisThrown() throws UserNotFoundException, PasswordIncorrectException, HotelAlreadyExistsException {
        Database.setUp();
        User user = User.getUser("victor", PassHashing.getMd5("victor"));
        Assertions.assertThrows(HotelAlreadyExistsException.class, () -> {
            ((HotelManager)user).addHotel(new Hotel("Iris",user.getName(),"nice hotel","pool",120));
        });

    }


}