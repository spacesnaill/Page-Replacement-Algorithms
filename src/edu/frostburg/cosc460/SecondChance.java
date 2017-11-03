package edu.frostburg.cosc460;

/**
 * Created by Patrick
 */
public class SecondChance {

    public SecondChance(){

    }

    public void SecondChanceAlgorithm(String[] refString, int frameSize){
        int pageFault = 0; //number of page faults (when the memory frame needs to be changed)
        int pageHit = 0; //number of page hits (when it does not)
        int pointer = 0; //helps Clock algorithm keep track of who to kick out to make space
        String[] frame = new String[frameSize]; // create an array to act as memory frame according to the frame size given
        boolean[] referenceBit = new boolean[frameSize]; //array to hold the reference bits associated with the frame

        for(int i = 0; i < refString.length; i++ ){
            boolean alreadyInMemory = false;

            for (int t = 0; t < frame.length; t++) {
                if(frame[t] == null){
                    referenceBit[t] = false;
                    break; //avoid getting exceptions by breaking out of this loop if there's nothing
                }
                if (frame[t].equals(refString[i])) {
                    pageHit = pageHit + 1;
                    alreadyInMemory = true; //tell the loop to go to the next page, this page is in the frame already
                    if(referenceBit[t] == false){
                        referenceBit[t] = true; //reference bit gets set to true because page was referenced
                    }
                }
            }//end of inner for each loop

            if (alreadyInMemory == true) {
                continue; //skip to the next page, this page is in the frame
            }

            //this executes if a new page is being introduce into the frame

            while(true){
                if(referenceBit[pointer] == false) {
                    frame[pointer] = refString[i];
                    pageFault = pageFault + 1;
                    pointer = pointer + 1;
                    break; //leave the loop
                }
                if(referenceBit[pointer] == true) {
                    referenceBit[pointer] = false;
                    pointer = pointer + 1;
                }
                if(pointer > frameSize - 1){
                    pointer = 0; //wrap back around to 0 if pointer goes past the frame size
                }
            }//end of bit reference loop
            if(pointer > frameSize - 1){
                pointer = 0; //wrap back around to 0 if pointer goes past the frame size
            }//insurance the pointer never goes past the frame size


        }//end of outer for loop

        System.out.println("Page Hits: " + pageHit);
        System.out.println("Page Faults: " + pageFault);

    }//end of method

}
