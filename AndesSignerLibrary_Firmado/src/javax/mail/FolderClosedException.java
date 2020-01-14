// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FolderClosedException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Folder

public class FolderClosedException extends MessagingException
{

    public FolderClosedException(Folder folder)
    {
        this(folder, null);
    }

    public FolderClosedException(Folder folder, String message)
    {
        super(message);
        this.folder = folder;
    }

    public FolderClosedException(Folder folder, String message, Exception e)
    {
        super(message, e);
        this.folder = folder;
    }

    public Folder getFolder()
    {
        return folder;
    }

    private transient Folder folder;
    private static final long serialVersionUID = 0x176c8d33ac752d2bL;
}
