// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ReadOnlyFolderException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Folder

public class ReadOnlyFolderException extends MessagingException
{

    public ReadOnlyFolderException(Folder folder)
    {
        this(folder, null);
    }

    public ReadOnlyFolderException(Folder folder, String message)
    {
        super(message);
        this.folder = folder;
    }

    public ReadOnlyFolderException(Folder folder, String message, Exception e)
    {
        super(message, e);
        this.folder = folder;
    }

    public Folder getFolder()
    {
        return folder;
    }

    private transient Folder folder;
    private static final long serialVersionUID = 0x4f447e8d4f54375dL;
}
