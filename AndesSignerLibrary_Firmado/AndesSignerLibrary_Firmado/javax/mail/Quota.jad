// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Quota.java

package javax.mail;


public class Quota
{
    public static class Resource
    {

        public String name;
        public long usage;
        public long limit;

        public Resource(String name, long usage, long limit)
        {
            this.name = name;
            this.usage = usage;
            this.limit = limit;
        }
    }


    public Quota(String quotaRoot)
    {
        this.quotaRoot = quotaRoot;
    }

    public void setResourceLimit(String name, long limit)
    {
        if(resources == null)
        {
            resources = new Resource[1];
            resources[0] = new Resource(name, 0L, limit);
            return;
        }
        for(int i = 0; i < resources.length; i++)
            if(resources[i].name.equalsIgnoreCase(name))
            {
                resources[i].limit = limit;
                return;
            }

        Resource ra[] = new Resource[resources.length + 1];
        System.arraycopy(resources, 0, ra, 0, resources.length);
        ra[ra.length - 1] = new Resource(name, 0L, limit);
        resources = ra;
    }

    public String quotaRoot;
    public Resource resources[];
}
