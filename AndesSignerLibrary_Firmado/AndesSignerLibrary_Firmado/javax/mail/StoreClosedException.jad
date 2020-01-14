// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StoreClosedException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Store

public class StoreClosedException extends MessagingException
{

    public StoreClosedException(Store store)
    {
        this(store, null);
    }

    public StoreClosedException(Store store, String message)
    {
        super(message);
        this.store = store;
    }

    public StoreClosedException(Store store, String message, Exception e)
    {
        super(message, e);
        this.store = store;
    }

    public Store getStore()
    {
        return store;
    }

    private transient Store store;
    private static final long serialVersionUID = 0xd4595255d6538f21L;
}
