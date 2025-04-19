package com.swift.manager.dto.response;

public class SwiftCodeBranchDto {
    private String swiftCode;
    private String address;
    private String bankName;
    private String countryISO2;
    private boolean isHeadquarter;
    public String getSwiftCode() { return swiftCode; }
    public void setSwiftCode(String swiftCode) { this.swiftCode = swiftCode; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }
    public String getCountryISO2() { return countryISO2; }
    public void setCountryISO2(String countryISO2) { this.countryISO2 = countryISO2; }
    public boolean isHeadquarter() { return isHeadquarter; }
    public void setHeadquarter(boolean headquarter) { isHeadquarter = headquarter; }
}
