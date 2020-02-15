package sweeper;

import java.util.Random;
import java.util.zip.CheckedOutputStream;

class Bomb
{
    private Matrix bombMap;
    private int totalBombs;
    Bomb (int totalBombs)
    {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    void start ()
    {
        bombMap = new Matrix(Box.zero);
        for (int j =0; j < totalBombs; j++)
        placeBomb ();
    }
    Box get (Coord coord)
    {
        return bombMap.get(coord);
    }
    private void fixBombsCount ()
    {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs )
            totalBombs = maxBombs;
    }
    private void placeBomb ()
    {
        while (true)
        {
            Coord coord = Ranges.getRandomCoord();
            if (Box.bomb == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.bomb);
            incNumbersArroundBomb(coord);
            break;
        }
    }
    private void incNumbersArroundBomb (Coord coord)
    {
        for (Coord arround : Ranges.getCoordsAround(coord))
            if (Box.bomb != bombMap.get(arround))
            bombMap.set (arround, bombMap.get(arround).getNextNumberBox());
    }

    int getTotalBombs ()
    {
        return totalBombs;
    }

}
