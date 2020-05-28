package enums;

public enum Category {
    MagazineBook(1),
    SiFiBook(2),
    RomanceBook(3);

    int _categoryType;
    Category(int categoryType){
        _categoryType = categoryType;
    }

    public int CategoryType()
    {
        return _categoryType;
    }
}