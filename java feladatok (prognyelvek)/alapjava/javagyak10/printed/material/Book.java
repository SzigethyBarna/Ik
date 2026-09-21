package printed.material;

import printed.material.specific.EBook;

public class Book{
    public static final String DEFAULT_AUTHOR="Kozsik Tamás";
    public static final String DEFAULT_TITLE="Java programozás";
    public static final int DEFAULT_PAGE_COUNT=234;

    private String author;
    public String getAuthor(){
        return this.author;
    }
    private String title;
    public String getTitle(){
        return this.title;
    }
    protected int pageCount;

    public int PageCount(){
        return this.pageCount;
    }

    public Book()throws InvalidBookException
    {
        initBook(DEFAULT_AUTHOR,DEFAULT_TITLE,DEFAULT_PAGE_COUNT);
    }

    public Book(String author, String title, int pageCount)throws InvalidBookException{
        initBook(author,title,pageCount);
    }

    public void checkInitData(String author, String title, int pageCount)throws InvalidBookException{
        if(author.length()<2 || title.length()<4 || pageCount < 1){
            throw new InvalidBookException(author, title);
        }
    }

    protected void initBook(String author, String title, int pageCount)throws InvalidBookException;{
        
        checkInitData(author,title,pageCount);
        this.author=author;
        this.title=title;
        this.pageCount=pageCount;
    }

    public int getPrice(){
        return getPageCount;
    }

    @Override
    public String toString(){
        return author+": "+title+"; "+pageCount;
    }


}