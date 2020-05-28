package enums;

public enum Reports {
    UserReport (1),
    BookReport(2),
    EventReport(3),
    EmployeeReport(4);

    int reportValue;
    Reports(int reportValue){ this.reportValue = reportValue;}

    public int ReportsValue(){ return reportValue;}
}