# easyway-barcode
this is a  barcode sample ,zxing,qrcode.
1： 使用SwetakeQRCode在Java项目中生成二维码 
http://swetake.com/qr/ 下载地址 
或着http://sourceforge.jp/projects/qrcode/downloads/28391/qrcode.zip 
这个是日本人写的，生成的是我们常见的方形的二维码 
可以用中文 

如：5677777ghjjjjj 


2： 使用BarCode4j生成条形码和二维码 
BarCode4j网址：http://sourceforge.net/projects/barcode4j/ 

barcode4j是使用datamatrix的二维码生成算法，为支持qr的算法 
datamatrix是欧美的标准，qr为日本的标准， 
barcode4j一般生成出来是长方形的 

如：88777alec000yan 
这个博客这方面说的挺清楚的： 
http://baijinshan.iteye.com/blog/1004554
 

3：zxing 
zxing 这个是google的 
下载地址 
http://code.google.com/p/zxing/downloads/list
 

Java代码：
 1.import java.io.File;   
2.import java.util.Hashtable;   
3.  
4.import com.google.zxing.BarcodeFormat;   
5.import com.google.zxing.EncodeHintType;   
6.import com.google.zxing.MultiFormatWriter;   
7.import com.google.zxing.client.j2se.MatrixToImageWriter;   
8.import com.google.zxing.common.BitMatrix;   
9.import com.google.zxing.qrcode.QRCodeWriter;   
10.  
11.  
12.  
13.public class QRCodeEvents {   
14.       
15.    public static void main(String []args)throws Exception{   
16.        String text = "你好";   
17.        int width = 100;   
18.        int height = 100;   
19.        String format = "png";   
20.        Hashtable hints= new Hashtable();   
21.        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");   
22.         BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height,hints);   
23.         File outputFile = new File("new.png");   
24.         MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);   
25.            
26.    }   
27.}  
 
4：google chart api就有实现二维码的方法
 
    利用这个api，使用google appengine进行实现。
 
 
 
5：JS生成二维码
 
使用jquery-qrcode生成二维码
 
先简单说一下jquery-qrcode，这个开源的三方库（可以从https://github.com/jeromeetienne/jquery-qrcode 获取），
 
qrcode.js 是实现二维码数据计算的核心类，
 
jquery.qrcode.js 是把它用jquery方式封装起来的，用它来实现图形渲染，其实就是画图（支持canvas和table两种方式）
 



支持的功能主要有：
 
Js代码：
 1.text     : "https://github.com/jeromeetienne/jquery-qrcode"  //设置二维码内容  
 
      

Js代码：
 1.render   : "canvas",//设置渲染方式   
2.width       : 256,     //设置宽度   
3.height      : 256,     //设置高度   
4.typeNumber  : -1,      //计算模式   
5.correctLevel    : QRErrorCorrectLevel.H,//纠错等级   
6.background      : "#ffffff",//背景颜色   
7.foreground      : "#000000" //前景颜色  
 
使用方式非常简单
 
 
 
Js代码：
 1.jQuery('#output').qrcode({width:200,height:200,correctLevel:0,text:content});  
 
经过简单实践，
 


使用canvas方式渲染性能还是非常不错的，但是如果用table方式，性能不太理想，特别是IE9以下的浏览器，所以需要自行优化一下渲染table的方式，这里就不细述了。
 
其实上面的js有一个小小的缺点，就是默认不支持中文。
 
这跟js的机制有关系，jquery-qrcode这个库是采用 charCodeAt() 这个方式进行编码转换的，
 
而这个方法默认会获取它的 Unicode 编码，一般的解码器都是采用UTF-8, ISO-8859-1等方式，
 
英文是没有问题，如果是中文，一般情况下Unicode是UTF-16实现，长度2位，而UTF-8编码是3位，这样二维码的编解码就不匹配了。
 
 
 
解决方式当然是，在二维码编码前把字符串转换成UTF-8，具体代码如下：
 1.function utf16to8(str) {   
2.    var out, i, len, c;   
3.    out = "";   
4.    len = str.length;   
5.    for(i = 0; i < len; i++) {   
6.    c = str.charCodeAt(i);   
7.    if ((c >= 0x0001) && (c <= 0x007F)) {   
8.        out += str.charAt(i);   
9.    } else if (c > 0x07FF) {   
10.        out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));   
11.        out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));   
12.        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
13.    } else {   
14.        out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));   
15.        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
16.    }   
17.    }   
18.    return out;   
19.}  
