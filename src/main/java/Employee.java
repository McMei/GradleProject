
public class Employee {

    private final String name;
    private String password;
    private String username = "";
    private String email = "";
    private final String reversePass;

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

    public boolean checkName() {
        for (int i = 0; i < name.length(); i++) {
            if (Character.isSpaceChar(name.charAt(i))) {
                return true;
            }
        }
        return false;
    }

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

    public String reverseString(String pw) {
        if (pw.length() == 1) {
            return pw;
        } else {
            return reverseString(pw.substring(1)) + pw.charAt(0);
        }
    }

    public String toString() {
        return "Employee Details" + "\nName : " + name + "\nUsername : " + username
                + "\nEmail : " + email + "\nInitial Password : " + password + "\nReversed Password: "
                + reversePass + "\n\n";
    }
}
