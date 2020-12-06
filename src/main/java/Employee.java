/**
 * Represents an employee.
 *
 * @author Michelle Quan
 */
public class Employee {

    /**
     * Employee's full name.
     */
    private final String name;

    /**
     * Employee's password.
     */
    private String password;

    /**
     * Employee's  username.
     */
    private String username = "";

    /**
     * Employee's email address.
     */

    private String email = "";

    /**
     * Employee's reversed password.
     */
    private final String reversePass;

    /**
     * Creates user information.
     *
     * @param name     The employee’s full name.
     * @param password The employee’s password
     */
    Employee(String name, String password) {
        this.name = name;
        this.password = password;

        if (!checkName()) {
            this.username = "default";
            this.email = "user@oracleacademy.Test";

        } else {
            setUsername(name);
            setEmail(name);
        }

        if (!isValidPassword()) {
            this.password = "pw";
            reversePass = reverseString(this.password);
        } else {
            this.password = password;
            reversePass = reverseString(this.password);
        }
    }

    /**
     * Check if the name contain a space
     *
     * @return A Boolean To check if the name contain a space.
     */
    public boolean checkName() {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the username field to the first name and then the last name, all lowercase.
     *
     * @param name employee's full name.
     */
    public void setUsername(String name) {
        username = name.substring(0, 1);
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                username += name.substring(i + 1);
                break;
            }
        }
        username = username.toLowerCase();
    }

    /**
     * Sets the email field to the first name, then a period, then the last name followed by @oracleacademy.Test
     *
     * @param name employee's full name.
     */
    public void setEmail(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                email += name.substring(0, i);
                email += ".";
                email += name.substring(i + 1);
                break;
            }
        }
        email = email.toLowerCase();
        email += "@oracleacademy.Test";
    }

    /**
     * Check if the password is valid (containing a lowercase letter, uppercase letter, and a special character).
     *
     * @return A Boolean if the password is valid.
     */
    public Boolean isValidPassword() {
        int lowercase = 0;
        int uppercase = 0;
        int character = 0;

        for (int i = 0; i < password.length(); i++) {
            if (Character.isLowerCase(password.charAt(i))) {
                lowercase++;
            } else if (Character.isUpperCase(password.charAt(i))) {
                uppercase++;
            } else if (!Character.isDigit(password.charAt(i)) && !Character.isLetter(password.charAt(i))) {
                character++;
            }
        }

        return lowercase != 0 && uppercase != 0 && character != 0;
    }

    /**
     * Reverse the order of the text stored for the database password.
     *
     * @param pw User's password.
     * @return A String representing the reversed password.
     */
    public String reverseString(String pw) {
        if (pw.length() == 1) {
            return pw;
        } else {
            return reverseString(pw.substring(1)) + pw.charAt(0);
        }
    }

    /**
     * Gets employee information.
     *
     * @return A String with employee information.
     */
    @Override
    public String toString() {
        return "Employee Details" + "\nName : " + name + "\nUsername : " + username
                + "\nEmail : " + email + "\nInitial Password : " + password + "\nReversed Password: "
                + reversePass + "\n\n";
    }
}
