package edu.frostburg.cosc460;

/**
 * Created by Patrick
 */
public class FIFO  {

    public FIFO() {

    }

    public void FIFOAlgorithm(String[] refString, int frameSize){
        int pageFault = 0; //number of page faults (when the memory frame needs to be changed)
        int pageHit = 0; //number of page hits (when it does not)
        int pointer = 0; //helps FIFO algorithm keep track of who to kick out to make space
        String[] frame = new String[frameSize]; // create an array to act as memory frame according to the frame size given

        //fill the frame array with something to avoid null pointer exceptions
        //for( int i = 0; i < frameSize; i++){
          //  frame[i] = "empty";
        //}

        for(int i = 0; i < refString.length; i++ ){
            boolean alreadyInMemory = false; //so we can tell whether or not to continue with inserting the page into memory

            //check to see if page is already in the frame

            for (String element : frame) {
                if(element == null){
                    break; //avoid getting exceptions by breaking out of this loop if there's nothing
                }
                if (element.equals(refString[i])) {
                    pageHit = pageHit + 1;
                    alreadyInMemory = true; //tell the loop to go to the next page, this page is in the frame already
                    break;
                }
            }//end of inner for each loop

            if (alreadyInMemory == true) {
                continue; //skip to the next page, this page is in the frame
            }

            //executes only if the current page is not in memory
            frame[pointer] = refString[i];
            pageFault = pageFault + 1;
            if (pointer == (frameSize - 1)) {
                pointer = 0; //this makes sure the pointer never surpasses the size of the frame
            } else {
                pointer = pointer + 1;
            }



        }// end of outer for loop

        System.out.println("Page Hits: " + pageHit);
        System.out.println("Page Faults: " + pageFault);
    }


}
