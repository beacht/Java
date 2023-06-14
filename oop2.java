import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    boolean programContinue = true;

    while(programContinue)
    {
      boolean listContinue = true;
      System.out.print("Enter how many name(s) you have: ");
      int nameCount = myScan.nextInt();
      myScan.nextLine();
      
      if(nameCount > 5)
      {
        System.out.println("Sorry, you can't have more than 5 names stored in our system! Enter -1 to exit this program.\n");
        continue;
      }

      else if(nameCount == -1)
      {
        System.out.printf("Bye!");
        programContinue = false;
        continue;
      }

      System.out.printf("Enter your %d names separated by * then hit the Enter Key : \n", nameCount);
      String namesString = myScan.nextLine();

      String namesArray[] = namesString.split("\\*");
      for(int x = 0; x < namesArray.length; x++)
        namesArray[x] = trim(namesArray[x]);

      ArrayList<String> namesList = new ArrayList<>(List.of(namesArray));
      System.out.println("Thank you!\nYou entered:");
      printNames(namesList);
      System.out.println();

      while(listContinue){
        System.out.print("Do you want to make a change to any of the entered names (enter y/Y for yes or e/E to Exit program): ");
        char choice = myScan.nextLine().toLowerCase().charAt(0);
        switch (choice){
          case 'y':
            System.out.print("\nEnter the name you would like to change: ");
            String nameToChange = myScan.nextLine();
            nameToChange = trim(nameToChange);

            if(namesList.contains(nameToChange)){
              System.out.print("Enter the new name: ");
              String newName = myScan.nextLine();
              String parsedNewName = trim(newName);
              namesList.set(namesList.indexOf(trim(nameToChange)), parsedNewName);
              System.out.println("Got it! Here is the new list:");
              printNames(namesList);
              System.out.println();
              break;
            }

            System.out.printf("Sorry no such name in your list. Would you like to add" + " \"%s\" to the list? enter y/Y for yes or n/N for no : ", nameToChange);
            char followUpChoice = myScan.nextLine().toLowerCase().charAt(0);
            if(followUpChoice == 'y' && namesList.size() < 5){
              String parsedName = trim(nameToChange);
              namesList.add(parsedName);
              System.out.printf("%s added to the list. Here is your new list:\n", parsedName);
              printNames(namesList);
              System.out.println();
              break;
            }
            else if(followUpChoice == 'y'){
              System.out.println("Sorry, you can't add a new name as you already have 5 names!\n");
              break;
            }
            break;
          default:
            listContinue = false;
            programContinue = false;
        }
      }
      System.out.println("\n\nGoodbye!");
    }
  }

  public static String trim(String nameString){
    int x = 0;
    while(nameString.charAt(x) == ' ')
      x++;
    int y = nameString.length() - 1;
    while(nameString.charAt(y) == ' ')
      y--;

    String trimmedNameString = nameString.substring(x, y + 1);

    boolean afterSpace = true;
    boolean[] keepChar = new boolean[trimmedNameString.length()];
    boolean[] capitalizeChar = new boolean[trimmedNameString.length()];
    Arrays.fill(keepChar, true);
    Arrays.fill(capitalizeChar, false);

    for(x = 0; x < trimmedNameString.length(); x++)
    {
      if(trimmedNameString.charAt(x) == ' ' && !afterSpace)
        afterSpace = true;
      else if(trimmedNameString.charAt(x) == ' ')
        keepChar[x] = false;
      else if(afterSpace)
      {
        capitalizeChar[x] = true;
        afterSpace = false;
      }
    }

    String parsedName = "";
    for(x = 0; x < keepChar.length; x++)
    {
      if(keepChar[x])
      {
        String nextSubstring = trimmedNameString.charAt(x) + "";
        parsedName += capitalizeChar[x] ? nextSubstring.toUpperCase() : nextSubstring;
      }
    }

    return parsedName;
  }

  public static void printNames(ArrayList<String> names){
    ArrayList<String> namesClone = new ArrayList<>(names);
    Collections.sort(namesClone, (s1, s2) -> { return s1.toLowerCase().compareTo(s2.toLowerCase());});

    System.out.printf("[");
    for(int x = 0; x < namesClone.size() - 1; x++)
      System.out.printf(namesClone.get(x).toUpperCase() + ", ");

    System.out.printf(namesClone.get(namesClone.size() - 1).toUpperCase() + "]\n");
  }
}