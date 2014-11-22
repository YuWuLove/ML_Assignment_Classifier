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
		kNN.Run();
		kNN.Evaluate();
	}
	
	public void AdaBoost()
	{
		//test for 
		Classifier d = new AdaBoostClassifier();
		d.Init();
		d.RandomSample(200);
		d.Run();
	}
	
	
	public void Logistic()
	{
		Classifier c = new LogisticClassifier();
		c.Init();
		//c.BalanceSample(100);
		c.RandomSample(100);
		c.Run();
		//c.TestMethod();	
	}

	public void decision_tree()
	{
		//test for 
		Classifier d = new DecisionTreeClassifier();
		d.Init();
		d.RandomSample(100);
		d.Run();
	}
	
	public void SVM()
	{
		SVMClassifier s = new SVMClassifier();
		s.Init();
		s.RandomSample(300);
		s.Run();
		
	}
	public static void main(String[] args) {
		// TODO 锟皆讹拷锟斤拷锟缴的凤拷锟斤拷锟斤拷锟�
		Main m = new Main();
		m.AdaBoost();
		
	}

}
