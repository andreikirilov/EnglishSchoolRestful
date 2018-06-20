package englishschoolrestful;

public class Classes {

    private String ID;
    private String name;
    private String date;
    private String topic;
    private String homework;
    private String mark;

    public Classes() {
    }

    public Classes(String ID, String name, String date, String topic, String homework, String mark) {
        this.ID = ID;
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.homework = homework;
        this.mark = mark;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String toString() {
        return "ID: '" + ID + "', name: '" + this.name + "', date: '" + this.date + "', topic: '" + this.topic + "'" + "', homework: '" + this.homework + "'" + "', mark: '" + this.mark + "'";
    }
}