package buaa.ml;

public class Main {
	
	public Main()
	{
		;
	}
	
	public void Knn()
	{
		KNNClassifier kNN = new KNNClassifier();
		kNN.Init();
		kNN.RandomSample(100);
		kNN.Run();
		kNN.Evaluate();
	}
	
	public void AdaBoost()
	{
		//test for 
		Classifier d = new AdaBoostClassifier();
		d.Init();
		int i = 5;
		double t = 0;
		while(i-->0)
		{
			d.RandomSample((int)(542*0.8));
			d.Run();
			t+=d.res;
		}
		System.out.println(t/5);
	}
	
	
	public void Logistic()
	{
		Classifier c = new LogisticClassifier();
		c.Init();
		//c.BalanceSample(100);
		int i = 5;
		double t = 0;
		while(i-->0)
		{
			c.RandomSample((int)(542*0.8));
			c.Run();
			t+=c.res;
		}
		System.out.println(t/5);
		//c.TestMethod();	
	}

	public void decision_tree()
	{
		//test for 
		Classifier d = new DecisionTreeClassifier();
		d.Init();
		int i = 5;
		double t = 0;
		while(i-->0)
		{
			d.RandomSample((int)(542*0.8));
			d.Run();
			t+=d.res;
		}
		System.out.println(t/5);
	}
	
	public void SVM()
	{
		SVMClassifier s = new SVMClassifier();
		s.Init();
		int i = 5;
		double t = 0;
		while(i-->0)
		{
			s.RandomSample((int)(542*0.8));
			s.Run();
			t+=s.res;
		}
		System.out.println(t/5);
		
	}
	public static void main(String[] args) {
		// TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
		Main m = new Main();
		m.Logistic();
		
	}

}
