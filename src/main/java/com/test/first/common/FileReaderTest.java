package com.test.first.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileReaderTest {
	private static JSONParser jsonParser = new JSONParser();

	/*
	 * public static void main(String[] args) { try (BufferedReader reader = new
	 * BufferedReader( new
	 * FileReader("C:\\python_workspace\\testcv\\namecard\\rec\\receiptOCR파스타.txt"))
	 * ;) { String bs = ""; String str; ArrayList<String> keys = new
	 * ArrayList<String>();
	 * 
	 * while ((str = reader.readLine()) != null) { bs += str; } //
	 * System.out.println(bs);
	 * 
	 * JSONObject o = (JSONObject) jsonParser.parse(bs); // System.out.println(o);
	 * 
	 * JSONArray imgfiled = (JSONArray) o.get("images"); JSONObject js =
	 * (JSONObject) imgfiled.get(0); JSONObject rjs = (JSONObject) ((JSONObject)
	 * js.get("receipt")).get("result");
	 * 
	 * System.out.println(rjs); Iterator iter = rjs.keySet().iterator(); while
	 * (iter.hasNext()) { str = (String) iter.next(); System.out.println(str);
	 * keys.add(str); } System.out.println(keys.size());
	 * System.out.println("이 영수증의 키값 개수 : " + keys); // 최대 4가지 키값 추출
	 * System.out.println("키값이 4개가 아닐경우 정보가 부족할 수 있습니다."); // rjs.containsKey(keys)
	 * 키값이 있는지 확인 가능 // totalPrice 에서 가격추출하기 if (rjs.containsKey("totalPrice")) { //
	 * totalPrice 키가 있다면 Set<Map.Entry<String, Object>> element = ((JSONObject)
	 * ((JSONObject) rjs.get("totalPrice")) .get("price")).entrySet(); //
	 * System.out.println(element); System.out.println(element.size()); // 키값개수 for
	 * (Map.Entry<String, Object> entry : element) { if (entry.getValue() instanceof
	 * String) { // text(총가격) 인경우 // if (entry.getKey().equals("text"))
	 * System.out.println("가격 : " + entry.getValue().toString()); } else if
	 * (entry.getValue() instanceof JSONObject) { // formatted 포맷된 총가격 인경우
	 * System.out.println(entry.getKey()); System.out.println("포맷된 가격 : " +
	 * ((JSONObject) entry.getValue()).get("value")); } } }
	 * 
	 * if (rjs.containsKey("storeInfo")) { // storeInfo 키가 있다면 Set<Map.Entry<String,
	 * Object>> element = ((JSONObject) rjs.get("storeInfo")).entrySet();
	 * System.out.println(element.size()); for (Map.Entry<String, Object> entry :
	 * element) { if (entry.getValue() instanceof JSONObject) { // "subName",
	 * "name", "bizNum" if (entry.getKey().equals("name")) { String name =
	 * ((JSONObject) entry.getValue()).get("text").toString();
	 * System.out.println("지점명 : " + name); } else if
	 * (entry.getKey().equals("bizNum")) { String bizNum = ((JSONObject)
	 * entry.getValue()).get("text").toString(); System.out.println("사업자번호 : " +
	 * bizNum); } }
	 * 
	 * } }
	 * 
	 * if (rjs.containsKey("subResults")) { // subResults 키가 있다면 JSONArray jarr =
	 * (JSONArray) rjs.get("subResults"); // 애만 JSONArray임
	 * System.out.println("상품목록 갯수 : " + jarr.size()); for (int i = 0; i <
	 * jarr.size(); i++) { JSONObject jo = (JSONObject) jarr.get(i); JSONObject jo2
	 * = (JSONObject) ((JSONArray) jo.get("items")).get(0); // 하나씩 //
	 * System.out.println(jo2); // 상품 정보 확인 JSONObject jo3 = (JSONObject)
	 * jo2.get("price"); // System.out.println(jo3); // 한개당가격 없을수도 있음 삭제해야할듯 //
	 * String onePrice= //
	 * ((JSONObject)((JSONObject)jo3.get("unitPrice")).get("formatted")).get("value"
	 * ).toString(); // 합계가격 String subPrice = ((JSONObject) ((JSONObject)
	 * jo3.get("price")).get("formatted")).get("value") .toString(); // 상품이름 String
	 * name = ((JSONObject) ((JSONObject)
	 * jo2.get("name")).get("formatted")).get("value") .toString(); // 개수 String
	 * count = ((JSONObject) ((JSONObject)
	 * jo2.get("count")).get("formatted")).get("value") .toString(); //
	 * System.out.println(name+" 은 한개당 " + onePrice + "원 입니다.");
	 * System.out.println("상품명 : " + name); System.out.println("개수 : " + count); //
	 * System.out.println("개당 가격 : " + onePrice); System.out.println("총 가격 : " +
	 * subPrice); }
	 * 
	 * } // paymentInfo 에서 날짜, 시간, 카드정보, 승인번호 추출 if (rjs.containsKey("paymentInfo"))
	 * { // paymentInfo 키가 있다면 Set<Map.Entry<String, Object>> element =
	 * ((JSONObject) rjs.get("paymentInfo")).entrySet();
	 * System.out.println(element.size()); // 개수 확인 data, cardInfo, confirmNum,
	 * time시간, for (Map.Entry<String, Object> entry : element) { switch
	 * (entry.getKey()) { case "date": String fDate = ((JSONObject)
	 * entry.getValue()).get("formatted").toString(); String tDate = ((JSONObject)
	 * entry.getValue()).get("text").toString(); System.out.println("날짜");
	 * System.out.println("formatted : " + fDate); System.out.println("text : " +
	 * tDate); break;
	 * 
	 * case "time": String fTime = ((JSONObject)
	 * entry.getValue()).get("formatted").toString(); String tTime = ((JSONObject)
	 * entry.getValue()).get("text").toString(); System.out.println("시간");
	 * System.out.println("formatted : " + fTime); System.out.println("text : " +
	 * tTime); break;
	 * 
	 * case "cardInfo": String fCard = ((JSONObject) ((JSONObject)
	 * entry.getValue()).get("number")).get("formatted") .toString(); String tCard =
	 * ((JSONObject) ((JSONObject) entry.getValue()).get("number")).get("text")
	 * .toString(); System.out.println("카드번호"); System.out.println("formatted : " +
	 * fCard); System.out.println("text : " + tCard); String fCom = ((JSONObject)
	 * ((JSONObject) entry.getValue()).get("company")).get("formatted") .toString();
	 * String tCom = ((JSONObject) ((JSONObject)
	 * entry.getValue()).get("company")).get("text") .toString();
	 * System.out.println("카드사"); System.out.println("formatted : " + fCom);
	 * System.out.println("text : " + tCom); break;
	 * 
	 * case "confirmNum": // String fCnum =
	 * ((JSONObject)entry.getValue()).get("formatted").toString(); String tCnum =
	 * ((JSONObject) entry.getValue()).get("text").toString();
	 * System.out.println("카드 승인 번호"); System.out.println(tCnum); break;
	 * 
	 * default: System.out.println(entry.getKey() + "새로운 키값 발견"); return; }
	 * 
	 * } }
	 * 
	 * } catch (Exception e) { System.out.println("영수증 데이터 정리중 오류발생!");
	 * e.printStackTrace(); }
	 * 
	 * }
	 */

	public JSONObject printInfo(JSONObject jobj) {
		String bs = "";
		String str;
		ArrayList<String> keys = new ArrayList<String>();
		// 전송용 json 객체 준비
		JSONObject sendJson = new JSONObject();
		// list 를 옮길 json 배열 객체 준비
		//JSONArray sjarr = new JSONArray();  // 필요없는듯

		JSONObject o = jobj;
		// System.out.println(o);

		JSONArray imgfiled = (JSONArray) o.get("images");
		JSONObject js = (JSONObject) imgfiled.get(0);
		JSONObject rjs = (JSONObject) ((JSONObject) js.get("receipt")).get("result");

		System.out.println(rjs);
		Iterator iter = rjs.keySet().iterator();
		while (iter.hasNext()) {
			str = (String) iter.next();
			System.out.println(str);
			keys.add(str);
		}
		System.out.println(keys.size());
		System.out.println("이 영수증의 키값 개수 : " + keys); // 최대 4가지 키값 추출
		System.out.println("키값이 4개가 아닐경우 정보가 부족할 수 있습니다.");
		// rjs.containsKey(keys) 키값이 있는지 확인 가능
		// totalPrice 에서 가격추출하기
		if (rjs.containsKey("totalPrice")) { // totalPrice 키가 있다면
			Set<Map.Entry<String, Object>> element = ((JSONObject) ((JSONObject) rjs.get("totalPrice")).get("price")).entrySet();
			// totalPrice 
			
			
			// System.out.println(element);
			System.out.println(element.size()); // 키값개수
			for (Map.Entry<String, Object> entry : element) {
				if (entry.getValue() instanceof String) {
					// text(총가격) 인경우
					// if (entry.getKey().equals("text"))
					//System.out.println(entry.getValue().getClass().getName());
					String tTotalPrice = (String) entry.getValue();
					sendJson.put("tTotalPrice", tTotalPrice);
					
					System.out.println("가격 : " + tTotalPrice);
				} else if (entry.getValue() instanceof JSONObject) {
					// formatted 포맷된 총가격 인경우
					System.out.println(entry.getKey());
					String fTotalPrice = (String) ((JSONObject) entry.getValue()).get("value");
					sendJson.put("fTotalPrice", fTotalPrice);
					System.out.println("포맷된 가격 : " + fTotalPrice);
				}
			}
		}

		if (rjs.containsKey("storeInfo")) { // storeInfo 키가 있다면
			Set<Map.Entry<String, Object>> element = ((JSONObject) rjs.get("storeInfo")).entrySet();
			System.out.println(element.size());
			for (Map.Entry<String, Object> entry : element) {
				if (entry.getValue() instanceof JSONObject) {
					// "subName", "name", "bizNum"
					if (entry.getKey().equals("name")) {
						String name = ((JSONObject) entry.getValue()).get("text").toString();
						sendJson.put("storeInfo_name", name);
						System.out.println("지점명 : " + name);
					} else if (entry.getKey().equals("bizNum")) {
						String bizNum = ((JSONObject) entry.getValue()).get("text").toString();
						sendJson.put("storeInfo_bizNum", bizNum);
						System.out.println("사업자번호 : " + bizNum);
					}
				}

			}
		}
		JSONArray sAr = new JSONArray(); //subResults항목 전송용 json배열
		if (rjs.containsKey("subResults")) { // subResults 키가 있다면
			JSONArray jarr = (JSONArray) rjs.get("subResults"); // 애만 JSONArray임
			System.out.println("상품목록 갯수 : " + jarr.size());
			sendJson.put("subResults_size", jarr.size());
			for (int i = 0; i < jarr.size(); i++) {
				JSONObject jo = (JSONObject) jarr.get(i);
				JSONObject jo2 = (JSONObject) ((JSONArray) jo.get("items")).get(0); // 하나씩
				// System.out.println(jo2); // 상품 정보 확인
				JSONObject jo3 = (JSONObject) jo2.get("price");
				// System.out.println(jo3);
				// 한개당가격 없을수도 있음 삭제해야할듯
				// String onePrice=
				// ((JSONObject)((JSONObject)jo3.get("unitPrice")).get("formatted")).get("value").toString();
				
				//각 subReuslt 저장할객체 생성
				JSONObject subjobj = new JSONObject();
				
				// 합계가격
				String subPrice = ((JSONObject) ((JSONObject) jo3.get("price")).get("formatted")).get("value").toString();
				//sendJson.put("subResults_subPrice", subPrice);
				subjobj.put("subResults_subPrice", subPrice);
				// 상품이름
				String name = ((JSONObject) ((JSONObject) jo2.get("name")).get("formatted")).get("value").toString();
				//sendJson.put("subResults_name", name);
				subjobj.put("subResults_name", name);
				// 개수
				String count = ((JSONObject) ((JSONObject) jo2.get("count")).get("formatted")).get("value").toString();
				//sendJson.put("subResults_count", count);
				subjobj.put("subResults_count", count);
				// System.out.println(name+" 은 한개당 " + onePrice + "원 입니다.");
				System.out.println("상품명 : " + name);
				System.out.println("개수 : " + count);
				// System.out.println("개당 가격 : " + onePrice);
				System.out.println("총 가격 : " + subPrice);
				sAr.add(subjobj);
			}
			sendJson.put("subResults", sAr);
		}
		// paymentInfo 에서 날짜, 시간, 카드정보, 승인번호 추출
		if (rjs.containsKey("paymentInfo")) { // paymentInfo 키가 있다면
			Set<Map.Entry<String, Object>> element = ((JSONObject) rjs.get("paymentInfo")).entrySet();
			System.out.println(element.size()); // 개수 확인 data, cardInfo, confirmNum, time시간,
			for (Map.Entry<String, Object> entry : element) {
				switch (entry.getKey()) {
				case "date":
					String fDate = ((JSONObject) entry.getValue()).get("formatted").toString();
					String tDate = ((JSONObject) entry.getValue()).get("text").toString();
					System.out.println("날짜");
					System.out.println("formatted : " + fDate);
					System.out.println("text : " + tDate);
					sendJson.put("fDate", fDate);
					sendJson.put("tDate", tDate);
					break;

				case "time":
					String fTime = ((JSONObject) entry.getValue()).get("formatted").toString();
					String tTime = ((JSONObject) entry.getValue()).get("text").toString();
					System.out.println("시간");
					System.out.println("formatted : " + fTime);
					System.out.println("text : " + tTime);
					sendJson.put("fTime", fTime);
					sendJson.put("tTime", tTime);
					break;

				case "cardInfo":
					String fCard = ((JSONObject) ((JSONObject) entry.getValue()).get("number")).get("formatted")
							.toString();
					String tCard = ((JSONObject) ((JSONObject) entry.getValue()).get("number")).get("text").toString();
					System.out.println("카드번호");
					System.out.println("formatted : " + fCard);
					System.out.println("text : " + tCard);
					sendJson.put("fCardnum", fCard);
					sendJson.put("tCardnum", tCard);
					
					String fCom = ((JSONObject) ((JSONObject) entry.getValue()).get("company")).get("formatted")
							.toString();
					String tCom = ((JSONObject) ((JSONObject) entry.getValue()).get("company")).get("text").toString();
					System.out.println("카드사");
					System.out.println("formatted : " + fCom);
					System.out.println("text : " + tCom);
					sendJson.put("fCom", fCom);
					sendJson.put("tCom", tCom);
					break;

				case "confirmNum":
					// String fCnum = ((JSONObject)entry.getValue()).get("formatted").toString();
					String tCnum = ((JSONObject) entry.getValue()).get("text").toString();
					System.out.println("카드 승인 번호");
					System.out.println(tCnum);
					sendJson.put("confirmNum", tCnum);
					
					break;

				default:
					System.out.println(entry.getKey() + "새로운 키값 발견");
					
				}

			}
		}
		return sendJson;
	}

}
