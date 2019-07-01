import java.io.*;
import hex.genmodel.easy.RowData;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.prediction.*;


public class Score_main 
{
	private static String modelClassName = "iris_km_model";

  	public static void main(String[] args) throws Exception 
	{
    		hex.genmodel.GenModel rawModel;
    		rawModel = (hex.genmodel.GenModel) Class.forName(modelClassName).newInstance();
    		//EasyPredictModelWrapper model = new EasyPredictModelWrapper(rawModel);
		
		// By default, unknown categorical levels throw PredictUnknownCategoricalLevelException.
		// Optionally configure the wrapper to treat unknown categorical levels as N/A instead
		// and strings that cannot be converted to numbers also to N/As:
		
		EasyPredictModelWrapper model = new EasyPredictModelWrapper(
			new EasyPredictModelWrapper.Config()
			.setModel(rawModel)
		        //.setConvertUnknownCategoricalLevelsToNa(true)
		        //.setConvertInvalidNumbersToNa(true)
			.setUseExtendedOutput(true)
		     );
		
		RowData row = new RowData();
		/*
		//3
		row.put("C1", "3.5");
		row.put("C2", "3.5");
		row.put("C3", "3.5");
		row.put("C4", "3.5");
		*/
		//2
		row.put("C1", "5.2");
		row.put("C2", "3.6");
		row.put("C3", "1.5");
		row.put("C4", ".3");
		/*
		//0		
		row.put("C1", "6.8");
		row.put("C2", "3.1");
		row.put("C3", "5.5");
		row.put("C4", "2.5");
		
		//0
		row.put("C1", "7.5");
		row.put("C2", "3.1");
		row.put("C3", "6.3");
		row.put("C4", "2.0");
		*/ 	 	 			

		ClusteringModelPrediction p = model.predictClustering(row);
		System.out.println("New Flower species: " + row.toString());
		System.out.println("Chosen Cluster (predicted): " + p.cluster);
		System.out.print("Squared distances to all cluster centers: ");
    		for (int i = 0; i < p.distances.length; i++) 
		{
			if (i > 0) 
			{
				System.out.print(",");
			}
			System.out.print(p.distances[i]);
		}
    		
		System.out.println("\nEnd");
	}
}
