package edu.frostburg.cosc460;

/**
 * Created by Patrick
 */
public class Tester {

    public Tester(){

    }

    public void Test(String refStr, int frameSize){
        System.out.println("Reference string: " + refStr);
        System.out.println("Frame size: " + frameSize);

        System.out.println("FIFO START: ");
        FIFO fifo = new FIFO();
        fifo.FIFOAlgorithm(refStr.split(","), frameSize); //prints out faults and hits using this algorithm
        System.out.println("---FIFO END---");

        System.out.println("LRU START: ");
        LRU lru = new LRU();
        lru.LRUAlgorithm(refStr.split(","), frameSize); //prints out faults and hits using this algorithm
        System.out.println("---LRU END---");

        System.out.println("OPT START: ");
        OPT opt = new OPT();
        opt.OPTAlgorithm(refStr.split(","), frameSize); //prints out faults and hits using this algorithm
        System.out.println("---OPT END---");

        System.out.println("CLOCK START: ");
        SecondChance clock = new SecondChance();
        clock.SecondChanceAlgorithm(refStr.split(","), frameSize); //prints out faults and hits using this algorithm
        System.out.println("---CLOCK END---");
    }

}
