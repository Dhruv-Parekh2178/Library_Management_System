package com.LMS.library.model;

public record ApiResponse<T>(boolean status , String message , T data) {
}