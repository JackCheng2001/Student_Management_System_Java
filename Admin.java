/**
 * Represents an administrator in the system.
 * This class extends {@code User}, inheriting its basic user attributes.
 * It can be used to define additional behaviors and properties specific to administrators.
 * @author Cheng Erxi, Jiayi Wang
 */
public class Admin extends User {

    /**
     * Constructs a new Admin instance.
     * This constructor initializes the admin's details using the provided parameters.
     * It calls the constructor of the superclass {@code User} to set the common attributes.
     *
     * @param id        The unique identifier for the admin.
     * @param name      The name of the admin.
     * @param username  The username for the admin's login.
     * @param password  The password for the admin's account.
     */
    public Admin(String id, String name, String username, String password) {

        super(id, name, username, password);
    }
    // Call the constructor of the superclass (User) to initialize common attributes

}