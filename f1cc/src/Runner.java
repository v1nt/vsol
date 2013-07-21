import java.io.IOException;

public class Runner {
	public static void main(String[] args) {
		try {
	 		ParserLogic.run(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

