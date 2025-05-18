package com.example.DiseaseTrackingApp;

import java.util.List;

public class PagedResponse<T> {
    private List<T> data;       // the list of items
    private int page;           // current page
    private int pageSize;       // page size
    private int totalRecords;   // total items available
    private int totalPages;     // total pages available

    // getters
    public List<T> getData()         { return data; }
    public int getPage()             { return page; }
    public int getPageSize()         { return pageSize; }
    public int getTotalRecords()     { return totalRecords; }
    public int getTotalPages()       { return totalPages; }
}
