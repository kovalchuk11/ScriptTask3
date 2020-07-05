package scriptTask;

public class ResolverThread implements Runnable{
    Thread thread;
    String filePath;
    String saveFilePath;
    @Override
    public void run() {
        new CaseCreator().createCases(filePath, saveFilePath);
    }

    public ResolverThread(String filePath, String saveFilePath) {
        thread = new Thread(this);
        this.filePath = filePath;
        this.saveFilePath = saveFilePath;
        thread.start();
    }
}
