import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static int nextId = 1;

    public static void main(String[] args) {
        patients.add(new Patient(nextId++, "Петров", "Іван", "Іванович", "Тернопіль", "0123456789", 123456, true, "Грип"));
        patients.add(new Patient(nextId++, "Іванова", "Марія", "Петрівна", "Тернопіль", "0123456789", 654321, false, "Грип"));
        patients.add(new Patient(nextId++, "Сидоренко", "Олег", "Вікторович", "Тернопіль", "0123456789", 111222, true, "Перелом руки"));
        patients.add(new Patient(nextId++, "Коваленко", "Юлія", "Олександрівна", "Тернопіль", "0123456789", 112233, false, "Перелом руки"));
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Добавити пацієнта");
            System.out.println("2. Переглянути пацієнтів");
            System.out.println("3. Вийти");
            System.out.println("Виберіть опцію:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character

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
        scanner.nextLine(); // consume the newline character

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
                filterByDiagnosis(scanner);
                break;
            case "2":
                filterByMedicalCardInterval(scanner);
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

    private static void filterByDiagnosis(Scanner scanner) {
        System.out.println("Введіть діагноз:");
        String diagnosis = scanner.nextLine();

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

    private static void filterByMedicalCardInterval(Scanner scanner) {
        System.out.println("Введіть початковий номер медичної карти:");
        int startCardNumber = scanner.nextInt();
        System.out.println("Введіть кінцевий номер медичної карти:");
        int endCardNumber = scanner.nextInt();

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
        }
    }
}

class Patient {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic; // по-батькові
    private String address;
    private String phone;
    private int medicalCard;
    private boolean insurance;
    private String diagnosis;

    public Patient(int id, String lastName, String firstName, String patronymic, String address, String phone, int medicalCard, boolean insurance, String diagnosis) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.phone = phone;
        this.medicalCard = medicalCard;
        this.insurance = insurance;
        this.diagnosis = diagnosis;
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
        return String.format("ID: %d, ПІБ: %s %s %s, Адреса: %s, Телефон: %s, Медична карта: %d, Страховка: %s, Діагноз: %s",
                id, lastName, firstName, patronymic, address, phone, medicalCard, (insurance ? "так" : "ні"), diagnosis);
    }
}
