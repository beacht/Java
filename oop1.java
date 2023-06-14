import java.util.Scanner;
class Main {
  public static void main(String[] args)
  {
    Scanner myScan = new Scanner(System.in);
    char choice = 'X';
    int value = 0;
    do
    {
      System.out.printf("Choose from the following options:\nA or a:  To display the divisors of a natural number\nB or b: To display the multiplication table for a naturalnumber\nQ or q to Exit\n\n\tEnter your choice: ");
      choice = myScan.nextLine().toLowerCase().charAt(0);
      if(choice != 'a' && choice != 'b' && choice != 'q')
        System.out.printf("\tInvalid option!\n\n");
      switch(choice)
      {
        case 'a':
          System.out.printf("\tEnter your number: ");
          value = myScan.nextInt();
          myScan.nextLine();
          if(value < 1)
          {
            System.out.printf("\t%d is not a natural number.\n\n", value);
            break;
          }

          int flag = 0;
          for(int x = 2; x < value / 2; x++)
          {
            if(value % x == 0)
            {
              flag = 1;
              break;
            }
          }
          
          if(flag == 0)
            System.out.printf("\t%d is prime (the only divisors of %d are %d and %d)\n\n", value, value, 1, value);
          else if(flag == 1)
          {
            System.out.printf("\tThe divisiors of %d are: 1", value);
            for(int x = 2; x < value; x++)
              if(value % x == 0)
                System.out.printf(",%d", x);
              System.out.printf(" and %d\n\n", value);
          }
          break;

        case 'b':
          System.out.printf("\tEnter your number: ");
          value = myScan.nextInt();
          myScan.nextLine();
          System.out.printf("\tEnter the size of the multiplication table (1,2...etc): ");
          int size = myScan.nextInt();
          myScan.nextLine();
          int counter = 1;
          while(counter <= size)
          {
            System.out.printf("\t%d x %d = %d\n", counter, value, counter * value);
            counter++;
          }
          System.out.printf("\n\n");
          break;

        case 'q':
          System.out.printf("\tBye!");
          break;
      }
      
    } while(choice != 'q');


    myScan.close();
  }
}