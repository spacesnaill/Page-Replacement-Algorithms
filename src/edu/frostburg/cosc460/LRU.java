package edu.frostburg.cosc460;

/**
 * Created by Patrick
 */
public class LRU   {

    public LRU(){

    }

    public void LRUAlgorithm(String[] refString, int frameSize) {
        int pageFault = 0; //number of page faults (when the memory frame needs to be changed)
        int pageHit = 0; //number of page hits (when it does not)
        //int counter = 0; //helps LRU algorithm keep track of who to kick out to make space
        String[] frame = new String[frameSize]; // create an array to act as memory frame according to the frame size given
        int[] clockArray = new int[frameSize]; //keep timestamps in here
        int clock = 0; //to keep track of when what arrives

        for (int i = 0; i < refString.length; i++) {
            boolean alreadyInMemory = false; //so we can tell whether or not to continue with inserting the page into memory
            int counter = 0; //used in the for each loop to keep track of where the element is kept
            clock = clock + 1; //add 1 to the "timestamp"
            //check to see if page is already in the frame

            for (String element : frame) {
                if (element == null) {
                    //avoid getting exceptions by breaking out of this loop if there's nothing
                    frame[counter] = refString[i];
                    clockArray[counter] = clock;
                    alreadyInMemory = true;
                    pageFault = pageFault + 1;
                    break; //if the element is null we need to fill the frame still, but we don't want to accidently double tap so we need to break from the for each loop
                }
                if (element.equals(refString[i])) {
                    pageHit = pageHit + 1;
                    alreadyInMemory = true; //tell the loop to go to the next page, this page is in the frame already

                    clockArray[counter] = clock; //object already in frame, therefore just change the timestamp
                    break;
                }
                counter = counter + 1;
            }//end of inner for each loop

            if (alreadyInMemory == true) {
                continue; //skip to the next page, this page is in the frame
            }

            //executes only if the current page is not in memory and the frame is full
            //need to find smallest timestamp to kick out the page for the new one
            int smallestTimestamp = clockArray[0];
            int smallestTimeStampIndex = 0;
            for(int t = 0; t < clockArray.length; t++){
                if(smallestTimestamp > clockArray[t]){
                    smallestTimestamp = clockArray[t];
                    smallestTimeStampIndex = t;
                }
            }
            frame[smallestTimeStampIndex] = refString[i]; //we found where the smallest time stamp is, replace it
            clockArray[smallestTimeStampIndex] = clock; //change the arrival time for the new item
            pageFault = pageFault + 1;
        }//end of outer for loop

        System.out.println("Page Hits: " + pageHit);
        System.out.println("Page Faults: " + pageFault);
    }

}
