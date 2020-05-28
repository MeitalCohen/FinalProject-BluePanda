package enums;

public enum BookStatus {
    Available(1),
    Borrowed(2);

    int statusValue;
    BookStatus(int statusValue){ this.statusValue = statusValue;}

    public int StatusValue(){ return this.statusValue;}
}
