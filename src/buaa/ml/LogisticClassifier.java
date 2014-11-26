package buaa.ml;

public class LogisticClassifier {

	public void Run()
	{
		Logistic m_classifier=new Logistic();
        String options[]=new String[4];
        options[0]="-R";
        options[1]="1E-5";
        options[2]="-M";
        options[3]="10";
        m_classifier.setOptions(options);
       
        m_classifier.buildClassifier(newInstancesTrain1); //ÑµÁ·   
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
