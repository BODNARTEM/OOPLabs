import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть текст: ");
        String input = scanner.nextLine();

        StringProcessor processing = new StringProcessor(input);

        processing.processingString();

        processing.printString();
    }
}
class StringProcessor{
    private String inputString;
    private String outputString;

    public StringProcessor(String inputString){
        this.inputString = inputString;
    }

    public String processingString(){
        String process = this.inputString;

        for(int i = 0; i < process.length() - 1; i++){
            if(process.substring(i, i + 1).endsWith("Р") || process.substring(i, i + 1).endsWith("р")){
                if(process.substring(i + 1, i + 2).endsWith("А") || process.substring(i + 1, i + 2).endsWith("а")){
                    String part1 = process.substring(0, i + 1);
                    String part2 = process.substring(i + 2, process.length());

                    process = part1 + (process.substring(i + 1, i + 2).endsWith("А")? 'О' : 'о') + part2;
                }
            }
        }
        this.outputString = process;
        return this.outputString;
    }

    public String getString(){
        return this.outputString;
    }

    public void printString(){
        System.out.println("Вхіний текст: " + inputString);
        System.out.print("Вихіний текст: " + outputString);
    }
}