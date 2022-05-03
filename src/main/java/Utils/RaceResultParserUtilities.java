package Utils;

import CustomClasses.RaceEvent;
import CustomClasses.Rower;
import CustomClasses.Team;
import MainPackage.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class RaceResultParserUtilities {

    public static String newFormattedLine(String input){ // formats txt output from ergrace
        StringBuilder sb = new StringBuilder();
        String[] arr = input.split("\\,");
        int[] indices = {0,3,1,4};
        for (int i = 0; i < 4; i++) {
            sb.append(arr[indices[i]].trim()).append("   ");
            //System.out.println(arr[indices[i]]);
        }
        return sb.append("\n").toString();
    }

    private static double getSecondsFromString(String line){
        double seconds = Double.parseDouble(line.split(":")[0])*60.0
                + Double.parseDouble(line.split(":")[1]);
        return seconds;
    }

    public static void updateRaces(Collection<Team> teamCollection, String line){
        List<Team> teamList = new LinkedList<>(teamCollection);
        String[] lineElements = line.split("   ");
        String nameRower = lineElements[1].split(" - ")[0];
        String nameTeam = lineElements[1].split(" - ")[1];
        double time = getSecondsFromString(lineElements[2]);
        double avg = getSecondsFromString(lineElements[3]);
        Rower rower = new Rower(nameRower);
        rower.setTime(time);

        boolean teamExists = false;
        Team rowerTeam = new Team("");
        for (var team : teamCollection){
            if (team.getName().equals(nameTeam)){
                teamExists = true;
                rowerTeam = team;
                break;
            }
        }

        if(nameTeam.equals("FBF2")){
            System.out.println("tople noci");
        }

        if (teamExists) {
            rowerTeam.setRower(rower);
        } else {
            rowerTeam = new Team(nameTeam);
            rowerTeam.setRower(rower);
            teamCollection.add(rowerTeam);
        }
        //System.out.println(rowerTeam);

    }

    public static String createFormattedFile(String paths, String name){ //creates excel ready file
        if (paths == null) {return "false";} //should never happen
        Path path = Paths.get(paths);
        Path newFile = Paths.get(Main.finalResPath.toString().concat(name).concat("_f.txt"));
        try {
            FileWriter myWriter = new FileWriter(newFile.toString());
            Files.readAllLines(path).stream().filter(o -> {//lambda that filters actual (usable) data from description
                if( o.split("\\,").length == 7 && !o.startsWith("Place")){
                    return true;
                }
                return false;
            }).forEach(o -> { //lambda that writes all the lines to the created txt file
                try {
                    //System.out.println(newFormattedLine(o));
                    updateRaces(Main.currentTeamList,newFormattedLine(o));
                    myWriter.write(newFormattedLine(o));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            myWriter.close();

            return "C:\\Users\\dobar\\OneDrive\\Radna površina\\Coding\\test_map\\".concat(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "false";

    }
    public static void updateRaceEventFromFile(RaceEvent raceEvent, String pathname) throws IOException {
        Path raceResultFile = Paths.get(pathname);
        List<String> lines = Files.readAllLines(raceResultFile);
        for (var s : lines){
            String[] parsedLine = s.split("   ");
            String[] timeStringArr = parsedLine[2].split(":");
            double time = 0.;
            time += Double.parseDouble(timeStringArr[0]);
            time += Double.parseDouble(timeStringArr[1]);
            String sName = parsedLine[1].split(" - ")[1];
            String rowerName = parsedLine[1].split(" - ")[0];
            for (var x : raceEvent.getTeamList()){
                if(x.getShortName().equals(sName)){
                    x.setTotalTime(x.getTotalTime() + time);
                    for(var rower : x.getRowers()){
                        if (rower.getName().equals(rowerName)){rower.setTime(time);}
                    }

                }
            }
        }
    }
}
