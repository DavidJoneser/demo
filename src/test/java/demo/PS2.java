package demo;

public class PS2 extends PS3 {
    int a;

    //Param Constructor
    public PS2(int a){
        super(a); //parent class constructor is invoked
        this.a=a;
    }
    public void test1(){
        System.out.println("This is class PS2");
    }
    public int increment(){
        a = a+1;
        return a;
    }
    public int decrement(){
        a=a-1;
        return a;
    }
}
