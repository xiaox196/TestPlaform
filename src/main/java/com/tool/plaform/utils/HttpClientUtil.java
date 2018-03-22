package com.tool.plaform.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.mongodb.DBObject;



/**
 * Http协议共通类，提供Http的Post/Get 等基础操作
 * 
 * <p>此类是Bruce整合其它Server中的Util而成</p>
 * 
 * @author Bruce
 *
 */
public class HttpClientUtil {

    private static final Logger LOG = Logger.getLogger(HttpClientUtil.class);
    
    public static final int TIMEOUT = 10000;
    
	public static final String ENCODING = "UTF-8";
    
    public static String get(String url) {
    	return get(url, TIMEOUT);
	}
    
    /**
     * 发送Http Get请求，并指定超时时间
     * <p>
     * 参数URL说明<br>
     * Get请求的参数请自己拼装好再传入，如果有中文请先进行URLEncode后再拼装<br>
     * 例：http://127.0.0.1:9030/tools/bairong?account=17238468229&cnid=150621197501067713&name=%E5%90%B4%E5%85%83%E4%B9%8B&occupation=12&product=PSBC-Staff&usecache=false
     * </p>
     * @param url  URL地址
     * @param timeOut 超时时间，毫秒
     * @return http返回结果
     */
    public static String get(String url, int timeOut) {
		return getWithHeader(url, timeOut);
	}
	
	public static String getWithHeader(String url, int timeOut, Header... headers) {
		LOG.info("get url: " + url);
		Request request = Request.Get(url);
		if (timeOut > 0) {
			request.connectTimeout(timeOut).socketTimeout(timeOut);
		}
		if(headers!=null&headers.length>0){
			for (Header header : headers) {
				request.addHeader(header);
			}
		}
		try {
			HttpResponse returnResponse = request.execute().returnResponse();
			int statusCode = returnResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				String result = StringUtils.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
				LOG.debug("get return: " + result);
				return result;
			} else {
				LOG.error("client.executeMethod failed: " + statusCode);
			}
		} catch (Exception e) {
			LOG.error("url:" + url + ", " + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * get请求，可设置超时时间以及重试次数
	 * @param url
	 * @param timeOut
	 * @param tryCount
	 * @return
	 */
	public static String get(String url, int timeOut, int tryCount) {
		String result = "";
		for(int i=1; i<=tryCount;i++) {
		    result = get(url, timeOut);
		    LOG.debug(url + ", 第" + i + "次调用结果为:" + result);
			if(StringUtils.isNotEmpty(result)) {
				DBObject obj = (DBObject) DBObjectUtil.parseJson(result);
				String responseCode = obj!=null? obj.get("ret").toString() : "";
				if("0".equals(responseCode)) {
					return result;
				}
			}
		}
		return result;
	}
	
	/**
	 * 发送 http post 请求，超时时间默认为0 （参数值以body形式发送）
	 * @param url
	 * @param data
	 * @return http返回结果
	 */
	public static String post(String url, String data){
		return post(url, data, TIMEOUT);
	}
	
	/**
	 * 发送 http post 请求并指定超时时间
	 * 
	 * @param url
	 * @param data json数据 
	 * @return http返回结果
	 */
	public static String post(String url, String data, int timeOut) {
		return postWithHeader(url, data, timeOut);
	}
	
	public static String postWithHeader(String url, String data, int timeOut, Header... headers) {
		if (StringUtils.isEmpty(url) || StringUtils.isEmpty(data)) {
			return null;
		}
		Request request = Request.Post(url).bodyString(data, ContentType.APPLICATION_JSON).addHeader(new BasicHeader("Accept", ContentType.APPLICATION_JSON.toString()));
		if (timeOut > 0) {
			request.connectTimeout(timeOut).socketTimeout(timeOut);
		}
		if(headers!=null&headers.length>0){
			for (Header header : headers) {
				request.addHeader(header);
			}
		}
		try {
			HttpResponse returnResponse = request.execute().returnResponse();
			int statusCode = returnResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				LOG.info("url:" + url+" post successfully.");
				String result = StringUtils.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
				LOG.debug("url:" + url+" post return: " + result);
				return result;
			} else {
				LOG.error("url:" + url+" post failed: " + statusCode);
			}
		} catch (Exception e) {
			LOG.error("url:" + url + ", " + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 发送 http post 请求并指定超时时间
	 * @param url
	 * @param data NameValuePair
	 * @param timeOut
	 * @return http返回结果
	 */
	public static String post(String url, Iterable<NameValuePair> data, int timeOut) {
		if (StringUtils.isEmpty(url)) {
			return null;
		}
		Request request = Request.Post(url).bodyForm(data, Consts.UTF_8);
		if (timeOut > 0) {
			request.connectTimeout(timeOut).socketTimeout(timeOut);
		}
		try {
			HttpResponse returnResponse = request.execute().returnResponse();
			int statusCode = returnResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				LOG.info("url:" + url+" post successfully.");
				String result = StringUtils
						.unicodeToString(EntityUtils.toString(returnResponse.getEntity(), StandardCharsets.UTF_8));
				LOG.info("url:" + url+" post return: " + result);
				return result;
			} else {
				LOG.error("url:" + url+" post failed: " + statusCode);
			}
		} catch (Exception e) {
			LOG.error("url:" + url + ", " + e.getMessage(), e);
		}
		return null;
	}
	
	/**
	 * 发送 http post , 指定多个数据，超时时间默认为0
	 * @param url
	 * @param data
	 * @return
	 */
	public static String post(String url, NameValuePair... data) {
		return post(url, Arrays.asList(data), 0);
	}
	
    /**
	 * URL转码方法
	 * @param parameter
	 * @return
	 */
	public static String encodeUrlUTF8(String parameter) {
		if (isNullStr(parameter))
			return "";
		try {
			parameter = URLEncoder.encode(parameter, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameter;
	}
	
	/**
	 * url解码方法
	 * @param parameter
	 * @return
	 */
	public static String decodeUrlUTF8(String parameter) {
		if (isNullStr(parameter))
			return "";
		try {
			parameter = java.net.URLDecoder.decode(parameter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return parameter;
	}
	
	private static boolean isNullStr(String value) {
		if (null == value || "".equals(value))
			return true;
		else
			return false;
	}
	
	/**
	 * 发送请求,参数值以body形式发送
	 * @param url 请求的服务器地址,如: http://localhost/xmlTest.do
	 * @param bodyData 需发送的Request Body数据,如: 我们要以流发送的数据...
	 * @return 返回服务器的响应
	 * @throws IOException
	 */
	public static String send(String url, String bodyData) throws IOException {
		return send(url, null, null, bodyData);
	}
	
	/**
	 * 发送请求[设置请求头],参数值以body形式发送
	 * @param url 请求服务器地址
	 * @param requestHeaders 请求头部值 key-value 如(头部有2个键值):
	 * 						  key=Content-Type value=application/json
	 * 						  key=Authorization value=bay dasfdfasddasf
 						为null默认为: "application/octet-stream"
	 * @param bodyData 需发送的Request Body数据
	 * @return URL所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String send(String url, Map<String, String> requestHeaders, String bodyData) throws IOException{
		return send(url, null, requestHeaders, bodyData);
	}
	
	/**
	 * 发送请求[设置请求方式GET POST PUT DELETE|设置请求头],参数值以body形式发送
	 * @param url 请求服务器地址
	 * @param requestMethod 请求方式: GET POST PUT DELETE HEAD OPTIONSTRACE
	 * @param requestHeaders 请求头部值 key-value 如(头部有2个键值):
	 * 						  key=Content-Type value=application/json
	 * 						  key=Authorization value=bay dasfdfasddasf
 						为null默认为: "application/octet-stream"
	 * @param bodyData 需发送的Request Body数据
	 * @return URL所代表远程资源的响应结果
	 * @throws IOException
	 */
	public static String send(String url, String requestMethod, Map<String, String> requestHeaders, String bodyData) throws IOException{
		LOG.debug("[发送请求]请求地址:"+url);
		if (null == url || "".equals(url))
			throw new NullPointerException("请求的地址不能为空!");
		bodyData = (null == bodyData || "".equals(bodyData)) ? "0" : bodyData;
		HttpURLConnection httpURLConnection;
		// 建立链接
		URL gatewayUrl = new URL(url);
		httpURLConnection = (HttpURLConnection) gatewayUrl.openConnection();
		
		// 设置连接属性
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setDoInput(true);
		httpURLConnection.setUseCaches(false);
		requestMethod = isNullStr(requestMethod) ? "POST" : requestMethod;
		httpURLConnection.setRequestMethod(requestMethod);
		// 获得数据字节数据，请求数据流的编码，必须和下面服务器端处理请求流的编码一致
		byte[] requestStringBytes = bodyData.getBytes(ENCODING);
		
		// 设置请求属性
		httpURLConnection.setRequestProperty("Content-length", ""+ requestStringBytes.length);
		if(null == requestHeaders || requestHeaders.size() < 1)
			httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
		else{
			Set<String> entries = requestHeaders.keySet();
			if(entries != null){
				Iterator<String> iterator = entries.iterator( );
				while(iterator.hasNext()){
					String key = iterator.next();
					String value = requestHeaders.get(key);
					httpURLConnection.setRequestProperty(key, value);
				}
			}
		}
		// 建立输出流，并写入数据
		OutputStream outputStream = httpURLConnection.getOutputStream();
		outputStream.write(requestStringBytes);
		outputStream.close();
		 // 获取所有响应头字段
		Map<String, List<String>> map = httpURLConnection.getHeaderFields();
		// 遍历所有的响应头字段
		StringBuffer sb = new StringBuffer();
		for (String key : map.keySet()) {
			sb.append(key+"=>"+map.get(key)+"\r\t");
		} 
		LOG.debug("[发送请求]响应头:"+sb);
		
		// 获得响应状态
		int responseCode = httpURLConnection.getResponseCode();
		StringBuffer responseBuffer = new StringBuffer();
		if (HttpURLConnection.HTTP_OK == responseCode) {
			String readLine;
			BufferedReader responseReader;
			// 处理响应流，必须与服务器响应流输出的编码一致
			responseReader = new BufferedReader(new InputStreamReader(
					httpURLConnection.getInputStream(), ENCODING));
			while ((readLine = responseReader.readLine()) != null) {
				responseBuffer.append(readLine).append("\n");
			}
			responseReader.close();
		}else
			responseBuffer.append(responseCode);
		LOG.debug("[发送请求]响应状态码:"+responseCode +" 响应内容:" + responseBuffer);
		return responseBuffer.toString();
	}

	/**
	 * 向指定URL发送GET方法的请求
	 * @param url 发送请求的URL
	 * @param param 请求参数(如果有中文参数必须转码且转一次仍有乱码,可转2次))，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return URL所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		StringBuffer result = new StringBuffer();
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			/*// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (IOException e) {
			LOG.error("发送GET请求出错! 请求地址:"+url+" 请求参数:"+param, e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				LOG.error("发送GET请求出错! 请求地址:"+url+" 请求参数:"+param, ex);
			}
		}
		return result.toString();
	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url 发送请求的 URL
	 * @param param 请求参数(中文参数可以不转码)，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result.append(line).append("\n");
			}
		} catch (Exception e) {
			LOG.error("发送POST请求出错! 请求地址:"+url+" 请求参数:"+param, e);
		}finally {// 使用finally块来关闭输出流、输入流
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				LOG.error("发送POST请求出错! 请求地址:"+url+" 请求参数:"+param, ex);
			}
		}
		return result.toString();
	}
	
	/**
	 * 验证用户是否已经登陆
	 * 
	 * @param xMobile
	 * @param xToken
	 * @return boolean
	 */
	public static boolean isAuthedByAdmin(String xMobile, String xToken, String url)
	{
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
					new java.security.SecureRandom());
			URL myURL = new URL(url);

			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
			httpsConn.setConnectTimeout(TIMEOUT);
			httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			httpsConn.setSSLSocketFactory(sc.getSocketFactory());
			httpsConn.setRequestProperty("X-User-Mobile", xMobile);
			httpsConn
					.setRequestProperty("X-User-Token", xToken);
			// 取得该连接返回的code,200为通过,401为过期或未通过
			if (httpsConn.getResponseCode() == 200)
			{
				return true;
			}
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage() + " when get url:" + url);
		}
		return false;
	}

	private static class TrustAnyTrustManager implements X509TrustManager
	{
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException
		{
			return;
		}
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException
		{
			return;
		}
		public X509Certificate[] getAcceptedIssuers()
		{
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier
	{
		public boolean verify(String hostname, SSLSession session)
		{
			return true;
		}
	}
}