// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:55 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   HeaderHandlerResolver.java
package co.com.andesscd.clientes.fna;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

public class HeaderHandlerResolver implements HandlerResolver {

    public String user;

    public String pass;

    public HeaderHandlerResolver() {
        this.user = "";
        this.pass = "";
    }

    public HeaderHandlerResolver(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "HeaderHandlerResolver{" + "user=" + user + ", pass=" + pass + '}';
    }
    
    @Override
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        List<Handler> handlerChain = new ArrayList<>();
        UserNameTokenHandler unth = new UserNameTokenHandler(this.user, this.pass);
        handlerChain.add(unth);
        return handlerChain;
    }
}
