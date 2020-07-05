package scriptTask;

import scriptTask.entity.Box;
import scriptTask.entity.TestCase;

import java.util.ArrayList;
import java.util.List;

public class CaseResolver {
    private StringBuilder stringBuilder = new StringBuilder();
    List<Integer> boxesQueue = new ArrayList<>();

    public String resolveCase(TestCase testCase) {
        int maxLop = testCase.getBoxesCount() * testCase.getBoxesCount();

        List<Integer> keys = new ArrayList(testCase.getStartKeys());
        List<Box> boxes = new ArrayList(testCase.getBoxesList());
        boolean isResolved = false;

        int i = 0;
        int loopCount = 0;
        while (!isResolved) {
            Box box = boxes.get(i);
            if (box.isClosed()) {
                Integer boxKeyType = box.getBoxType();

                if (keys.contains(boxKeyType)) {

                    if (!isLastKey(keys, boxKeyType)) {

                        i = updateData(keys, boxes, boxKeyType, i);

                    } else if (box.getBoxKeys().contains(boxKeyType)) {

                        i = updateData(keys, boxes, boxKeyType, i);

                    } else if (isLastBoxType(boxes, boxKeyType)) {
                        i = updateData(keys, boxes, boxKeyType, i);

                    } else if (isClosedBoxesContainsKeySameType(boxes, keys, boxKeyType)) {

                        if (keys.size() > 1 /*|| box.getBoxKeys().contains(boxKeyType)*/) {
                            i = updateData(keys, boxes, boxKeyType, i);

                        } else {
                            i++;
                        }
                    }

                } else {
                    if (i < boxes.size() - 1) {
                        i++;
                    } else {
                        i = 0;
                    }
                }

            } else {
                if (i < boxes.size() - 1) {
                    i++;
                } else {
                    i = 0;
                }
            }


            if (!isClosedBoxExist(boxes)) {
                isResolved = true;
            }

            if (loopCount > maxLop) {
                isResolved = true;
                stringBuilder = new StringBuilder().append("IMPOSSIBLE");

            }
            loopCount++;
        }


        return stringBuilder.toString();
    }

    private int updateData(List<Integer> keys, List<Box> boxes, Integer boxKeyType, int index) {
        keys.remove(boxKeyType);
        boxes.get(index).setClosed(false);
        keys.addAll(boxes.get(index).getBoxKeys());

        stringBuilder.append(index + 1).append(" ");
        boxesQueue.add(index + 1);
        return 0;
    }

    private boolean isClosedBoxExist(List<Box> boxes) {
        for (Box box : boxes) {
            if (box.isClosed()) {
                return true;
            }
        }
        return false;
    }

    public boolean isClosedBoxesContainsKeySameType(List<Box> boxes, List<Integer> keys, int boxTypeKey) {

        for (int key : keys) {
            for (int i = 0; i < boxes.size(); i++) {
                if (boxes.get(i).isClosed() && boxes.get(i).getBoxType() == key) {
                    if (boxes.get(i).getBoxKeys().contains(boxTypeKey)) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    public boolean isLastBoxType(List<Box> boxes, int key) {
        int boxTypeCount = 0;
        for (Box box : boxes) {

            if (box.isClosed() && box.getBoxType() == key) {
                boxTypeCount++;
            }
        }
        return boxTypeCount <= 1;
    }

    public boolean isLastKey(List<Integer> keys, int key) {
        int keysCount = 0;
        for (int keyInList : keys) {
            if (keyInList == key) {
                keysCount++;
            }
        }
        return keysCount <= 1;
    }

//    public boolean isBoxContainsThatKey(Box box, int key) {
//        return box.getBoxKeys().contains(key);
//
//    }
}
