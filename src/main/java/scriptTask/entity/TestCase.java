package scriptTask.entity;

import java.util.List;

public class TestCase {
    private int startKeyCount;
    private int boxesCount;
    private List<Integer> startKeys;
    private List<Box> boxesList;

    public TestCase() {
    }

    public TestCase(int startKeyCount, int boxesCount, List<Integer> startKeys, List<Box> boxesList) {
        this.startKeyCount = startKeyCount;
        this.boxesCount = boxesCount;
        this.startKeys = startKeys;
        this.boxesList = boxesList;
    }

    public int getStartKeyCount() {
        return startKeyCount;
    }

    public void setStartKeyCount(int startKeyCount) {
        this.startKeyCount = startKeyCount;
    }

    public int getBoxesCount() {
        return boxesCount;
    }

    public void setBoxesCount(int boxesCount) {
        this.boxesCount = boxesCount;
    }

    public List<Integer> getStartKeys() {
        return startKeys;
    }

    public void setStartKeys(List<Integer> startKeys) {
        this.startKeys = startKeys;
    }

    public List<Box> getBoxesList() {
        return boxesList;
    }

    public void setBoxesList(List<Box> boxesList) {
        this.boxesList = boxesList;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "startKeyCount=" + startKeyCount +
                ", boxesCount=" + boxesCount +
                ", startKeys=" + startKeys +
                ", boxesList=" + boxesList +
                '}';
    }
}
