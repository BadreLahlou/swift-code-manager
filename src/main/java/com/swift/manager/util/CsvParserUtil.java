package com.swift.manager.util;

import com.swift.manager.entity.SwiftCode;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                String[] data = line.split(",");
                SwiftCode code = new SwiftCode();
                code.setSwiftCode(data[0]);
                code.setAddress(data[1]);
                code.setBankName(data[2]);
                code.setCountryISO2(data[3]);
                code.setCountryName(data[4]);
                code.setHeadquarter(Boolean.parseBoolean(data[5]));
                code.setHeadquarterCode(data[6]);
                swiftCodes.add(code);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to parse CSV file: " + fileName, e);
        }
        return swiftCodes;
    }
}