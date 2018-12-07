package Utils;

public class SelectOption {
    private int intValue;
    private String description;
    private String stringValue;
    private static int counter=0;

    public SelectOption(String description, int intValue)
    {
        this.intValue = intValue;
        this.description = description;
    }

    public SelectOption(String description, String stringValue)
    {
        this.stringValue = stringValue;
        this.description = description;
    }

    public SelectOption(String description, int intValue, String stringValue)
    {
        this.intValue = intValue;
        this.stringValue = stringValue;
        this.description = description;
    }

    public int getId()
    {
        return intValue;
    }

    public String getKey()
    {
        return stringValue;
    }

    public String toString()
    {
//        counter++;
//        System.out.println("akuku "+counter);
        return description;
    }
}