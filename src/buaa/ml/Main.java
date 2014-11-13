package buaa.ml;

public class Main {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Classifier c = new NaiveClassifier();
		c.Init();
		c.BalanceSample(200);
		//c.RandomSample(200);
		c.Run();
		c.TestMethod();
	}

}
