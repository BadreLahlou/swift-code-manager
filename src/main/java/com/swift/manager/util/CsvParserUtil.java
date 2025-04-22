package com.swift.manager.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.swift.manager.entity.SwiftCode;

public class CsvParserUtil {
    private static final Logger LOGGER = Logger.getLogger(CsvParserUtil.class.getName());

    public static List<SwiftCode> parseCsv(String fileName) {
        List<SwiftCode> swiftCodes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        CsvParserUtil.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                // Use a CSV parser to handle quoted fields correctly
                String[] data = line.split(",(?=(?:[^\"']*\"[^\"']*\")*[^\"']*$)");
                SwiftCode code = new SwiftCode();
                code.setSwiftCode(data[0].replaceAll("[\"']", "").trim());
                code.setAddress(data[1].replaceAll("[\"']", "").trim());
                code.setBankName(data[2].replaceAll("[\"']", "").trim());
                String countryISO2 = data[3].replaceAll("[\"']", "").trim();
                System.out.println("DEBUG: countryISO2 value: '" + countryISO2 + "' length: " + countryISO2.length());
                code.setCountryISO2(countryISO2);
                code.setCountryName(data[4].replaceAll("[\"']", "").trim());
                code.setHeadquarter(Boolean.parseBoolean(data[5].replaceAll("[\"']", "").trim()));
                code.setHeadquarterCode(data[6].replaceAll("[\"']", "").trim());
                swiftCodes.add(code);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to parse CSV file: " + fileName, e);
        }
        return swiftCodes;
    }
}