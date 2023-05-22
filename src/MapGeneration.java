import java.util.ArrayList;
import java.util.Map;

public class MapGeneration {
    Node[][] map = new Node[15][7];
    public MapGeneration(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = new Node(EventType.EMPTY);
                map[i][j].placement = "["+i+","+j+"]";
            }
        }
    }
    public void generateEmptyWeb(){
        int fI = (int) (Math.random()*7);
        int sI = (int) (Math.random()*7);
        while(sI==fI){
            sI = (int) (Math.random()*7);
        }
        generateLine(fI);
        generateLine(sI);
        for (int i = 0; i < 4; i++) {
            generateLine((int) (Math.random()*7));
        }

    }
    public void generateLine(int startIndex){
        for (int i = 14; i > 0; i--) {
            map[i][startIndex].eventType = EventType.TEMP;
            int c = (int) (Math.random()*3);
            if(c==0){ //decides where to go next //left
                if(!map[i][startIndex].connections.contains(map[i-1][Math.abs(startIndex-1)]))
                map[i][startIndex].connections.add(map[i-1][Math.abs(startIndex-1)]);
                startIndex = Math.abs(startIndex-1);//even if start index is 0, the path moves to the right
            }
            else if(c==1){//middle
                if(!map[i][startIndex].connections.contains(map[i-1][startIndex]))
                map[i][startIndex].connections.add(map[i-1][startIndex]);

            }
            else{//right
                if(startIndex==6){
                    if(!map[i][startIndex].connections.contains(map[i-1][startIndex]))
                    map[i][startIndex].connections.add(map[i-1][startIndex]);//if all the way right, stay straight
                }
                else{
                    if(!map[i][startIndex].connections.contains(map[i-1][startIndex+1]))
                    map[i][startIndex].connections.add(map[i-1][startIndex+1]);
                    startIndex++;
                }
            }
        }
    }
    public void fillEvents(){
        for (int i = 0; i < 7; i++) { //rest sites at the top
            map[0][i].eventType = EventType.REST;
        }
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 7; j++) {
                if(map[i][j].eventType==EventType.TEMP){
                    int c = (int) (Math.random()*100);
                    if(i>9){
                        c = (int) (Math.random()*72) + 28;
                    }
                    if(c>=55){
                        map[i][j].eventType = EventType.ENEMY;
                    }
                    else if(c>=33){
                        map[i][j].eventType = EventType.QUESTION;
                    }
                    else if(c>=28){
                        map[i][j].eventType = EventType.SHOP;
                    }
                    else if(c>=16&&i!=1){
                        map[i][j].eventType = EventType.REST;
                    }
                    else{
                        map[i][j].eventType = EventType.ELITE;
                    }
                }
            }
        }
        for (int i = 0; i < 7; i++) { //rest sites at the top
            if(map[14][i].eventType==EventType.TEMP)
            map[14][i].eventType = EventType.ENEMY;
        }
        for (int i = 0; i < 7; i++) { //rest sites at the top
            if(map[6][i].eventType!=EventType.EMPTY)
                map[6][i].eventType = EventType.CHEST;
        }


    }
    public void printMap(){
        for (int i = 0; i<map.length; i++) {
            System.out.print("ROW " + i + ": ");
            if(i<10){
                System.out.print(" ");
            }
            for (int j = 0; j<map[0].length; j++) {
                System.out.print("("+map[i][j].eventType+ ", C: " + map[i][j].listConnections()+")|");
                if(map[i][j].connections.size()!=3){
                    if(map[i][j].connections.size()==1){
                        System.out.print("            ");
                    }
                    else if(map[i][j].connections.size()==0){
                        System.out.print("                 ");
                    }
                    else if(map[i][j].connections.size()==2){
                        System.out.print("      ");
                    }
                }  //just to make the grid even
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MapGeneration m = new MapGeneration();
        m.generateEmptyWeb();
        m.fillEvents();
        m.printMap();

    }
}
class Node{
    EventType eventType;
    String placement;
    ArrayList<Node> connections = new ArrayList<>();
    public Node(EventType e){
        eventType = e;
    }
    public String listConnections(){
        String toReturn = "";
        for (Node n: connections) {
            toReturn+= n.placement + " ";
        }
        return toReturn;
    }
}
enum EventType{
    EMPTY,
    TEMP,
    ENEMY,
    REST,
    ELITE,
    SHOP,
    CHEST,
    QUESTION,
    CARDMASTER;
}

