package model;

public class Outsourced extends Part{
    private String companyName;
    
    public Outsourced (int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }
    public Outsourced (String name, double price, int stock, int min, int max, String companyName){
        super(name, price, stock, min, max);
        this.companyName = companyName;
    }

    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    public String getCompanyName(){
        return companyName;
    }

    @Override
    public String toString() {
        return "Outsourced{" + super.toString() + "companyName: " + companyName + '}';
    }
    
}
