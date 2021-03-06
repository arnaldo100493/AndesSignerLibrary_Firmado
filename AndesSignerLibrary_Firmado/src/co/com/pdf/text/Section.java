// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 17/11/2017 7:07:04 p. m.
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Section.java

package co.com.pdf.text;

import co.com.pdf.text.api.Indentable;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfName;
import co.com.pdf.text.pdf.PdfObject;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.*;

// Referenced classes of package co.com.pdf.text:
//            Paragraph, Element, DocumentException, MarkedSection, 
//            MarkedObject, Chunk, TextElementArray, LargeElement, 
//            ElementListener, AccessibleElementId

public class Section extends ArrayList
    implements TextElementArray, LargeElement, Indentable, IAccessibleElement
{

    protected Section()
    {
        numberStyle = 0;
        bookmarkOpen = true;
        triggerNewPage = false;
        subsections = 0;
        numbers = null;
        complete = true;
        addedCompletely = false;
        notAddedYet = true;
        title = new Paragraph();
        numberDepth = 1;
        title.setRole(new PdfName((new StringBuilder()).append("H").append(numberDepth).toString()));
    }

    protected Section(Paragraph title, int numberDepth)
    {
        numberStyle = 0;
        bookmarkOpen = true;
        triggerNewPage = false;
        subsections = 0;
        numbers = null;
        complete = true;
        addedCompletely = false;
        notAddedYet = true;
        this.numberDepth = numberDepth;
        this.title = title;
        title.setRole(new PdfName((new StringBuilder()).append("H").append(numberDepth).toString()));
    }

    public boolean process(ElementListener listener)
    {
        try
        {
            Element element;
            for(Iterator i$ = iterator(); i$.hasNext(); listener.add(element))
            {
                Object element2 = i$.next();
                element = (Element)element2;
            }

            return true;
        }
        catch(DocumentException de)
        {
            return false;
        }
    }

    public int type()
    {
        return 13;
    }

    public boolean isChapter()
    {
        return type() == 16;
    }

    public boolean isSection()
    {
        return type() == 13;
    }

    public List getChunks()
    {
        List tmp = new ArrayList();
        Object element;
        for(Iterator i$ = iterator(); i$.hasNext(); tmp.addAll(((Element)element).getChunks()))
            element = i$.next();

        return tmp;
    }

    public boolean isContent()
    {
        return true;
    }

    public boolean isNestable()
    {
        return false;
    }

    public void add(int index, Element element)
    {
        if(isAddedCompletely())
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        try
        {
            if(element.isNestable())
                super.add(index, element);
            else
                throw new ClassCastException(MessageLocalization.getComposedMessage("you.can.t.add.a.1.to.a.section", new Object[] {
                    element.getClass().getName()
                }));
        }
        catch(ClassCastException cce)
        {
            throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", new Object[] {
                cce.getMessage()
            }));
        }
    }

    public boolean add(Element element)
    {
        if(isAddedCompletely())
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        MarkedSection mo;
        Section section;
        try
        {
            if(element.type() == 13)
            {
                Section section = (Section)element;
                section.setNumbers(++subsections, numbers);
                return super.add(section);
            }
        }
        catch(ClassCastException cce)
        {
            throw new ClassCastException(MessageLocalization.getComposedMessage("insertion.of.illegal.element.1", new Object[] {
                cce.getMessage()
            }));
        }
        if((element instanceof MarkedSection) && ((MarkedObject)element).element.type() == 13)
        {
            mo = (MarkedSection)element;
            section = (Section)mo.element;
            section.setNumbers(++subsections, numbers);
            return super.add(mo);
        }
        if(element.isNestable())
            return super.add(element);
        throw new ClassCastException(MessageLocalization.getComposedMessage("you.can.t.add.a.1.to.a.section", new Object[] {
            element.getClass().getName()
        }));
    }

    public boolean addAll(Collection collection)
    {
        if(collection.size() == 0)
            return false;
        Element element;
        for(Iterator i$ = collection.iterator(); i$.hasNext(); add(element))
            element = (Element)i$.next();

        return true;
    }

    public Section addSection(float indentation, Paragraph title, int numberDepth)
    {
        if(isAddedCompletely())
        {
            throw new IllegalStateException(MessageLocalization.getComposedMessage("this.largeelement.has.already.been.added.to.the.document", new Object[0]));
        } else
        {
            Section section = new Section(title, numberDepth);
            section.setIndentation(indentation);
            add(section);
            return section;
        }
    }

    public Section addSection(float indentation, Paragraph title)
    {
        return addSection(indentation, title, numberDepth + 1);
    }

    public Section addSection(Paragraph title, int numberDepth)
    {
        return addSection(0.0F, title, numberDepth);
    }

    protected MarkedSection addMarkedSection()
    {
        MarkedSection section = new MarkedSection(new Section(null, numberDepth + 1));
        add(section);
        return section;
    }

    public Section addSection(Paragraph title)
    {
        return addSection(0.0F, title, numberDepth + 1);
    }

    public Section addSection(float indentation, String title, int numberDepth)
    {
        return addSection(indentation, new Paragraph(title), numberDepth);
    }

    public Section addSection(String title, int numberDepth)
    {
        return addSection(new Paragraph(title), numberDepth);
    }

    public Section addSection(float indentation, String title)
    {
        return addSection(indentation, new Paragraph(title));
    }

    public Section addSection(String title)
    {
        return addSection(new Paragraph(title));
    }

    public void setTitle(Paragraph title)
    {
        this.title = title;
    }

    public Paragraph getTitle()
    {
        return constructTitle(title, numbers, numberDepth, numberStyle);
    }

    public static Paragraph constructTitle(Paragraph title, ArrayList numbers, int numberDepth, int numberStyle)
    {
        if(title == null)
            return null;
        int depth = Math.min(numbers.size(), numberDepth);
        if(depth < 1)
            return title;
        StringBuffer buf = new StringBuffer(" ");
        for(int i = 0; i < depth; i++)
        {
            buf.insert(0, ".");
            buf.insert(0, ((Integer)numbers.get(i)).intValue());
        }

        if(numberStyle == 1)
            buf.deleteCharAt(buf.length() - 2);
        Paragraph result = new Paragraph(title);
        result.add(0, new Chunk(buf.toString(), title.getFont()));
        return result;
    }

    public void setNumberDepth(int numberDepth)
    {
        this.numberDepth = numberDepth;
    }

    public int getNumberDepth()
    {
        return numberDepth;
    }

    public void setNumberStyle(int numberStyle)
    {
        this.numberStyle = numberStyle;
    }

    public int getNumberStyle()
    {
        return numberStyle;
    }

    public void setIndentationLeft(float indentation)
    {
        indentationLeft = indentation;
    }

    public float getIndentationLeft()
    {
        return indentationLeft;
    }

    public void setIndentationRight(float indentation)
    {
        indentationRight = indentation;
    }

    public float getIndentationRight()
    {
        return indentationRight;
    }

    public void setIndentation(float indentation)
    {
        this.indentation = indentation;
    }

    public float getIndentation()
    {
        return indentation;
    }

    public void setBookmarkOpen(boolean bookmarkOpen)
    {
        this.bookmarkOpen = bookmarkOpen;
    }

    public boolean isBookmarkOpen()
    {
        return bookmarkOpen;
    }

    public void setTriggerNewPage(boolean triggerNewPage)
    {
        this.triggerNewPage = triggerNewPage;
    }

    public boolean isTriggerNewPage()
    {
        return triggerNewPage && notAddedYet;
    }

    public void setBookmarkTitle(String bookmarkTitle)
    {
        this.bookmarkTitle = bookmarkTitle;
    }

    public Paragraph getBookmarkTitle()
    {
        if(bookmarkTitle == null)
            return getTitle();
        else
            return new Paragraph(bookmarkTitle);
    }

    public void setChapterNumber(int number)
    {
        numbers.set(numbers.size() - 1, Integer.valueOf(number));
        Iterator i = iterator();
        do
        {
            if(!i.hasNext())
                break;
            Object s = i.next();
            if(s instanceof Section)
                ((Section)s).setChapterNumber(number);
        } while(true);
    }

    public int getDepth()
    {
        return numbers.size();
    }

    private void setNumbers(int number, ArrayList numbers)
    {
        this.numbers = new ArrayList();
        this.numbers.add(Integer.valueOf(number));
        this.numbers.addAll(numbers);
    }

    public boolean isNotAddedYet()
    {
        return notAddedYet;
    }

    public void setNotAddedYet(boolean notAddedYet)
    {
        this.notAddedYet = notAddedYet;
    }

    protected boolean isAddedCompletely()
    {
        return addedCompletely;
    }

    protected void setAddedCompletely(boolean addedCompletely)
    {
        this.addedCompletely = addedCompletely;
    }

    public void flushContent()
    {
        setNotAddedYet(false);
        title = null;
        for(Iterator i = iterator(); i.hasNext(); i.remove())
        {
            Element element = (Element)i.next();
            if(!(element instanceof Section))
                continue;
            Section s = (Section)element;
            if(!s.isComplete() && size() == 1)
            {
                s.flushContent();
                return;
            }
            s.setAddedCompletely(true);
        }

    }

    public boolean isComplete()
    {
        return complete;
    }

    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }

    public void newPage()
    {
        add(Chunk.NEXTPAGE);
    }

    public PdfObject getAccessibleAttribute(PdfName key)
    {
        return title.getAccessibleAttribute(key);
    }

    public void setAccessibleAttribute(PdfName key, PdfObject value)
    {
        title.setAccessibleAttribute(key, value);
    }

    public HashMap getAccessibleAttributes()
    {
        return title.getAccessibleAttributes();
    }

    public PdfName getRole()
    {
        return title.getRole();
    }

    public void setRole(PdfName role)
    {
        title.setRole(role);
    }

    public AccessibleElementId getId()
    {
        return title.getId();
    }

    public void setId(AccessibleElementId id)
    {
        title.setId(id);
    }

    public volatile void add(int x0, Object x1)
    {
        add(x0, (Element)x1);
    }

    public volatile boolean add(Object x0)
    {
        return add((Element)x0);
    }

    public static final int NUMBERSTYLE_DOTTED = 0;
    public static final int NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT = 1;
    private static final long serialVersionUID = 0x2e21d558d8a63c0bL;
    protected Paragraph title;
    protected String bookmarkTitle;
    protected int numberDepth;
    protected int numberStyle;
    protected float indentationLeft;
    protected float indentationRight;
    protected float indentation;
    protected boolean bookmarkOpen;
    protected boolean triggerNewPage;
    protected int subsections;
    protected ArrayList numbers;
    protected boolean complete;
    protected boolean addedCompletely;
    protected boolean notAddedYet;
}