import java.util.ArrayList;
import java.util.Scanner;

import static java.sql.Types.NULL;

public class Main {
    private static ArrayList<Patient> patients = new ArrayList<>();

    private static long nextId = 1;

    public static void main(String[] args) {
        patients.add(new Patient(nextId++, "Петров", "Іван", "Іванович", "вул. Центральна 1", "0123456789", 123456, false, "Грип"));
        patients.add(new Patient(nextId++, "Іванов", "Петро", "вул. Головна 2", "0987654321", "Грип", true));
        patients.add(new Patient(nextId++, "Микола", "0991234567", "Перелом"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавити пацієнта");
            System.out.println("2. Переглянути пацієнтів");
            System.out.println("3. Вийти");
            System.out.println("Виберіть опцію:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    displayPatients(scanner);
                    break;
                case 3:
                    System.out.println("До побачення!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.println("Введіть прізвище:");
        String lastName = scanner.nextLine();

        System.out.println("Введіть ім'я:");
        String firstName = scanner.nextLine();

        System.out.println("Введіть по-батькові:");
        String patronymic = scanner.nextLine();

        System.out.println("Введіть адресу:");
        String address = scanner.nextLine();

        System.out.println("Введіть номер телефону:");
        String phone = scanner.nextLine();

        System.out.println("Введіть номер медичної карти:");
        int medicalCard = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Чи є страховка (так/ні):");
        boolean insurance = scanner.nextLine().equalsIgnoreCase("так");

        System.out.println("Введіть діагноз:");
        String diagnosis = scanner.nextLine();

        Patient patient = new Patient(nextId++, lastName, firstName, patronymic, address, phone, medicalCard, insurance, diagnosis);
        patients.add(patient);

        System.out.println("Пацієнт доданий успішно!");
    }

    private static void displayPatients(Scanner scanner) {
        System.out.println("Виберіть критерій фільтрації:");
        System.out.println("1) Список пацієнтів з вказаним діагнозом");
        System.out.println("2) Список пацієнтів з номером медичної карти у заданому інтервалі");
        System.out.println("3) Кількість та список пацієнтів без медичної страховки");
        System.out.println("4) Переглянути всіх");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                filterByDiagnosis();
                break;
            case "2":
                filterByMedicalCardInterval();
                break;
            case "3":
                filterByNoInsurance();
                break;
            case "4":
                displayAllPatients();
                break;
            default:
                System.out.println("Невірний вибір. Спробуйте ще раз.");
        }
    }

    private static void filterByDiagnosis() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть діагноз:");
        String diagnosis = scanner.nextLine();

        filterByDiagnosis(diagnosis);
    }

    private static void filterByDiagnosis(String diagnosis) {

        ArrayList<Patient> filteredPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getDiagnosis().equalsIgnoreCase(diagnosis)) {
                filteredPatients.add(patient);
            }
        }

        if (filteredPatients.isEmpty()) {
            System.out.println("Пацієнтів з вказаним діагнозом не знайдено.");
        } else {
            System.out.println("Пацієнти з вказаним діагнозом:");
            for (Patient patient : filteredPatients) {
                System.out.println(patient.toString());
            }
        }
    }

    private static void filterByMedicalCardInterval() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть початковий номер медичної карти:");
        Integer startCardNumber = scanner.nextInt();
        System.out.println("Введіть кінцевий номер медичної карти:");
        Integer endCardNumber = scanner.nextInt();

        filterByMedicalCardInterval(startCardNumber, endCardNumber);
    }
    private static void filterByMedicalCardInterval(Integer startCardNumber, Integer endCardNumber) {

        ArrayList<Patient> filteredPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.getMedicalCard() >= startCardNumber && patient.getMedicalCard() <= endCardNumber) {
                filteredPatients.add(patient);
            }
        }

        if (filteredPatients.isEmpty()) {
            System.out.println("Пацієнтів у заданому інтервалі номерів медичної карти не знайдено.");
        } else {
            System.out.println("Пацієнти з номером медичної карти у заданому інтервалі:");
            for (Patient patient : filteredPatients) {
                System.out.println(patient.toString());
            }
        }
    }

    private static void filterByNoInsurance() {
        int count = 0;
        ArrayList<Patient> noInsurancePatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (!patient.hasInsurance()) {
                count++;
                noInsurancePatients.add(patient);
            }
        }

        if (count == 0) {
            System.out.println("Всі пацієнти мають медичну страховку.");
        } else {
            System.out.println("Кількість пацієнтів без медичної страховки: " + count);
            System.out.println("Список пацієнтів без медичної страховки:");
            for (Patient patient : noInsurancePatients) {
                System.out.println(patient.toString());
            }
        }
    }

    private static void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("Список пацієнтів порожній.");
        } else {
            System.out.println("Список пацієнтів:");
            for (Patient patient : patients) {
                System.out.println(patient.toString());
            }
            System.out.println("Загальна кількість пацієнтів: " + Patient.counter);
        }
    }
}

class Patient {

    static int counter = 0;
    Long id;     //private int id;
    private String lastName;
    private String firstName;
    private String patronymic; // по-батькові
    private String address;
    private String phone;
    Integer medicalCard;
    Boolean insurance;
    private String diagnosis;

    public Patient(){
        lastName = "Patient";
        id = 0L;
        medicalCard = 0;
        insurance = false;
        counter++;
    }

    public Patient(long id, String lastName, String firstName, String patronymic, String address, String phone, int medicalCard, boolean insurance, String diagnosis) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
        this.medicalCard = medicalCard;
        this.insurance = insurance;
        this.diagnosis = diagnosis;
        counter++;
    }

    public Patient(long id, String lastName, String firstName, String address, String phone, String diagnosis, boolean insurance) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = " - ";
        this.address = address;
        this.phone = phone;
        this.medicalCard = 0;
        this.insurance = insurance;
        this.diagnosis = diagnosis;
        counter++;
    }

    public Patient(long id, String firstName, String phone, String diagnosis) {
        this.id = id;
        this.lastName = " - ";
        this.firstName = firstName;
        this.patronymic = " - ";
        this.address = " - ";
        this.phone = phone;
        this.medicalCard = 0;
        this.insurance = false;
        this.diagnosis = diagnosis;
        counter++;
    }

    public Patient(Patient other){
        this.id = other.id;
        this.lastName = other.lastName;
        this.firstName = other.firstName;
        this.patronymic = other.patronymic;
        this.address = other.address;
        this.phone = other.phone;
        this.medicalCard = other.medicalCard;
        this.insurance = other.insurance;
        this.diagnosis = other.diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public int getMedicalCard() {
        return medicalCard;
    }

    public boolean hasInsurance() {
        return insurance;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, ПІБ: %s %s %s, Адреса: %s, Телефон: %s, Медична карта: %s, Страховка: %s, Діагноз: %s",
                id, lastName, firstName, patronymic, address, phone, (medicalCard != 0 ? medicalCard : "невідомо"), insurance ? "присутня" : (medicalCard != 0 ? "відсутня" : "невідомо або відсутня"),diagnosis);
    }
}