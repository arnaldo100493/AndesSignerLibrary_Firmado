// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FolderNotFoundException.java

package javax.mail;


// Referenced classes of package javax.mail:
//            MessagingException, Folder

public class FolderNotFoundException extends MessagingException
{

    public FolderNotFoundException()
    {
    }

    public FolderNotFoundException(Folder folder)
    {
        this.folder = folder;
    }

    public FolderNotFoundException(Folder folder, String s)
    {
        super(s);
        this.folder = folder;
    }

    public FolderNotFoundException(Folder folder, String s, Exception e)
    {
        super(s, e);
        this.folder = folder;
    }

    public FolderNotFoundException(String s, Folder folder)
    {
        super(s);
        this.folder = folder;
    }

    public Folder getFolder()
    {
        return folder;
    }

    private transient Folder folder;
    private static final long serialVersionUID = 0x68f0e358302dafbL;
}
