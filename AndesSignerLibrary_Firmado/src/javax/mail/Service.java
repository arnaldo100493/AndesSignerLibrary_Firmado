// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Service.java

package javax.mail;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;

// Referenced classes of package javax.mail:
//            URLName, AuthenticationFailedException, PasswordAuthentication, EventQueue, 
//            Session, MessagingException

public abstract class Service
{
    static class TerminatorEvent extends MailEvent
    {

        public void dispatch(Object listener)
        {
            Thread.currentThread().interrupt();
        }

        private static final long serialVersionUID = 0x4ce9c033019effa0L;

        TerminatorEvent()
        {
            super(new Object());
        }
    }


    protected Service(Session session, URLName urlname)
    {
        url = null;
        debug = false;
        connected = false;
        qLock = new Object();
        this.session = session;
        debug = session.getDebug();
        url = urlname;
        String protocol = null;
        String host = null;
        int port = -1;
        String user = null;
        String password = null;
        String file = null;
        if(url != null)
        {
            protocol = url.getProtocol();
            host = url.getHost();
            port = url.getPort();
            user = url.getUsername();
            password = url.getPassword();
            file = url.getFile();
        }
        if(protocol != null)
        {
            if(host == null)
                host = session.getProperty((new StringBuilder()).append("mail.").append(protocol).append(".host").toString());
            if(user == null)
                user = session.getProperty((new StringBuilder()).append("mail.").append(protocol).append(".user").toString());
        }
        if(host == null)
            host = session.getProperty("mail.host");
        if(user == null)
            user = session.getProperty("mail.user");
        if(user == null)
            try
            {
                user = System.getProperty("user.name");
            }
            catch(SecurityException sex) { }
        url = new URLName(protocol, host, port, file, user, password);
    }

    public void connect()
        throws MessagingException
    {
        connect(null, null, null);
    }

    public void connect(String host, String user, String password)
        throws MessagingException
    {
        connect(host, -1, user, password);
    }

    public void connect(String user, String password)
        throws MessagingException
    {
        connect(null, user, password);
    }

    public synchronized void connect(String host, int port, String user, String password)
        throws MessagingException
    {
        if(isConnected())
            throw new IllegalStateException("already connected");
        boolean connected = false;
        boolean save = false;
        String protocol = null;
        String file = null;
        if(url != null)
        {
            protocol = url.getProtocol();
            if(host == null)
                host = url.getHost();
            if(port == -1)
                port = url.getPort();
            if(user == null)
            {
                user = url.getUsername();
                if(password == null)
                    password = url.getPassword();
            } else
            if(password == null && user.equals(url.getUsername()))
                password = url.getPassword();
            file = url.getFile();
        }
        if(protocol != null)
        {
            if(host == null)
                host = session.getProperty((new StringBuilder()).append("mail.").append(protocol).append(".host").toString());
            if(user == null)
                user = session.getProperty((new StringBuilder()).append("mail.").append(protocol).append(".user").toString());
        }
        if(host == null)
            host = session.getProperty("mail.host");
        if(user == null)
            user = session.getProperty("mail.user");
        if(user == null)
            try
            {
                user = System.getProperty("user.name");
            }
            catch(SecurityException sex) { }
        if(password == null && url != null)
        {
            setURLName(new URLName(protocol, host, port, file, user, null));
            PasswordAuthentication pw = session.getPasswordAuthentication(getURLName());
            if(pw != null)
            {
                if(user == null)
                {
                    user = pw.getUserName();
                    password = pw.getPassword();
                } else
                if(user.equals(pw.getUserName()))
                    password = pw.getPassword();
            } else
            {
                save = true;
            }
        }
        AuthenticationFailedException authEx = null;
        try
        {
            connected = protocolConnect(host, port, user, password);
        }
        catch(AuthenticationFailedException ex)
        {
            authEx = ex;
        }
        if(!connected)
        {
            InetAddress addr;
            try
            {
                addr = InetAddress.getByName(host);
            }
            catch(UnknownHostException e)
            {
                addr = null;
            }
            PasswordAuthentication pw = session.requestPasswordAuthentication(addr, port, protocol, null, user);
            if(pw != null)
            {
                user = pw.getUserName();
                password = pw.getPassword();
                connected = protocolConnect(host, port, user, password);
            }
        }
        if(!connected)
        {
            if(authEx != null)
                throw authEx;
            if(user == null)
                throw new AuthenticationFailedException("failed to connect, no user name specified?");
            if(password == null)
                throw new AuthenticationFailedException("failed to connect, no password specified?");
            else
                throw new AuthenticationFailedException("failed to connect");
        }
        setURLName(new URLName(protocol, host, port, file, user, password));
        if(save)
            session.setPasswordAuthentication(getURLName(), new PasswordAuthentication(user, password));
        setConnected(true);
        notifyConnectionListeners(1);
    }

    protected boolean protocolConnect(String host, int port, String user, String s)
        throws MessagingException
    {
        return false;
    }

    public synchronized boolean isConnected()
    {
        return connected;
    }

    protected synchronized void setConnected(boolean connected)
    {
        this.connected = connected;
    }

    public synchronized void close()
        throws MessagingException
    {
        setConnected(false);
        notifyConnectionListeners(3);
    }

    public synchronized URLName getURLName()
    {
        if(url != null && (url.getPassword() != null || url.getFile() != null))
            return new URLName(url.getProtocol(), url.getHost(), url.getPort(), null, url.getUsername(), null);
        else
            return url;
    }

    protected synchronized void setURLName(URLName url)
    {
        this.url = url;
    }

    public void addConnectionListener(ConnectionListener l)
    {
        connectionListeners.addElement(l);
    }

    public void removeConnectionListener(ConnectionListener l)
    {
        connectionListeners.removeElement(l);
    }

    protected void notifyConnectionListeners(int type)
    {
        if(connectionListeners.size() > 0)
        {
            ConnectionEvent e = new ConnectionEvent(this, type);
            queueEvent(e, connectionListeners);
        }
        if(type == 3)
            terminateQueue();
    }

    public String toString()
    {
        URLName url = getURLName();
        if(url != null)
            return url.toString();
        else
            return super.toString();
    }

    protected void queueEvent(MailEvent event, Vector vector)
    {
        synchronized(qLock)
        {
            if(q == null)
                q = new EventQueue();
        }
        Vector v = (Vector)vector.clone();
        q.enqueue(event, v);
    }

    private void terminateQueue()
    {
        synchronized(qLock)
        {
            if(q != null)
            {
                Vector dummyListeners = new Vector();
                dummyListeners.setSize(1);
                q.enqueue(new TerminatorEvent(), dummyListeners);
                q = null;
            }
        }
    }

    protected void finalize()
        throws Throwable
    {
        super.finalize();
        terminateQueue();
    }

    protected Session session;
    protected URLName url;
    protected boolean debug;
    private boolean connected;
    private final Vector connectionListeners = new Vector();
    private EventQueue q;
    private Object qLock;
}
