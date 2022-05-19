package com.util;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import com.BitmapStore;
import com.GOSpecs.GameObjectSpec;

public class JSONReader {

	private static JSONArray objectList = new JSONArray();
	private static JSONArray levelBuilderList = new JSONArray();
	
	private static HashMap<String, PointF> data = new HashMap<String, PointF>();
	private static ArrayList<String> bitmapNames = new ArrayList<>();

	private static JSONReader mOurInstance;

	public static JSONReader getInstance() {
		if (mOurInstance == null) {
			mOurInstance = new JSONReader();
		}
		return mOurInstance;
	}

	@SuppressWarnings("unchecked")
	private JSONReader() {
		JSONParser parser = new JSONParser();

		String path = System.getProperty("user.dir") + "/src/com/GOSpecs/";

		try (FileReader reader = new FileReader(path + "GameObjectSpecs.json")) {
			Object object = parser.parse(reader);
			objectList = (JSONArray) object;
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static GameObjectSpec getObjectSpec(Integer id) {
		return parseGameObject((JSONObject) objectList.get(0), id);
	}

	private static GameObjectSpec parseGameObject(JSONObject object, int id) {
		JSONObject gameObject = (JSONObject) object.get("" + id);

		String tag = (String) gameObject.get("tag");
		String bitmapName = (String) gameObject.get("bitmapName");
		Double speed = (Double) gameObject.get("speed");

		Double width = (Double) gameObject.get("width");
		Double height = (Double) gameObject.get("height");

		PointF size = new PointF(width.floatValue(), height.floatValue());
		long framesOfAnimation = (long) gameObject.get("framesOfAnimation");

		JSONArray array = (JSONArray) gameObject.get("components");
		if (array.size() > 2) {
			String[] components = { (String) array.get(0), (String) array.get(1), (String) array.get(2) };
			return new GameObjectSpec(tag, bitmapName, speed.floatValue(), size, components, (int) framesOfAnimation);
		} else {
			String[] components = { (String) array.get(0), (String) array.get(1) };
			return new GameObjectSpec(tag, bitmapName, speed.floatValue(), size, components, (int) framesOfAnimation);
		}
	}
	
	public static HashMap<String, PointF> getData() {
		for (int i = 0; i < 39; i++) {
			addGameObject((JSONObject) objectList.get(0), i);
		}
		return data;
	}
	
	private  static void addGameObject(JSONObject object, int id) {
		JSONObject gameObject = (JSONObject) object.get("" + id);

		String bitmapName = (String) gameObject.get("bitmapName");

		Double width = (Double) gameObject.get("width");
		Double height = (Double) gameObject.get("height");

		PointF size = new PointF(width.floatValue(), height.floatValue());
		data.put(bitmapName, size);
		bitmapNames.add(bitmapName);
	}
	
	public static ArrayList<String> getBitmapNames() {
		return bitmapNames;
	}
}