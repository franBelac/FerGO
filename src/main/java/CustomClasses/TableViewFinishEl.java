package CustomClasses;

public class TableViewFinishEl {
    String team;
    String time;
    String average;
    String gap;

    public TableViewFinishEl(String name, String time, String average, String gap) {
        this.team= name;
        this.time = time;
        this.average = average;
        this.gap = gap;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String name) {
        this.team = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getGap() {
        return gap;
    }

    public void setGap(String gap) {
        this.gap = gap;
    }
}
