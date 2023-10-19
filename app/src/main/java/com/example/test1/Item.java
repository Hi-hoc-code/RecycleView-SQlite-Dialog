package com.example.test1;

public class Item {
    private Integer id;
    private String name;
    private String email;
    private String sdt;

    public Item(Integer id, String name, String email, String sdt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.sdt = sdt;
    }

    public Item(String name, String email, String sdt) {
        this.name = name;
        this.email = email;
        this.sdt = sdt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
}
