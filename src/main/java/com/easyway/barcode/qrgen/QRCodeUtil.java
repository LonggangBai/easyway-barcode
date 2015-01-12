/**
 * 
 */
package com.easyway.barcode.qrgen;

/**
 * @author longgangbai
 * 2015-1-9  下午3:35:02
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.easyway.barcode.qrcode.QRCodeEncoderHandler;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class QRCodeUtil {

    public static void main(String[] args) {
	ByteArrayOutputStream out = QRCode.from("Hello World").to(ImageType.PNG).stream();
	try {
	    String imgPath = QRCodeEncoderHandler.class.getResource("/res/qrcode.png").getPath();
	    FileOutputStream fout = new FileOutputStream(new File(imgPath));
	    fout.write(out.toByteArray());
	    fout.flush();
	    fout.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	// get QR file from text using defaults
	File file = QRCode.from("Hello World").file();
	// get QR stream from text using defaults
	ByteArrayOutputStream stream = QRCode.from("Hello World").stream();

	// override the image type to be JPG
	QRCode.from("Hello World").to(ImageType.JPG).file();
	QRCode.from("Hello World").to(ImageType.JPG).stream();

	// override image size to be 250x250
	QRCode.from("Hello World").withSize(250, 250).file();
	QRCode.from("Hello World").withSize(250, 250).stream();

	// override size and image type
	QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).file();
	QRCode.from("Hello World").to(ImageType.GIF).withSize(250, 250).stream();

	// Website Link (URLs) QR Code in Java
	ByteArrayOutputStream out1 = QRCode.from("http://viralpatel.net").to(ImageType.PNG).stream();
    }

}
