// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Authenticator.java

package javax.mail;

import java.net.InetAddress;

// Referenced classes of package javax.mail:
//            PasswordAuthentication

public abstract class Authenticator
{

    public Authenticator()
    {
    }

    private void reset()
    {
        requestingSite = null;
        requestingPort = -1;
        requestingProtocol = null;
        requestingPrompt = null;
        requestingUserName = null;
    }

    final PasswordAuthentication requestPasswordAuthentication(InetAddress addr, int port, String protocol, String prompt, String defaultUserName)
    {
        reset();
        requestingSite = addr;
        requestingPort = port;
        requestingProtocol = protocol;
        requestingPrompt = prompt;
        requestingUserName = defaultUserName;
        return getPasswordAuthentication();
    }

    protected final InetAddress getRequestingSite()
    {
        return requestingSite;
    }

    protected final int getRequestingPort()
    {
        return requestingPort;
    }

    protected final String getRequestingProtocol()
    {
        return requestingProtocol;
    }

    protected final String getRequestingPrompt()
    {
        return requestingPrompt;
    }

    protected final String getDefaultUserName()
    {
        return requestingUserName;
    }

    protected PasswordAuthentication getPasswordAuthentication()
    {
        return null;
    }

    private InetAddress requestingSite;
    private int requestingPort;
    private String requestingProtocol;
    private String requestingPrompt;
    private String requestingUserName;
}
