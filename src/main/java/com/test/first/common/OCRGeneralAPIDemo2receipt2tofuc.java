package com.test.first.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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

public class OCRGeneralAPIDemo2receipt2tofuc {

	private static JSONParser jsonParser = new JSONParser();

	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary)
			throws IOException {
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
			fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
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

	public JSONObject mainMethod(File imgfile) {
		String apiURL = "https://evf6weqvcx.apigw.ntruss.com/custom/v1/18345/20cdbec9bb98ca069f612b81d9088acbeadb89cab2024c0a6f3936b2d9b893b8/document/receipt";
		String secretKey = "V0xJSWVaZmR2VEJrZ0lSSUxTVWRESE1XZWhMTG92Z0k=";
//		String imageFile = "C:\\python_workspace\\testcv\\namecard\\rec\\고피.jpg";
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
			File file = imgfile;
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
			} catch (Exception e) {
				e.printStackTrace();
			}

			JSONObject data = (JSONObject) obj;

			System.out.println(data);

			// 텍스트 파일저장
			try (FileWriter fw = new FileWriter("receiptOCR_ajaxText.txt");
					FileWriter fw2 = new FileWriter("C:\\python_workspace\\testcv\\namecard\\rec\\receiptOCR_ajaxText.txt");) {
				fw.write(data.toString());
				fw2.write(data.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// 끝 ----------
			System.out.println("정상작동 완료 data리턴");
			return data;
			// System.out.println(data.get("inferText"));

//			JSONArray imgfiled = (JSONArray) data.get("images");
//			System.out.println("imgfiled" + imgfiled);
//			JSONObject js = (JSONObject) imgfiled.get(0);
//			System.out.println(js);
//			JSONObject rjs = (JSONObject) js.get("receipt");
//
//			JSONObject storeInfo = (JSONObject) rjs.get("storeInfo");
//			JSONObject totalPrice = (JSONObject) rjs.get("totalPrice");
//			JSONObject paymentInfo = (JSONObject) rjs.get("paymentInfo");
//			JSONArray subResults = (JSONArray) rjs.get("subResults");
//			System.out.println("jarr");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("에러발생 null리턴");
		return null;

	}
}
