package com.swift.manager.dto.response;

public class SwiftCodeResponseDto {
    private String swiftCode;
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private boolean headquarter;
    private String headquarterCode;

    public SwiftCodeResponseDto() {}

    public String getSwiftCode() { return swiftCode; }
    public void setSwiftCode(String swiftCode) { this.swiftCode = swiftCode; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getCountryISO2() { return countryISO2; }
    public void setCountryISO2(String countryISO2) { this.countryISO2 = countryISO2; }
    public String getCountryName() { return countryName; }
    public void setCountryName(String countryName) { this.countryName = countryName; }
    public boolean isHeadquarter() { return headquarter; }
    public void setHeadquarter(boolean headquarter) { this.headquarter = headquarter; }
    public String getHeadquarterCode() { return headquarterCode; }
    public void setHeadquarterCode(String headquarterCode) { this.headquarterCode = headquarterCode; }
}