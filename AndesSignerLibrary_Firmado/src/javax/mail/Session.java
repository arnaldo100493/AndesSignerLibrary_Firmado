// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Session.java

package javax.mail;

import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.MailLogger;
import java.io.*;
import java.lang.reflect.Constructor;
import java.net.InetAddress;
import java.net.URL;
import java.security.*;
import java.util.*;
import java.util.logging.Level;

// Referenced classes of package javax.mail:
//            Provider, NoSuchProviderException, URLName, Store, 
//            Transport, PasswordAuthentication, Authenticator, MessagingException, 
//            StreamLoader, Address, Folder

public final class Session
{

    private Session(Properties props, Authenticator authenticator)
    {
        debug = false;
        this.props = props;
        this.authenticator = authenticator;
        if(Boolean.valueOf(props.getProperty("mail.debug")).booleanValue())
            debug = true;
        initLogger();
        logger.log(Level.CONFIG, "JavaMail version {0}", "${mail.version}");
        Class cl;
        if(authenticator != null)
            cl = authenticator.getClass();
        else
            cl = getClass();
        loadProviders(cl);
        loadAddressMap(cl);
    }

    private final void initLogger()
    {
        logger = new MailLogger(getClass(), "DEBUG", debug, getDebugOut());
    }

    public static Session getInstance(Properties props, Authenticator authenticator)
    {
        return new Session(props, authenticator);
    }

    public static Session getInstance(Properties props)
    {
        return new Session(props, null);
    }

    public static synchronized Session getDefaultInstance(Properties props, Authenticator authenticator)
    {
        if(defaultSession == null)
            defaultSession = new Session(props, authenticator);
        else
        if(defaultSession.authenticator != authenticator && (defaultSession.authenticator == null || authenticator == null || defaultSession.authenticator.getClass().getClassLoader() != authenticator.getClass().getClassLoader()))
            throw new SecurityException("Access to default session denied");
        return defaultSession;
    }

    public static Session getDefaultInstance(Properties props)
    {
        return getDefaultInstance(props, null);
    }

    public synchronized void setDebug(boolean debug)
    {
        this.debug = debug;
        initLogger();
        logger.log(Level.CONFIG, "setDebug: JavaMail version {0}", "${mail.version}");
    }

    public synchronized boolean getDebug()
    {
        return debug;
    }

    public synchronized void setDebugOut(PrintStream out)
    {
        this.out = out;
        initLogger();
    }

    public synchronized PrintStream getDebugOut()
    {
        if(out == null)
            return System.out;
        else
            return out;
    }

    public synchronized Provider[] getProviders()
    {
        Provider _providers[] = new Provider[providers.size()];
        providers.copyInto(_providers);
        return _providers;
    }

    public synchronized Provider getProvider(String protocol)
        throws NoSuchProviderException
    {
        if(protocol == null || protocol.length() <= 0)
            throw new NoSuchProviderException("Invalid protocol: null");
        Provider _provider = null;
        String _className = props.getProperty((new StringBuilder()).append("mail.").append(protocol).append(".class").toString());
        if(_className != null)
        {
            if(logger.isLoggable(Level.FINE))
                logger.fine((new StringBuilder()).append("mail.").append(protocol).append(".class property exists and points to ").append(_className).toString());
            _provider = (Provider)providersByClassName.get(_className);
        }
        if(_provider != null)
            return _provider;
        _provider = (Provider)providersByProtocol.get(protocol);
        if(_provider == null)
            throw new NoSuchProviderException((new StringBuilder()).append("No provider for ").append(protocol).toString());
        if(logger.isLoggable(Level.FINE))
            logger.fine((new StringBuilder()).append("getProvider() returning ").append(_provider.toString()).toString());
        return _provider;
    }

    public synchronized void setProvider(Provider provider)
        throws NoSuchProviderException
    {
        if(provider == null)
        {
            throw new NoSuchProviderException("Can't set null provider");
        } else
        {
            providersByProtocol.put(provider.getProtocol(), provider);
            props.put((new StringBuilder()).append("mail.").append(provider.getProtocol()).append(".class").toString(), provider.getClassName());
            return;
        }
    }

    public Store getStore()
        throws NoSuchProviderException
    {
        return getStore(getProperty("mail.store.protocol"));
    }

    public Store getStore(String protocol)
        throws NoSuchProviderException
    {
        return getStore(new URLName(protocol, null, -1, null, null, null));
    }

    public Store getStore(URLName url)
        throws NoSuchProviderException
    {
        String protocol = url.getProtocol();
        Provider p = getProvider(protocol);
        return getStore(p, url);
    }

    public Store getStore(Provider provider)
        throws NoSuchProviderException
    {
        return getStore(provider, null);
    }

    private Store getStore(Provider provider, URLName url)
        throws NoSuchProviderException
    {
        if(provider == null || provider.getType() != Provider.Type.STORE)
            throw new NoSuchProviderException("invalid provider");
        try
        {
            return (Store)getService(provider, url);
        }
        catch(ClassCastException cce)
        {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    public Folder getFolder(URLName url)
        throws MessagingException
    {
        Store store = getStore(url);
        store.connect();
        return store.getFolder(url);
    }

    public Transport getTransport()
        throws NoSuchProviderException
    {
        return getTransport(getProperty("mail.transport.protocol"));
    }

    public Transport getTransport(String protocol)
        throws NoSuchProviderException
    {
        return getTransport(new URLName(protocol, null, -1, null, null, null));
    }

    public Transport getTransport(URLName url)
        throws NoSuchProviderException
    {
        String protocol = url.getProtocol();
        Provider p = getProvider(protocol);
        return getTransport(p, url);
    }

    public Transport getTransport(Provider provider)
        throws NoSuchProviderException
    {
        return getTransport(provider, null);
    }

    public Transport getTransport(Address address)
        throws NoSuchProviderException
    {
        String transportProtocol = getProperty((new StringBuilder()).append("mail.transport.protocol.").append(address.getType()).toString());
        if(transportProtocol != null)
            return getTransport(transportProtocol);
        transportProtocol = (String)addressMap.get(address.getType());
        if(transportProtocol != null)
            return getTransport(transportProtocol);
        else
            throw new NoSuchProviderException((new StringBuilder()).append("No provider for Address type: ").append(address.getType()).toString());
    }

    private Transport getTransport(Provider provider, URLName url)
        throws NoSuchProviderException
    {
        if(provider == null || provider.getType() != Provider.Type.TRANSPORT)
            throw new NoSuchProviderException("invalid provider");
        try
        {
            return (Transport)getService(provider, url);
        }
        catch(ClassCastException cce)
        {
            throw new NoSuchProviderException("incorrect class");
        }
    }

    private Object getService(Provider provider, URLName url)
        throws NoSuchProviderException
    {
        if(provider == null)
            throw new NoSuchProviderException("null");
        if(url == null)
            url = new URLName(provider.getProtocol(), null, -1, null, null, null);
        Object service = null;
        ClassLoader cl;
        if(authenticator != null)
            cl = authenticator.getClass().getClassLoader();
        else
            cl = getClass().getClassLoader();
        Class serviceClass = null;
        try
        {
            ClassLoader ccl = getContextClassLoader();
            if(ccl != null)
                try
                {
                    serviceClass = Class.forName(provider.getClassName(), false, ccl);
                }
                catch(ClassNotFoundException ex) { }
            if(serviceClass == null)
                serviceClass = Class.forName(provider.getClassName(), false, cl);
        }
        catch(Exception ex1)
        {
            try
            {
                serviceClass = Class.forName(provider.getClassName());
            }
            catch(Exception ex)
            {
                logger.log(Level.FINE, "Exception loading provider", ex);
                throw new NoSuchProviderException(provider.getProtocol());
            }
        }
        try
        {
            Class c[] = {
                javax/mail/Session, javax/mail/URLName
            };
            Constructor cons = serviceClass.getConstructor(c);
            Object o[] = {
                this, url
            };
            service = cons.newInstance(o);
        }
        catch(Exception ex)
        {
            logger.log(Level.FINE, "Exception loading provider", ex);
            throw new NoSuchProviderException(provider.getProtocol());
        }
        return service;
    }

    public void setPasswordAuthentication(URLName url, PasswordAuthentication pw)
    {
        if(pw == null)
            authTable.remove(url);
        else
            authTable.put(url, pw);
    }

    public PasswordAuthentication getPasswordAuthentication(URLName url)
    {
        return (PasswordAuthentication)authTable.get(url);
    }

    public PasswordAuthentication requestPasswordAuthentication(InetAddress addr, int port, String protocol, String prompt, String defaultUserName)
    {
        if(authenticator != null)
            return authenticator.requestPasswordAuthentication(addr, port, protocol, prompt, defaultUserName);
        else
            return null;
    }

    public Properties getProperties()
    {
        return props;
    }

    public String getProperty(String name)
    {
        return props.getProperty(name);
    }

    private void loadProviders(Class cl)
    {
        StreamLoader loader = new StreamLoader() {

            public void load(InputStream is)
                throws IOException
            {
                loadProvidersFromStream(is);
            }

            final Session this$0;

            
            {
                this$0 = Session.this;
                super();
            }
        }
;
        try
        {
            String res = (new StringBuilder()).append(System.getProperty("java.home")).append(File.separator).append("lib").append(File.separator).append("javamail.providers").toString();
            loadFile(res, loader);
        }
        catch(SecurityException sex)
        {
            logger.log(Level.CONFIG, "can't get java.home", sex);
        }
        loadAllResources("META-INF/javamail.providers", cl, loader);
        loadResource("/META-INF/javamail.default.providers", cl, loader);
        if(providers.size() == 0)
        {
            logger.config("failed to load any providers, using defaults");
            addProvider(new Provider(Provider.Type.STORE, "imap", "com.sun.mail.imap.IMAPStore", "Sun Microsystems, Inc.", "${mail.version}"));
            addProvider(new Provider(Provider.Type.STORE, "imaps", "com.sun.mail.imap.IMAPSSLStore", "Sun Microsystems, Inc.", "${mail.version}"));
            addProvider(new Provider(Provider.Type.STORE, "pop3", "com.sun.mail.pop3.POP3Store", "Sun Microsystems, Inc.", "${mail.version}"));
            addProvider(new Provider(Provider.Type.STORE, "pop3s", "com.sun.mail.pop3.POP3SSLStore", "Sun Microsystems, Inc.", "${mail.version}"));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtp", "com.sun.mail.smtp.SMTPTransport", "Sun Microsystems, Inc.", "${mail.version}"));
            addProvider(new Provider(Provider.Type.TRANSPORT, "smtps", "com.sun.mail.smtp.SMTPSSLTransport", "Sun Microsystems, Inc.", "${mail.version}"));
        }
        if(logger.isLoggable(Level.CONFIG))
        {
            logger.config("Tables of loaded providers");
            logger.config((new StringBuilder()).append("Providers Listed By Class Name: ").append(providersByClassName.toString()).toString());
            logger.config((new StringBuilder()).append("Providers Listed By Protocol: ").append(providersByProtocol.toString()).toString());
        }
    }

    private void loadProvidersFromStream(InputStream is)
        throws IOException
    {
        if(is != null)
        {
            LineInputStream lis = new LineInputStream(is);
            do
            {
                String currLine;
                if((currLine = lis.readLine()) == null)
                    break;
                if(!currLine.startsWith("#"))
                {
                    Provider.Type type = null;
                    String protocol = null;
                    String className = null;
                    String vendor = null;
                    String version = null;
                    StringTokenizer tuples = new StringTokenizer(currLine, ";");
                    do
                    {
                        if(!tuples.hasMoreTokens())
                            break;
                        String currTuple = tuples.nextToken().trim();
                        int sep = currTuple.indexOf("=");
                        if(currTuple.startsWith("protocol="))
                            protocol = currTuple.substring(sep + 1);
                        else
                        if(currTuple.startsWith("type="))
                        {
                            String strType = currTuple.substring(sep + 1);
                            if(strType.equalsIgnoreCase("store"))
                                type = Provider.Type.STORE;
                            else
                            if(strType.equalsIgnoreCase("transport"))
                                type = Provider.Type.TRANSPORT;
                        } else
                        if(currTuple.startsWith("class="))
                            className = currTuple.substring(sep + 1);
                        else
                        if(currTuple.startsWith("vendor="))
                            vendor = currTuple.substring(sep + 1);
                        else
                        if(currTuple.startsWith("version="))
                            version = currTuple.substring(sep + 1);
                    } while(true);
                    if(type == null || protocol == null || className == null || protocol.length() <= 0 || className.length() <= 0)
                    {
                        logger.log(Level.CONFIG, "Bad provider entry: {0}", currLine);
                    } else
                    {
                        Provider provider = new Provider(type, protocol, className, vendor, version);
                        addProvider(provider);
                    }
                }
            } while(true);
        }
    }

    public synchronized void addProvider(Provider provider)
    {
        providers.addElement(provider);
        providersByClassName.put(provider.getClassName(), provider);
        if(!providersByProtocol.containsKey(provider.getProtocol()))
            providersByProtocol.put(provider.getProtocol(), provider);
    }

    private void loadAddressMap(Class cl)
    {
        StreamLoader loader = new StreamLoader() {

            public void load(InputStream is)
                throws IOException
            {
                addressMap.load(is);
            }

            final Session this$0;

            
            {
                this$0 = Session.this;
                super();
            }
        }
;
        loadResource("/META-INF/javamail.default.address.map", cl, loader);
        loadAllResources("META-INF/javamail.address.map", cl, loader);
        try
        {
            String res = (new StringBuilder()).append(System.getProperty("java.home")).append(File.separator).append("lib").append(File.separator).append("javamail.address.map").toString();
            loadFile(res, loader);
        }
        catch(SecurityException sex)
        {
            logger.log(Level.CONFIG, "can't get java.home", sex);
        }
        if(addressMap.isEmpty())
        {
            logger.config("failed to load address map, using defaults");
            addressMap.put("rfc822", "smtp");
        }
    }

    public synchronized void setProtocolForAddress(String addresstype, String protocol)
    {
        if(protocol == null)
            addressMap.remove(addresstype);
        else
            addressMap.put(addresstype, protocol);
    }

    private void loadFile(String name, StreamLoader loader)
    {
        InputStream clis = null;
        clis = new BufferedInputStream(new FileInputStream(name));
        loader.load(clis);
        logger.log(Level.CONFIG, "successfully loaded file: {0}", name);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_214;
        FileNotFoundException fex;
        fex;
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_214;
        IOException e;
        e;
        if(logger.isLoggable(Level.CONFIG))
            logger.log(Level.CONFIG, (new StringBuilder()).append("not loading file: ").append(name).toString(), e);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_214;
        SecurityException sex;
        sex;
        if(logger.isLoggable(Level.CONFIG))
            logger.log(Level.CONFIG, (new StringBuilder()).append("not loading file: ").append(name).toString(), sex);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_214;
        Exception exception;
        exception;
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        throw exception;
    }

    private void loadResource(String name, Class cl, StreamLoader loader)
    {
        InputStream clis = null;
        clis = getResourceAsStream(cl, name);
        if(clis != null)
        {
            loader.load(clis);
            logger.log(Level.CONFIG, "successfully loaded resource: {0}", name);
        }
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_142;
        IOException e;
        e;
        logger.log(Level.CONFIG, "Exception loading resource", e);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_142;
        SecurityException sex;
        sex;
        logger.log(Level.CONFIG, "Exception loading resource", sex);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_142;
        Exception exception;
        exception;
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException ex) { }
        throw exception;
    }

    private void loadAllResources(String name, Class cl, StreamLoader loader)
    {
        boolean anyLoaded = false;
        URL urls[];
        ClassLoader cld = null;
        cld = getContextClassLoader();
        if(cld == null)
            cld = cl.getClassLoader();
        if(cld != null)
            urls = getResources(cld, name);
        else
            urls = getSystemResources(name);
        if(urls == null) goto _L2; else goto _L1
_L1:
        int i = 0;
_L4:
        URL url;
        InputStream clis;
        if(i >= urls.length)
            break; /* Loop/switch isn't completed */
        url = urls[i];
        clis = null;
        logger.log(Level.CONFIG, "URL {0}", url);
        clis = openStream(url);
        if(clis != null)
        {
            loader.load(clis);
            anyLoaded = true;
            logger.log(Level.CONFIG, "successfully loaded resource: {0}", url);
        } else
        {
            logger.log(Level.CONFIG, "not loading resource: {0}", url);
        }
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException cex) { }
          goto _L3
        FileNotFoundException fex;
        fex;
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException cex) { }
          goto _L3
        IOException ioex;
        ioex;
        logger.log(Level.CONFIG, "Exception loading resource", ioex);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException cex) { }
          goto _L3
        SecurityException sex;
        sex;
        logger.log(Level.CONFIG, "Exception loading resource", sex);
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException cex) { }
          goto _L3
        Exception exception;
        exception;
        try
        {
            if(clis != null)
                clis.close();
        }
        catch(IOException cex) { }
        throw exception;
_L3:
        i++;
        if(true) goto _L4; else goto _L2
        Exception ex;
        ex;
        logger.log(Level.CONFIG, "Exception loading resource", ex);
_L2:
        if(!anyLoaded)
            loadResource((new StringBuilder()).append("/").append(name).toString(), cl, loader);
        return;
    }

    private static ClassLoader getContextClassLoader()
    {
        return (ClassLoader)AccessController.doPrivileged(new PrivilegedAction() {

            public Object run()
            {
                ClassLoader cl = null;
                try
                {
                    cl = Thread.currentThread().getContextClassLoader();
                }
                catch(SecurityException ex) { }
                return cl;
            }

        }
);
    }

    private static InputStream getResourceAsStream(Class c, String name)
        throws IOException
    {
        try
        {
            return (InputStream)AccessController.doPrivileged(new PrivilegedExceptionAction(c, name) {

                public Object run()
                    throws IOException
                {
                    return c.getResourceAsStream(name);
                }

                final Class val$c;
                final String val$name;

            
            {
                c = class1;
                name = s;
                super();
            }
            }
);
        }
        catch(PrivilegedActionException e)
        {
            throw (IOException)e.getException();
        }
    }

    private static URL[] getResources(ClassLoader cl, String name)
    {
        return (URL[])(URL[])AccessController.doPrivileged(new PrivilegedAction(cl, name) {

            public Object run()
            {
                URL ret[] = null;
                try
                {
                    Vector v = new Vector();
                    Enumeration e = cl.getResources(name);
                    do
                    {
                        if(e == null || !e.hasMoreElements())
                            break;
                        URL url = (URL)e.nextElement();
                        if(url != null)
                            v.addElement(url);
                    } while(true);
                    if(v.size() > 0)
                    {
                        ret = new URL[v.size()];
                        v.copyInto(ret);
                    }
                }
                catch(IOException ioex) { }
                catch(SecurityException ex) { }
                return ret;
            }

            final ClassLoader val$cl;
            final String val$name;

            
            {
                cl = classloader;
                name = s;
                super();
            }
        }
);
    }

    private static URL[] getSystemResources(String name)
    {
        return (URL[])(URL[])AccessController.doPrivileged(new PrivilegedAction(name) {

            public Object run()
            {
                URL ret[] = null;
                try
                {
                    Vector v = new Vector();
                    Enumeration e = ClassLoader.getSystemResources(name);
                    do
                    {
                        if(e == null || !e.hasMoreElements())
                            break;
                        URL url = (URL)e.nextElement();
                        if(url != null)
                            v.addElement(url);
                    } while(true);
                    if(v.size() > 0)
                    {
                        ret = new URL[v.size()];
                        v.copyInto(ret);
                    }
                }
                catch(IOException ioex) { }
                catch(SecurityException ex) { }
                return ret;
            }

            final String val$name;

            
            {
                name = s;
                super();
            }
        }
);
    }

    private static InputStream openStream(URL url)
        throws IOException
    {
        try
        {
            return (InputStream)AccessController.doPrivileged(new PrivilegedExceptionAction(url) {

                public Object run()
                    throws IOException
                {
                    return url.openStream();
                }

                final URL val$url;

            
            {
                url = url1;
                super();
            }
            }
);
        }
        catch(PrivilegedActionException e)
        {
            throw (IOException)e.getException();
        }
    }

    private final Properties props;
    private final Authenticator authenticator;
    private final Hashtable authTable = new Hashtable();
    private boolean debug;
    private PrintStream out;
    private MailLogger logger;
    private final Vector providers = new Vector();
    private final Hashtable providersByProtocol = new Hashtable();
    private final Hashtable providersByClassName = new Hashtable();
    private final Properties addressMap = new Properties();
    private static Session defaultSession = null;



}
