package computation;

public class ResultWrapper {
    private int partitions;
    private double result;
    private boolean isValid;

    public ResultWrapper() {
        this.isValid = false;
    }

    public ResultWrapper(int partitions, double result) {
        this.partitions = partitions;
        this.result = result;
        this.isValid = true;
    }

    public int getPartitions() {
        return partitions;
    }

    public double getResult() {
        return result;
    }

    public boolean isValid() {
        return isValid;
    }

}
