package demo;
import org.testng.annotations.Test;

public class PS1 extends PS  {

    @Test
    public void m (){
        //this is a PS method
        toDo();
        //
        PS2 ps2 = new PS2(3);

        ps2.test1();
        System.out.println(ps2.increment());
        System.out.println(ps2.decrement());
        System.out.println(ps2.multiplyTwo());
        System.out.println(ps2.multiplyThree());

        // so the mission is to access via ps2 object PS3 methods.
        // it is possible by creating an object of the class (example is PS2)
        // now the mission is to access method without using an object of PS3.
        //
    }
}
