package com.papeleria.soho.papeleriasoho.payload.response;


import lombok.Getter;

public class Response<T> {
    @Getter Boolean error = false;
    @Getter String mensagge;
    @Getter T data;

    
    public Response(Boolean error, String mensagge, T data) {
        this.error = error;
        this.mensagge = mensagge;
        this.data = data;
    }

    public Response(String mensagge, T data) {
        this.mensagge = mensagge;
        this.data = data;
    }

    public Response(Boolean error, String mensagge) {
        this.error = error;
        this.mensagge = mensagge;
    }

}
