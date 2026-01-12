package com.project.irctc.irctc_trains.dto;

public record ErrorResponse(String message, String code, boolean success) {
}
