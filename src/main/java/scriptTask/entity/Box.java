package scriptTask.entity;

import java.util.List;

public class Box {
    private boolean isClosed;
    private int boxType;
    private int keysCount;
    private List<Integer> boxKeys;

    public Box() {
    }

    public Box(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Box(boolean isClosed, int boxType, int keysCount, List<Integer> boxKeys) {
        this.isClosed = isClosed;
        this.boxType = boxType;
        this.keysCount = keysCount;
        this.boxKeys = boxKeys;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public int getBoxType() {
        return boxType;
    }

    public void setBoxType(int boxType) {
        this.boxType = boxType;
    }

    public int getKeysCount() {
        return keysCount;
    }

    public void setKeysCount(int keysCount) {
        this.keysCount = keysCount;
    }

    public List<Integer> getBoxKeys() {
        return boxKeys;
    }

    public void setBoxKeys(List<Integer> boxKeys) {
        this.boxKeys = boxKeys;
    }

    @Override
    public String toString() {
        return "Box{" +
                "isOpened=" + isClosed +
                ", boxType=" + boxType +
                ", keysCount=" + keysCount +
                ", boxKeys=" + boxKeys +
                '}';
    }
}
