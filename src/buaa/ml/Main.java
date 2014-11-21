package buaa.ml;

public class Main {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Classifier c = new LogisticClassifier();
		c.Init();
		//c.BalanceSample(100);
		c.RandomSample(100);
		c.Run();
		//c.TestMethod();
		
		
		//test for 
		Classifier d = new DecisionTreeClassifier();
		d.Init();
		d.RandomSample(100);
		d.Run();
	}

}
