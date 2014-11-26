package buaa.ml;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Yu
 *
 */
public class Classifier {

	People people;
	People trainingSet;
	People testSet;
	public double res;
	
	public void Init()
	{
		people = new People();
		trainingSet = new People();
		testSet = new People();
		
		
		Loaddata("dataset1.txt");
		Loaddata("dataset2.txt");
		Loaddata("dataset3.txt");
		
		System.out.println("Total Num:\t"+people.GetTotalNum());
		System.out.println("Total Male:\t"+people.GetTotalMale());
		System.out.println("Total FeMale:\t"+people.GetTotalFeMale());
		
		System.out.println("------------------------------------");
	}
	
	/**
	 * @param path
	 */
	private void Loaddata(String path)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while((line = br.readLine())!=null)
			{
				Person p = new Person();
				String[] tmp = line.split("\t");
				Feature f = new Feature();
				f.height = Double.parseDouble(tmp[0]);
				f.weight = Double.parseDouble(tmp[1]);
				
				p.feature = f;
				if(tmp[2].toLowerCase().equals("f"))
					p.IsMale = false;
				else
					p.IsMale = true;
				
				people.Add(p);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @param total 
	 */
	public void RandomSample(int totaltrainingsample)
	{
		this.people.GetRandomPeople(totaltrainingsample,this.trainingSet,this.testSet);
		
		System.out.println("TrainingSize:\t"+trainingSet.people.size());
		System.out.println("TestSize:\t"+testSet.people.size());
	}
	
	
	/** 
	 * @param totaltrainingsample 跪求别给超过150的数字
	 */
	public void BalanceSample(int totaltrainingsample)
	{
		this.people.GetBalancePeople(totaltrainingsample,this.trainingSet,this.testSet);
		
		System.out.println("TrainingSize:\t"+trainingSet.people.size());
		System.out.println("TestSize:\t"+testSet.people.size());
	} 
	
	public void Run()
	{
		;
	}
	
	
	public double TestMethod()
	{
		System.out.println("---------------------Test---------------------");
		double d=0;
		double f= 0;
		
		Iterator i = this.testSet.people.iterator();
		while(i.hasNext())
		{
			Person p = (Person) i.next();
			if(p.Perdict.equals(p.IsMale))
				d++;
			else
				f++;
		}
		
		System.out.println("Accurate Rate:\t"+d/testSet.people.size());
		System.out.println("False Rate:\t"+f/testSet.people.size());
		return d/testSet.people.size();
	}
}
