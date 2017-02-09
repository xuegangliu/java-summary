import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 各种格式的编码加码工具类. 集成Commons-Codec,Commons-Lang及JDK提供的编解码方法.
 * 
 * @author liuxuegang
 */
public class EncodeUtils {

	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	
	private static final byte[] CONFUSION = {'1', '6', '8', '3', '5', '0', '8', '8', '2',
		'3', '3', '3', '5', '5', '6', '9'};
	
	private static final int COUNT = 3;

	private static Logger logger = LoggerFactory.getLogger(EncodeUtils.class);

	/**
	 * Hex编码.
	 */
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("Hex Decoder exception", e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符如+,/=转为其他字符, 见RFC3548).
	 */
	public static String base64UrlSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 */
	public static String urlEncode(String input) {
		return urlEncode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 编码.
	 */
	public static String urlEncode(String input, String encoding) {
		try {
			return URLEncoder.encode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 */
	public static String urlDecode(String input) {
		return urlDecode(input, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码.
	 */
	public static String urlDecode(String input, String encoding) {
		try {
			return URLDecoder.decode(input, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * Html 转码.
	 */
	public static String htmlEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * Html 解码.
	 */
	public static String htmlUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String xmlEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String xmlUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * MD5编码
	 */
	public static String md5Encode(String input) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(input.getBytes());
			byte[] byteDigest = md5.digest();
			return hexEncode(byteDigest);
		} catch (NoSuchAlgorithmException e) {
			logger.error("e:{}", e);
			return null;
		}
	}
	
	public static String SHA1Encode(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest m = MessageDigest.getInstance("sha-1");  
        m.update(input.getBytes("UTF8"));  
        byte s[] = m.digest();  
        return hex(s); 
	}
	
    private static String hex(byte[] arr) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {  
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,3));  
        }  
        return sb.toString();  
    }
    
    public static String securityEncode(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    	input = "ctfo"+input+"cfto";
    	MessageDigest m = MessageDigest.getInstance("SHA-256");  
        m.update(input.getBytes("UTF8"));  
        byte s[] = m.digest();  
        return hex(s); 
    }

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);

		for (i = 0; i < src.length(); i++) {

			j = src.charAt(i);

			if (Character.isDigit(j) || Character.isLowerCase(j) || Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
	 * 混淆
	 * @param str
	 * @return
	 */
	public static String confusion(String str){
		if(StringUtils.isBlank(str)) return "";
		int k = 0;
		int j = 0;
		byte[] b = str.getBytes();
		byte[] b1 = new byte[b.length + CONFUSION.length];
		for(int i=0;i<b1.length;i++){
			if(j < b.length){
				if(i%COUNT == 0 && k < CONFUSION.length){
					b1[i] = CONFUSION[k];
					b1[i+1] = b[j];
					i++;k++;j++;
				}else{
					b1[i] = b[j];
					j++;
				}
			}else{
				if(k < CONFUSION.length){
					b1[i] = CONFUSION[k];
					k++;
				}
			}
		}
		return new String(b1);
	}
	
	/**
	 * 解混淆
	 * @param str
	 * @return
	 */
	public static String deConfusion(String str){
		if(StringUtils.isBlank(str)) return "";
		int k = 0;
		byte[] b = str.getBytes();
		byte[] result = new byte[b.length-CONFUSION.length];
		for(int i=0;i<b.length;i++){
			if(k == result.length) break;
			if(i%COUNT == 0)
				continue;
			else{
				result[k] = b[i];
				k++;
			}
		}
		return new String(result);
	}
}
