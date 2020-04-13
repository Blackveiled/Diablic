package com.Blackveiled.Diablic.Commands.Utils;

/**
 * Class: PageSorter
 * Author: Blackveiled (Adam Canfield)
 */

public class PageSorter {

    /**
     * The page sorter is a useful tool for sorting objects into collectible arrays, to be used to display pages, and values for those fields.
     *
     */

    // Sortables
    private Object[] objectValues;

    // Vars
    private int pageNum = 1;
    private int objectsPerPage = 7; // Default val: 7
    private int totalPages;
    private int resultsFound = 0;

    /**
     * Object[] Sorter
     *
     * Creates a page sorter for an array of objects (can be a list of Warps, Players, or anything else), and sorts them into pages.
     *
     * These pages can be returned individually, the array length of the objects returned are equal to the Objects Per Page declared
     * in the constructor of the PageSorter.  This objects can be casted back into their original class.  Please note; the PageSorter
     * should not be used for instances of multiple classes, only a single class type.
     *
     * Note: The constructor actually does nothing besides create the class.  You must use the PageSorter.sort() method to execute the
     * sorting for the page.  You can also sort for additional pages by using different sort methods.
     *
     * @param values
     * @param pageNumber
     * @param objectsPerPage
     */
    public PageSorter(Object[] values, int pageNumber, int objectsPerPage)    {
        this.objectValues = values;
        this.pageNum = pageNumber;
        this.objectsPerPage = objectsPerPage;
        // Ensure values are greater than 0, otherwise the class will break.
        if(pageNumber <= 0) this.pageNum = 1;
        if(objectsPerPage <= 0) this.objectsPerPage = 1;

        setTotalPages();
    }

    public PageSorter(Object[] values)  {
        this.objectValues = values;
        setTotalPages();
    }

    /**
     * This function will sort the objects and return an array of objects for the provided page.  These objects must be converted back into
     * their original forms to be utilized.  You can re-use this method after using the setPage(int Page) function without recreating the
     * PageSorter class as well.
     *
     * @return Object[] (An array of any object)
     */
    public Object[] sort() {
        int pageCount = (pageNum * objectsPerPage) - objectsPerPage;
        int pageFinal = pageCount + objectsPerPage;
        if(pageFinal > objectValues.length)  pageFinal = objectValues.length;
        resultsFound = pageFinal - pageCount;
        int counter = 0;

        Object[] output = new Object[resultsFound];

        for(int p = pageCount; p < pageFinal; p++)  {
            output[counter] = objectValues[p];
            counter++;
        }
        if(output == null) { throw new NullPointerException("PageSorter objects returned are null!"); }
        return output;
    }

    /**
     * This method will sort the objects and return an array of Objects for the provided page number.  The default amount of objects per page
     * is 7.  You can change this by using the sort(PageNumber, ObjectsPerPage) method.  You can also set it by using a different constructor.
     * @param PageNumber
     * @return
     */
    public Object[] sort(int PageNumber) {
        this.setPageNumber(PageNumber);

        int pageCount = (pageNum * objectsPerPage) - objectsPerPage;
        int pageFinal = pageCount + objectsPerPage;
        if(pageFinal > objectValues.length)  pageFinal = objectValues.length;
        resultsFound = pageFinal - pageCount;
        int counter = 0;

        Object[] output = new Object[resultsFound];

        for(int p = pageCount; p < pageFinal; p++)  {
            output[counter] = objectValues[p];
            counter++;
        }
        if(output == null) { throw new NullPointerException("PageSorter objects returned are null!"); }
        return output;
    }

    public Object[] sort(int PageNumber, int ObjectsPerPage) {
        this.setPageNumber(PageNumber);
        this.setObjectsPerPage(ObjectsPerPage);

        int pageCount = (pageNum * objectsPerPage) - objectsPerPage;
        int pageFinal = pageCount + objectsPerPage;
        if(pageFinal > objectValues.length)  pageFinal = objectValues.length;
        resultsFound = pageFinal - pageCount;
        int counter = 0;

        Object[] output = new Object[resultsFound];

        for(int p = pageCount; p < pageFinal; p++)  {
            output[counter] = objectValues[p];
            counter++;
        }
        if(output == null) { throw new NullPointerException("PageSorter objects returned are null!"); }
        return output;
    }


    /**
     * Get the index for the last page in this page sorter.
     * @return (int) Value of the last page index.
     */
    public int getTotalPages()    {
        return this.totalPages;
    }


    /**
     * Get the current page the sorter is on.
     * @return
     */
    public int getPageNumber()  {
        return this.pageNum;
    }

    /**
     * Get the total amount of entries (the length of the Objects[] array).
     * @return
     */
    public int getTotalEntries()    {
        return this.objectValues.length;
    }

    private void setPageNumber(int i)   {
        this.pageNum = i;
        if(i <= 0) this.pageNum = 1;
    }

    private void setObjectsPerPage(int i)   {
        this.objectsPerPage = i;
        if(i <= 0) this.objectsPerPage = 1;
        setTotalPages();
    }

    /**
     * Returns the amount of results found after using the sort() method.  This can be useful if you wish to display the amount of results found on a page. (Found: X results).
     * @return
     */
    public int getResultsFound()    {
        return this.resultsFound;
    }

    private void setTotalPages()    {
        double totalPagesD = (this.objectValues.length / objectsPerPage) + .5;
        this.totalPages = (int) Math.round(totalPagesD);
    }

}
