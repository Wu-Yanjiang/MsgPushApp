package cn.wyj.msgpushapp.entity;


public class UserInfo {

    private Long id;
    private String name;
    private String email;
    private String sendKey;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSendKey() {
        return sendKey;
    }

    public void setSendKey(String sendKey) {
        this.sendKey = sendKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
