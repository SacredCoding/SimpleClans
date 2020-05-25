package net.sacredlabyrinth.phaed.simpleclans.utils;

/**
 * 
 * @author RoinujNosde
 *
 */
public class Paginator {
	
	private int currentPage;
	private final int sizePerPage;
	private final int totalElements;
	
	public Paginator(int sizePerPage, int totalElements) {
		if (sizePerPage < 1) {
			throw new IllegalArgumentException("sizePerPage cannot be less than 1");
		}
		if (totalElements < 0) {
			throw new IllegalArgumentException("totalElements cannot be less than 0");
		}
		this.sizePerPage = sizePerPage;
		this.totalElements = totalElements;
	}
	
	/**
	 * 
	 * @return the total elements
	 *
	 * @author RoinujNosde
	 */
	public int getTotalElements() {
		return totalElements;
	}
	
	/**
	 * 
	 * @return the size per page
	 *
	 * @author RoinujNosde
	 */
	public int getSizePerPage() {
		return sizePerPage;
	}
	
	/**
	 * 
	 * @return the minimal index based on the current page
	 *
	 * @author RoinujNosde
	 */
	public int getMinIndex() {
		return getCurrentPage() * getSizePerPage();
	}
	
	/**
	 * 
	 * @return the max index based on the current page
	 *
	 * @author RoinujNosde
	 */
	public int getMaxIndex() {
		return (getCurrentPage() + 1) * getSizePerPage();
	}
	
	/**
	 * 
	 * @return the current page, starting at 0
	 *
	 * @author RoinujNosde
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * Increases the page number if there are elements to display
	 *
	 * @author RoinujNosde
	 */
	public boolean nextPage() {
		if ((sizePerPage * (currentPage + 1)) > totalElements) {
			return false;
		}
		currentPage++;
		return true;
	}
	
	/**
	 * Decreases the page number if current > 0
	 *
	 * @author RoinujNosde
	 */
	public boolean previousPage() {
		if (currentPage > 0) {
			currentPage--;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param index the index
	 * @return if this index will not cause any IndexOutOfBoundsException
	 *
	 * @author RoinujNosde
	 */
	public boolean isValidIndex(int index) {
        return index >= getMinIndex() && index < getMaxIndex() && index < getTotalElements();
    }
}
