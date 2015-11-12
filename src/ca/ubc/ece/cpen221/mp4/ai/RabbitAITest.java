package ca.ubc.ece.cpen221.mp4.ai;

import static org.junit.Assert.*;

import org.junit.Test;
import ca.ubc.ece.cpen221.mp4.ai.RabbitAI;

public class RabbitAITest {

	@Test
	public void sortWeightsTest() {
		int[] x = {3,5,7,4,2,6,1};
		int[] y = {1,2,3,4,5,6,7};
		
		RabbitAI ai = new RabbitAI();
		ai.sortWeights(x);
		
		for(int i = 0; i < 7; i++)
			assertEquals(x[i],y[i]);
	}

}
