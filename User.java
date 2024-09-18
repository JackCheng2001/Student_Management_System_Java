import java.util.Scanner;

/**
 * @author Cheng Erxi, Jiayi Wang
 * * Abstract base class representing a generic user in the system.
 *  * This class provides common properties and methods shared by all types of users.
 */
public abstract class User {
    // Protected fields to store user information

    protected String id;
    protected String name;
    protected String username;
    protected String password;
    /**
     * Constructor for creating a new User object.
     * Initializes the user with the provided details.
     *
     * @param id       The unique identifier for the user.
     * @param name     The name of the user.
     * @param username The username for the user's login.
     * @param password The password for the user's account.
     */
    public User(String id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }
    // Getter method for the username
    public String getUsername() {
        return username;
    }

    // Getter for the password
    public String getPassword() {
        return password;
    }

    // Getter method for the name
    public String getName() {
        return name;
    }
    // Getter method for the ID
    public String getId() {
        return id;
    }


}