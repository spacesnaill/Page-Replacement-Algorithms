package edu.frostburg.cosc460;

/**
 * Created by Patrick
 */
public class OPT {

    public OPT(){

    }

    public void OPTAlgorithm(String[] refString, int frameSize){
        int pageFault = 0; //number of page faults (when the memory frame needs to be changed)
        int pageHit = 0; //number of page hits (when it does not)
        int pointer = 0; //helps FIFO algorithm keep track of who to kick out to make space
        String[] frame = new String[frameSize]; // create an array to act as memory frame according to the frame size given

        for(int i = 0; i < refString.length; i++ ){
            boolean alreadyInMemory = false;

            for(int t = 0; t < frame.length; t++){
                if(frame[t] == null){
                    frame[t] = refString[i];
                    pageFault = pageFault + 1;
                    alreadyInMemory = true;
                    break;
                }
                if(frame[t].equals(refString[i])){
                    alreadyInMemory = true;
                    pageHit = pageHit + 1;
                    break;
                }
            }//end of for loop to check if page is either in frame or frame is not full

            if(alreadyInMemory == true){
                continue;
            }

            int furthestFuturePage = 0; //need to keep track of the index of the page to see which is furthest into the future
            int frameToBeReplaced = 0; //keeping track of which frame we are going to replace
            boolean neverUsed; //covers the possibility a page is never used in the future

            //execute this if a page needs to be kicked out of the frame to make room
            //we compare each page in frame to the future to see which is needed furthest in the future
            for(int pageInFrame = 0; pageInFrame < frame.length; pageInFrame++){
                //compare the page in the frame to the future
                //we start at i because the past isn't what we're looking at
                neverUsed = true;

                for(int x = i; x < refString.length; x++){
                    if(frame[pageInFrame].equals(refString[x])){
                        //if page is needed in future we compare to what we already have
                        neverUsed = false; //page found, therefore used
                        if ( furthestFuturePage < x) {
                            furthestFuturePage = x;
                            frameToBeReplaced = pageInFrame;
                        }
                        break;
                    }
                }//end of inner loop for kicking out a page from frame

                if(neverUsed == true){
                    frameToBeReplaced = pageInFrame;
                    break; //leave the loop, we found what we were looking for
                }
            }//end of outer loop for kicking out a page from frame

            //after we find the page that can be kicked out safely, we kick it out
            frame[frameToBeReplaced] = refString[i];
            pageFault = pageFault + 1;

        }//end of outer for loop

        System.out.println("Page Hits: " + pageHit);
        System.out.println("Page Faults: " + pageFault);

    }

}
