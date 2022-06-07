package reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayFileReader {

    File file;

    public ArrayFileReader(File file) {
        this.file = file;
    }

    /**
     *
     * Получить массив чисел из файла
     * */

    public List<Double> getDoubleList() throws IOException {
        List<Double> dataFromFile = new ArrayList<>();

       try( FileReader fr = new FileReader(file)) {
           Scanner scan = new Scanner(fr);
           while (scan.hasNextLine()) {
               String s = scan.nextLine();
               //Разрешить и "." и "," при записи числа
               if (s.contains(",")) s = s.replace(",", ".");
               if(s.isEmpty()) continue;
               dataFromFile.add(Double.parseDouble(s));
           }
           return dataFromFile;
       }
    }
}

