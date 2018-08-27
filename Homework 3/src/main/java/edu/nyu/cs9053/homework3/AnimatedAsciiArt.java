package edu.nyu.cs9053.homework3;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * User: blangel
 */
public class AnimatedAsciiArt {

	public static void main(String[] args) {
		AnimatedAsciiArt animatedAsciiArt = new AnimatedAsciiArt(args);
		animatedAsciiArt.play();
	}

	/**
	 * @implNote input must not be null or empty if so
	 *           {@code throw new IllegalArgumentException();}
	 * @implNote use the {@linkplain #convert(String)}
	 * @param files
	 *            to convert into {@linkplain ImageInfoProvider[]}
	 * @return converted {@linkplain ImageInfoProvider[]}
	 */
	protected static ImageInfoProvider[] convert(String[] files) {
		if (files == null || files.length == 0) {
			throw new IllegalArgumentException();
		}
		ImageInfoProvider[] imageData = new ImageInfoProvider[files.length];
		for (int fileNumber = 0; fileNumber < files.length; fileNumber++) {
			imageData[fileNumber] = AnimatedAsciiArt.convert(files[fileNumber]);
		}
		return imageData;
	}

	protected static ImageInfoProvider convert(String file) {
		try {
			return new ImageInfoProvider(ImageIO.read(new File(file)));
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

	private final ImageInfoProvider[] providers;

	private final AsciiArtConverter converter;

	private final AsciiArtPrinter printer;

	public AnimatedAsciiArt(String[] files) {
		this(convert(files), new AsciiArtConverter(), new AsciiArtPrinter());
	}

	public AnimatedAsciiArt(ImageInfoProvider[] providers, AsciiArtConverter converter, AsciiArtPrinter printer) {
		if ((providers == null) || (converter == null) || (printer == null)) {
			throw new IllegalArgumentException();
		}
		this.providers = providers;
		this.converter = converter;
		this.printer = printer;
	}

	/**
	 * Converts the image data from {@linkplain #providers} into {@literal ASCII}
	 * art and prints.
	 * 
	 * @implNote before each print of art ensure the screen is cleared
	 * @implNote after each print invoke {@linkplain #sleep()}
	 */
	public void play() {
		for (int imageNumber = 0; imageNumber < this.providers.length; imageNumber++) {
			this.printer.clearScreen();
			this.printer.print((this.converter.convert(this.providers[imageNumber])));
			this.sleep();
		}
	}

	protected void sleep() {
		try {
			Thread.sleep(400L);
		} catch (InterruptedException ie) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(ie);
		}
	}

}
