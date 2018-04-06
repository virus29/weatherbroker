package com.weatherbroker.view;

//import javax.validation.constraints.NotNull;

public class OrgListViewRequest {

    /**
     * Краткое название организации
     */
//    @NotNull
    private String name;

    /**
     * ИНН организации
     */
    private String inn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
