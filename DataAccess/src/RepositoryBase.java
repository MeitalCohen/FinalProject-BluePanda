import java.io.*;
import java.util.Date;
import java.util.List;

public abstract class RepositoryBase<T> {

    protected String _FileLocation;
    protected String _FileStorgeName = this.getClass().getName() + ".txt";
    protected Date _lastUpdatedRepository;

    public List<T> loadData() {
        try {
            String filepath = _FileLocation + _FileStorgeName;

            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            Object obj = objectIn.readObject();

            System.out.println("The Object has been read from the file");
            objectIn.close();

            _lastUpdatedRepository = new Date();
            // return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return null;

    }

    protected boolean saveData(List<T> data) {

        FileOutputStream fileOut = null;
        ObjectOutputStream objectOut = null;

        try {

            fileOut = new FileOutputStream("c:\\temp\\address.ser");
            objectOut = new ObjectOutputStream(fileOut);

            //data.forEach(item -> objectOut.writeObject(item));
            objectOut.writeObject(data);

            System.out.println("Done");

        } catch (Exception ex) {

            ex.printStackTrace();

        } finally {

            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }

            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

}
