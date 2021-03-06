// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EventQueue.java

package javax.mail;

import java.util.Vector;
import javax.mail.event.MailEvent;

class EventQueue
    implements Runnable
{
    static class QueueElement
    {

        QueueElement next;
        QueueElement prev;
        MailEvent event;
        Vector vector;

        QueueElement(MailEvent event, Vector vector)
        {
            next = null;
            prev = null;
            this.event = null;
            this.vector = null;
            this.event = event;
            this.vector = vector;
        }
    }


    public EventQueue()
    {
        head = null;
        tail = null;
        qThread = new Thread(this, "JavaMail-EventQueue");
        qThread.setDaemon(true);
        qThread.start();
    }

    public synchronized void enqueue(MailEvent event, Vector vector)
    {
        QueueElement newElt = new QueueElement(event, vector);
        if(head == null)
        {
            head = newElt;
            tail = newElt;
        } else
        {
            newElt.next = head;
            head.prev = newElt;
            head = newElt;
        }
        notifyAll();
    }

    private synchronized QueueElement dequeue()
        throws InterruptedException
    {
        while(tail == null) 
            wait();
        QueueElement elt = tail;
        tail = elt.prev;
        if(tail == null)
            head = null;
        else
            tail.next = null;
        elt.prev = elt.next = null;
        return elt;
    }

    public void run()
    {
label0:
        do
        {
            try
            {
                QueueElement qe = dequeue();
                MailEvent e = qe.event;
                Vector v = qe.vector;
                for(int i = 0; i < v.size(); i++)
                    try
                    {
                        e.dispatch(v.elementAt(i));
                        continue;
                    }
                    catch(Throwable t)
                    {
                        if(t instanceof InterruptedException)
                            break label0;
                    }

                qe = null;
                e = null;
                v = null;
                continue;
            }
            catch(InterruptedException e) { }
            break;
        } while(true);
    }

    void stop()
    {
        if(qThread != null)
        {
            qThread.interrupt();
            qThread = null;
        }
    }

    private QueueElement head;
    private QueueElement tail;
    private Thread qThread;
}
