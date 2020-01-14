// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   TabSettings.java

package co.com.pdf.text;

import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            TabStop

public class TabSettings
{

    public static TabStop getTabStopNewInstance(float currentPosition, TabSettings tabSettings)
    {
        if(tabSettings != null)
            return tabSettings.getTabStopNewInstance(currentPosition);
        else
            return TabStop.newInstance(currentPosition, 36F);
    }

    public TabSettings()
    {
        tabStops = new ArrayList();
        tabInterval = 36F;
    }

    public TabSettings(List tabStops)
    {
        this.tabStops = new ArrayList();
        tabInterval = 36F;
        this.tabStops = tabStops;
    }

    public TabSettings(float tabInterval)
    {
        tabStops = new ArrayList();
        this.tabInterval = 36F;
        this.tabInterval = tabInterval;
    }

    public TabSettings(List tabStops, float tabInterval)
    {
        this.tabStops = new ArrayList();
        this.tabInterval = 36F;
        this.tabStops = tabStops;
        this.tabInterval = tabInterval;
    }

    public List getTabStops()
    {
        return tabStops;
    }

    public void setTabStops(List tabStops)
    {
        this.tabStops = tabStops;
    }

    public float getTabInterval()
    {
        return tabInterval;
    }

    public void setTabInterval(float tabInterval)
    {
        this.tabInterval = tabInterval;
    }

    public TabStop getTabStopNewInstance(float currentPosition)
    {
        TabStop tabStop;
label0:
        {
            tabStop = null;
            if(tabStops == null)
                break label0;
            Iterator i$ = tabStops.iterator();
            TabStop currentTabStop;
            do
            {
                if(!i$.hasNext())
                    break label0;
                currentTabStop = (TabStop)i$.next();
            } while((double)(currentTabStop.getPosition() - currentPosition) <= 0.001D);
            tabStop = new TabStop(currentTabStop);
        }
        if(tabStop == null)
            tabStop = TabStop.newInstance(currentPosition, tabInterval);
        return tabStop;
    }

    public static final float DEFAULT_TAB_INTERVAL = 36F;
    private List tabStops;
    private float tabInterval;
}