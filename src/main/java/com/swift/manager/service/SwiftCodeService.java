package com.swift.manager.service;

import java.util.List;

import com.swift.manager.dto.request.SwiftCodeRequestDto;
import com.swift.manager.dto.response.CountrySwiftCodesResponse;
import com.swift.manager.dto.response.SwiftCodeResponseDto;

public interface SwiftCodeService {
    SwiftCodeResponseDto getSwiftCodeDetails(String swiftCode);
    CountrySwiftCodesResponse findByCountry(String countryISO2);
    SwiftCodeResponseDto addSwiftCode(SwiftCodeRequestDto request);
    String deleteSwiftCode(String swiftCode);
    List<SwiftCodeResponseDto> findBranches(String headquarterCode);
}