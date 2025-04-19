package com.swift.manager.dto.response;

import java.util.List;

public class CountrySwiftCodesResponse {
    private String countryISO2;
    private String countryName;
    private List<SwiftCodeBranchDto> swiftCodes;
    public String getCountryISO2() { return countryISO2; }
    public void setCountryISO2(String countryISO2) { this.countryISO2 = countryISO2; }
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    public List<SwiftCodeBranchDto> getSwiftCodes() { return swiftCodes; }
    public void setSwiftCodes(List<SwiftCodeBranchDto> swiftCodes) { this.swiftCodes = swiftCodes; }
}
