package buaa.ml;

import java.util.Iterator;

public class NaiveClassifier extends Classifier {
	public void Run()
	{
		Iterator i = this.testSet.people.iterator();
		
		while(i.hasNext())
		{
			Person p = (Person) i.next();
			p.Perdict = true;
		}
	}
}
