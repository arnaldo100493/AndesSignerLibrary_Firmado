// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Store.java

package javax.mail;

import java.util.Vector;
import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;

// Referenced classes of package javax.mail:
//            Service, Folder, MessagingException, Session, 
//            URLName

public abstract class Store extends Service
{

    protected Store(Session session, URLName urlname)
    {
        super(session, urlname);
        storeListeners = null;
        folderListeners = null;
    }

    public abstract Folder getDefaultFolder()
        throws MessagingException;

    public abstract Folder getFolder(String s)
        throws MessagingException;

    public abstract Folder getFolder(URLName urlname)
        throws MessagingException;

    public Folder[] getPersonalNamespaces()
        throws MessagingException
    {
        return (new Folder[] {
            getDefaultFolder()
        });
    }

    public Folder[] getUserNamespaces(String user)
        throws MessagingException
    {
        return new Folder[0];
    }

    public Folder[] getSharedNamespaces()
        throws MessagingException
    {
        return new Folder[0];
    }

    public synchronized void addStoreListener(StoreListener l)
    {
        if(storeListeners == null)
            storeListeners = new Vector();
        storeListeners.addElement(l);
    }

    public synchronized void removeStoreListener(StoreListener l)
    {
        if(storeListeners != null)
            storeListeners.removeElement(l);
    }

    protected void notifyStoreListeners(int type, String message)
    {
        if(storeListeners == null)
        {
            return;
        } else
        {
            StoreEvent e = new StoreEvent(this, type, message);
            queueEvent(e, storeListeners);
            return;
        }
    }

    public synchronized void addFolderListener(FolderListener l)
    {
        if(folderListeners == null)
            folderListeners = new Vector();
        folderListeners.addElement(l);
    }

    public synchronized void removeFolderListener(FolderListener l)
    {
        if(folderListeners != null)
            folderListeners.removeElement(l);
    }

    protected void notifyFolderListeners(int type, Folder folder)
    {
        if(folderListeners == null)
        {
            return;
        } else
        {
            FolderEvent e = new FolderEvent(this, folder, type);
            queueEvent(e, folderListeners);
            return;
        }
    }

    protected void notifyFolderRenamedListeners(Folder oldF, Folder newF)
    {
        if(folderListeners == null)
        {
            return;
        } else
        {
            FolderEvent e = new FolderEvent(this, oldF, newF, 3);
            queueEvent(e, folderListeners);
            return;
        }
    }

    private volatile Vector storeListeners;
    private volatile Vector folderListeners;
}
