import java.util.*;

//THIS FOR SOME REASON ONLY USES NEXT LETTER IN SEQUENCE EASY FIX
public class Organism
{
  public String brain;
  public int fitness;
  public ArrayList<Integer> target;
  public BFToCode b;
  public Map table;
  public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  
  public static void main(String[] args)
  {
    ArrayList<Integer> target = new ArrayList<Integer>();
    //Strings can be obtained from ascii
    target.add(71);
    Organism s =  new Organism(target);
    s.printBrain();
    s.printMap();
  }
  
  
  public Organism(ArrayList<Integer> target)
  {
    b = new BFToCode();
    this.target = target;
    fitness = 0;
    brain = "";
    table = new HashMap();
    formMap();
    //forming brain in the wrong spot
    formBrain();
  }
  
  //must be called first
  public void formMap()
  {
    formStatement(table, "A");
  }
  
  //must be called second
  public void formBrain()
  {
    brain = formBrainHelper("A");
  }
  
  public String formBrainHelper(String letter)
  {
    String s = ((String)table.get(letter));
    String b = "";
    for (int i = 0; i < s.length(); i++)
    {
      if (ALPHABET.contains(s.substring(i,i+1)))
      {
        b += formBrainHelper(s.substring(i,i+1));
      }
      else
      {
        b += s.substring(i,i+1);
      }
    }
    return b;
  }
  
  public void formStatement(Map m, String letter)
  {
    String temp = "";
    for (int i = 0; i < 5; i++)
    {
      int t = (int)(Math.random()*9) + 1;
      if (t == 1)
      {
        temp += "<";
      }
      else if (t == 2)
      {
        temp += ">";
      }
      else if (t == 3)
      {
        temp += "[";
      }
      else if (t == 4)
      {
        temp += "]";
      }
      else if (t == 5)
      {
        temp += "+";
      }
      else if (t == 6)
      {
        temp += "-";
      }
      else if (t == 7)
      {
        temp += ".";
      }
      else if (t == 8)
      {
        //the letters that are not allowed to have more letters
        if (letter.equals("W") || letter.equals("X") || letter.equals("Y") || letter.equals("Z"))
        {}
        else
        {
          //add letter from the next
          String[] split = ALPHABET.split(letter);
          int rand = (int)(Math.random()*split[1].length());
          String document = split[1].substring(rand,rand+1); //THIS NEEDS TO BE ANY LETTER
          formStatement(m, document);
          temp += document;
        }
      }
      else
      {
        temp += ",";
      }
    }
    m.put(letter, temp);
  }
  
  public Organism(ArrayList<Integer> target, HashMap table)
  {
    this.target = target;
    this.table = table;
    formBrain();
  }
  
  //ONLY CALL THIS ONCE
  public int findFitness()
  {
    BFToCode b = new BFToCode();
    b.translate(brain);
    ArrayList<Integer> console = b.getOutputs();
    for (int i = 0; i < console.size(); i++)
    {
      if (i < target.size())
        fitness += 256 - Math.abs(console.get(i) - target.get(i));
    }
    if (b.failed())
    {
      fitness = 0;
    }
    return fitness;
  }
  
  //call this after finding fitness
  public int getFitness()
  {
    return fitness;
  }
  
  public void resetFitness()
  {
    fitness = 0;
  }
  
  public Organism copy()
  {
    //edit table and pass it on
    ArrayList<String> toDo = new ArrayList<String>();
    Set keyChainz = table.keySet();
    HashMap temp = new HashMap();
    for (Object k: keyChainz)
    {
      String letter = (String) k;
      String s = (String)table.get(k);
      String fin = "";
      for(int i = 0; i < s.length(); i++)
      {
        if (Math.random() < 0.05)
        {
          //add char/letter
          if (Math.random() > 0.5)
          {
            String toAdd = "";
              int t = (int)(Math.random()*9) + 1;
              if (t == 1)
              {
                toAdd += "<";
              }
              else if (t == 2)
              {
                toAdd += ">";
              }
              else if (t == 3)
              {
                toAdd += "[";
              }
              else if (t == 4)
              {
                toAdd += "]";
              }
              else if (t == 5)
              {
                toAdd += "+";
              }
              else if (t == 6)
              {
                toAdd += "-";
              }
              else if (t == 7)
              {
                toAdd += ".";
              }
              else if (t == 8)
              {
                //the letters that are not allowed to have more letters
                if (s.substring(i,i+1).equals("W") || s.substring(i,i+1).equals("X") || s.substring(i,i+1).equals("Y") || s.substring(i,i+1).equals("Z"))
                {
                  String[] split = ALPHABET.split(letter);
                  int rand = (int)(Math.random()*split[1].length());
                  String document = split[1].substring(rand,rand+1);
                  if (!table.containsKey(document))
                  {
                    toDo.add(document);
                  }
                  toAdd += document;
                }
                else
                {
                  //add letter from the next
                  String[] split = ALPHABET.split(letter);
                  if (split.length == 1)
                  {}
                  else
                  {
                    int rand = (int)(Math.random()*split[1].length());
                    String document = split[1].substring(rand,rand+1);
                    if (!table.containsKey(document))
                    {
                      toDo.add(document);
                    }
                    toAdd += document;
                  }
                }
              }
              else
              {
                toAdd += ",";
              }
              fin = fin + s.substring(i,i+1) + toAdd;
          }
          //remove char/letter
          else
          {
          }
        }
        else
        {
          fin += s.substring(i,i+1);
        }
      }
      temp.put(k,fin);
      for (String w: toDo)
      {
        formStatement(temp,w);
      }
    }
    return new Organism(target, temp);
  }
  
  public Organism crossBreed(Organism other)
  {
    //need new crossbreeding
    throw new RuntimeException("IMPLEMENT THIS");
  }
  
  public void printBrain()
  {
    System.out.println(brain);
  }
  
  public String getBrain()
  {
    return brain;
  }
  
  public void printMap()
  {
    System.out.println(table);
  }
}