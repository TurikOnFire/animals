package exceptions;

public class ProbabilityException extends Exception{

    private int percent;

    public int getPercent(){
        return percent;
    }

    public ProbabilityException(String message, int num){
        super(message);
        percent=num;
    }
}
