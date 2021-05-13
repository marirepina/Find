package main.java;/*Вариант 4 — find
Поиск файла(ов) с заданным в командной строке именем в указанной ключом -d
директории, по умолчанию в текущей директории. Ключ -r указывает на необходимость
поиска также во всех поддиректориях.
Command Line: find [-r] [-d directory] filename.txt
Кроме самой программы, следует написать автоматические тесты к ней.
*/

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FindLauncher {
    @Option(name = "-r")
    private Boolean depth = false;

    @Option(name = "-d")
    private File directoryName;

    @Argument(required = true, metaVar = "fileName")
    private File fileName;


    public static void main(String[] args) {
        List<String> res = new FindLauncher().launch(args);
        System.out.println(res);
    }

    public List<String> launch(String... args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar find.jar [-r] [-d directory] filename.txt");
            parser.printUsage(System.err);
            return Collections.singletonList("Incorrect data entry");
        }

        Find find = new Find(depth, directoryName, fileName);
        try {
            return find.main();
        } catch (IOException | IllegalArgumentException e) {
            return Collections.singletonList("The specified file / directory is invalid");
        }
    }
}