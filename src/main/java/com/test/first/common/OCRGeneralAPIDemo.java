package com.test.first.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OCRGeneralAPIDemo {
	
	private static JSONParser jsonParser = new JSONParser();
	
	public static void main(String[] args) {
		String apiURL = "https://y7z4eszcep.apigw.ntruss.com/custom/v1/18346/a2215e04d8bc8cb0330cb6fdc4fa11925083e70777663b26c9593ae3421af5e7/general";
		String secretKey = "dEJTTHBDdXZXenlSVG1kQWxKeHZlb3RDT1hyVVZQWng=";
		String imageFile = "C:\\python_workspace\\testcv\\namecard\\rec\\3.jpg";
		Map<String, Object> response = new HashMap<>();
		
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.add(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(imageFile);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int sbCode = con.getResponseCode();
			BufferedReader br;
			if (sbCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer sb = new StringBuffer();
			
			
			
			
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();

			System.out.println(sb);
			
			Object obj = null;
			try {
				obj = jsonParser.parse(sb.toString());
			}
			 catch (Exception e) {
				e.printStackTrace();
			}
			
			JSONObject data = (JSONObject) obj;
			
			System.out.println(data);
			//System.out.println(data.get("inferText"));
			
			
			JSONArray imgfiled = (JSONArray) data.get("images");
			System.out.println("imgfiled"+imgfiled);
			JSONObject js = (JSONObject) imgfiled.get(0);
			System.out.println(js);
			JSONArray jarr = (JSONArray) js.get("fields");
			System.out.println("jarr");
			for(Object o : jarr) {
				JSONObject item = (JSONObject) o;
				System.out.println(item.get("inferText"));
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary) throws
	IOException {
	StringBuilder sb = new StringBuilder();
	sb.append("--").append(boundary).append("\r\n");
	sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
	sb.append(jsonMessage);
	sb.append("\r\n");

	out.write(sb.toString().getBytes("UTF-8"));
	out.flush();

	if (file != null && file.isFile()) {
		out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
		StringBuilder fileString = new StringBuilder();
		fileString
			.append("Content-Disposition:form-data; name=\"file\"; filename=");
		fileString.append("\"" + file.getName() + "\"\r\n");
		fileString.append("Content-Type: application/octet-stream\r\n\r\n");
		out.write(fileString.toString().getBytes("UTF-8"));
		out.flush();

		try (FileInputStream fis = new FileInputStream(file)) {
			byte[] buffer = new byte[8192];
			int count;
			while ((count = fis.read(buffer)) != -1) {
				out.write(buffer, 0, count);
			}
			out.write("\r\n".getBytes());
		}

		out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
	}
	out.flush();
}
}
