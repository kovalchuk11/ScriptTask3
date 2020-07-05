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


        int index = 0;
        int loopCount = 0;
        while (!isResolved) {
            Box box = boxes.get(index);
            if (box.isClosed()) {
                Integer boxKeyType = box.getBoxType();

                if (keys.contains(boxKeyType)) {

                    if (!isLastKey(keys, boxKeyType)) {

                        index = updateData(keys, boxes, boxKeyType, index);

                    } else if (box.getBoxKeys().contains(boxKeyType)) {

                        index = updateData(keys, boxes, boxKeyType, index);

                    } else if (isLastBoxType(boxes, boxKeyType)) {
                        index = updateData(keys, boxes, boxKeyType, index);

                    } else if (isClosedBoxesContainsKeySameType(boxes, keys, boxKeyType)) {

                        if (keys.size() > 1) {
                            index = updateData(keys, boxes, boxKeyType, index);

                        } else {
                            index++;
                        }
                    }

                } else {
                    if (index < boxes.size() - 1) {
                        index++;
                    } else {
                        index = 0;
                    }
                }

            } else {
                if (index < boxes.size() - 1) {
                    index++;
                } else {
                    index = 0;
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

    private boolean isClosedBoxesContainsKeySameType(List<Box> boxes, List<Integer> keys, int boxTypeKey) {

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

    private boolean isLastBoxType(List<Box> boxes, int key) {
        int boxTypeCount = 0;
        for (Box box : boxes) {

            if (box.isClosed() && box.getBoxType() == key) {
                boxTypeCount++;
            }
        }
        return boxTypeCount <= 1;
    }

    private boolean isLastKey(List<Integer> keys, int key) {
        int keysCount = 0;
        for (int keyInList : keys) {
            if (keyInList == key) {
                keysCount++;
            }
        }
        return keysCount <= 1;
    }

}
