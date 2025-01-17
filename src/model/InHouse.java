package model;

public class InHouse extends Part{
    private int machineID;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID){
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    public InHouse(String name, double price, int stock, int min, int max, int machineID){
        super(name, price, stock, min, max);
        this.machineID = machineID;
    }
    
    public void setMachineID(int machineID){
        this.machineID = machineID;
    }
    
    public int getMachineID(){
        return machineID;
    }

    @Override
    public String toString() {
        return "InHouse{" + super.toString() + "machineID: " + machineID + '}';
    }
    
}
