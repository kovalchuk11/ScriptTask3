package scriptTask;

import scriptTask.entity.Box;
import scriptTask.entity.TestCase;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CaseCreator {

    public List<String> getDataFromFile(String filePath){
        StringBuilder sb = new StringBuilder();
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
                lines.add(line);
            }

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

        return lines;
    }



    public void createCases(String filePath, String saveFilePath){
        List<String> lines = getDataFromFile(filePath);
        int casesCount = Integer.parseInt(lines.get(0));
        List <TestCase> testCaseList = new ArrayList<>();


        for (int startCaseIndex = 1; startCaseIndex < lines.size();){
            List<Box> boxList = new ArrayList<>();
            TestCase testCase = new TestCase();
            int startBoxIndex = startCaseIndex + 2;
            int endBoxIndex;

            String[] caseInfo = lines.get(startCaseIndex).split(" ");

            int boxesCount = Integer.parseInt(caseInfo[1]);
            endBoxIndex = startBoxIndex + boxesCount;

            String[] startKeys = lines.get(startCaseIndex+1).split(" ");
            int[] keysArray = Arrays.stream(startKeys).mapToInt(Integer::parseInt).toArray();

            startCaseIndex= endBoxIndex;

            int boxIndex = startBoxIndex;
            for (int j = 0; j < boxesCount; j++) {

                String[] boxInfo = lines.get(boxIndex++).split(" ");

                boxList.add(createBox(boxInfo));
            }

            testCase.setStartKeyCount(Integer.parseInt(caseInfo[0]));
            testCase.setBoxesCount(boxesCount);
            testCase.setStartKeys(convertIntArrayToList(keysArray));
            testCase.setBoxesList(boxList);

            testCaseList.add(testCase);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i< testCaseList.size(); i++){
            stringBuilder.append("Case #").append(i+1).append(": ")
                    .append(new CaseResolver().resolveCase(testCaseList.get(i)))
                    .append(System.lineSeparator());

        }
        saveSolutionIntoFile(stringBuilder.toString(), filePath, saveFilePath);

    }
    private void saveSolutionIntoFile(String solution, String path, String savePath){
        String[] filePath = path.split(Pattern.quote(File.separator));
        String name = filePath[filePath.length-1];

        try (FileWriter writer = new FileWriter(savePath+ "\\Solution_Kovalchuk" + name);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(solution);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Box createBox(String[] boxInfo){
        List<Integer> boxKeys = new ArrayList<>();
        Box box = new Box(true);
        int boxType = Integer.parseInt(boxInfo[0]);
        int keysCount = Integer.parseInt(boxInfo[1]);
        if(keysCount > 0){
            String[] keys = Arrays.copyOfRange(boxInfo, 2, boxInfo.length);
            int[] keysArray = Arrays.stream(keys).mapToInt(Integer::parseInt).toArray();
            boxKeys = convertIntArrayToList(keysArray);
        }
        box.setBoxType(boxType);
        box.setKeysCount(keysCount);
        box.setBoxKeys(boxKeys);

        return box;
    }

    private List<Integer> convertIntArrayToList(int[] input) {

        List<Integer> list = new ArrayList<>();
        for (int i : input) {
            list.add(i);
        }
        return list;

    }


}
