// SPRINT 5

package cat.tecnocampus.ofc.application.dtos;

import java.util.UUID;

public class ConsumerDTO {
    private String id;
    private String name;
    private String secondName;
    private String email;


    public ConsumerDTO(String id, String name, String secondName, String email) {
        this.id = id;
        this.name = name;
        this.secondName = secondName;
        this.email = email;
    }
    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id){this.id=id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
