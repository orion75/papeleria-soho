package com.papeleria.soho.papeleriasoho.payload.response;

import java.util.Map;

import lombok.Getter;

public class DetailResponse {
    @Getter private Boolean error = true;
    @Getter Map<String, String> menssage;

    public DetailResponse(Map<String, String> menssage) {
        this.menssage = menssage;
    }
}
