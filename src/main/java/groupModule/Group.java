package groupModule;

public class Group {

    private String groupNumber;
    private String avhMark;

    public Group(String groupNumber, String avhMark) {
        this.groupNumber = groupNumber;
        this.avhMark = avhMark;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }
    public String getID() {
        return getGroupNumber();
    }
    public void setID(String id)
    {
        this.groupNumber = id;
    }

    public String getAvhMark() {
        return avhMark;
    }

    public void setAvhMark(String avhMark) {
        this.avhMark = avhMark;
    }
}
