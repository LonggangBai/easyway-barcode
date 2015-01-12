/**
 * 
 */
package com.easyway.barcode.zxing;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import javax.imageio.ImageIO;

import com.easyway.barcode.qrcode.QRCodeEncoderHandler;
import com.google.zxing.BarcodeFormat;

import com.google.zxing.Binarizer;

import com.google.zxing.BinaryBitmap;

import com.google.zxing.ChecksumException;

import com.google.zxing.FormatException;

import com.google.zxing.LuminanceSource;

import com.google.zxing.NotFoundException;

import com.google.zxing.Result;

import com.google.zxing.WriterException;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;

import com.google.zxing.common.HybridBinarizer;

import com.google.zxing.qrcode.QRCodeReader;

import com.google.zxing.qrcode.QRCodeWriter;

/**
 * <pre>
 * DefaultConfiguration cfg = new DefaultConfiguration(&quot;barcode&quot;);
 * DefaultConfiguration child = new DefaultConfiguration(&quot;datamatrix&quot;); // code128
 * DefaultConfiguration attr;
 * // attr= new DefaultConfiguration(&quot;height&quot;);
 * // attr.setValue(&quot;10&quot;);
 * cfg.addChild(child);
 * // child.addChild(attr);
 * attr = new DefaultConfiguration(&quot;module-width&quot;);
 * attr.setValue(&quot;0.6&quot;);
 * child.addChild(attr);
 * int orientation = 0;
 * int resolution = 300;
 * 
 * BarcodeUtil util = BarcodeUtil.getInstance();
 * BarcodeGenerator gen = util.createBarcodeGenerator(cfg);
 * 
 * ByteArrayOutputStream bout = new ByteArrayOutputStream(4096);
 * 
 * BitmapCanvasProvider bitmap = new BitmapCanvasProvider(bout, MimeTypes.MIME_JPEG, resolution, BufferedImage.TYPE_BYTE_BINARY, false, orientation);
 * gen.generateBarcode(bitmap, code);
 * try {
 *     bitmap.finish();
 * } catch (IOException e) {
 * } finally {
 *     try {
 * 	bout.close();
 *     } catch (IOException e) {
 *     }
 * }
 * response.setContentType(MimeTypes.MIME_JPEG);
 * response.setContentLength(bout.size());
 * response.getOutputStream().write(bout.toByteArray());
 * response.getOutputStream().flush();
 * 
 * </pre>
 * 
 * <pre>
 * QRCodeWriter writer = new QRCodeWriter();
 * response.setHeader(&quot;Pragma&quot;, &quot;No-cache&quot;);
 * response.setHeader(&quot;Cache-Control&quot;, &quot;no-cache&quot;);
 * response.setDateHeader(&quot;Expires&quot;, 0);
 * response.setContentType(&quot;image/jpeg&quot;);
 * BitMatrix bitMatrix = null;
 * try {
 *     bitMatrix = writer.encode(code, BarcodeFormat.QR_CODE, 300, 300);
 *     MatrixToImageWriter.writeToStream(bitMatrix, &quot;jpeg&quot;, response.getOutputStream());
 *     response.getOutputStream().flush();
 *     response.getOutputStream().close();
 * } catch (WriterException e) {
 *     e.printStackTrace();
 * } catch (IOException e) {
 *     e.printStackTrace();
 * }
 * </pre>
 * 
 * ZXing生成二维码的方式
 * 
 * @author longgangbai 2015-1-9 下午4:14:55
 */
public class ZXingQRCode {

    private static final String FORMAT = "PNG";

    /**
     * 
     * 生成二维码
     * 
     * @param contents
     *            内容，换行可以用\n
     * 
     * @param dest
     *            生成二维码图片地址
     * 
     * @param width
     *            宽度
     * 
     * @param height
     *            高度
     * 
     * @throws WriterException
     * 
     * @throws FileNotFoundException
     * 
     * @throws IOException
     */

    public static void encode(String contents, String dest, int width, int height) throws WriterException, FileNotFoundException, IOException {

	contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");

	QRCodeWriter writer = new QRCodeWriter();

	BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, width, height);

	// MatrixToImageWriter.writeToFile(matrix, format, new
	// File(dest));//过时方法不推荐

	MatrixToImageWriter.writeToStream(matrix, FORMAT, new FileOutputStream(new File(dest)));

    }

    /**
     * 
     * 从一张图片解析出二维码信息
     * 
     * @param dest
     *            目标地址
     * 
     * @return String 二维码信息
     * 
     * @throws IOException
     * 
     * @throws NotFoundException
     * 
     * @throws ChecksumException
     * 
     * @throws FormatException
     */

    public static String decode(String dest) throws IOException, NotFoundException, ChecksumException, FormatException {

	QRCodeReader reader = new QRCodeReader();

	BufferedImage image = ImageIO.read(new File(dest));

	LuminanceSource source = new BufferedImageLuminanceSource(image);

	Binarizer binarizer = new HybridBinarizer(source);

	BinaryBitmap imageBinaryBitmap = new BinaryBitmap(binarizer);

	Result result = reader.decode(imageBinaryBitmap);

	// System.out.println("result = "+ result.toString());

	// System.out.println("resultFormat = "+ result.getBarcodeFormat());

	// System.out.println("resultText = "+ result.getText());

	return result.getText();

    }

    public static void main(String[] args) throws WriterException, IOException, NotFoundException, ChecksumException, FormatException {
	String imgPath = QRCodeEncoderHandler.class.getResource("/res/qrcode.png").getPath();
	ZXingQRCode.encode("http://www.xdemo.org/", imgPath, 200, 200);
	System.out.println(ZXingQRCode.decode(imgPath));

    }

}