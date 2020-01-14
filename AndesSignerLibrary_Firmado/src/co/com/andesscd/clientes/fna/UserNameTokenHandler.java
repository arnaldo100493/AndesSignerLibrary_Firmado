// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:06:56 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UserNameTokenHandler.java

import co.com.andesscd.Base64Coder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Set;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class UserNameTokenHandler implements SOAPHandler<SOAPMessageContext> {

    public String user;

    public String pass;

    public UserNameTokenHandler() {
        this.user = "";
        this.pass = "";
    }

    public UserNameTokenHandler(String user, String pass) {
        setUser(user);
        setPass(pass);
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserNameTokenHandler{" + "user=" + user + ", pass=" + pass + '}';
    }

    @Override
    public boolean handleMessage(SOAPMessageContext smc) {
        Boolean outboundProperty = (Boolean) smc.get("javax.xml.ws.handler.message.outbound");
        if (outboundProperty.booleanValue()) {
            SOAPMessage message = smc.getMessage();
            try {
                String usernameStr = getUser();
                String passwordStr = getPass();
                byte[] nonceBytes = String.valueOf(Math.round(Math.random() * 1000000.0D)).getBytes();
                long create = (new Date()).getTime();
                String createdDate = String.valueOf(create);
                byte[] createdDateBytes = createdDate.getBytes("UTF-8");
                byte[] passwordBytes = passwordStr.getBytes("UTF-8");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.write(nonceBytes);
                baos.write(createdDateBytes);
                baos.write(passwordBytes);
                MessageDigest md = MessageDigest.getInstance("SHA-1");
                byte[] digestedPassword = md.digest(baos.toByteArray());
                String passwordB64 = new String(Base64Coder.encode(digestedPassword));
                String nonceB64 = new String(Base64Coder.encode(nonceBytes));
                SOAPEnvelope envelope = smc.getMessage().getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                if (header == null) {
                    header = envelope.addHeader();
                }
                SOAPElement security = header.addChildElement("Security", "wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                SOAPElement usernameToken = security.addChildElement("UsernameToken", "wsse");
                SOAPElement username = usernameToken.addChildElement("Username", "wsse");
                username.addTextNode(usernameStr);
                SOAPElement password = usernameToken.addChildElement("Password", "wsse");
                password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordDigest");
                password.addTextNode(passwordB64);
                SOAPElement nonce = usernameToken.addChildElement("Nonce", "wsse");
                nonce.addTextNode(nonceB64);
                SOAPElement created = usernameToken.addChildElement("Created", "wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
                created.addTextNode(createdDate);
            } catch (IOException | java.security.NoSuchAlgorithmException | javax.xml.soap.SOAPException | org.w3c.dom.DOMException e) {
                e.printStackTrace();
            }
        } else {
            try {
                SOAPMessage message = smc.getMessage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return outboundProperty.booleanValue();
    }

    @Override
    public Set getHeaders() {
        return null;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }

    @Override
    public void close(MessageContext context) {
    }
}
