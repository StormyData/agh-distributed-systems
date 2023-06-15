package sr.thrift.server;

import org.apache.thrift.TException;

import sr.rpc.thrift.OperationType;
import sr.rpc.thrift.InvalidArguments;
import sr.rpc.thrift.AdvancedCalculator;

// Generated code

import java.util.*;

public class AdvancedCalculatorHandler implements AdvancedCalculator.Iface {

	int id;

	public AdvancedCalculatorHandler(int id) {
		this.id = id;
	}

	public int add(int n1, int n2) {
		System.out.println("AdvCalcHandler#" + id + " add(" + n1 + "," + n2 + ")");
		//try { Thread.sleep(9000); } catch(java.lang.InterruptedException ex) { }
		System.out.println("DONE");
		return n1 + n2;
	}


	@Override
	public double op(OperationType type, Set<Double> val) throws TException 
	{
		System.out.println("AdvCalcHandler#" + id + " op() with " + val.size() + " arguments");
		
		if(val.size() == 0) {
			throw new InvalidArguments(0, "no data");
		}
		
		double res = 0;
		switch (type) {
		case SUM:
			for (Double d : val) res += d;
			return res;
		case AVG:
			for (Double d : val) res += d;
			return res/val.size();
		case MIN:
			return val
					.parallelStream()
					.min(Comparator.naturalOrder())
					.orElseThrow(() -> new InvalidArguments(0, "no data"));
		case MAX:
			return val
					.parallelStream()
					.max(Comparator.naturalOrder())
					.orElseThrow(() -> new InvalidArguments(0, "no data"));
		}
		
		return 0;
	}

	
	@Override
	public int subtract(int num1, int num2) throws TException {
		return num1 - num2;
	}

	@Override
	public List<Double> matmul(List<List<Double>> matrix, List<Double> vector) throws InvalidArguments, TException {
		if(matrix == null)
			throw new InvalidArguments(0, "matrix cannot be null");
		if(matrix.stream().anyMatch(Objects::isNull))
			throw new InvalidArguments(0, "matrix cannot be null");
		int size = matrix.get(0).size();
		if(!matrix.stream().allMatch(l -> l.size() == size))
			throw new InvalidArguments(0, "all columns must be the same size");

		if(vector == null)
			throw new InvalidArguments(1, "vector cannot be null");
		if(vector.size() != size)
			throw new InvalidArguments(0, "incompatible sizes");
		List<Double> out = new ArrayList<>(matrix.size());
		for(int i=0;i<matrix.size();i++)
		{
			double sum = 0;
			for(int j=0;j<size;j++)
			{
				sum += 	vector.get(j) * matrix.get(i).get(j);
			}
			out.add(sum);
		}
		return out;
	}
}

