package interfaces;

import entities.BookStock;

public interface IBookAvailableStrategy {
    boolean isBookAvailableToBorrow(BookStock book);
}
