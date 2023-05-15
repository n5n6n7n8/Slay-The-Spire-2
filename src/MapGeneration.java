public class MapGeneration {



    public static void main(String[] args) {
        EventType[][] map = new EventType[15][6];
        for (int i = 0; i<map.length; i++) {
            for (int j = 0; j<map[0].length; j++) {
                System.out.print("("+map[i][j]+")");
            }
            System.out.println();
        }
    }
}
enum EventType{
    ENEMY,
    REST,
    ELITE,
    SHOP,
    CHEST,
    QUESTION,
    CARDMASTER;
}

