package model;

public class User {

    private String username;
    private String school;
    private String sessionName;


    public User() {

        this.username = "";
        this.school = "";
        this.sessionName = "";

    }

    public User(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;

    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getSchool() {

        return school;
    }

    public void setSchool(String school) {
        this.school = school;

    }

    public String getSessionName() {

        return sessionName;

    }

    public void setSessionName(String sessionName) {

        this.sessionName = sessionName;

    }

    public boolean isComplete() {
        return username != null && !username.trim().isEmpty()
                && school != null && !school.trim().isEmpty()
                && sessionName != null && !sessionName.trim().isEmpty();

    }



    @Override
    public String toString() {

        return "User{username='" + username + "', school='" + school + "', sessionName='" + sessionName + "'}";

    }

}