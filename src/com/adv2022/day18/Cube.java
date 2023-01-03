package com.adv2022.day18;

public class Cube {
    int x;
    int y;
    int z;

    public Cube(String s) {
        String[] parts = s.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
        this.z = Integer.parseInt(parts[2]);
    }


    public Cube(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getNeighborCount() {
        int count = 0;
        String[] neighborCoords = getNeighborCoords();
        for (String cubeCoord : Day18.allCubes) {
            for (String neighCoord : neighborCoords) {
                if (cubeCoord.equals(neighCoord)) {
                    count++;
                }
            }
        }
        return count;
    }

    String[] getNeighborCoords() {
        String[] retVal = new String[6];
        retVal[0] = (x-1) + "," + y + "," + z;
        retVal[1] = (x+1) + "," + y + "," + z;
        retVal[2] = x + "," + (y-1) + "," + z;
        retVal[3] = x + "," + (y+1) + "," + z;
        retVal[4] = x + "," + y + "," + (z-1);
        retVal[5] = x + "," + y + "," + (z+1);
        return retVal;
    }

    @Override
    public String toString() {
        return x + "," + y + "," + z;
    }

}
