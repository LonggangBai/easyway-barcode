/**
 * 
 */
package com.easyway.barcode.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

/**
 * 二维码现在很流行，特别是在手机系统应用中。在Android系统中推荐使用第三方开源工具类库com.google.zxing来实现二维码（QRCode）
 * 及一维码（条形码）的生成和解析。zxing已封装了对条形码、二维码图像的解码和生成工作，对外提供了简单的api。
 * 下面看看J2SE和Android两个版本的二维码生成代码：
 * 
 * <pre>
 * QRCodeWriter writer = new QRCodeWriter();
 * try {
 *     BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE, width, height);
 *     File file = new File(imagePath);
 *     MatrixToImageWriter.writeToFile(matrix, &quot;png&quot;, file);
 * } catch (WriterException e) {
 *     e.printStackTrace();
 * }
 * </pre>
 * 
 * @author longgangbai 2015-1-9 下午4:29:39
 */
public class ZXingQRcodeTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws WriterException {

	ZXingQRcodeTest t = new ZXingQRcodeTest();
	// t.encode();
	t.decode();
	String text = "姓名：张三;年龄:25;电话：15188254969;";// 二维码内容；
	int width = 100;
	int height = 100;
	String format = "png";
	@SuppressWarnings("rawtypes")
	Hashtable hints = new Hashtable();
	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
	File outputFile = new File("D:\\Users\\Administrator\\Workspaces\\MyEclipse 10\\strutsLianxi2\\WebRoot\\img\\a.png");
	try {
	    MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    @SuppressWarnings("unchecked")
    public void decode() {
	try {
	    @SuppressWarnings("unused")
	    Reader reader = new MultiFormatReader();
	    String imgPath = "D:\\Users\\Administrator\\Workspaces\\MyEclipse 10\\strutsLianxi2\\WebRoot\\img\\a.png"; // 这里把生成的二维码图片保存在tomcat的路径里面
	    File file = new File(imgPath);
	    BufferedImage image;
	    try {
		image = ImageIO.read(file);
		if (image == null) {
		    System.out.println("Could not decode image");
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result;
		@SuppressWarnings("rawtypes")
		Hashtable hints = new Hashtable();
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		result = new MultiFormatReader().decode(bitmap, hints);
		String resultStr = result.getText();
		System.out.println(resultStr);

	    } catch (IOException ioe) {
		System.out.println(ioe.toString());
	    } catch (ReaderException re) {
		System.out.println(re.toString());
	    }

	} catch (Exception ex) {

	}
    }

}