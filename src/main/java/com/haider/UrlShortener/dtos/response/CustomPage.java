package com.haider.UrlShortener.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Generic paginated response")
public class CustomPage<T> {
    @Schema(
            description = "List of items in current page"
    )
    private List<T> content;

    @Schema(
            description = "Current page number (0-based index)",
            example = "0"
    )
    private int page;

    @Schema(
            description = "Number of items per page",
            example = "10"
    )
    private int size;

    @Schema(
            description = "Indicates if there is a next page available",
            example = "true"
    )
    private boolean hasNext;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

}
