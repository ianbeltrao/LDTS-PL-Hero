import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    private int height;
    private int width;

    private final Hero hero;

    public Arena(int x, int y) {
        width = x;
        height = y;
        hero = new Hero(x/2, y/2);
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() < 0) return false;
        if (position.getY() < 0) return false;
        if (position.getX() > width - 1) return false;
        if (position.getY() > height - 1) return false;
        return true;
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void processKey(KeyStroke key) {
        if(key.getKeyType() == KeyType.ArrowUp)     moveHero(hero.moveUp());
        if(key.getKeyType() == KeyType.ArrowDown)   moveHero(hero.moveDown());
        if(key.getKeyType() == KeyType.ArrowLeft)   moveHero(hero.moveLeft());
        if(key.getKeyType() == KeyType.ArrowRight)  moveHero(hero.moveRight());
    }

    public void draw(Screen screen) {
        hero.draw(screen);
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