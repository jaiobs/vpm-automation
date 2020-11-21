package com.vpm.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;

public class AudioUtilities implements Runnable {
	private AudioInputStream audioInputStream;

	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
            44100, 16, 2, 4, 44100, false);
	public TargetDataLine line;
	public Thread thread;
	private double duration;

	public AudioUtilities() {
		super();		
	}

	public void start() {
		thread = new Thread(this);
		thread.setName("Capture");
		thread.start();
	}

	public void stop() {
		thread = null;
	}

	@Override
	public void run() {
		duration = 0;
		
		line = getTargetDataLineForRecord();
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final int frameSizeInBytes = format.getFrameSize();
		final int bufferLengthInFrames = line.getBufferSize() / 16;
		final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
		final byte[] data = new byte[bufferLengthInBytes];
		int numBytesRead;
		line.start();
		while (thread != null) {
			if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
				break;
			}
			out.write(data, 0, numBytesRead);
		}
		// we reached the end of the stream. stop and close the line.
		line.stop();
		line.close();
		line = null;
		// stop and close the output stream
		try {
			out.flush();
			out.close();
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
		// load bytes into the audio input stream for playback
		final byte audioBytes[] = out.toByteArray();
		final ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
		audioInputStream = new AudioInputStream(bais, format, audioBytes.length / frameSizeInBytes);
		final long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format.getFrameRate());
		duration = milliseconds / 1000.0;
		System.out.println(duration);
		try {
			audioInputStream.reset();
			System.out.println("resetting...");
		} catch (final Exception ex) {
			ex.printStackTrace();
			return;
		}
	}

	private TargetDataLine getTargetDataLineForRecord() {
		TargetDataLine line;
		final DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if (!AudioSystem.isLineSupported(info)) {
			return null;
		}
		// get and open the target data line for capture.
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format, line.getBufferSize());
		} catch (final Exception ex) {
			return null;
		}
		return line;
	}

	public AudioInputStream getAudioInputStream() {
		return audioInputStream;
	}

	public AudioFormat getFormat() {
		return format;
	}

	public void setFormat(AudioFormat format) {
		this.format = format;
	}

	public Thread getThread() {
		return thread;
	}

	public double getDuration() {
		return duration;
	}

	public boolean saveToFile(String name, AudioFileFormat.Type fileType, AudioInputStream audioInputStream) {
		System.out.println("Saving...");
		if (null == name || null == fileType || audioInputStream == null) {
			return false;
		}
		File myFile = new File(name + "." + fileType.getExtension());
// reset to the beginnning of the captured data  
		try {
			audioInputStream.reset();
		} catch (Exception e) {
			return false;
		}
		int i = 0;
		while (myFile.exists()) {
			String temp = "" + i + myFile.getName();
			myFile = new File(temp);
		}
		try {
			AudioSystem.write(audioInputStream, fileType, myFile);
		} catch (Exception ex) {
			return false;
		}
		System.out.println("Saved " + myFile.getAbsolutePath());
		return true;
	}

	public void audioCapture(String fileName) {
		
		AudioUtilities au = new AudioUtilities(); 
	     au.start();
	     try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	     au.stop();  
	     	    	     
	     au.saveToFile(fileName,  AudioFileFormat.Type.WAVE, au.getAudioInputStream());  
	}
}
