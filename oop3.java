interface Codable{
  String createCode();
}

class Main {
  public static void main(String[] args) {
    Book b1 = new LibraryBook("Emily jones", "The OrigiN of SPEciEs", "23654585", 1965, "bio"), b2 = new BookstoreBook("Emily Jones", "The Origin of SPEciEs", "23654585", 89.99,1965);
    
    System.out.println(b1.createCode());
    System.out.println(b2.createCode());

    System.out.println(b1);
    System.out.println(b2);
  }
}

abstract class Book implements Codable{
  private String author;
  private String title;
  private String isbn;
  private int year;

  public void setAuthor(String author){
    this.author = author.toLowerCase();
  }

  public String getAuthor(){
    return this.author;
  }

  public void setTitle(String title){
    this.title = title.toLowerCase();
  }

  public String getTitle(){
    return this.title;
  }

  public void setIsbn(String isbn){
    this.isbn = isbn;
  }

  public String getIsbn(){
    return this.isbn;
  }

  public void setYear(int year){
    this.year = year;
  }

  public int getYear(){
    return this.year;
  }

  public Book(String author, String title, String isbn, int year){
    setAuthor(author);
    setTitle(title);
    setIsbn(isbn);
    setYear(year);
  }
  
  //This method is never called by itself.
  //I noticed that, when printing the info of either kind of book, the "[title] by [author] published in [year], " format is shared by both.
  //This method makes the code less redundant because I only now need to write the body of it once. See lines 91 and 134.
  public String toString(){
    return getTitle() + " by " + getAuthor() + " published in " + getYear() + ", ";
  }
}

class BookstoreBook extends Book{
  private double price;

  public void setPrice(double price){
    this.price = price;
  }

  public double getPrice(){
    return this.price;
  }

  public BookstoreBook(String author, String title, String isbn, double price, int year){
    super(author, title, isbn, year);
    setPrice(price);
  }
  
  public String createCode(){
    return "[" + getIsbn() + "-" + getAuthor() + "-" + getPrice() + "]";
  }

  public String toString(){
    return getIsbn() + ": " + super.toString() + "$" + getPrice();
  }
}

class LibraryBook extends Book{
  private String subject;

  public void setSubject(String subject){
    this.subject = subject;
  }

  public String getSubject(){
    return this.subject;
  }

  /*Note to the grader:
    This method is simply for finding the full name of the subject of the book.
    For example, if the subject is "bio", this method returns "biology".
    This is used in the second set of print statements in the HW pdf.
  */
  public String getFullSubject(){
    if("bio".equals(getSubject()))
      return "biology";
    else if("acc".equals(getSubject()))
      return "accounting";
    else if("psy".equals(getSubject()))
      return "psychology";
    else if("rel".equals(getSubject()))
      return "religion";
    else
      return "ERROR";
  }

  public LibraryBook(String author, String title, String isbn, int year, String subject){
    super(author, title, isbn, year);
    setSubject(subject);
  }

  public String createCode(){
    return "[" + getSubject() + "-" + getAuthor() + "-" + getTitle() + "-" + getYear() + "]";
  }

  public String toString(){
    return super.toString() + getFullSubject();
  }
}