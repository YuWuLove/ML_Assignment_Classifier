package buaa.ml;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class People {
	ArrayList<Person> people;
	
	public People()
	{
		people = new ArrayList<Person>();
	}
	
	public void Add(Person p)
	{
		people.add(p);
	}
	
	
	public void GetRandomPeople(int i, People trainingSet, People testSet)
	{		
		Random r = new Random();
		for(int j=0;j<10000;j++)
		{
			int pos1 = r.nextInt(this.people.size()-1);
			int pos2 = r.nextInt(this.people.size()-1);
			
			Person t = people.get(pos1);
			people.set(pos1, people.get(pos2));
			people.set(pos2, t);
			
		}
		trainingSet.people.clear();
		testSet.people.clear();
	
		trainingSet.people.addAll(people.subList(0, i)) ;
		testSet.people.addAll(people.subList(i, people.size())) ;

	}
	
	public void GetBalancePeople(int i, People trainingSet, People testSet)
	{		
		int male = i/2;
		int female = i-i/2;
		
		Random r = new Random();
		for(int j=0;j<10000;j++)
		{
			int pos1 = r.nextInt(this.people.size()-1);
			int pos2 = r.nextInt(this.people.size()-1);
			
			Person t = people.get(pos1);
			people.set(pos1, people.get(pos2));
			people.set(pos2, t);
			
		}
		trainingSet.people.clear();
		testSet.people.clear();
		
		
		Iterator it = this.people.iterator();
		
		while(it.hasNext())
		{
			Person p = (Person) it.next();
			if(p.IsMale)
			{
				if(male-->0)
					trainingSet.people.add(p);
				else
					testSet.people.add(p);
			}
		}
		
		it = this.people.iterator();
		
		while(it.hasNext())
		{
			Person p = (Person) it.next();
			if(p.IsMale==false)
			{
				if(female-->0)
					trainingSet.people.add(p);
				else
					testSet.people.add(p);
			}
		}
		
		System.out.println("Training Male:\t"+trainingSet.GetTotalMale());
		System.out.println("Training FeMale:\t"+trainingSet.GetTotalFeMale());

	}
	
	
	public double GetTotalNum()
	{
		return people.size();
	}
	
	
	public double GetTotalFeMale()
	{
		double d=0;
		for(int i=0;i<people.size();i++)
		{
			if(people.get(i).IsMale==false)
			{
				d++;
			}
		}
		return d;
	}
	
	public double GetTotalMale()
	{
		double d=0;
		for(int i=0;i<people.size();i++)
		{
			if(people.get(i).IsMale)
			{
				d++;
			}
		}
		return d;
	}
}
