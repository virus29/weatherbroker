package com.weatherbroker.view;

import java.util.Objects;

public class OrgListViewResponse {

    /**
     * Id организации
     */
    private Long id;

    /**
     * Краткое название организации
     */
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrgListViewResponse)) return false;
        OrgListViewResponse that = (OrgListViewResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }
}
