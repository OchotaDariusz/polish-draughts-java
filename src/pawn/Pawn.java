public class Pawn {

    private String color;
    private boolean isCrowned;
    private int posX;
    private int posY;
    Coordinates position = new Coordinates(posX, posY);

    public void setPosX(int posX) {
        this.posX = posX;
        position.setX(posX);

    }

    public void setPosY(int posY) {
        this.posY = posY;
        position.setY(posY);
    }
    public static void moveValidation(int posX, int posY){

    }



    public static String getColor() {
        return "1";
    }

}
