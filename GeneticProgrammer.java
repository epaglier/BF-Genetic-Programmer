import java.util.*;

public class GeneticProgrammer
{
  private Organism[] population;
  private ArrayList<Integer> target;
  
  public static void main(String[] args)
  {
    ArrayList<Integer> target = new ArrayList<Integer>();
    //Strings can be obtained from ascii
    target.add(84);
    target.add(104);
    target.add(97);
    target.add(110);
    target.add(107);
    target.add(32);
    target.add(89);
    target.add(111);
    target.add(117);
    target.add(33);
    GeneticProgrammer g = new GeneticProgrammer(target);
    g.loop();
  }
  
  public GeneticProgrammer(ArrayList<Integer> target)
  {
    population = new Organism[100];
    for (int i = 0; i < population.length; i++)
      population[i] = new Organism(target);
    this.target = target;
  }
  
  public void loop()
  {
    int number = 0;
    while (true)
    {
      for (int i = 0; i < population.length; i++)
      {
        population[i].findFitness();
      }
      
      sortPopulationByFitness(population);
      
      number++;
      
      //pick winners and go again
      System.out.println("Generation: " + number + ": Fitness " + population[99].getFitness() + "/"+ target.size()*256);
      
      //selection CHANGE THE 100!!!!!!!!! based on num games
      for (int i = 0; i < population.length *.5; i++)
      {
        if (Math.random() > 0.5)
          population[i] = population[population.length - 1 - i].copy();
      }
      
      //this is for once you understand what values will be given
      if (population[population.length - 1].getFitness() >= target.size()*256)
      {
        population[population.length - 1].printBrain();
        population[population.length - 1].printMap();
        BFToCode b = new BFToCode();
        b.translate(population[population.length - 1].getBrain());
        System.out.println(b.getOutputs());
      }
      
      if (population[population.length - 1].getFitness() >= target.size()*256)
        System.exit(0);
      
      for (Organism org: population)
        org.resetFitness();
      
      //only for hi test
      /*if (number > 10000)
      {
        population[population.length - 1].printBrain();
        System.exit(0);
      }*/
    }
  }
  
  public void sortPopulationByFitness(Organism[] a)
  {
    for (int i = 0; i < a.length; i++)
    {
      int min = indexOfMin(a, i);
      Organism save = a[i];
      a[i] = a[min];
      a[min] = save;
    }
  }
  
  public static int indexOfMin(Organism[] a, int startIndex)
  {
    int lowest = startIndex;
    Organism y = a[lowest];
    for (int i = startIndex; i < a.length; i++)
    {
      if (y.getFitness() > a[i].getFitness())
      {
        y = a[i];
        lowest = i;
      }
    }
    return lowest;
  }
}