package CustomClasses;


import org.apache.poi.ss.usermodel.Row;

import java.util.*;

import static java.lang.Math.min;
import static java.lang.Math.round;

public class Team implements Comparable<Team>{

    String name;
    List<Rower> rowers;
    Set<Rower> rowerSet;
    String shortName;
    double totalTime = 0.; //in seconds

    public Team (String name, Rower... rowers) {
        this.name = name;

        this.rowers = new LinkedList<>();

        this.rowers.addAll(Arrays.asList(rowers));

        for (var rower: rowers){
            totalTime += rower.getTime();
        }

        rowerSet = new HashSet<Rower>(this.rowers);

    }




    public double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    public Team (String name) {
        this.name = name;

        this.rowers = new LinkedList<>();
    }

    public List<Rower> getRowers() {
        return rowers;
    }

    public void setRower (Rower rower) {

        rowers.add(rower);
        totalTime += rower.getTime();
    }

    public String getName() {
        return name;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public int compareTo(Team o) {
        return (int) Math.round(this.totalTime - o.totalTime);
    }
    public String displayTime(double time){
        int minutes = (int) (time/60.);
        double seconds = time - minutes * 60.;
        StringBuilder sb = new StringBuilder();
        sb.append(minutes);
        sb.append(":"); //14:21.1 for example
        sb.append(String.format("%.2f",seconds));
        return sb.toString();
    }



    @Override
    public String toString() {
        return name + " "
                + displayTime(totalTime) + " "
                + displayTime(totalTime / rowers.size());
    }
}
