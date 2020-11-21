/**
 * 
 */
package com.vpm.utilities;

/**
 * @author maithili.s
 *
 */
import javax.sound.sampled.*;
import java.io.*;
 
/**
 * A sample program is to demonstrate how to record sound in Java
 * author: www.codejava.net
 */


public class MediaUtilities {
    // record duration, in milliseconds
	  static final long RECORD_TIME = 10000;  // 1 minute	  	  
	 
	    // the line from which audio data is captured
	    TargetDataLine line;
	    TargetDataLine speakers;
	 
    /**
     * Defines an audio format
     */
    AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
                44100, 16, 2, 4, 44100, true);
        return format;
    }
 
    /**
     * Captures the sound and record into a WAV file
     */
    void start(String fileName) {
        try {
        	  // path of the wav file
    	    File wavFile = new File(fileName);
    	 
    	    // format of audio file
    	    AudioFileFormat.Type fileType = AudioFileFormat.Type.WAVE;
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int numBytesRead;
            int CHUNK_SIZE = 1024;
            byte[] data = new byte[line.getBufferSize() / 5];
            line.start();   // start capturing
 
            System.out.println("Start capturing...");
            int bytesRead = 0;
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, format);
            speakers = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            int bufferSize = 16 * 44100;
            byte[] buffer = new byte[bufferSize];
            speakers.open(format, bufferSize);
            speakers.start();           
            AudioInputStream ais = new AudioInputStream(speakers);
            
            System.out.println("Start recording...");
           
 
            // start recording
            AudioSystem.write(ais, fileType, wavFile);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
 
    /**
     * Closes the target data line to finish capturing and recording
     */
    void finish() {
    	speakers.stop();
    	speakers.close();
        line.stop();
        line.close();
        System.out.println("Finished");
    }
 
    /**
     * Entry to run the program
     */
   public void audioCapture(String fileName) {
	   	   
        final MediaUtilities recorder = new MediaUtilities();
 
        // creates a new thread that waits for a specified
        // of time before stopping
        Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(RECORD_TIME);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                recorder.finish();
            }
        });
 
        stopper.start();
 
        // start recording
        recorder.start(fileName);
    }
}

