package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class Grayscaler {

	/**
	 * Turns {@code pixel} into gray-scale
	 * 
	 * @implNote {@code pixel} is an RGB value where blue is the first 8 bits, green
	 *           is the second 8 bits and red is the third 8 bits
	 * @implNote to gray-scale an RGB use this formula
	 *           {@literal 0.21 * red + 0.72 * green + 0.07 * blue}
	 * @param pixel
	 *            to gray-scale
	 * @return the gray-scaled value
	 */
	public double grayScale(int pixel) {
		String binaryRgbValue = String.format("%32s", Integer.toBinaryString(pixel)).replace(' ', '0');
		double grayScaleValue = 0.21 * Integer.parseInt(binaryRgbValue.substring(16, 24), 2)
				+ 0.72 * Integer.parseInt(binaryRgbValue.substring(8, 16), 2)
				+ 0.07 * Integer.parseInt(binaryRgbValue.substring(0, 8), 2);
		return grayScaleValue;
	}

}