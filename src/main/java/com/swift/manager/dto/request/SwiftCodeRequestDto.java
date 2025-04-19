package com.swift.manager.dto.request;

import com.swift.manager.validation.ValidSwiftCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SwiftCodeRequestDto {
    @ValidSwiftCode
    @NotBlank(message = "SWIFT code is required")
    private String swiftCode;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Bank name is required")
    private String bankName;

    @NotBlank(message = "Country ISO2 code is required")
    @Size(min = 2, max = 2, message = "Country ISO2 must be 2 characters")
    private String countryISO2;

    @NotBlank(message = "Country name is required")
    private String countryName;

    @NotNull(message = "Headquarter status is required")
    private Boolean headquarter;

    @NotBlank(message = "Headquarter code is required")
    @Size(min = 8, max = 8, message = "Headquarter code must be 8 characters")
    private String headquarterCode;

    public SwiftCodeRequestDto() {}

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
    public Boolean getHeadquarter() { return headquarter; }
    public void setHeadquarter(Boolean headquarter) { this.headquarter = headquarter; }
    public String getHeadquarterCode() { return headquarterCode; }
    public void setHeadquarterCode(String headquarterCode) { this.headquarterCode = headquarterCode; }
}