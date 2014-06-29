/**
 * @author cknill
 */

public final class PercolationStats
{
	public PercolationStats(int N, int T)
	{
		if (N < 1 || T < 1)
		{
			throw new IllegalArgumentException();
		}
		gridDim = N;
		numTrials = T;
		avg = 0.0;
		dev = 1.0;
		runExperiments();
	}
	

	
	private void runExperiments()
	{
		double[] samples = new double[numTrials];
		for (int i = 0; i != samples.length; ++i)
		{
			samples[i] = doPercolation();
		}
		// calculate the mean
		avg = StdStats.mean(samples);
		
		// calculate the stddev
		dev = StdStats.stddev(samples);
		;
	}
	
	private double doPercolation()
	{
		// Make a percolation model
		Percolation model = new Percolation(gridDim);
		double sitesOpened = 0.0;
		// Count until the model percolates
		for (; !model.percolates(); ++sitesOpened)
		{
			model.open(StdRandom.uniform(1, gridDim+1),
					   StdRandom.uniform(1, gridDim+1));
		}

		// Return the ratio of open sites
		return sitesOpened / (gridDim*gridDim);
	}
	
	public double mean()
	{
		return avg;
	}
	
	public double stddev()
	{
		return dev;
	}
	
	public double confidenceLo()
	{
		return avg - ((1.96*dev)/Math.sqrt(numTrials));
	}
	
	public double confidenceHi()
	{
		return avg + ((1.96*dev)/Math.sqrt(numTrials));
	}

	private final int gridDim;
	private final int numTrials;
	private double avg;
	private double dev;

	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			throw new IllegalArgumentException();
		}
		
		System.out.print("Args: (");
		System.out.print(args[0] + ", ");
		System.out.println(args[1] + ")");
		
		int gridSize = Integer.parseInt(args[0]);
		int numTrials = Integer.parseInt(args[1]);

		// Make an experiment
		PercolationStats experiment =
				new PercolationStats(gridSize, numTrials); 
		// Get the mean
		System.out.print("Mean: ");
		System.out.println(experiment.mean());
		
		// Get the stddev
		System.out.print("Std Dev: ");
		System.out.println(experiment.stddev());
		
		// Get the confidence interval
		System.out.print("Confidence: (");
		System.out.println(experiment.confidenceLo() +
						  ", " +
						   experiment.confidenceHi() +
						   ")");

	}
}