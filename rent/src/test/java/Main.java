import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        String date = "2023-01-17";
        System.out.println(localDate);
        if (date.equals(localDate)){
            System.out.println("A mesma");
        }
    }
}

