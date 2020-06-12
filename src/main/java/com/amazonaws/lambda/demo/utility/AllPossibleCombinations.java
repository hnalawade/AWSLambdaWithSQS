package com.amazonaws.lambda.demo.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.lambda.demo.LambdaFunctionHandler;
import com.google.gson.Gson;

public class AllPossibleCombinations {
	private static final Logger logger = LoggerFactory.getLogger(AllPossibleCombinations.class);

	public static void main(String[] args) {

		String[] inputArray = { "A", "B", "C", "D" };
		AllPossibleCombinations.generatePossibleCombinations(Arrays.asList(inputArray));
	}

	public static String generatePossibleCombinations(List<String> inputList) {

		List<String> finalOutput = new ArrayList<String>();
		List<List<String>> list = new AllPossibleCombinations().getAllCombinations(inputList);

		for (List<String> arr : list) {
			String temp = "";
			for (String s : arr) {
				temp = temp + s;
			}
			finalOutput.add(temp);
		}
		Map map = new HashMap<String, List>();
		map.put("response", finalOutput);
		logger.info("Final Response " + map);

		logger.info(" Json String ************" + map.toString());
		return map.toString();
	}

	public static List<List<String>> getAllCombinations(List<String> elements) {
		List<List<String>> combinationList = new ArrayList<List<String>>();
		for (long i = 1; i < Math.pow(2, elements.size()); i++) {
			List<String> list = new ArrayList<String>();
			for (int j = 0; j < elements.size(); j++) {
				if ((i & (long) Math.pow(2, j)) > 0) {
					list.add(elements.get(j));
				}
			}
			combinationList.add(list);
		}
		logger.info("Combination List : " + combinationList);

		return combinationList;
	}

}
