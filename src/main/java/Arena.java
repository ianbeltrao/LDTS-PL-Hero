import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;

    private final Hero hero;

    private List<Wall> walls;

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }

    private List<Coin> coins;

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1,
                    random.nextInt(height - 2) + 1));
        return coins;
    }

    public Arena(int x, int y) {
        width = x;
        height = y;
        hero = new Hero(x/2, y/2);
        this.walls = createWalls();
        this.coins = createCoins();
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() < 0) return false;
        if (position.getY() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() > height - 1) return false;

        for (Wall wall : walls)
            if (wall.getPosition().equals(position)) return false;

        return true;
    }

    public void retrieveCoins(Position position){
        for(int i = 0; i < coins.size() ; i++){
            if(coins.get(i).position.equals(position)){
                coins.remove(i);
            }
        }
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            retrieveCoins(position);
            hero.setPosition(position);
        }
    }

    public void processKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.ArrowUp)     moveHero(hero.moveUp());
        if(key.getKeyType() == KeyType.ArrowDown)   moveHero(hero.moveDown());
        if(key.getKeyType() == KeyType.ArrowLeft)   moveHero(hero.moveLeft());
        if(key.getKeyType() == KeyType.ArrowRight)  moveHero(hero.moveRight());
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#000099"));
        graphics.fillRectangle(new TerminalPosition(0,0),new TerminalSize(width, height),' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}


