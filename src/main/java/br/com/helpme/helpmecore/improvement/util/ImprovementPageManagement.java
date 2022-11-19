package br.com.helpme.helpmecore.improvement.util;

import br.com.helpme.helpmecore.improvement.dto.ImprovementDto;
import br.com.helpme.helpmecore.improvement.model.Improvement;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ImprovementPageManagement {

    private int previousPage;
    private int nextPage;
    private int currentPage;
    private int totalPages;

    private List<Integer> pages;

    public ImprovementPageManagement(final Page<Improvement> allPages) {
        this.currentPage = allPages.getNumber();
        this.totalPages = allPages.getTotalPages();
        pages = new ArrayList<>();
        updatePages();
    }

    public ImprovementPageManagement(int size) {
        int initialPage = 0;
        this.currentPage = initialPage;
        this.totalPages = size;
        pages = new ArrayList<>();
        updatePages();
    }

    public void updatePages(){
        updatePreviousPage();
        updateNextPage();
        updatePagesOptions();
    }

    private void updatePagesOptions() {
        pages.add(currentPage);

        if(hasPage(this.currentPage + 1))
            pages.add(nextPage);

        if(hasPage(this.currentPage + 2))
            pages.add(nextPage + 2);
    }

    private void updateNextPage() {
        if(hasPage(this.currentPage + 1))
            this.nextPage = currentPage + 1;
        else
            this.nextPage = totalPages;
    }

    private void updatePreviousPage() {
        if(this.currentPage != 0)
            this.previousPage = this.currentPage - 1;
        else
            this.previousPage = currentPage;
    }

    private boolean hasPage(int pageToCompare){
        return pageToCompare < totalPages;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }
}
