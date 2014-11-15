package buaa.ml;

public class Main {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Classifier c = new LogisticClassifier();
		c.Init();
		//c.BalanceSample(100);
		c.RandomSample(100);
		c.Run();
		//c.TestMethod();
	}

}
