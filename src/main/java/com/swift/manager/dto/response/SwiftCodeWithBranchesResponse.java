package com.swift.manager.dto.response;

public class SwiftCodeWithBranchesResponse {
    private String swiftCode;
    private String address;
    private String bankName;
    private String countryISO2;
    private String countryName;
    private boolean isHeadquarter;
    private java.util.List<SwiftCodeBranchDto> branches;
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
    public boolean isHeadquarter() { return isHeadquarter; }
    public void setHeadquarter(boolean headquarter) { isHeadquarter = headquarter; }
    public java.util.List<SwiftCodeBranchDto> getBranches() { return branches; }
    public void setBranches(java.util.List<SwiftCodeBranchDto> branches) { this.branches = branches; }
}
