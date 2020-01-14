// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MailSessionDefinition.java

package javax.mail;

import java.lang.annotation.Annotation;

public interface MailSessionDefinition
    extends Annotation
{

    public abstract String description();

    public abstract String name();

    public abstract String storeProtocol();

    public abstract String transportProtocol();

    public abstract String host();

    public abstract String user();

    public abstract String password();

    public abstract String from();

    public abstract String[] properties();
}
